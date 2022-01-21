package com.mr.order.service;

import com.mr.order.repository.ordering.OrderingRepository;
import com.mr.order.repository.orderingitems.OrderingItemsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Mock
    private OrderingRepository orderingRepository;

    @Mock
    private OrderingItemsRepository orderingItemsRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void saveOrder() {
    }

    @Test
    void updateOrderingItemsCountByOrderingId() {
    }

    @Test
    void findWithOrderingItemsById() {
    }

    @Test
    void updateDoneToTrue() {
    }
}