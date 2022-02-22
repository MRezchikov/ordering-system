package com.mr.concurrency.robot.example01join;

public class Robot {

    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            invokeThreads();
        }
    }

    public static void invokeThreads() {

        Thread left = new Thread(Robot::stepLeft);
        Thread right = new Thread(Robot::stepRight);

        try {
            left.start();
            left.join();
            threadSleep(1000);
            right.start();
            right.join();
            threadSleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void stepLeft() {
        System.out.println("Step left");
    }

    private static void stepRight() {
        System.out.println("Step right");
    }

    private static void threadSleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
