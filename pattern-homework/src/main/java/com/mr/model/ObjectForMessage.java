package com.mr.model;

import java.util.ArrayList;
import java.util.List;

public class ObjectForMessage {
    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    protected ObjectForMessage clone() {
        ObjectForMessage copy = new ObjectForMessage();
        List<String> copyData = new ArrayList<>(data);
        copy.setData(copyData);
        return copy;
    }

    @Override
    public String toString() {
        return "ObjectForMessage{" +
                "data=" + data +
                '}';
    }
}
