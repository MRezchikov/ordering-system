package com.mr.orderingsystemapp.ordering.controller;

import com.mr.orderingsystemapp.ordering.entity.Ordering;
import com.mr.orderingsystemapp.ordering.dto.OrderingDto;
import com.mr.orderingsystemapp.ordering.service.OrderingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderingController {

    private final ConversionService conversionService;
    private final OrderingService orderingService;

    @GetMapping("/api/ordering/{id}")
    public ResponseEntity<Ordering> findById(@PathVariable Long id) {
        return ResponseEntity.ok(orderingService.findById(id));
    }

    @PostMapping("/api/ordering")
    public ResponseEntity<Ordering> save(@RequestBody OrderingDto orderingDto) {
        Ordering ordering = conversionService.convert(orderingDto, Ordering.class);
        return ResponseEntity.ok(orderingService.saveOrdering(ordering));
    }

    @PutMapping("/api/ordering/orderings/done")
    public void changeOrderingToDone() {
        orderingService.changeOrderingsToDone();
    }
}
