package com.owen.javaBasic.concurrent;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Semaphore is used to control the number of threads that occupy
 * the resource at the same time.
 */
public class SemaphoreDemo {

    private Semaphore washRoom;
    private Random random;
    private int RANGE = 5000;

    public SemaphoreDemo(int count){
        this.washRoom = new Semaphore(count);
        this.random = new Random();
    }

    public static void main(String[] args) throws InterruptedException
    {
        SemaphoreDemo app = new SemaphoreDemo(5);
        for(int i = 0; i < 15; i++){
            Thread thread = new Thread(app.new People());
            thread.setName("Thread-" + i);
            thread.start();
        }
        Thread.sleep(2 * app.RANGE);
    }

    public class People implements Runnable
    {
        @Override
        public void run()
        {
            try {
                washRoom.acquire();
                System.out.println(Thread.currentThread().getName()
                        + " occupied a wash room...");
                Thread.sleep(random.nextInt(RANGE));
                System.out.println(Thread.currentThread().getName()
                        + " released a wash room...");
                washRoom.release();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
