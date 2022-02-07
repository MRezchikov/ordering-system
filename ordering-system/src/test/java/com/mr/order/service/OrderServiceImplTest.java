package com.mr.order.service;

import com.mr.order.datasource.ConnectionPool;
import com.mr.order.datasource.PostgreSQLContainerShared;
import com.mr.order.dto.OrderingItemsUpdatingCountRequest;
import com.mr.order.entity.Ordering;
import com.mr.order.entity.OrderingItems;
import com.mr.order.exeption.EntityNotFoundException;
import com.mr.order.repository.ordering.OrderingRepositoryImpl;
import com.mr.order.repository.orderingitems.OrderingItemsRepositoryImpl;
import com.mr.order.testdata.TestData;
import com.mr.order.util.transaction.TransactionRunnerJdbc;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderServiceImplTest {

    private static final PostgreSQLContainer<PostgreSQLContainerShared> POSTGRE_SQL_CONTAINER
            = PostgreSQLContainerShared.getInstance();

    static {
        POSTGRE_SQL_CONTAINER.start();
    }

    private static ConnectionPool connectionPool;
    private OrderService orderService;

    @BeforeAll
    static void beforeAll() {
        connectionPool = new ConnectionPool(
                POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                POSTGRE_SQL_CONTAINER.getUsername(),
                POSTGRE_SQL_CONTAINER.getPassword()
        );
    }

    @BeforeEach
    void setUp() {
        var transactionRunnerJdbc = new TransactionRunnerJdbc(connectionPool.createConnectionPoolAndGetDataSource());
        var orderingRepository = new OrderingRepositoryImpl();
        var orderingItemsRepository = new OrderingItemsRepositoryImpl();
        orderService = new OrderServiceImpl(transactionRunnerJdbc, orderingRepository, orderingItemsRepository);
    }

    @Test
    void shouldSaveOrderingAndReturnSameSavedOrderingWithId() {

        int orderingItemSize = 5;

        Ordering orderingToSave = TestData.createAndGetOrderingWithOrderingItemsToSave(orderingItemSize);
        Ordering expectedOrdering = TestData.createAndGetExpectedOrdering(orderingItemSize,
                4L,
                false);

        Ordering savedOrder = orderService.saveOrder(orderingToSave);

        assertThat(savedOrder)
                .usingRecursiveComparison()
                .ignoringFields("id", "orderingItems")
                .isEqualTo(expectedOrdering);

        assertThat(savedOrder.getId()).isNotNull();
        assertThat(savedOrder.getOrderingItems()).isNotEmpty();
        assertThat(savedOrder.getOrderingItems().size()).isEqualTo(orderingItemSize);
    }

    @Test
    void shouldUpdateOrderingItemsByOrderingId() {

        long orderId = 1L;
        int expectedItemCount = 1;
        var request = new OrderingItemsUpdatingCountRequest(1L, expectedItemCount);

        orderService.updateOrderingItemsCountByOrderingId(orderId, request);
        Optional<Ordering> optionalOrdering = orderService.findWithOrderingItemsById(orderId);
        Ordering savedOrderingWithOrderingItems = optionalOrdering.orElseThrow();
        List<OrderingItems> orderingItemsList = savedOrderingWithOrderingItems.getOrderingItems();

        assertThat(orderingItemsList.get(0).getItemCount()).isEqualTo(expectedItemCount);
    }

    @Test
    void shouldThrowExceptionIfOrderingItemListIsEmpty() {

        long orderId = 10L;
        var request = new OrderingItemsUpdatingCountRequest(10L, 1);

        assertThrows(EntityNotFoundException.class,
                () -> orderService.updateOrderingItemsCountByOrderingId(orderId, request));
    }

    @Test
    void shouldFindWithOrderingItemsById() {

        Long orderingId = 1L;

        Ordering expectedOrdering = TestData.createAndGetExpectedOrdering(1,
                1L,
                false);

        Ordering orderingFromDb = orderService.findWithOrderingItemsById(orderingId)
                .orElseThrow();

        assertThat(orderingFromDb).isEqualTo(expectedOrdering);
    }

    @Test
    void shouldUpdateDoneToTrue() {

        Ordering expectedOrdering = TestData.createAndGetExpectedOrdering(1,
                1L,
                true);

        orderService.updateDoneToTrue();
        Ordering ordering = orderService.findWithOrderingItemsById(1L).orElseThrow();

        assertThat(ordering.getDone()).isEqualTo(expectedOrdering.getDone());
    }
}