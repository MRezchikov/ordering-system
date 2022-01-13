package com.mr.order.repository.factory;

import com.mr.order.repository.ordering.OrderingRepositoryImpl;
import com.mr.order.repository.ordering.OrderingRepository;
import com.mr.order.util.connection.DatabasePostgreSqlConnection;

public class OrderingRepositoryFactory {

    private static final OrderingRepositoryFactory ORDERING_REPOSITORY_FACTORY = new OrderingRepositoryFactory();

    private OrderingRepositoryFactory() {
    }

    public static OrderingRepositoryFactory getOrderingRepositoryFactory() {
        return ORDERING_REPOSITORY_FACTORY;
    }

    public OrderingRepository createOrderRepository() {
        return new OrderingRepositoryImpl(DatabasePostgreSqlConnection.getJdbcPostgreConnection());
    }
}
