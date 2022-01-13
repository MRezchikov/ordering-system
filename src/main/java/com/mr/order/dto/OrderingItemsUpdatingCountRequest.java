package com.mr.order.dto;

public class OrderingItemsUpdatingCountRequest {

    private Long orderingItemsId;
    private Integer countItem;

    public OrderingItemsUpdatingCountRequest(Long orderingItemsId, Integer countItem) {
        this.orderingItemsId = orderingItemsId;
        this.countItem = countItem;
    }

    public Long getOrderingItemsId() {
        return orderingItemsId;
    }

    public Integer getCountItem() {
        return countItem;
    }
}
