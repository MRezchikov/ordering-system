package com.mr.orderingsystemapp.orderingitems.repository;

import com.mr.orderingsystemapp.orderingitems.entity.OrderingItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderingItemsRepository extends JpaRepository<OrderingItems, Long> {

    OrderingItems findOrderingItemsByIdAndAndOrderingId(Long id, Long orderingId);

    List<OrderingItems> findOrderingItemsByOrderingId(Long orderingId);
}
