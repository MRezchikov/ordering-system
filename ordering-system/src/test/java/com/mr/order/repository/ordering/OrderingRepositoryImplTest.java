package com.mr.order.repository.ordering;

import com.mr.order.entity.Ordering;
import com.mr.order.entity.OrderingItems;
import com.mr.order.repository.PostgreSQLContainerShared;
import com.mr.order.util.connection.ConnectionPool;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class OrderingRepositoryImplTest {

    private static final PostgreSQLContainer<PostgreSQLContainerShared> POSTGRE_SQL_CONTAINER = PostgreSQLContainerShared.getInstance();

    private OrderingRepository orderingRepository;

    @BeforeEach
    public void setUp() {
        POSTGRE_SQL_CONTAINER.start();
        new OrderingRepositoryImpl();
    }

    @AfterEach
    public void after() {
        POSTGRE_SQL_CONTAINER.stop();
    }

    @Test
    void shouldReturnOrderingWithIdIfSaveOrderingSuccessful() throws SQLException {

        Ordering orderingToSave = Ordering.builder()
                .username("Aleksei")
                .done(true)
                .updatedAt(LocalDateTime.now())
                .build();

        Ordering expectedOrdering = Ordering.builder()
                .id(1L)
                .username("Aleksei")
                .done(true)
                .updatedAt(LocalDateTime.now())
                .build();

        orderingRepository = new OrderingRepositoryImpl();
        try (var connection = makeSingleConnection()) {
            Ordering savedOrdering = orderingRepository.save(connection, orderingToSave);
            assertThat(savedOrdering).isEqualTo(expectedOrdering);
        }
    }

    @Test
    void shouldReturnOrderingById() throws SQLException {

        Long id = 1L;

        Ordering ordering = Ordering.builder()
                .id(1L)
                .username("Pavel")
                .done(false)
                .updatedAt(LocalDateTime.now())
                .build();

        orderingRepository = new OrderingRepositoryImpl();

        try(var connection = makeSingleConnection()) {
            Ordering savedOrdering = orderingRepository.save(connection, ordering);
            Optional<Ordering> optionalOrdering = orderingRepository.findById(connection, id);
            Ordering expectedOrdering = optionalOrdering.orElseThrow();
            assertThat(savedOrdering).isEqualTo(expectedOrdering);
        }
    }

    @Test
    void shouldReturnTrueIdUpdateSuccessful() throws SQLException {

    }

    private Connection makeSingleConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                POSTGRE_SQL_CONTAINER.getUsername(),
                POSTGRE_SQL_CONTAINER.getPassword());
        connection.setAutoCommit(false);
        return connection;
    }
}