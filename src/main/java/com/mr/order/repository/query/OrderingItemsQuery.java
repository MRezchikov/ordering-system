package com.mr.order.repository.query;

public class OrderingItemsQuery {

    public static final String ORDERING_ITEMS_INSERT_QUERY = "INSERT INTO ordering_items (ordering_id, item_name, item_count, item_price) " +
            "VALUES (?, ?, ?, ?)";

    public static final String ORDERING_ITEMS_GET_BY_ORDERING_ID = "SELECT * FROM ordering_items WHERE ordering_id = ?";

    public static final String ORDERING_ITEMS_UPDATES_ITEMS_COUNT = "UPDATE ordering_items SET item_count = ? WHERE id = ?";
}
