package com.mr.order.service;

import com.mr.order.dto.OrderingItemsUpdatingCountRequest;
import com.mr.order.entity.Ordering;
import com.mr.order.entity.OrderingItems;
import com.mr.order.exeption.EntityNotFoundException;
import com.mr.order.exeption.UnknownException;
import com.mr.order.exeption.UnsupportedOrderIdException;
import com.mr.order.repository.ordering.OrderingRepository;
import com.mr.order.repository.ordering.OrderingRepositoryImpl;
import com.mr.order.repository.orderingitems.OrderingItemsRepository;
import com.mr.order.repository.orderingitems.OrderingItemsRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderingRepository orderingRepository = new OrderingRepositoryImpl();
    private final OrderingItemsRepository orderingItemsRepository = new OrderingItemsRepositoryImpl();

    @Override
    public Ordering saveOrder(Connection connection, Ordering ordering) {

        LOGGER.info("Saving the ordering entity");

        try {
            Ordering savedOrdering = orderingRepository.save(connection, ordering);

            List<OrderingItems> orderingItems = ordering.getOrderingItems();

            if (!orderingItems.isEmpty()) {
                for (OrderingItems orderingItem : orderingItems) {
                    orderingItem.setOrderingId(savedOrdering.getId());
                }

                orderingItemsRepository.saveAll(connection, orderingItems);
            }
            connection.commit();

            return savedOrdering;
        } catch (Exception e) {
            LOGGER.error("Trying to rollback transactions after unsuccessful saving the ordering entity");
            rollbackTransaction(connection);
            throw new UnknownException(e);
        }
    }

    @Override
    public void updateOrderingItemsCountByOrderingId(Connection connection,
                                                     long orderId,
                                                     OrderingItemsUpdatingCountRequest countRequests) {

        LOGGER.info("Updating the ordering items entity by order id: {}", orderId);

        try {
            if (orderId < 1) {
                throw new UnsupportedOrderIdException("Invalid order number entered " + orderId);
            }

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

            connection.commit();
        } catch (Exception e) {
            LOGGER.error("Trying to rollback transactions after unsuccessful updating the ordering items");
            rollbackTransaction(connection);
            throw new UnknownException(e);
        }
    }

    @Override
    public Optional<Ordering> findWithOrderingItemsById(Connection connection, long orderingId) {

        LOGGER.info("Extract the ordering with ordering items by ordering id: {}", orderingId);

        try {
            Optional<Ordering> optionalOrdering = orderingRepository.findById(connection, orderingId);
            List<OrderingItems> orderingItemsList = orderingItemsRepository.findByOrderingId(connection, orderingId);
            optionalOrdering.ifPresent(o -> o.setOrderingItems(orderingItemsList));
            return optionalOrdering;
        } catch (Exception e) {
            LOGGER.error("Trying to rollback transactions after unsuccessful ordering extraction with ordering items");
            rollbackTransaction(connection);
            throw new UnknownException(e);
        }
    }

    @Override
    public void updateDoneToTrue(Connection connection) {
        orderingRepository.updateDoneToTrue(connection, true);
    }

    private void rollbackTransaction(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new UnknownException(ex);
        }
    }
}
