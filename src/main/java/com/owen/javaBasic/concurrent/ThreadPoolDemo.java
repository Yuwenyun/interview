package com.owen.javaBasic.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor -> AbstractExecutorService -> ExecutorService -> Executor
 * 1. core args
 *   a. corePoolSize: new task will be put to blocking queue when thread number reaches corePoolSize
 *   b. maximumPoolSize: the biggest number of threads that allowed in pool
 *   c. keepAliveTime: how long shall a thread survive when idle
 *   d. workQueue: blocking queue for task
 *   e. threadFactory: used to create new thread
 *   f. handler: abortion policy handler
 * 2. execute(Runnable t) is in Executor while submit(Runnable t) in ExecutorService, the latter can
 *    return a result
 * 3. state of thread pool: RUNNING, SHUTDOWN, STOP, TIDYING, TERMINATED
 *    state of thread: NEW, RUNNABLE, RUNNING, BLOCKED, DEAD
 *    state of thread in JVM: RUNNABLE, BLOCKED, WAITING, TIMED_WAITING
 *
 * 4. when call shutdown(), thread pool state is SHUTDOWN, the pool won't accept new task and will wait
 *    for running task to finish
 *    when call shutdownNow(), thread pool state is STOP, the pool won't accept new task and will try to
 *    stop running tasks.
 *
 * 5. theory of thread pool
 *   a. number of threads in pool less than corePoolSize, create new thread when have new task
 *   b. number bigger than corePoolSize, put new task to blocking queue
 *   c. blocking queue full, create new thread for new task till reach maximumPoolSize
 *   d. number reaches maximumPoolSize, discard task according to Abortion Policy
 *
 * 6. Abortion Policy:
 *   a. ThreadPoolExecutor.AbortPolicy: discard task and throw RejectedExecutionException (default)
 *   b. ThreadPoolExecutor.DiscardPolicy: discard task without exception
 *   c. ThreadPoolExecutor.DiscardOldestPolicy: discard oldest task in the queue
 *   d. ThreadPoolExecutor.CallerRunsPolicy: run by caller thread
 */
public class ThreadPoolDemo {

    public static void main(String[] args)
    {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));

        for(int i = 0; i < 20; i++)
        {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("pool size：" + executor.getPoolSize() +
                    ", queue size：" + executor.getQueue().size() +
                    ", completed task size：" + executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}


class MyTask implements Runnable
{
    private int taskNum;

    public MyTask(int num) {
        this.taskNum = num;
    }

    @Override
    public void run()
    {
        System.out.println("running task " + taskNum);
        try {
            Thread.currentThread().sleep(4000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task " + taskNum + " finished...");
    }
}
