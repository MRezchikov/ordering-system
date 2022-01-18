package com.mr.order.repository.orderingitems;

import com.mr.order.entity.OrderingItems;
import com.mr.order.repository.Repository;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface OrderingItemsRepository extends Repository<OrderingItems> {

    void saveAll(Connection connection, List<OrderingItems> orderingItems);

    List<OrderingItems> findByOrderingId(Connection connection, long id);

    Optional<OrderingItems> updateItemCount(Connection connection, OrderingItems orderingItems);
}
