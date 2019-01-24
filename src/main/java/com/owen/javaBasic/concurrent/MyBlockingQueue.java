package com.owen.javaBasic.concurrent;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * take() and put() shall block the thread when satisfy the condition
 */
public class MyBlockingQueue<T> {

    private Object[] array;
    private ReentrantLock lock;
    private Condition notEmpty;
    private Condition notFull;
    private int head, tail, size;

    public MyBlockingQueue(int capacity){
        array = new Object[capacity];
        this.lock = new ReentrantLock();
        this.notEmpty = this.lock.newCondition();
        this.notFull = this.lock.newCondition();
    }

    public void put(T newElement) throws InterruptedException
    {
        this.lock.lock();
        // block the thread since the array is full
        while(size == this.array.length){
            System.out.println(Thread.currentThread().getName() + " blocked...");
            notEmpty.await();
        }
        // insert the element
        array[tail] = newElement;
        size++;
        tail++;
        // reset the tail to the head
        if(tail == this.array.length){
            tail = 0;
        }
        notFull.signal();
        this.lock.unlock();
    }

    public T get() throws InterruptedException
    {
        this.lock.lock();
        while(size == 0){
            System.out.println(Thread.currentThread().getName() + " blocked...");
            notFull.await();
        }
        T element = (T)this.array[head];
        this.array[head] = null;
        size--;
        head++;
        if(head == this.array.length){
            head = 0;
        }
        notEmpty.signal();
        this.lock.unlock();
        return element;
    }

    public class Producer<T> implements Runnable
    {
        private MyBlockingQueue<T> queue;
        private Random random;
        private T t;

        public Producer(MyBlockingQueue<T> queue, Random random, T t){
            this.queue = queue;
            this.random = random;
            this.t = t;
        }

        @Override
        public void run()
        {
            try {
                Thread.sleep(this.random.nextInt(5000));
                this.queue.put(t);
                System.out.println(Thread.currentThread().getName()
                        + " put: " + t.toString());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class Consumer<T> implements Runnable
    {
        private MyBlockingQueue<T> queue;
        private Random random;

        public Consumer(MyBlockingQueue<T> queue, Random random){
            this.queue = queue;
            this.random = random;
        }

        @Override
        public void run()
        {
            try {
                Thread.sleep(this.random.nextInt(5000));
                System.out.println(Thread.currentThread().getName()
                        + " get: " + this.queue.get());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(5);
        for(int i = 0; i < 10; i++){
            Thread thread = new Thread(queue.new Producer<Integer>(queue, new Random(), i));
            thread.setName("Producer-" + i);
            thread.start();
        }
        Thread.sleep(6000);
        for(int i = 0; i < 10; i++){
            Thread thread = new Thread(queue.new Consumer<Integer>(queue, new Random()));
            thread.setName("Consumer-" + i);
            thread.start();
        }
        Thread.sleep(2 * 5000);
    }
}
