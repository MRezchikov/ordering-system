package com.mr.concurrency.robot.example03volatile;

public class Robot {

    private volatile boolean currentLeg = true;

    public static void main(String[] args) {

        Robot robot = new Robot();
        Leg left = robot.new Leg("LEFT", false);
        Leg right = robot.new Leg("RIGHT", true);

        new Thread(left).start();
        new Thread(right).start();
    }

    class Leg implements Runnable {

        private final String name;
        private final boolean leg;

        public Leg(String name, boolean leg) {
            this.name = name;
            this.leg = leg;
        }

        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()) {
                if (leg == currentLeg) {
                    System.out.println(name);
                    currentLeg = !leg;
                }
            }
        }
    }
}
