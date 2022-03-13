package com.mr.orderingsystemapp.orderingitems.service;

import com.mr.orderingsystemapp.orderingitems.dto.OrderingItemCountDto;
import com.mr.orderingsystemapp.orderingitems.entity.OrderingItems;

import java.util.List;

public interface OrderingItemsService {

    OrderingItems saveOrderingItem(OrderingItems orderingItem);

    OrderingItems changeItemCount(OrderingItemCountDto request);

    List<OrderingItems> findOrderingItemsByOrderingId(Long orderingId);
}
