package com.mr.order.repository.ordering;

import com.mr.order.entity.Ordering;
import com.mr.order.repository.Repository;

import java.sql.Connection;
import java.util.Optional;

public interface OrderingRepository extends Repository<Ordering> {

    Optional<Ordering> findById(Connection connection, Long id);

    void updateDoneToTrue(Connection connection, boolean done);
}
