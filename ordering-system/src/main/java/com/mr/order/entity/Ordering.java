package com.mr.order.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ordering {

    private long id;
    private String username;
    private boolean done;
    private LocalDateTime updatedAt;
    private List<OrderingItems> orderingItems = new ArrayList<>();

    private Ordering() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public boolean getDone() {
        return done;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<OrderingItems> getOrderingItems() {
        return orderingItems;
    }

    public void setOrderingItems(List<OrderingItems> orderingItems) {
        this.orderingItems = orderingItems;
    }

    public static Builder builder() {
        return new Ordering().new Builder();
    }


    public class Builder {

        private Builder() {
        }

        public Builder id(long id) {
            Ordering.this.id = id;
            return this;
        }

        public Builder username(String username) {
            Ordering.this.username = username;
            return this;
        }

        public Builder done(boolean done) {
            Ordering.this.done = done;
            return this;
        }

        public Builder updatedAt(LocalDateTime updatedAt) {
            Ordering.this.updatedAt = updatedAt;
            return this;
        }

        public Builder orderingItems(List<OrderingItems> orderingItems) {
            Ordering.this.orderingItems = orderingItems;
            return this;
        }

        public Ordering build() {
            return Ordering.this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ordering ordering = (Ordering) o;

        return id == ordering.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Ordering{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", done=" + done +
                ", updatedAt=" + updatedAt +
                ", orderingItems=" + orderingItems +
                '}';
    }
}
