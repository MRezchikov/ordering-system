package com.mr.orderingsystemapp.ordering.service;

import com.mr.orderingsystemapp.ordering.entity.Ordering;

public interface OrderingService {

    Ordering saveOrdering(Ordering ordering);

    Ordering findById(Long id);

    void changeOrderingsToDone();
}
