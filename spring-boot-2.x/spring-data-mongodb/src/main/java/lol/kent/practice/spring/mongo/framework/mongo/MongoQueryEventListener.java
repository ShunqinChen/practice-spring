package lol.kent.practice.spring.mongo.framework.mongo;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterLoadEvent;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

/**
 * <pre>
 *    类描述:
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2020年09月02日 10:58
 * <p>
 * Company: AMPM Fit
 * <p>
 *
 * @author Shunqin.ChenX
 * @version 1.0.0
 */
@Slf4j
@Component
public class MongoQueryEventListener extends AbstractMongoEventListener {

    private MongoTemplate mongoTemplate;

    public MongoQueryEventListener(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    @SneakyThrows
    public void onAfterLoad(AfterLoadEvent event) {
        Document document = event.getDocument();
        if (Objects.isNull(document)) {
            super.onAfterLoad(event);
            return;
        }

        String targetClassName = document.getString("_class");

        ReflectionUtils.doWithFields(Class.forName(targetClassName), field -> {
            if (log.isDebugEnabled()) {
                log.info("Populate注解显示跟踪:{}={}", field.getName(), field.isAnnotationPresent(Populate.class));
            }

            ReflectionUtils.makeAccessible(field);

            if (!field.isAnnotationPresent(Populate.class)) {
                return;
            }

            Populate annotation = field.getAnnotation(Populate.class);
            Class collectionClass = annotation.ref();
            String pushFiled = annotation.as();
            if (log.isDebugEnabled()) {
                log.info("Populate注解工作中: ref={},as={}", collectionClass, pushFiled);
            }

            if (isSingleDocument(field)) {
                Object result = processSingleDocument(document, field, collectionClass);
                document.put(pushFiled, result);
            } else {
                List<?> result = processListDocuments(document, field, collectionClass);
                document.put(pushFiled, result);
            }
        });

        super.onAfterLoad(event);
    }

    /**
     * Parse the filed which wait for processed is reference by one id or multiple ids
     *
     * @param field filed annotated with populate
     * @return Boolean is
     */
    private boolean isSingleDocument(Field field) {
        Class fieldType = field.getType();
        return fieldType == ObjectId.class || fieldType == String.class;
    }

    /**
     * Process single manual reference documents
     *
     * @param document data from mongoTemplate
     * @param field field which annotated with @Populate
     * @param collectionClass collection class type for which being reference
     * @return referenced document from foreign collection
     */
    private Object processSingleDocument(Document document, Field field, Class collectionClass) {
        ObjectId id = document.getObjectId(field.getName());
        if (Objects.isNull(id)) {
            return null;
        }

        if (log.isDebugEnabled()) {
            log.info("Populate注解工作中: id {}", id);
        }

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
     * @param collectionClass collection class type for which being reference
     * @return referenced document from foreign collection
     */
    private List<?> processListDocuments(Document document, Field field, Class collectionClass) {
        List<ObjectId> ids = document.getList(field.getName(), ObjectId.class);
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }

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
