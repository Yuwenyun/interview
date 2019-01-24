package com.owen.javaBasic.concurrent;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock, multi-thread read is allowed, while read lock locked,
 * write lock is not allowed, and while write lock locked, read/write lock is not allowed
 */
public class ReentrantReadWriteLockDemo {

    private AtomicInteger count;

    private ReentrantReadWriteLock lock;
    private Random random;
    private int RANGE = 5000;

    public ReentrantReadWriteLockDemo(){
        this.lock = new ReentrantReadWriteLock();
        this.random = new Random();
        this.count = new AtomicInteger(0);
    }

    public static void main(String[] args) throws InterruptedException
    {
        ReentrantReadWriteLockDemo app = new ReentrantReadWriteLockDemo();
        for(int i = 0; i < 3; i++) {
            Thread readerThread = new Thread(app.new IntegerReader());
            readerThread.setName("Reader-" + i);
            readerThread.start();
        }
        for(int i = 0; i < 3; i++) {
            Thread writerThread = new Thread(app.new IntegerWriter());
            writerThread.setName("Writer");
            writerThread.start();
        }

        Thread.sleep(2 * app.RANGE);
    }

    public class IntegerReader implements Runnable
    {
        @Override
        public void run() {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName()
                    + " get read lock: " + count.get());
            try {
                Thread.sleep(random.nextInt(RANGE));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    + " release read lock: " + count.get());
            lock.readLock().unlock();
        }
    }

    public class IntegerWriter implements Runnable
    {
        @Override
        public void run() {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName()
                    + " get write lock: " + count.incrementAndGet());
            try {
                Thread.sleep(random.nextInt(RANGE));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    + " release write lock: " + count.get());
            lock.writeLock().unlock();
        }
    }
}
