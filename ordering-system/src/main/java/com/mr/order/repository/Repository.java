package com.mr.order.repository;

import java.sql.Connection;
import java.util.Optional;

public interface Repository<T> {

    T save(Connection connection, T t);

    Optional<T> findById(Connection connection, long id);
}
