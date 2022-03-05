package com;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerBlockingQueueMain {

    public static void main(String[] args) throws Exception {

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);


       // Queue

        final Runnable producer = () -> {
            while (true) {
                try {
                    int value = new Random().nextInt(1000);
                    queue.put(value);
                    System.out.println("Thread ID: " + Thread.currentThread().getName() + " Produced Value : " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(producer, "Producer-1").start();
        new Thread(producer, "Producer-2").start();

        final Runnable consumer = () -> {
            while (true) {
                try {
                    int value = queue.take();
                    System.out.println("Thread ID: " + Thread.currentThread().getName() + " Consumed Value : " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(consumer, "Consumer-1").start();
        new Thread(consumer, "Consumer-1").start();

        Thread.sleep(1000);
    }

}
