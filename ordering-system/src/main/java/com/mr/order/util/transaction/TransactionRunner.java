package com.mr.order.util.transaction;

public interface TransactionRunner {
    <T> T doInTransaction(TransactionAction<T> action);
}
