package ro.db.vendor.config.persistence;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.Properties;

import static ro.db.vendor.config.persistence.Constants.*;

@Configuration
@ComponentScan({"ro.db.vendor.service", "ro.db.vendor.security"})
@EnableJpaRepositories("ro.db.vendor.repository")
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class PersistenceConfig {
    private final Environment environment;

    @Inject
    public PersistenceConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setUrl(environment.getProperty(PROPERTY_URL));
        ds.setUsername(environment.getProperty(PROPERTY_USERNAME));
        ds.setPassword(environment.getProperty(PROPERTY_PASSWORD));
        ds.setDriverClassName(environment.getProperty(PROPERTY_DRIVER));
        return ds;
    }


    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lfb = new LocalContainerEntityManagerFactoryBean();
        lfb.setDataSource(dataSource());
        lfb.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        lfb.setPackagesToScan("ro.db.vendor.domain");
        lfb.setJpaProperties(hibernateProps());
        return lfb;
    }

    private Properties hibernateProps() {
        Properties properties = new Properties();
        properties.setProperty(PROPERTY_DIALECT, environment.getProperty(PROPERTY_DIALECT));
        properties.setProperty(PROPERTY_SHOW_SQL, environment.getProperty(PROPERTY_SHOW_SQL));
        return properties;
    }

    @Bean
    JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }


}
