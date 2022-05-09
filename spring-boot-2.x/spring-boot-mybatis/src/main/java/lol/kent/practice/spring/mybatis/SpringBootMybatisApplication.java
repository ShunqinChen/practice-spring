package lol.kent.practice.spring.mybatis;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootMybatisApplication {

    @Bean(name = "dataSource")
    @ConditionalOnProperty("spring.datasource.hikari.*")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariDataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    @ConditionalOnBean(SqlSessionFactoryBean.class)
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage(
                "lol.kent.practice.spring.mybatis.**.dao,lol.kent.practice.spring.mybatis.**.repo,lol.kent.practice.spring.mybatis.**.mapper,lol.kent.practice.spring.mybatis.**.repository");
        scannerConfigurer.setSqlSessionFactoryBeanName("sessionFactory");
        return scannerConfigurer;
    }

    @Bean
    @ConditionalOnBean(HikariDataSource.class)
    public SqlSessionFactory sessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(this.dataSource());
        return sessionFactory.getObject();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisApplication.class, args);
    }

}
