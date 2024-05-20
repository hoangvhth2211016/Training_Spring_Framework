package com.tasc.training.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.tasc.training.repository",
        entityManagerFactoryRef = "H2EntityManager",
        transactionManagerRef = "H2TransactionManager"
)
public class H2Config {
    @Value("${spring.h2.datasource.url}")
    private String url;
    
    @Value("${spring.h2.datasource.username}")
    private String username;
    
    @Value("${spring.h2.datasource.password}")
    private String password;
    
    @Value("${spring.h2.datasource.driverClassName}")
    private String driver;
    
    @Value("${spring.h2.jpa.database-platform}")
    private String dialect;
    
    @Value("${spring.h2.jpa.properties.hibernate.format-sql}")
    private String showSql;
    
    @Value("${spring.h2.jpa.properties.hibernate.show-sql}")
    private String formatSql;
   
    
    @Bean("${H2DataSource}")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
    
    @Bean("H2EntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] {"com.tasc.training.model"});
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(adapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.format_sql", formatSql);
        em.setJpaPropertyMap(properties);
        return em;
    }
    
    
    @Bean("H2TransactionManager")
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return manager;
    }
}
