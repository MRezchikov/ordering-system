package com.mr.order.repository.query;

public class OrderingQuery {

    private OrderingQuery() {
    }

    public static final String ORDERING_INSERT_QUERY = "INSERT INTO ordering (user_name, done, updated_at) VALUES (?, ?, ?)";

    public static final String ORDERING_FIND_BY_ID_QUERY = "SELECT * FROM ordering WHERE id = ?";

    public static final String ORDERING_UPDATE_DONE_TO_TRUE = "UPDATE ordering SET done = ? WHERE done = false";
}
