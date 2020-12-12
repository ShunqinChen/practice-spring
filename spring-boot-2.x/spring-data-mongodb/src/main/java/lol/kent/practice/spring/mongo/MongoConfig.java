package lol.kent.practice.spring.mongo;

import lol.kent.practice.spring.mongo.audit.DateTimeAuditImpl;
import lol.kent.practice.spring.mongo.audit.UserAuditImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * <pre>
 * MongoDB设置,一般为覆盖原设置,原设置查看AbstractMongoClientConfiguration
 * 可继承修改,继承时要求制定MongoDatabaseFactory,因此此处没有继承
 * 多数据源请另行优化
 * </pre>
 *
 * @author Shunqin.Chen
 */
@Configuration
@EnableMongoAuditing(auditorAwareRef = "auditorAwareProvider", dateTimeProviderRef = "dateTimeAwareProvider")
public class MongoConfig {


    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory databaseFactory,
            MongoCustomConversions customConversions, MongoMappingContext mappingContext) {

        DbRefResolver dbRefResolver = new DefaultDbRefResolver(databaseFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
        converter.setCustomConversions(customConversions);
        converter.setCodecRegistryProvider(databaseFactory);
        // Close insert _class field
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        // Open Annotation Index
        mappingContext.setAutoIndexCreation(true);

        return converter;
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Bean
    public AuditorAware<String> auditorAwareProvider() {
        return new UserAuditImpl();
    }

    /**
     * 这个只是测试自定义CreatedDate可以不用改
     */
    @Bean
    public DateTimeProvider dateTimeAwareProvider() {
        return new DateTimeAuditImpl();
    }
}

