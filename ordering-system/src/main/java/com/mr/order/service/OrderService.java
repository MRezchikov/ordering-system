package com.mr.order.service;

import com.mr.order.dto.OrderingItemsUpdatingCountRequest;
import com.mr.order.entity.Ordering;

import java.util.Optional;

public interface OrderService {

    Ordering saveOrder(Ordering ordering);

    void updateOrderingItemsCountByOrderingId(Long orderId,
                                              OrderingItemsUpdatingCountRequest countRequests);

    Optional<Ordering> findWithOrderingItemsById(Long id);

    void updateDoneToTrue();
}
