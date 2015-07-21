package com.skplanet.tutorial.testautomation.todo.domain;

import javax.sql.DataSource;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan(basePackages = "com.skplanet.tutorial.testautomation.todo", 
               excludeFilters = @ComponentScan.Filter(
                       type = FilterType.ANNOTATION,
                       value=org.springframework.stereotype.Controller.class))
@EnableTransactionManagement
public class TodoServiceTestConfig
{

    @Bean public DataSource dataSource() 
    {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql").build();
    }
    
    @Bean public PlatformTransactionManager txManager() 
    {
        return new DataSourceTransactionManager(dataSource());
    }
}