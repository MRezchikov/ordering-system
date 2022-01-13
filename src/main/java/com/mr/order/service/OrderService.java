package com.mr.order.service;

import com.mr.order.dto.OrderingItemsUpdatingCountRequest;
import com.mr.order.entity.Ordering;
import com.mr.order.entity.OrderingItems;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderService {

    Ordering createOrder(Ordering ordering);

    void updateOrderingItemsCountByOrderingId(Long orderId,
                                              OrderingItemsUpdatingCountRequest countRequests);

    Optional<Ordering> findWithOrderingItemsById(Long id);

    void updateDoneToTrue();
}
