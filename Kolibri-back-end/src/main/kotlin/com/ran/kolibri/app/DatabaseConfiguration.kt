package com.ran.kolibri.app

import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.hibernate5.HibernateTransactionManager
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import java.util.*
import javax.sql.DataSource

@Configuration
class DatabaseConfiguration {

    @Bean
    fun dataSource(@Value("\${jdbc.url}") url: String,
                   @Value("\${jdbc.username}") username: String,
                   @Value("\${jdbc.password}") password: String,
                   @Value("\${jdbc.driver}") driverClassName: String): DataSource {
        val dataSource = DriverManagerDataSource(url, username, password)
        dataSource.setDriverClassName(driverClassName)
        return dataSource
    }

    @Bean
    @Autowired
    fun sessionFactory(@Value("\${hibernate.dialect}") dialect: String,
                       @Value("\${hibernate.show_sql}") showSql: String,
                       @Value("\${hibernate.hbm2ddl.auto}") hbm2ddl: String,
                       dataSource: DataSource): LocalSessionFactoryBean {
        val hibernateProperties = Properties()
        hibernateProperties["hibernate.dialect"] = dialect
        hibernateProperties["hibernate.show_sql"] = showSql
        hibernateProperties["hibernate.hbm2ddl.auto"] = hbm2ddl

        val sessionFactory = LocalSessionFactoryBean()
        sessionFactory.setDataSource(dataSource)
        sessionFactory.setPackagesToScan("com.ran.kolibri.entity")
        sessionFactory.hibernateProperties = hibernateProperties
        return sessionFactory
    }

    @Bean
    @Autowired
    fun transactionManager(sessionFactory: SessionFactory): HibernateTransactionManager {
        val txManager = HibernateTransactionManager()
        txManager.sessionFactory = sessionFactory
        return txManager
    }

    @Bean
    fun exceptionTranslation(): PersistenceExceptionTranslationPostProcessor {
        return PersistenceExceptionTranslationPostProcessor()
    }

}