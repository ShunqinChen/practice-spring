package lol.kent.practice.core.framework.spring;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.TimeZone;

import static com.fasterxml.jackson.databind.MapperFeature.SORT_PROPERTIES_ALPHABETICALLY;
import static com.fasterxml.jackson.databind.SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2019年02月27日 20:47
 * <p>
 * Company:
 * <p>
 *
 * @author Shunqin.Chen
 * @version 1.0.0
 */
@SpringBootApplication(scanBasePackages = "lol.kent.*")
@MapperScan(basePackages = "lol.kent.*")
public abstract class AbstractApplication {

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        Jackson2ObjectMapperBuilder jsonBuilderConfig = new Jackson2ObjectMapperBuilder();
        jsonBuilderConfig.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        jsonBuilderConfig.failOnUnknownProperties(false);
        jsonBuilderConfig.serializationInclusion(JsonInclude.Include.ALWAYS);
        jsonBuilderConfig.timeZone(TimeZone.getDefault());
        jsonBuilderConfig.featuresToEnable(SORT_PROPERTIES_ALPHABETICALLY, ORDER_MAP_ENTRIES_BY_KEYS);
        return jsonBuilderConfig;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    @Lazy
    public HikariDataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    @Lazy
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(this.dataSource());
        return sessionFactory.getObject();
    }


}
