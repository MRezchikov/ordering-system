package com.mr.order.util.connection;

import java.sql.Connection;
import java.util.ResourceBundle;

public class DatabasePostgreSqlConnection implements DatabaseConnection {

    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static final DatabasePostgreSqlConnection JDBC_POSTGRE_CONNECTION = new DatabasePostgreSqlConnection();

    static {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
        URL = resourceBundle.getString("db.url");
        USERNAME = resourceBundle.getString("db.username");
        PASSWORD = resourceBundle.getString("db.password");
    }

    private DatabasePostgreSqlConnection() {
    }

    public static DatabasePostgreSqlConnection getJdbcPostgreConnection() {
        return JDBC_POSTGRE_CONNECTION;
    }

    @Override
    public Connection getConnection() {
        return ConnectionFactory.getConnection(URL, USERNAME, PASSWORD);
    }
}
