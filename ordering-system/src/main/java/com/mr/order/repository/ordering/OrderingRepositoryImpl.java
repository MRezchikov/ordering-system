package com.mr.order.repository.ordering;

import com.mr.order.entity.Ordering;
import com.mr.order.exeption.DatabaseInteractionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import static com.mr.order.repository.query.OrderingQuery.ORDERING_FIND_BY_ID_QUERY;
import static com.mr.order.repository.query.OrderingQuery.ORDERING_INSERT_QUERY;
import static com.mr.order.repository.query.OrderingQuery.ORDERING_UPDATE_DONE_TO_TRUE;
import static java.sql.PreparedStatement.RETURN_GENERATED_KEYS;

public class OrderingRepositoryImpl implements OrderingRepository {

    private static final String ORDERING_ID = "id";
    private static final String USERNAME = "user_name";
    private static final String DONE = "done";
    private static final String UPDATED_AT = "updated_at";

    @Override
    public Ordering save(Connection connection, Ordering ordering) {

        try (var preparedStatement = connection.prepareStatement(
                ORDERING_INSERT_QUERY, RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, ordering.getUsername());
            preparedStatement.setBoolean(2, ordering.getDone());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(ordering.getUpdatedAt()));
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (var generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return Ordering.builder()
                            .id(generatedKeys.getLong(ORDERING_ID))
                            .username(ordering.getUsername())
                            .done(ordering.getDone())
                            .updatedAt(ordering.getUpdatedAt())
                            .build();
                }

                return ordering;
            } catch (SQLException e) {
                throw new DatabaseInteractionException("Some error was occurred while saving ordering ", e);
            }
        } catch (SQLException e) {
            throw new DatabaseInteractionException("Some error was occurred while saving ordering ", e);
        }
    }

    @Override
    public Optional<Ordering> findById(Connection connection, Long id) {

        try (var preparedStatement = connection.prepareStatement(ORDERING_FIND_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Ordering orderingFromDb = Ordering.builder()
                            .id(resultSet.getLong(ORDERING_ID))
                            .username(resultSet.getString(USERNAME))
                            .done(resultSet.getBoolean(DONE))
                            .updatedAt(resultSet.getTimestamp(UPDATED_AT).toLocalDateTime())
                            .build();

                    return Optional.of(orderingFromDb);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new DatabaseInteractionException("Some error was occurred while getting orderings", e);
        }
    }

    @Override
    public boolean updateDoneToTrue(Connection connection, boolean done) {

        try (var preparedStatement = connection.prepareStatement(ORDERING_UPDATE_DONE_TO_TRUE)) {
            preparedStatement.setBoolean(1, done);
            int affectedRow = preparedStatement.executeUpdate();

            if (affectedRow != 0) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new DatabaseInteractionException("Some error was occurred while updating done", e);
        }
    }
}
