package com.mr.atmsimulator.atm.data;

public final class ListenerAttribute {

    private final long lowerLimit;
    private final long higherLimit;
    private boolean stopNotificationFlag;

    public ListenerAttribute(long lowerLimit, long higherLimit) {
        this.lowerLimit = lowerLimit;
        this.higherLimit = higherLimit;
    }

    public long getLowerLimit() {
        return lowerLimit;
    }

    public long getHigherLimit() {
        return higherLimit;
    }

    public boolean isStopNotificationFlag() {
        return stopNotificationFlag;
    }

    public void setStopNotificationFlag(boolean stopNotificationFlag) {
        this.stopNotificationFlag = stopNotificationFlag;
    }
}
