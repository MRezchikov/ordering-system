package com.mr.order.service;

import com.mr.order.dto.OrderingItemsUpdatingCountRequest;
import com.mr.order.entity.Ordering;

import java.sql.Connection;
import java.util.Optional;

public interface OrderService {

    Ordering saveOrder(Connection connection, Ordering ordering);

    void updateOrderingItemsCountByOrderingId(Connection connection,
                                              long orderId,
                                              OrderingItemsUpdatingCountRequest countRequests);

    Optional<Ordering> findWithOrderingItemsById(Connection connection, long id);

    void updateDoneToTrue(Connection connection);
}
