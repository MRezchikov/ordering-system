package com.mr.order.util.connection;

import com.mr.order.util.DatabaseProperty;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class ConnectionPool {

    private DataSource dataSource;

    public DataSource getDataSource() {
        createConnectionPool();
        return this.dataSource;
    }

    public void createConnectionPool() {

        DatabaseProperty dbProperty = DatabaseProperty.getDatabaseProperty();

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbProperty.getUrl());
        config.setConnectionTimeout(3000); //ms
        config.setIdleTimeout(60000); //ms
        config.setMaxLifetime(600000);//ms
        config.setAutoCommit(false);
        config.setMinimumIdle(5);
        config.setMaximumPoolSize(10);
        config.setPoolName("OrderSystemPool");
        config.setRegisterMbeans(true);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        config.setUsername(dbProperty.getUsername());
        config.setPassword(dbProperty.getPassword());

        dataSource = new HikariDataSource(config);
    }
}
