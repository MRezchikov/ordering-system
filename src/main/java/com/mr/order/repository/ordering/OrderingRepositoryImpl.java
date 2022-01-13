package com.mr.order.repository.ordering;

import com.mr.order.entity.Ordering;
import com.mr.order.entity.OrderingItems;
import com.mr.order.util.connection.DatabaseConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mr.order.repository.query.OrderingQuery.ORDERING_FIND_BY_ID_QUERY;
import static com.mr.order.repository.query.OrderingQuery.ORDERING_INSERT_QUERY;
import static com.mr.order.repository.query.OrderingQuery.ORDERING_UPDATE_DONE_TO_TRUE;
import static com.mr.order.repository.query.OrderingQuery.ORDERING_WITH_ORDER_ITEMS;

public class OrderingRepositoryImpl implements OrderingRepository {

    private final DatabaseConnection databaseConnection;

    public OrderingRepositoryImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Ordering create(Ordering ordering) {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(ORDERING_INSERT_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, ordering.getUsername());
            preparedStatement.setBoolean(2, ordering.getDone() != null ? ordering.getDone() : false);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(ordering.getUpdatedAt()));
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                ordering.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordering;
    }

    @Override
    public Optional<Ordering> findWithOrderingItemsById(Long id) {

        List<OrderingItems> orderingItemsList = new ArrayList<>();

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ORDERING_WITH_ORDER_ITEMS)
        ) {
            preparedStatement.setLong(1, id);
            final Optional<Ordering> orderingOptional = findById(id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    final OrderingItems orderingItems = OrderingItems.builder()
                            .id(resultSet.getLong(5))
                            .orderingId(resultSet.getLong(6))
                            .itemName(resultSet.getString(7))
                            .itemCount(resultSet.getInt(8))
                            .itemPrice(resultSet.getDouble(9))
                            .build();

                    orderingItemsList.add(orderingItems);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            orderingOptional.ifPresent(o -> o.setOrderingItems(orderingItemsList));
            return orderingOptional;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<Ordering> findById(Long id) {

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(ORDERING_FIND_BY_ID_QUERY)
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Ordering orderingFromDb = Ordering.builder()
                            .id(resultSet.getLong(1))
                            .username(resultSet.getString(2))
                            .done(resultSet.getBoolean(3))
                            .updatedAt(resultSet.getTimestamp(4).toLocalDateTime())
                            .build();

                    return Optional.of(orderingFromDb);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void updateDoneToTrue(boolean done) {

        try(Connection connection = databaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ORDERING_UPDATE_DONE_TO_TRUE)
        ) {
            preparedStatement.setBoolean(1, done);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
