package com.mr.order.service;

import com.mr.order.dto.OrderingItemsUpdatingCountRequest;
import com.mr.order.entity.Ordering;
import com.mr.order.entity.OrderingItems;
import com.mr.order.exeption.EntityNotFoundException;
import com.mr.order.exeption.UnsupportedOrderIdException;
import com.mr.order.repository.orderingitems.OrderingItemsRepository;
import com.mr.order.repository.ordering.OrderingRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private OrderingRepository orderingRepository;
    private OrderingItemsRepository orderingItemsRepository;

    public OrderServiceImpl(OrderingRepository orderingRepository) {
        this.orderingRepository = orderingRepository;
    }

    public OrderServiceImpl(OrderingItemsRepository orderingItemsRepository) {
        this.orderingItemsRepository = orderingItemsRepository;
    }

    public OrderServiceImpl(OrderingRepository orderingRepository, OrderingItemsRepository orderingItemsRepository) {
        this.orderingRepository = orderingRepository;
        this.orderingItemsRepository = orderingItemsRepository;
    }

    @Override
    public Ordering createOrder(Ordering ordering) {
        Ordering ordering1 = orderingRepository.create(ordering);
        System.out.println(ordering1.getId());
        List<OrderingItems> orderingItems = ordering.getOrderingItems();

        if (!orderingItems.isEmpty() || orderingItems != null) {

            for (OrderingItems orderingItem : orderingItems) {
                orderingItem.setOrderingId(ordering1.getId());
            }

            orderingItemsRepository.saveAll(orderingItems);
        }
        return ordering1;
    }

    @Override
    public void updateOrderingItemsCountByOrderingId(Long orderId,
                                                     OrderingItemsUpdatingCountRequest countRequests) {

        if (Objects.isNull(orderId) || orderId < 1) {
            throw new UnsupportedOrderIdException("Invalid order number entered " + orderId);
        }

        List<OrderingItems> orderingItemsList = orderingItemsRepository.findByOrderingId(orderId);

        if (orderingItemsList.isEmpty() || Objects.isNull(orderingItemsList)) {
            throw new EntityNotFoundException("Entity was not foun by orderId " + orderId);
        }

        for (OrderingItems orderingItems : orderingItemsList) {
            Long id = orderingItems.getId();
            if (id.equals(countRequests.getOrderingItemsId())) {
                OrderingItems orderingItemsToUpdating = OrderingItems.builder()
                        .id(countRequests.getOrderingItemsId())
                        .itemCount(countRequests.getCountItem())
                        .build();
                orderingItemsRepository.updateItemCount(orderingItemsToUpdating);
            }
        }
    }

    @Override
    public Optional<Ordering> findWithOrderingItemsById(Long id) {
        return orderingRepository.findWithOrderingItemsById(id);
    }

    @Override
    public void updateDoneToTrue() {
        orderingRepository.updateDoneToTrue(true);
    }
}
