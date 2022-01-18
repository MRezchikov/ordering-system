package com.mr.order.util.connection;

import com.mr.order.util.DatabaseProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private DatabaseConnection() {
    }

    public static Connection getDbConnection() throws SQLException {

        DatabaseProperty dbProperty = DatabaseProperty.getDatabaseProperty();

        return DriverManager.getConnection(dbProperty.getUrl(),
                dbProperty.getUsername(),
                dbProperty.getPassword());
    }
}
