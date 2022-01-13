package com.mr.order.repository.factory;

import com.mr.order.repository.orderingitems.OrderingItemsRepositoryImpl;
import com.mr.order.repository.orderingitems.OrderingItemsRepository;
import com.mr.order.util.connection.DatabasePostgreSqlConnection;

public class OrderingItemsRepositoryFactory {

    private static final OrderingItemsRepositoryFactory ORDERING_ITEMS_REPOSITORY_FACTORY
            = new OrderingItemsRepositoryFactory();

    private OrderingItemsRepositoryFactory() {
    }

    public static OrderingItemsRepositoryFactory getOrderingItemsRepositoryFactory() {
        return ORDERING_ITEMS_REPOSITORY_FACTORY;
    }

    public OrderingItemsRepository createOrderingItemsRepository() {
        return new OrderingItemsRepositoryImpl(DatabasePostgreSqlConnection.getJdbcPostgreConnection());
    }
}
