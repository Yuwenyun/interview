package com.owen.javaBasic.concurrent;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition object of ReentrantLock is used as wait()/notify() of Object
 *
 * ReentrantLock.lock()
 * 1. CAS AbstractQueuedSynchronizer.state = 1,
 * if success:
 *     Set AbstractOwnableSynchronizer = currentThread
 * else
 *     tryAcquire()
 *
 * 2. tryAcquire()
 * if CAS AQS.state = 0
 *     return
 * else if AbstractOwnableSynchronizer = currentThread
 *     AQS.state ++;
 * else
 *     addWaiter()
 *
 * 3. addWaiter()
 * Node currNode = new Node(), enqueue()
 * if currNode.pre == head && tryAcquire(), return
 * else LockSupport.park()
 *
 *
 * ReentrantLock.unlock()
 * Reset AQS.state = 0, AbstractOwnableSynchronizer = null
 * LockSupport.unpark(head.next)
 */
public class ReentrantLockDemo {

    private ReentrantLock lock;
    private Condition prodCondition;
    private Condition consCondition;

    private AtomicInteger count;
    private int CAP = 10;
    private int BOTTOM = 0;

    private Random random;
    private int RANGE = 5000;

    public ReentrantLockDemo(){
        this.lock = new ReentrantLock();
        this.prodCondition = this.lock.newCondition();
        this.consCondition = this.lock.newCondition();
        this.count = new AtomicInteger(0);
        this.random = new Random(5000);
    }

    public class Producer implements Runnable
    {
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(Math.abs(random.nextInt(RANGE)));
                    lock.lock();
                    System.out.println(Thread.currentThread().getName()
                            + " get the lock and is about to produce...");
                    if(count.get() == CAP){
                        System.out.println(Thread.currentThread().getName() + " count FULL, await...");
                        prodCondition.await();
                    }
                    System.out.println(Thread.currentThread().getName()
                            + ": " + count.incrementAndGet());
                    consCondition.signal();
                    lock.unlock();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public class Consumer implements Runnable
    {
        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(Math.abs(random.nextInt(RANGE)));
                    lock.lock();
                    System.out.println(Thread.currentThread().getName()
                            + " get the lock and is about to consume...");
                    if(count.get() == BOTTOM){
                        System.out.println(Thread.currentThread().getName() + " count EMPTY, await...");
                        consCondition.await();
                    }
                    System.out.println(Thread.currentThread().getName()
                            + ": " + count.decrementAndGet());
                    prodCondition.signal();
                    lock.unlock();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){

        ReentrantLockDemo lockDemo = new ReentrantLockDemo();
        for(int i = 0; i < 3; i++){
            Thread thread = new Thread(lockDemo.new Producer());
            thread.setName("Producer_" + i);
            thread.start();
        }

        for(int i = 0; i < 5; i++){
            Thread thread = new Thread(lockDemo.new Consumer());
            thread.setName("Consumer_" + i);
            thread.start();
        }
    }
}
