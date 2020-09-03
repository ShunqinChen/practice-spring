package lol.kent.practice.spring.mongo.framework.mongo;

import static java.util.stream.Collectors.toList;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterLoadEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年09月03日 10:16
 * <p>
 * Company: Luoke101.com
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@Slf4j
@Component
public class MongoIdRefAnnotationHandler extends AbstractMongoEventListener {

    private final MongoTemplate mongoTemplate;

    public MongoIdRefAnnotationHandler(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @SneakyThrows
    @Override
    public void onBeforeSave(BeforeSaveEvent event) {
        Object source = event.getSource();
        Document document = event.getDocument();
        if (Objects.isNull(document)) {
            super.onBeforeSave(event);
            return;
        }

        Class<?> entityClass = source.getClass();

        ReflectionUtils.doWithFields(entityClass, field -> {
            ReflectionUtils.makeAccessible(field);

            if (!field.isAnnotationPresent(MongoIdRef.class)) {
                return;
            }

            MongoIdRef docAnnotation = field.getAnnotation(MongoIdRef.class);
            String newNameOfField = docAnnotation.renamed();
            String finalFieldName = StringUtils.isEmpty(newNameOfField) ? field.getName() : newNameOfField;

            if (isSingleDocument(field)) {
                ObjectId id = processSingleDocumentBeforeSave(field, source);
                document.remove(field.getName());
                document.put(finalFieldName, id);
            } else {
                List<ObjectId> ids = processListDocumentsBeforeSave(field, source);
                document.remove(field.getName());
                document.put(finalFieldName, ids);
            }
        });

        super.onBeforeSave(event);
    }

    @SneakyThrows
    @Override
    public void onAfterLoad(AfterLoadEvent event) {
        Document document = event.getDocument();
        if (Objects.isNull(document)) {
            super.onAfterLoad(event);
            return;
        }

        String targetClassName = document.getString("_class");

        ReflectionUtils.doWithFields(Class.forName(targetClassName), field -> {
            ReflectionUtils.makeAccessible(field);

            if (!field.isAnnotationPresent(MongoIdRef.class)) {
                return;
            }

            if (isSingleDocument(field)) {
                Object result = processSingleDocumentAfterLoad(document, field);
                document.put(field.getName(), result);
            } else {
                List<?> result = processListDocumentsAfterLoad(document, field);
                document.put(field.getName(), result);
            }
        });

        super.onAfterLoad(event);
    }

    private boolean isSingleDocument(Field field) {
        Class fieldType = field.getType();
        return !(fieldType == List.class || fieldType == Set.class);
    }

    private ObjectId processSingleDocumentBeforeSave(Field field, Object source) {
        Object annotatedFieldObj = ReflectionUtils.getField(field, source);
        if (Objects.isNull(annotatedFieldObj)) {
            return null;
        }

        Field idFieldInRefObj = ReflectionUtils.findField(annotatedFieldObj.getClass(), "id");

        if (Objects.isNull(idFieldInRefObj)) {
            throw new IllegalArgumentException("IdRef Objects needs Field named id");
        }

        ReflectionUtils.makeAccessible(idFieldInRefObj);

        Object idValue = ReflectionUtils.getField(idFieldInRefObj, annotatedFieldObj);
        if (Objects.isNull(idValue)) {
            return null;
        }

        if (!(idValue instanceof ObjectId) && !(idValue instanceof String)) {
            throw new IllegalArgumentException("Field with @IdRef annotation id Field must be String or ObjectId");
        }

        return idValue instanceof ObjectId ? (ObjectId) idValue : new ObjectId(idValue.toString());
    }


    private List<ObjectId> processListDocumentsBeforeSave(Field field, Object source) {
        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return Collections.emptyList();
        }

        ParameterizedType parameterizedType = (ParameterizedType) genericType;
        Class<?> actualItemClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
        Field idFieldInRefObj = ReflectionUtils.findField(actualItemClass, "id");

        if (Objects.isNull(idFieldInRefObj)) {
            throw new IllegalArgumentException("IdRef annotated List needs an item with Field named `id`");
        }

        ReflectionUtils.makeAccessible(idFieldInRefObj);

        Object annotatedFieldObj = ReflectionUtils.getField(field, source);
        if (Objects.isNull(annotatedFieldObj)) {
            return Collections.emptyList();
        }

        List<Object> annotatedFieldActualType = (List<Object>) annotatedFieldObj;
        List<ObjectId> result = annotatedFieldActualType.stream().map(o -> {
            Object idValue = ReflectionUtils.getField(idFieldInRefObj, o);
            if (Objects.isNull(idValue)) {
                return null;
            }

            if (!(idValue instanceof ObjectId) && !(idValue instanceof String)) {
                throw new IllegalArgumentException("Field with @IdRef annotation id Field must be String or ObjectId");
            }

            return idValue instanceof ObjectId ? (ObjectId) idValue : new ObjectId(idValue.toString());
        }).collect(toList());

        return result;
    }

    /**
     * Process single manual reference documents
     *
     * @param document data from mongoTemplate
     * @param field field which annotated with @Populate
     * @return referenced document from foreign collection
     */
    private Object processSingleDocumentAfterLoad(Document document, Field field) {
        MongoIdRef fieldAnnotation = field.getAnnotation(MongoIdRef.class);
        String realStoreFieldName =
                StringUtils.isEmpty(fieldAnnotation.renamed()) ? field.getName() : fieldAnnotation.renamed();
        ObjectId id = document.getObjectId(realStoreFieldName);
        if (Objects.isNull(id)) {
            return null;
        }

        if (log.isDebugEnabled()) {
            log.info("Populate注解工作中: id {}", id);
        }

        Class<?> collectionClass = field.getType();
        org.springframework.data.mongodb.core.mapping.Document docAnnotation = AnnotationUtils
                .findAnnotation(collectionClass, org.springframework.data.mongodb.core.mapping.Document.class);

        String collectionName = docAnnotation.collection();

        if (log.isDebugEnabled()) {
            log.debug("@Populate Document collection is {}:", docAnnotation.collection());
        }

        Query query = new BasicQuery(new Document("_id", id));
        Object result = mongoTemplate.findOne(query, collectionClass, collectionName);

        if (log.isDebugEnabled()) {
            log.info("Populate注解,push前查询:{}", result);
        }

        return result;
    }

    /**
     * Process list manual reference documents
     *
     * @param document data from mongoTemplate
     * @param field field which annotated with @Populate
     * @return referenced document from foreign collection
     */
    private List<?> processListDocumentsAfterLoad(Document document, Field field) {
        MongoIdRef fieldAnnotation = field.getAnnotation(MongoIdRef.class);
        String realStoreFieldName =
                StringUtils.isEmpty(fieldAnnotation.renamed()) ? field.getName() : fieldAnnotation.renamed();
        List<ObjectId> ids = document.getList(realStoreFieldName, ObjectId.class);
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }

        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return Collections.emptyList();
        }

        ParameterizedType parameterizedType = (ParameterizedType) genericType;
        Class<?> collectionClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
        org.springframework.data.mongodb.core.mapping.Document docAnnotation = AnnotationUtils
                .findAnnotation(collectionClass, org.springframework.data.mongodb.core.mapping.Document.class);

        String collectionName = docAnnotation.collection();
        Criteria filter = Criteria.where("_id").in(ids);
        List<?> result = mongoTemplate.find(Query.query(filter), collectionClass, collectionName);
        if (log.isDebugEnabled()) {
            log.info("Populate注解,push前查询:List={}", result);
        }

        return result;
    }
}
