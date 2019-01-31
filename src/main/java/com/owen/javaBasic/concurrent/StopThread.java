package com.owen.javaBasic.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * When stopping a thread,
 * 1. if the thread is blocked by calling sleep, call its interrupt() method to mark interrupt flag as True,
 *    at the same time, an InterruptedException will be thrown
 * 2. if the thread is blocked on synchronized method waiting for lock, call its interrupt() method won't
 *    interrupt the thread at all.
 * 2. if the thread is running, call isInterrupted() method to get the value of interrupt flag
 *
 * note:
 *   1. interrupt() is instance method of Thread, only set the interrupt flag to true,
 *      when thread is blocked by wait()/sleep()/join(), interrupt flag will be set to false and
 *      throws an InterruptedException
 *   2. isInterrupted() is instance method of Thread, only return the value of interrupted flag
 *   3. interrupted() is static method of Thread, return the value of interrupted flag and reset to
 *      false if necessary.
 */
public class StopThread {

    public static void main(String[] args) throws InterruptedException
    {
        StopThread app = new StopThread();

        // interrupt the block thread
        Thread blockingThread = new Thread(app.new BlockingThread());
        blockingThread.start();
        Thread.sleep(2000);
        // interrupt() won't interrupt the thread, instead it set the interrupt flag to true
        blockingThread.interrupt();

        // interrupt the running thread
        Thread runningThread = new Thread(app.new RunningThread());
        runningThread.start();
        Thread.sleep(2000);
        runningThread.interrupt();

        Thread.sleep(3000);
    }

    public class BlockingThread implements Runnable
    {
        @Override
        public void run() {
            try {
                while(true){
                    // sleep(), wait() etc. methods will block the thread,
                    // when interrupt flag of the thread is set to true, those method
                    // will set it back to false immediately and throw an InterruptedException
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class RunningThread implements Runnable
    {
        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public void run() {
            // isInterrupted() won't reset the interrupt flag
            while(!Thread.currentThread().isInterrupted()){
                count.incrementAndGet();
            }
            System.out.println("Running thread interrupted with interrupt flag: "
                    + Thread.currentThread().isInterrupted());
            // interrupted() will reset the interrupt flag
            System.out.println("Interrupt flag: " + Thread.interrupted());
            System.out.println("Interrupted() will reset interrupt flag to: "
                    + Thread.currentThread().isInterrupted());
        }
    }
}
