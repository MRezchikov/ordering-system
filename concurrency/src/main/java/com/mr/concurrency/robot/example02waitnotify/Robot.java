package com.mr.concurrency.robot.example02waitnotify;

public class Robot {

    private String prevLeg = "Right";

    public static void main(String[] args) {

        Robot robot = new Robot();

        Thread left = new Thread(() -> robot.step("Left"));
        Thread right = new Thread(() -> robot.step("Right"));

        left.setName("LEFT_THREAD");
        right.setName("RIGHT_THREAD");

        left.start();
        right.start();
    }

    private synchronized void step(String leg) {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                while (prevLeg.equals(leg)) {
                    wait();
                }
                threadSleep(500);
                System.out.println(leg + " " + Thread.currentThread().getName());
                prevLeg = leg;
                notify();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    private static void threadSleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
