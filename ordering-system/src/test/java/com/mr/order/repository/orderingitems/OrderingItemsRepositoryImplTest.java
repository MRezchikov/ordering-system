package com.mr.order.repository.orderingitems;

import com.mr.order.entity.Ordering;
import com.mr.order.entity.OrderingItems;
import com.mr.order.repository.PostgreSQLContainerShared;
import com.mr.order.repository.ordering.OrderingRepository;
import com.mr.order.repository.ordering.OrderingRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderingItemsRepositoryImplTest {

    private static final PostgreSQLContainer<PostgreSQLContainerShared> POSTGRE_SQL_CONTAINER = PostgreSQLContainerShared.getInstance();

    private OrderingRepository orderingRepository;
    private OrderingItemsRepository orderingItemsRepository;

    @BeforeEach
    public void setUp() {
        POSTGRE_SQL_CONTAINER.start();
        orderingRepository = new OrderingRepositoryImpl();
        orderingItemsRepository = new OrderingItemsRepositoryImpl();
    }

    @AfterEach
    public void after() {
        POSTGRE_SQL_CONTAINER.stop();
    }

    @Test
    void shouldSaveOrderingItems() throws SQLException {

        Ordering ordering = Ordering.builder()
                .id(1L)
                .username("Pavel")
                .done(false)
                .updatedAt(LocalDateTime.now())
                .build();

        OrderingItems orderingItem = OrderingItems.builder()
                .orderingId(1L)
                .itemName("Laptop")
                .itemCount(2)
                .itemPrice(50)
                .build();

        try(var connection = makeSingleConnection()) {
            orderingRepository.save(connection, ordering);
            OrderingItems savedOrderingItems = orderingItemsRepository.save(connection, orderingItem);
            assertThat(savedOrderingItems).isEqualTo(orderingItem);
        }

    }

    @Test
    void shouldSaveListOfOrderingId() throws SQLException {

        Ordering ordering = Ordering.builder()
                .id(1L)
                .username("Pavel")
                .done(false)
                .updatedAt(LocalDateTime.now())
                .build();

        OrderingItems orderingItem1 = OrderingItems.builder()
                .orderingId(1L)
                .itemName("Laptop")
                .itemCount(2)
                .itemPrice(50)
                .build();

        OrderingItems orderingItem2 = OrderingItems.builder()
                .orderingId(1L)
                .itemName("Laptop")
                .itemCount(2)
                .itemPrice(50)
                .build();

        List<OrderingItems> orderingItemsList = new ArrayList<>();
        orderingItemsList.add(orderingItem1);
        orderingItemsList.add(orderingItem2);

        try(var connection = makeSingleConnection()) {
            Ordering savedOrdering = orderingRepository.save(connection, ordering);
            orderingItemsRepository.saveAll(connection, orderingItemsList);
            List<OrderingItems> savedOrderingItemsList = orderingItemsRepository
                    .findByOrderingId(connection, savedOrdering.getId());

            assertThat(savedOrderingItemsList.size()).isEqualTo(orderingItemsList.size());
        }
    }

    @Test
    void findByOrderingId() {
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