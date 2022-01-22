package com.mr.order.util.transaction;

import com.mr.order.exeption.DatabaseInteractionException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class TransactionRunnerJdbc implements TransactionRunner {

    private static final String EXCEPTION_MESSAGE = "Exception occurred";

    private final DataSource dataSource;

    public TransactionRunnerJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public <T> T doInTransaction(TransactionAction<T> action) {
        return wrapException(() -> {
            try (var connection = dataSource.getConnection()) {
                try {
                    var result = action.apply(connection);
                    connection.commit();
                    return result;
                } catch (SQLException e) {
                    throw new DatabaseInteractionException(EXCEPTION_MESSAGE, e);
                }
            }
        });
    }

    @Override
    public void doInTransactionWithReturnNothing(Consumer<Connection> action) {
        try (var connection = dataSource.getConnection()) {
            try {
                action.accept(connection);
                connection.commit();
            } catch (SQLException e) {
                throw new DatabaseInteractionException(EXCEPTION_MESSAGE, e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private <T> T wrapException(Callable<T> action) {
        try {
            return action.call();
        } catch (Exception e) {
            throw new DatabaseInteractionException(EXCEPTION_MESSAGE, e);
        }
    }
}
