package com.mr.order.service;

import com.mr.order.dto.OrderingItemsUpdatingCountRequest;
import com.mr.order.entity.Ordering;
import com.mr.order.entity.OrderingItems;
import com.mr.order.exeption.EntityNotFoundException;
import com.mr.order.repository.ordering.OrderingRepository;
import com.mr.order.repository.ordering.OrderingRepositoryImpl;
import com.mr.order.repository.orderingitems.OrderingItemsRepository;
import com.mr.order.repository.orderingitems.OrderingItemsRepositoryImpl;
import com.mr.order.util.transaction.TransactionRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final TransactionRunner transactionRunner;
    private final OrderingRepository orderingRepository = new OrderingRepositoryImpl();
    private final OrderingItemsRepository orderingItemsRepository = new OrderingItemsRepositoryImpl();

    public OrderServiceImpl(TransactionRunner transactionRunner) {
        this.transactionRunner = transactionRunner;
    }

    @Override
    public Ordering saveOrder(Ordering ordering) {

        LOGGER.info("Saving the ordering entity");

        return transactionRunner.doInTransaction(connection -> {
            Ordering savedOrdering = orderingRepository.save(connection, ordering);
            List<OrderingItems> orderingItems = ordering.getOrderingItems();

            if (!orderingItems.isEmpty()) {
                for (OrderingItems orderingItem : orderingItems) {
                    orderingItem.setOrderingId(savedOrdering.getId());
                }

                orderingItemsRepository.saveAll(connection, orderingItems);
            }

            return savedOrdering;
        });
    }

    @Override
    public void updateOrderingItemsCountByOrderingId(Long orderId,
                                                     OrderingItemsUpdatingCountRequest countRequests) {

        LOGGER.info("Updating the ordering items entity by order id: {}", orderId);

        transactionRunner.doInTransaction(connection -> {
            List<OrderingItems> orderingItemsList = orderingItemsRepository.findByOrderingId(connection, orderId);

            if (orderingItemsList.isEmpty()) {
                throw new EntityNotFoundException("Entity was not found by orderId " + orderId);
            }

            for (OrderingItems orderingItems : orderingItemsList) {
                Long id = orderingItems.getId();
                if (id.equals(countRequests.getOrderingItemsId())) {
                    OrderingItems orderingItemsToUpdating = OrderingItems.builder()
                            .id(countRequests.getOrderingItemsId())
                            .itemCount(countRequests.getItemCount())
                            .build();
                    orderingItemsRepository.updateItemCount(connection, orderingItemsToUpdating);
                }
            }

            return orderingItemsList;
        });
    }

    @Override
    public Optional<Ordering> findWithOrderingItemsById(Long orderingId) {

        LOGGER.info("Extract the ordering with ordering items by ordering id: {}", orderingId);

        return transactionRunner.doInTransaction(connection -> {
            Optional<Ordering> optionalOrdering = orderingRepository.findById(connection, orderingId);
            List<OrderingItems> orderingItemsList = orderingItemsRepository.findByOrderingId(connection, orderingId);
            optionalOrdering.ifPresent(o -> o.setOrderingItems(orderingItemsList));

            return optionalOrdering;
        });
    }

    @Override
    public boolean updateDoneToTrue() {

        LOGGER.info("Updating done to true");

        return transactionRunner.doInTransaction(connection ->
                orderingRepository.updateDoneToTrue(connection, true));
    }
}
