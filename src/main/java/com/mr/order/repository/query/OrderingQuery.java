package com.mr.order.repository.query;

public class OrderingQuery {

    public static final String ORDERING_INSERT_QUERY = "INSERT INTO ordering (user_name, done, updated_at) VALUES (?, ?, ?)";

    public static final String ORDERING_FIND_BY_ID_QUERY = "SELECT * FROM ordering WHERE id = ?";

    public static final String ORDERING_WITH_ORDER_ITEMS = "SELECT * FROM ordering " +
            "LEFT JOIN ordering_items ON ordering.id = ordering_items.ordering_id " +
            "WHERE ordering.id = ?";

    public static final String ORDERING_UPDATE_DONE_TO_TRUE = "UPDATE ordering SET done = ? WHERE done = false";
}
