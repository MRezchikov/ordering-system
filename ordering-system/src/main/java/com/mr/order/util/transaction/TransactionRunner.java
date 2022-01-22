package com.mr.order.util.transaction;

import java.sql.Connection;
import java.util.function.Consumer;

public interface TransactionRunner {

    <T> T doInTransaction(TransactionAction<T> action);

    void doInTransactionWithReturnNothing(Consumer<Connection> action);
}
