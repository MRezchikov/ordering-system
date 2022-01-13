package com.mr.order.entity;

public class OrderingItems {

    private Long id;
    private Long orderingId;
    private String itemName;
    private Integer itemCount;
    private Double itemPrice;

    public Long getId() {
        return id;
    }

    public Long getOrderingId() {
        return orderingId;
    }

    public void setOrderingId(Long orderingId) {
        this.orderingId = orderingId;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public static Builder builder() {
        return new OrderingItems().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder id(Long id) {
            OrderingItems.this.id=id;
            return this;
        }

        public Builder orderingId(Long orderingId) {
            OrderingItems.this.orderingId = orderingId;
            return this;
        }

        public Builder itemName(String itemName) {
            OrderingItems.this.itemName = itemName;
            return this;
        }

        public Builder itemCount(Integer itemCount) {
            OrderingItems.this.itemCount = itemCount;
            return this;
        }

        public Builder itemPrice(Double itemPrice) {
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

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
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
