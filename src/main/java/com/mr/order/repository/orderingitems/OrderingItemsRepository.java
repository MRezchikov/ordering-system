package com.mr.order.repository.orderingitems;

import com.mr.order.entity.OrderingItems;
import com.mr.order.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface OrderingItemsRepository extends Repository<OrderingItems> {

    void saveAll(List<OrderingItems> orderingItems);

    List<OrderingItems> findByOrderingId(Long id);

    Optional<OrderingItems> updateItemCount(OrderingItems orderingItems);
}
