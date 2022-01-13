package com.mr.order.repository;

import java.util.Optional;

public interface Repository<T> {

    T create(T t);

    Optional<T> findById(Long id);

//    Optional<T> update(T t);
}
