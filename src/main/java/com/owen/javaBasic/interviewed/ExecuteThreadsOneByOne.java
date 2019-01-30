package com.owen.javaBasic.interviewed;

/**
 * Two means to execute threads one by one
 * 1. call t.join() one by one. The join() method block the caller thread by waiting(Object.wait()),
 *    the caller thread need to obtain the lock of t thread before enter the join() method
 *
 * 2. use threadpool
 */
public class ExecuteThreadsOneByOne {

    public static void main(String[] args) throws InterruptedException {
        ExecuteThreadsOneByOne app = new ExecuteThreadsOneByOne();

        Thread thread1 = new Thread(app.new DemoThread("Thread1"));
        Thread thread2 = new Thread(app.new DemoThread("Thread2"));
        Thread thread3 = new Thread(app.new DemoThread("Thread3"));

        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
    }

    public class DemoThread implements Runnable
    {
        private String demo;
        public DemoThread(String demo){
            this.demo = demo;
        }

        @Override
        public void run() {
            System.out.println(this.demo);
        }
    }
}
