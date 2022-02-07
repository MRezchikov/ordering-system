package com.mr.order.util;

import com.mr.order.exeption.PropertyFileReadingException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class DatabaseProperty {

    private static final String DATABASE_PROPERTIES = "database.properties";
    private static final String DB_URL = "db.url";
    private static final String DB_USERNAME = "db.username";
    private static final String DB_PASSWORD = "db.password";

    private String url;
    private String username;
    private String password;

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static DatabaseProperty getDatabaseProperty() {
        return new DatabaseProperty().createDatabaseProperty();
    }

    private DatabaseProperty createDatabaseProperty() {

        try (InputStream resource = getClass().getClassLoader().getResourceAsStream(DATABASE_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(resource);
            this.url = properties.getProperty(DB_URL);
            this.username = properties.getProperty(DB_USERNAME);
            this.password = properties.getProperty(DB_PASSWORD);
        } catch (IOException e) {
            throw new PropertyFileReadingException("Error occurred while reading database property file", e);
        }

        return DatabaseProperty.this;
    }
}
