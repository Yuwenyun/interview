package com.owen.javaBasic.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch is used to wait for all the threads finishing their tasks
 *
 * This demo verifies different resources whether they are ready
 */
public class CountDownLatchDemo {

    private CountDownLatch latch;
    private Random random;
    private int RANGE = 5000;

    public CountDownLatchDemo(int count){
        this.latch = new CountDownLatch(count);
        this.random = new Random(5000);
    }

    public static void main(String[] args) throws InterruptedException
    {
        CountDownLatchDemo app = new CountDownLatchDemo(3);

        Thread dbVer = new Thread(app.new DatabaseVerification());
        dbVer.setName("Database-Verification");
        Thread httpVer = new Thread(app.new HttpCallVerification());
        httpVer.setName("Http-Verification");
        Thread redisVer = new Thread(app.new RedisVerification());
        redisVer.setName("Redis-Verification");

        dbVer.start();
        httpVer.start();
        redisVer.start();

        app.latch.await();
        System.out.println("All verification finished...");
    }

    public class DatabaseVerification implements Runnable
    {
        @Override
        public void run()
        {
            try {
                System.out.println(Thread.currentThread().getName() + " verifying...");
                Thread.sleep(random.nextInt(RANGE));
                System.out.println(Thread.currentThread().getName() + " finished");
                latch.countDown();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class HttpCallVerification implements Runnable
    {
        @Override
        public void run()
        {
            try {
                System.out.println(Thread.currentThread().getName() + " verifying...");
                Thread.sleep(random.nextInt(RANGE));
                System.out.println(Thread.currentThread().getName() + " finished");
                latch.countDown();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class RedisVerification implements Runnable
    {
        @Override
        public void run()
        {
            try {
                System.out.println(Thread.currentThread().getName() + " verifying...");
                Thread.sleep(random.nextInt(RANGE));
                System.out.println(Thread.currentThread().getName() + " finished");
                latch.countDown();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
