package com.mr.order.repository.ordering;

import com.mr.order.entity.Ordering;
import com.mr.order.repository.Repository;

import java.util.Optional;

public interface OrderingRepository extends Repository<Ordering> {

    Optional<Ordering> findWithOrderingItemsById(Long id);

    void updateDoneToTrue(boolean done);
}
