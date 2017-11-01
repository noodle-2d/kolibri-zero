package com.ran.kolibri.app

object Config {

    // JDBC properties
    const val JDBC_URL_PROD = "\${jdbc.url.prod}"
    const val JDBC_URL_DEV = "\${jdbc.url.dev}"
    const val JDBC_URL_LIQUIBASE_UPDATE = "\${jdbc.url.liquibase-update}"
    const val JDBC_USERNAME = "\${jdbc.username}"
    const val JDBC_PASSWORD = "\${jdbc.password}"
    const val JDBC_DRIVER = "\${jdbc.driver}"

    // Hibernate properties
    const val HIBERNATE_DIALECT = "\${hibernate.dialect}"
    const val HIBERNATE_SHOW_SQL = "\${hibernate.show_sql}"
    const val HIBERNATE_HBM2DDL_AUTO = "\${hibernate.hbm2ddl.auto}"

    // VCS properties
    const val VCS_REPOSITORIES_FILE_PATH = "\${vcs.repositories-file-path}"

}