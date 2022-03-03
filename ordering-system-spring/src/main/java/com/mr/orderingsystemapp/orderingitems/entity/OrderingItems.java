package com.mr.orderingsystemapp.orderingitems.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class OrderingItems {

    public OrderingItems() {
    }

    public OrderingItems(Long id, Long orderingId, String itemName, Integer itemCount, Double itemPrice) {
        this.id = id;
        this.orderingId = orderingId;
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.itemPrice = itemPrice;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ordering_id")
    private Long orderingId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_count")
    private Integer itemCount;

    @Column(name = "item_price")
    private Double itemPrice;
}
