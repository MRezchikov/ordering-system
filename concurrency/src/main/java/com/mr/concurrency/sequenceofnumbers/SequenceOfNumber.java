package com.mr.concurrency.sequenceofnumbers;

import java.util.concurrent.atomic.AtomicInteger;

public class SequenceOfNumber {

    private int lastThread = 2;
    private volatile int previousCounter = 0;
    private final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {

        SequenceOfNumber sequenceOfNumber = new SequenceOfNumber();

        var firstThread = new Thread(() -> sequenceOfNumber.printNumbers(1));
        var secondThread = new Thread(() -> sequenceOfNumber.printNumbers(2));

        firstThread.setName("Thread 1");
        secondThread.setName("Thread 2");

        firstThread.start();
        secondThread.start();
    }

    private synchronized void printNumbers(int threadNumber) {
        while (!Thread.currentThread().isInterrupted()) {
            while (counter.get() < 10) {
                try {
                    while (lastThread == threadNumber) {
                        wait();
                    }
                    previousCounter = counter.get();
                    if (threadNumber == 1) {
                        System.out.println(Thread.currentThread().getName() + ": " + counter.incrementAndGet());
                    }
                    if (threadNumber == 2) {
                        System.out.println(Thread.currentThread().getName() + ": " + previousCounter);
                    }
                    threadSleep(500);
                    lastThread = threadNumber;
                    notify();
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
            while (counter.get() > 1) {
                try {
                    while (lastThread == threadNumber) {
                        wait();
                    }
                    previousCounter = counter.get();
                    if (threadNumber == 1) {
                        System.out.println(Thread.currentThread().getName() + ": " + counter.decrementAndGet());
                    }
                    if (threadNumber == 2) {
                        System.out.println(Thread.currentThread().getName() + ": " + previousCounter);
                    }
                    threadSleep(500);
                    lastThread = threadNumber;
                    notify();
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
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
