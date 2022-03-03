package com.mr.orderingsystemapp.ordering.repository;

import com.mr.orderingsystemapp.ordering.entity.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderingRepository extends JpaRepository<Ordering, Long> {
}
