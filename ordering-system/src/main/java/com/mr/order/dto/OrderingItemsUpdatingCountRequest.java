package com.mr.order.dto;

public class OrderingItemsUpdatingCountRequest {

    private final long orderingItemsId;
    private final int itemCount;

    public OrderingItemsUpdatingCountRequest(long orderingItemsId, int countItem) {
        this.orderingItemsId = orderingItemsId;
        this.itemCount = countItem;
    }

    public long getOrderingItemsId() {
        return orderingItemsId;
    }

    public int getItemCount() {
        return itemCount;
    }
}
