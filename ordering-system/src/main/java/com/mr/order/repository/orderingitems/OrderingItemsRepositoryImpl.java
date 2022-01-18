package com.mr.order.repository.orderingitems;

import com.mr.order.entity.OrderingItems;
import com.mr.order.exeption.DatabaseInteractionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mr.order.repository.query.OrderingItemsQuery.ORDERING_ITEMS_GET_BY_ORDERING_ID;
import static com.mr.order.repository.query.OrderingItemsQuery.ORDERING_ITEMS_INSERT_QUERY;
import static com.mr.order.repository.query.OrderingItemsQuery.ORDERING_ITEMS_UPDATES_ITEMS_COUNT;

public class OrderingItemsRepositoryImpl implements OrderingItemsRepository {

    private static final String ORDERING_ITEMS_ID = "id";
    private static final String ORDERING_ID = "ordering_id";
    private static final String ITEM_NAME = "item_name";
    private static final String ITEM_COUNT = "item_count";
    private static final String ITEM_PRICE = "item_price";

    @Override
    public OrderingItems save(Connection connection, OrderingItems orderingItems) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(ORDERING_ITEMS_INSERT_QUERY)) {
            preparedStatement.setLong(1, orderingItems.getOrderingId());
            preparedStatement.setString(2, orderingItems.getItemName());
            preparedStatement.setInt(3, orderingItems.getItemCount());
            preparedStatement.setDouble(4, orderingItems.getItemPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseInteractionException("Some error was occurred while saving ordering item ", e);
        }

        return orderingItems;
    }

    @Override
    public void saveAll(Connection connection, List<OrderingItems> orderingItems) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(ORDERING_ITEMS_INSERT_QUERY)) {
            for (int i = 0; i < orderingItems.size(); i++) {
                OrderingItems orderingItem = orderingItems.get(i);
                preparedStatement.setLong(1, orderingItem.getOrderingId());
                preparedStatement.setString(2, orderingItem.getItemName());
                preparedStatement.setInt(3, orderingItem.getItemCount());
                preparedStatement.setDouble(4, orderingItem.getItemPrice());

                preparedStatement.addBatch();
                if (i % 100 == 0) {
                    preparedStatement.executeBatch();
                }
            }

            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DatabaseInteractionException("Some error was occurred while saving all orderings items ", e);
        }
    }

    @Override
    public Optional<OrderingItems> findById(Connection connection, long id) {
        return Optional.empty();
    }

    @Override
    public List<OrderingItems> findByOrderingId(Connection connection, long id) {

        List<OrderingItems> orderingItems = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(ORDERING_ITEMS_GET_BY_ORDERING_ID)) {
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    OrderingItems orderingItemsFromDb = OrderingItems.builder()
                            .id(resultSet.getLong(ORDERING_ITEMS_ID))
                            .orderingId(resultSet.getLong(ORDERING_ID))
                            .itemName(resultSet.getString(ITEM_NAME))
                            .itemCount(resultSet.getInt(ITEM_COUNT))
                            .itemPrice(resultSet.getDouble(ITEM_PRICE))
                            .build();

                    orderingItems.add(orderingItemsFromDb);
                }
            } catch (SQLException e) {
                throw new DatabaseInteractionException("Some error was occurred while finding orderings items ", e);
            }
        } catch (SQLException e) {
            throw new DatabaseInteractionException("Some error was occurred while finding orderings items ", e);
        }

        return orderingItems;
    }

    @Override
    public Optional<OrderingItems> updateItemCount(Connection connection, OrderingItems orderingItems) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(ORDERING_ITEMS_UPDATES_ITEMS_COUNT)) {
            preparedStatement.setInt(1, orderingItems.getItemCount());
            preparedStatement.setLong(2, orderingItems.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseInteractionException("Some error was occurred while getting item count ", e);
        }

        return Optional.of(orderingItems);
    }
}
