package com.mr.atmsimulator.atm.data;

public class EventData {

    private long balanceCash;

    private EventData() {
    }

    public long getBalanceCash() {
        return balanceCash;
    }

    public static Builder builder() {
        return new EventData().new Builder();
    }

    public EventData(long balanceCash) {
        this.balanceCash = balanceCash;
    }

    public class Builder {

        private Builder() {
        }

        public Builder balanceCash(long balanceCash) {
            EventData.this.balanceCash = balanceCash;
            return this;
        }

        public EventData build() {
            return EventData.this;
        }
    }
}
