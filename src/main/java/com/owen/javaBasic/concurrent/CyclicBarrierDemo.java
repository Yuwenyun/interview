package com.owen.javaBasic.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Difference between CountDownLatch and CyclicBarrier
 * 1. CountDownLatch: count down from n to 0 and wake up all the threads while
 *    CyclicBarrier plus from 0 to n and wake up all the threads
 * 2. CyclicBarrier can be reused
 */
public class CyclicBarrierDemo {

    private CyclicBarrier barrier;
    private Random random;
    private int RANGE = 5000;

    public void setBarrier(CyclicBarrier barrier){
        this.barrier = barrier;
    }

    public CyclicBarrierDemo(){
        this.random = new Random();
    }

    public static void main(String[] args) throws InterruptedException
    {
        CyclicBarrierDemo app = new CyclicBarrierDemo();
        // provide the barrier action which will run after all threads awaited
        TourGuide guide = app.new TourGuide();
        app.setBarrier(new CyclicBarrier(10, guide));

        for(int i = 0; i < 10; i++){
            Thread thread = new Thread(app.new Traveller());
            thread.setName("Thread-" + i);
            thread.start();
        }

        Thread.sleep(10000);
    }

    public class Traveller implements Runnable
    {
        @Override
        public void run()
        {
            try {
                Thread.sleep(random.nextInt(RANGE));
                System.out.println(Thread.currentThread().getName() + " arrived...");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " start travelling...");
            }
            catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public class TourGuide implements Runnable
    {
        @Override
        public void run()
        {
            try {
                System.out.println("Everyone arrived, give ticket...");
                Thread.sleep(random.nextInt(RANGE));
                System.out.println("Now ready...");
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
