package com.mr.order.repository.orderingitems;

import com.mr.order.entity.OrderingItems;
import com.mr.order.util.connection.DatabaseConnection;

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

    private final DatabaseConnection databaseConnection;


    public OrderingItemsRepositoryImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public OrderingItems create(OrderingItems orderingItems) {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(ORDERING_ITEMS_INSERT_QUERY)
        ) {
            preparedStatement.setLong(1, orderingItems.getOrderingId());
            preparedStatement.setString(2, orderingItems.getItemName());
            preparedStatement.setInt(3, orderingItems.getItemCount());
            preparedStatement.setDouble(4, orderingItems.getItemPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderingItems;
    }

    @Override
    public void saveAll(List<OrderingItems> orderingItems) {

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(ORDERING_ITEMS_INSERT_QUERY)
        ) {
            for (OrderingItems orderingItem : orderingItems) {
                preparedStatement.setLong(1, orderingItem.getOrderingId());
                preparedStatement.setString(2, orderingItem.getItemName());
                preparedStatement.setInt(3, orderingItem.getItemCount());
                preparedStatement.setDouble(4, orderingItem.getItemPrice());

                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<OrderingItems> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<OrderingItems> findByOrderingId(Long id) {

        List<OrderingItems> orderingItems = new ArrayList<>();

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(ORDERING_ITEMS_GET_BY_ORDERING_ID);
        ) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long orderingItemsId = resultSet.getLong(1);
                    long orderingId = resultSet.getLong(2);
                    String itemName = resultSet.getString(3);
                    int itemCount = resultSet.getInt(4);
                    double itemPrice = resultSet.getDouble(5);

                    OrderingItems orderingItemsFromDb = OrderingItems.builder()
                            .id(orderingItemsId)
                            .orderingId(orderingId)
                            .itemName(itemName)
                            .itemCount(itemCount)
                            .itemPrice(itemPrice)
                            .build();

                    orderingItems.add(orderingItemsFromDb);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderingItems;
    }

    @Override
    public Optional<OrderingItems> updateItemCount(OrderingItems orderingItems) {

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(ORDERING_ITEMS_UPDATES_ITEMS_COUNT);
        ) {
            preparedStatement.setInt(1, orderingItems.getItemCount());
            preparedStatement.setLong(2, orderingItems.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.of(orderingItems);
    }
}
