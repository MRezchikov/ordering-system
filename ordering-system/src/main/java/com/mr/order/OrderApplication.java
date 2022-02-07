package com.mr.order;

import com.mr.order.entity.Ordering;
import com.mr.order.entity.OrderingItems;
import com.mr.order.repository.ordering.OrderingRepositoryImpl;
import com.mr.order.repository.orderingitems.OrderingItemsRepositoryImpl;
import com.mr.order.service.OrderServiceImpl;
import com.mr.order.util.connection.ConnectionPool;
import com.mr.order.util.transaction.TransactionRunnerJdbc;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger("OrderApplication");

    public static void main(String[] args) {

        LOGGER.info("Database schema migration");

        var connectionPool = new ConnectionPool();
        var dataSource = connectionPool.createConnectionPoolAndGetDataSource();
        flyWayMigrate(dataSource);
        var transactionRunner = new TransactionRunnerJdbc(dataSource);

        var orderingRepository = new OrderingRepositoryImpl();
        var orderingItemsRepository = new OrderingItemsRepositoryImpl();

        var orderService = new OrderServiceImpl(transactionRunner, orderingRepository, orderingItemsRepository);

        orderService.findWithOrderingItemsById(1L)
                .ifPresent(System.out::println);

        OrderingItems orderingItems1 = OrderingItems.builder()
                .itemName("PC")
                .itemCount(2)
                .itemPrice(1999.99)
                .build();

        OrderingItems orderingItems2 = OrderingItems.builder()
                .itemName("Laptop")
                .itemCount(2)
                .itemPrice(2999.99)
                .build();

        List<OrderingItems> orderingItemsList = new ArrayList<>();
        orderingItemsList.add(orderingItems1);
        orderingItemsList.add(orderingItems2);

        Ordering test2 = Ordering.builder()
                .username("Aleksei")
                .done(true)
                .updatedAt(LocalDateTime.now())
                .orderingItems(orderingItemsList)
                .build();

        orderService.saveOrder(test2);

        orderService.updateDoneToTrue();

/*        OrderingItemsUpdatingCountRequest orderingItemsUpdatingCountRequest =
                new OrderingItemsUpdatingCountRequest(8, 20);

        orderService.updateOrderingItemsCountByOrderingId(-5L, orderingItemsUpdatingCountRequest);*/

    }

    public static void flyWayMigrate(DataSource dataSource) {

        LOGGER.info("Db migration starting");
        Flyway.configure()
                .dataSource(dataSource)
                .load()
                .migrate();
        LOGGER.info("Db migration finished");
    }
}
