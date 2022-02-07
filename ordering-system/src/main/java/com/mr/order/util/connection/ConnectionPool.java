package com.mr.order.util.connection;

import com.mr.order.util.DatabaseProperty;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Logger;

public class ConnectionPool implements DataSource {

    private static final DatabaseProperty DATABASE_PROPERTY = DatabaseProperty.getDatabaseProperty();

    private DataSource dataSource;

    @Override
    public Connection getConnection() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Connection getConnection(String username, String password) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PrintWriter getLogWriter() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLogWriter(PrintWriter out) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLoginTimeout(int seconds) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getLoginTimeout() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Logger getParentLogger() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T unwrap(Class<T> iface) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) {
        return false;
    }

    public DataSource createConnectionPoolAndGetDataSource() {
        createConnectionPool();
        return this.dataSource;
    }

    private void createConnectionPool() {

        var config = new HikariConfig();
        config.setJdbcUrl(DATABASE_PROPERTY.getUrl());
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

        config.setUsername(DATABASE_PROPERTY.getUsername());
        config.setPassword(DATABASE_PROPERTY.getPassword());

        dataSource = new HikariDataSource(config);
    }
}
