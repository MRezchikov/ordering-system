package com.mr.order.service;

import com.mr.order.dto.OrderingItemsUpdatingCountRequest;
import com.mr.order.entity.Ordering;
import com.mr.order.entity.OrderingItems;
import com.mr.order.exeption.EntityNotFoundException;
import com.mr.order.repository.ordering.OrderingRepository;
import com.mr.order.repository.orderingitems.OrderingItemsRepository;
import com.mr.order.util.transaction.TransactionRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final TransactionRunner transactionRunner;
    private final OrderingRepository orderingRepository;
    private final OrderingItemsRepository orderingItemsRepository;

    public OrderServiceImpl(TransactionRunner transactionRunner,
                            OrderingRepository orderingRepository,
                            OrderingItemsRepository orderingItemsRepository) {
        this.transactionRunner = transactionRunner;
        this.orderingRepository = orderingRepository;
        this.orderingItemsRepository = orderingItemsRepository;
    }

    @Override
    public Ordering saveOrder(Ordering ordering) {

        LOGGER.info("Saving the ordering entity");

        return transactionRunner.doInTransaction(connection -> {
            var savedOrdering = orderingRepository.save(connection, ordering);
            var orderingItems = ordering.getOrderingItems();

            if (!orderingItems.isEmpty()) {
                for (OrderingItems orderingItem : orderingItems) {
                    orderingItem.setOrderingId(savedOrdering.getId());
                }

                orderingItemsRepository.saveAll(connection, orderingItems);
                savedOrdering.setOrderingItems(orderingItems);
            }

            return savedOrdering;
        });
    }

    @Override
    public void updateOrderingItemsCountByOrderingId(Long orderId,
                                                     OrderingItemsUpdatingCountRequest countRequests) {

        LOGGER.info("Updating the ordering items entity by order id: {}", orderId);

        transactionRunner.doInTransactionWithReturnNothing(connection -> {
            var orderingItemsList = orderingItemsRepository.findByOrderingId(connection, orderId);

            if (orderingItemsList.isEmpty()) {
                throw new EntityNotFoundException("Entity was not found by orderId " + orderId);
            }

            for (OrderingItems orderingItems : orderingItemsList) {
                var id = orderingItems.getId();
                if (id.equals(countRequests.getOrderingItemsId())) {
                    var orderingItemsToUpdating = OrderingItems.builder()
                            .id(countRequests.getOrderingItemsId())
                            .itemCount(countRequests.getItemCount())
                            .build();
                    orderingItemsRepository.updateItemCount(connection, orderingItemsToUpdating);
                }
            }
        });
    }

    @Override
    public Optional<Ordering> findWithOrderingItemsById(Long orderingId) {

        LOGGER.info("Extract the ordering with ordering items by ordering id: {}", orderingId);

        return transactionRunner.doInTransaction(connection -> {
            var optionalOrdering = orderingRepository.findById(connection, orderingId);
            var orderingItemsList = orderingItemsRepository.findByOrderingId(connection, orderingId);
            optionalOrdering.ifPresent(o -> o.setOrderingItems(orderingItemsList));

            return optionalOrdering;
        });
    }

    @Override
    public void updateDoneToTrue() {
        LOGGER.info("Updating done to true");
        transactionRunner.doInTransactionWithReturnNothing(connection ->
                orderingRepository.updateDoneToTrue(connection, true));
    }
}
