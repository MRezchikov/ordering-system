package com.mr.orderingsystemapp.orderingitems.service;

import com.mr.orderingsystemapp.orderingitems.dto.OrderingItemCountDto;
import com.mr.orderingsystemapp.orderingitems.entity.OrderingItems;
import com.mr.orderingsystemapp.orderingitems.repository.OrderingItemsRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderingItemsServiceImpl implements OrderingItemsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderingItemsServiceImpl.class);

    private final OrderingItemsRepository orderingItemsRepository;

    @Override
    public OrderingItems saveOrderingItem(OrderingItems orderingItem) {
        LOGGER.info("Save ordering item {}", orderingItem);
        return orderingItemsRepository.save(orderingItem);
    }

    @Override
    public List<OrderingItems> findOrderingItemsByOrderingId(Long orderingId) {
        LOGGER.info("Finding ordering items by ordering id {}", orderingId);
        return orderingItemsRepository.findOrderingItemsByOrderingId(orderingId);
    }

    @Override
    public OrderingItems changeItemCount(OrderingItemCountDto orderingItemCountDto) {

        LOGGER.info("Changing item count {}", orderingItemCountDto);

        OrderingItems orderingItem = orderingItemsRepository.findOrderingItemsByIdAndAndOrderingId(orderingItemCountDto.getId(),
                orderingItemCountDto.getOrderingId());
        orderingItem.setItemCount(orderingItemCountDto.getCount());
        return orderingItemsRepository.save(orderingItem);
    }
}
