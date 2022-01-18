package com.mr.order;

import com.mr.order.service.OrderService;
import com.mr.order.service.OrderServiceImpl;
import com.mr.order.util.DatabaseProperty;
import com.mr.order.util.connection.ConnectionPool;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;

public class OrderApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderApplication.class);

    public static void main(String[] args) {

        LOGGER.info("Database schema migration");
        flyWayMigrate();

        ConnectionPool connectionPool = new ConnectionPool();
        DataSource dataSource = connectionPool.getDataSource();

        try (Connection connection = dataSource.getConnection()) {

            OrderService orderService = new OrderServiceImpl();

/*            orderService.findWithOrderingItemsById(connection, 1L)
                    .ifPresent(System.out::println);*/

            /*OrderingItems orderingItems1 = OrderingItems.builder()
                    .itemName("Smth1")
                    .itemCount(2)
                    .itemPrice(599.99)
                    .build();

            OrderingItems orderingItems2 = OrderingItems.builder()
                    .itemName("Smth2")
                    .itemCount(2)
                    .itemPrice(233.99)
                    .build();

            List<OrderingItems> orderingItemsList = new ArrayList<>();
            orderingItemsList.add(orderingItems1);
            orderingItemsList.add(orderingItems2);

            Ordering test2 = Ordering.builder()
                    .username("test2")
                    .done(true)
                    .updatedAt(LocalDateTime.now())
                    .orderingItems(orderingItemsList)
                    .build();

            orderService.saveOrder(connection, test2);*/

        } catch (Exception e) {
            LOGGER.error("Unknown exception while calling service methods");
            throw new RuntimeException("Unexpected exception", e);
        }
    }

    private static void flyWayMigrate() {

        DatabaseProperty dbProperty = DatabaseProperty.getDatabaseProperty();
        LOGGER.info("Db migration starting");
        Flyway.configure()
                .dataSource(
                        dbProperty.getUrl(),
                        dbProperty.getUsername(),
                        dbProperty.getPassword()
                )
                .load()
                .migrate();
        LOGGER.info("Db migration finished");
    }
}
