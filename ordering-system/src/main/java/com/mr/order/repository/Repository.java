package com.mr.order.repository;

import java.sql.Connection;

public interface Repository<T> {

    T save(Connection connection, T t);
}
