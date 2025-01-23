package pe.gob.pj.conexiones.infraestructure.db.seguridad;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pe.gob.pj.conexiones.infraestructure.db.seguridad.repositories",
        entityManagerFactoryRef = "seguridadEntityManagerFactory",
        transactionManagerRef = "txManagerSeguridad")
public class SeguridadConfig {

    // CONEXION CON LA BASE DE DATOS SEGURIDAD
    @Bean(name = "cxSeguridadDS")
    DataSource seguridadDataSource() throws NamingException {
        return (DataSource) new InitialContext()
                .lookup("java:jboss/datasources/serviciodepositosAPISeguridad");
    }

    @Bean(name = "seguridadEntityManagerFactory")
    @Primary
    LocalContainerEntityManagerFactoryBean seguridadEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("cxSeguridadDS") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("pe.gob.pj.conexiones.infraestructure.db.seguridad.entities")
                .persistenceUnit("seguridad").properties(getHibernateProperties()).build();
    }

    //Para usar transacciones
    @Bean(name = "txManagerSeguridad")
    PlatformTransactionManager seguridadTransactionManager(
            @Qualifier("seguridadEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    //Para usar hibernate con SessionFactory
    @Bean(name = "sessionSeguridad")
    SessionFactory sessionFactorySeguridad(
            @Qualifier("seguridadEntityManagerFactory") EntityManagerFactory emf) {
        if (emf.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("seguridad factory is not a hibernate factory");
        }
        return emf.unwrap(SessionFactory.class);
    }

    //  Para usar querydsl
    @Bean(name = "seguridadQDSL")
    JPAQueryFactory jpaQueryFactorySeguridad(
            @Qualifier("seguridadEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return new JPAQueryFactory(entityManager);
    }

    private Map<String, Object> getHibernateProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.connection.release_mode", "AFTER_TRANSACTION");
        properties.put("hibernate.type", "true");
        return properties;
    }
}
