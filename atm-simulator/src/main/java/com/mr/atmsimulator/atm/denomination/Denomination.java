package com.mr.atmsimulator.atm.denomination;

public enum Denomination {

    FIVE_THOUSAND(5000),
    ONE_THOUSAND(1000),
    FIVE_HUNDRED(500),
    ONE_HUNDRED(100),
    FIFTY(50),
    TEN(10);

    private final Integer value;

    Denomination(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
