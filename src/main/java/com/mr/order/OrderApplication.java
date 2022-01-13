package com.mr.order;

import com.mr.order.entity.Ordering;
import com.mr.order.entity.OrderingItems;
import com.mr.order.repository.factory.OrderingItemsRepositoryFactory;
import com.mr.order.repository.factory.OrderingRepositoryFactory;
import com.mr.order.repository.orderingitems.OrderingItemsRepository;
import com.mr.order.repository.ordering.OrderingRepository;
import com.mr.order.service.OrderService;
import com.mr.order.service.OrderServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderApplication {

    public static void main(String[] args) {
        OrderingRepository orderRepository = OrderingRepositoryFactory
                .getOrderingRepositoryFactory()
                .createOrderRepository();
        OrderingItemsRepository orderingItemsRepository = OrderingItemsRepositoryFactory
                .getOrderingItemsRepositoryFactory()
                .createOrderingItemsRepository();

        OrderService orderService = new OrderServiceImpl(orderRepository, orderingItemsRepository);

        /*OrderingItems orderingItems1 = OrderingItems.builder()
                .itemName("Smth1")
                .itemCount(2)
                .itemPrice(599.99)
                .build();

        OrderingItems orderingItems2 = OrderingItems.builder()
                .itemName("Smth2")
                .itemCount(2)
                .itemPrice(233.99)
                .build();

        List<OrderingItems> orderingItemsList = new ArrayList<>();
        orderingItemsList.add(orderingItems1);
        orderingItemsList.add(orderingItems2);

        Ordering test2 = Ordering.builder()
                .username("test2")
                .done(true)
                .updatedAt(LocalDateTime.now())
                .orderingItems(orderingItemsList)
                .build();

        orderService.createOrder(test2);*/

/*        OrderingItemsUpdatingCountRequest countRequest = new OrderingItemsUpdatingCountRequest(1L, 5);

        orderService.updateOrderingItemsCountByOrderingId(1L, countRequest);*/

        //orderService.findWithOrderingItemsById(1L).ifPresent(System.out::println);

        orderService.updateDoneToTrue();

    }
}
