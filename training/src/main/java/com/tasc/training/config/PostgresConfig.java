package com.tasc.training.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.tasc.training.pg.repository",
        entityManagerFactoryRef = "PGEntityManager",
        transactionManagerRef = "PGTransactionManager"
)
public class PostgresConfig {
    
    @Value("${spring.postgres.datasource.url}")
    private String url;
    
    @Value("${spring.postgres.datasource.username}")
    private String username;
    
    @Value("${spring.postgres.datasource.password}")
    private String password;
    
    @Value("${spring.postgres.hibernate.dialect}")
    private String dialect;
    
    @Value("${spring.postgres.hibernate.show-sql}")
    private String showSql;
    
    @Value("${spring.postgres.hibernate.ddl-auto}")
    private String ddlAuto;
    
    @Bean("${PGDataSource}")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
    
    @Bean("PGEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] {"com.tasc.training.pg.model"});
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(adapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.hbm2ddl.auto", ddlAuto);
        em.setJpaPropertyMap(properties);
        return em;
    }
    
    
    @Bean("PGTransactionManager")
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return manager;
    }
    
    @Bean("PGJdbcTemplate")
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
    
    @Bean("PGNamedJdbcTemplate")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(jdbcTemplate());
    }
    
}
