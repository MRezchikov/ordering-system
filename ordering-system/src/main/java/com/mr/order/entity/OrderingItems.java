package com.mr.order.entity;

public class OrderingItems {

    private long id;
    private long orderingId;
    private String itemName;
    private int itemCount;
    private double itemPrice;

    public long getId() {
        return id;
    }

    public long getOrderingId() {
        return orderingId;
    }

    public void setOrderingId(long orderingId) {
        this.orderingId = orderingId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public static Builder builder() {
        return new OrderingItems().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder id(long id) {
            OrderingItems.this.id=id;
            return this;
        }

        public Builder orderingId(long orderingId) {
            OrderingItems.this.orderingId = orderingId;
            return this;
        }

        public Builder itemName(String itemName) {
            OrderingItems.this.itemName = itemName;
            return this;
        }

        public Builder itemCount(int itemCount) {
            OrderingItems.this.itemCount = itemCount;
            return this;
        }

        public Builder itemPrice(double itemPrice) {
            OrderingItems.this.itemPrice = itemPrice;
            return this;
        }

        public OrderingItems build() {
            return OrderingItems.this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderingItems that = (OrderingItems) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "OrderingItems{" +
                "id=" + id +
                ", orderingId=" + orderingId +
                ", itemName='" + itemName + '\'' +
                ", itemCount=" + itemCount +
                ", itemPrice=" + itemPrice +
                '}';
    }
}
