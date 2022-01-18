package com.mr.order.repository.ordering;

import com.mr.order.entity.Ordering;
import com.mr.order.repository.Repository;

import java.sql.Connection;

public interface OrderingRepository extends Repository<Ordering> {

    void updateDoneToTrue(Connection connection, boolean done);
}
