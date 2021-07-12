package org.application.config;

import org.application.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;

@EnableJpaRepositories(basePackages = {"org.application"})
@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    private final ApplicationContext context;

    @Autowired
    public HibernateConfig(ApplicationContext context) {
        this.context = context;
    }

//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//
//        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
//
//        factoryBean.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
//        factoryBean.setAnnotatedClasses(UserData.class);
//
//        return factoryBean;
//    }
//
//    @Bean
//    public HibernateTransactionManager transactionManager() {
//
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//
//        transactionManager.setSessionFactory(sessionFactory().getObject());
//
//        return transactionManager;
//    }

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("customerManager");

        return factoryBean;
    }
}