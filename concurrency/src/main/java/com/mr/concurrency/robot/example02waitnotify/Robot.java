package com.mr.concurrency.robot.example02waitnotify;

public class Robot {

    public static void main(String[] args) {

        Robot robot = new Robot();

        Thread left = new Thread(() -> robot.step("Left"));
        Thread right = new Thread(() -> robot.step("Right"));

        left.setName("LEFT_THREAD");
        right.setName("RIGHT_THREAD");

        left.start();
        right.start();
    }

    private void step(String leg) {
        synchronized (this) {
            while (true) {
                System.out.println(leg + " " + Thread.currentThread().getName());
                notify();
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
