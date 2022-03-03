package com.mr.orderingsystemapp.orderingitems.controller;

import com.mr.orderingsystemapp.orderingitems.dto.OrderingItemCountDto;
import com.mr.orderingsystemapp.orderingitems.entity.OrderingItems;
import com.mr.orderingsystemapp.orderingitems.service.OrderingItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderingItemsController {

    private final OrderingItemsService orderingItemsService;

    @GetMapping("/api/item/orderingitem/{orderingId}")
    public ResponseEntity<List<OrderingItems>> findByOrderingId(@PathVariable Long orderingId) {
        return ResponseEntity.ok(orderingItemsService.findOrderingItemsByOrderingId(orderingId));
    }

    @PostMapping("/api/item/orderingitem")
    public ResponseEntity<OrderingItems> saveOrderingItemByWithOrderingId(@RequestBody OrderingItems orderingItem) {
        return ResponseEntity.ok(orderingItemsService.saveOrderingItem(orderingItem));
    }

    @PutMapping("/api/item/orderingitem/itemcount")
    public ResponseEntity<OrderingItems> changeItemCount(@RequestBody OrderingItemCountDto request) {
        return ResponseEntity.ok(orderingItemsService.changeItemCount(request));
    }
}
