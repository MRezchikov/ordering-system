package com.mr.orderingsystemapp.ordering.service;

import com.mr.orderingsystemapp.ordering.entity.Ordering;
import com.mr.orderingsystemapp.ordering.repository.OrderingRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderingServiceImpl implements OrderingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderingServiceImpl.class);

    private final OrderingRepository orderingRepository;

    @Override
    public Ordering saveOrdering(Ordering ordering) {
        LOGGER.info("Save ordering {}", ordering);
        return orderingRepository.save(ordering);
    }

    @Override
    public Ordering findById(Long id) {
        LOGGER.info("Find by id {}", id);
        return orderingRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void changeOrderingsToDone() {

        LOGGER.info("Change orderings to done");

        List<Ordering> allOrderings = orderingRepository.findAll();

        allOrderings.stream()
                .filter(ordering -> !ordering.getDone())
                .forEach(ordering -> ordering.setDone(true));

        orderingRepository.saveAll(allOrderings);
    }
}
