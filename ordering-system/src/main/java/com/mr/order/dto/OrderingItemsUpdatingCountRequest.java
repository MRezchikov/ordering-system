package com.mr.order.dto;

public class OrderingItemsUpdatingCountRequest {

    private final long orderingItemsId;
    private final int itemCount;

    public OrderingItemsUpdatingCountRequest(long orderingItemsId, int itemCount) {
        this.orderingItemsId = orderingItemsId;
        this.itemCount = itemCount;
    }

    public long getOrderingItemsId() {
        return orderingItemsId;
    }

    public int getItemCount() {
        return itemCount;
    }
}
