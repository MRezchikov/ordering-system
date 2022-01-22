package com.mr.order.testdata;

import com.mr.order.entity.Ordering;
import com.mr.order.entity.OrderingItems;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TestData {

    public static Ordering createAndGetOrderingWithOrderingItemsToSave(int orderingItemListSize) {

        List<OrderingItems> orderingItemsList = creatAndGetOrderingItemsList(orderingItemListSize);

        return Ordering.builder()
                .username("Vadim")
                .done(false)
                .updatedAt(LocalDateTime.now())
                .orderingItems(orderingItemsList)
                .build();
    }

    public static Ordering createAndGetExpectedOrdering(int orderingItemsListSize,
                                                        Long orderingId,
                                                        boolean done) {
        return Ordering.builder()
                .id(orderingId)
                .username("Vadim")
                .done(done)
                .updatedAt(LocalDateTime.now())
                .orderingItems(creatAndGetOrderingItemsList(orderingItemsListSize))
                .build();
    }

    public static List<OrderingItems> creatAndGetOrderingItemsList(int orderingItemListSize) {

        List<OrderingItems> orderingItemsList = new ArrayList<>();

        IntStream.range(0, orderingItemListSize)
                .forEach(i -> {
                    if (i % 2 == 0) {
                        orderingItemsList.add(
                                OrderingItems.builder()
                                        .itemName("PC")
                                        .itemCount(2)
                                        .itemPrice(3999.99)
                                        .build()
                        );
                    } else {
                        orderingItemsList.add(
                                OrderingItems.builder()
                                        .itemName("Laptop")
                                        .itemCount(3)
                                        .itemPrice(2999.99)
                                        .build()
                        );
                    }
                });

        return orderingItemsList;
    }
}
