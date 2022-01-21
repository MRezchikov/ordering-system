package com.mr.order.repository;

import org.flywaydb.core.Flyway;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSQLContainerShared extends PostgreSQLContainer<PostgreSQLContainerShared> {

    private static final String IMAGE_VERSION = "postgres:12";
    private static PostgreSQLContainerShared container;

    private PostgreSQLContainerShared() {
        super(IMAGE_VERSION);
    }

    public static PostgreSQLContainerShared getInstance() {
        if (container == null) {
            container = new PostgreSQLContainerShared();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        flyWayMigrate(container.getJdbcUrl(), container.getUsername(), container.getPassword());
    }

    private void flyWayMigrate(String url, String username, String password) {
        Flyway.configure()
                .dataSource(url, username, password)
                .load()
                .migrate();
    }

    @Override
    public void stop() {
        super.stop();
    }
}
