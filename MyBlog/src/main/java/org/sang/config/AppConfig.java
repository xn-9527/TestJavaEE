package org.sang.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.sang.entity.Test2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by sang on 17-3-10.
 */
@Configuration
@ComponentScan
@PropertySource(value = "classpath:jdbc.properties",encoding = "UTF-8")
public class AppConfig {

    /*
     * PropertySourcesPlaceHolderConfigurer Bean only required for @Value("{}") annotations.
     * Remove this bean if you are not using @Value annotations for injecting properties.
     * 注意PropertySourcesPlaceholderConfigurer这个bean，这个bean主要用于解决@value中使用的${…}占位符。
     * 假如你不使用${…}占位符的话，可以不使用这个bean。
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${app.name}")
    private String name;
    @Value("${db.driverClass}")
    public String driver;
    @Value("${db.url}")
    public String url;
    @Value("${db.username}")
    public String username;
    @Value("${db.password}")
    public String password;
    @Value("${db.initialSize}")
    public int initialSize;
    @Value("${db.maxActive}")
    public int maxActive;
    @Value("${db.maxIdle}")
    public int maxIdle;
    @Value("${db.minIdle}")
    public int minIdle;
    @Value("${db.maxWait}")
    public int maxWait;

    @Bean
    public Test2 tester2() {
        return new Test2(driver);
    }



    @Bean
    public BasicDataSource dataSource() {
        System.out.println("driver:" + driver);

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxIdle(maxIdle);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxWait(maxWait);
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        try {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapping/*.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSessionFactoryBean;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }



}
