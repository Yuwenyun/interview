package com.owen.javaBasic.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledExecutorService is using DelayQueue to store the tasks, when calling scheduleAtFixedRate():
 * 1. the task will be added to DelayQueue
 */
public class ScheduleExecutorServiceDemo {

    public static void main(String[] args){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        service.scheduleAtFixedRate(new MyRunnable(), 0, 100, TimeUnit.MILLISECONDS);
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run()
    {
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("Hello Schedule...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
