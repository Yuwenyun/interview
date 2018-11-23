package com.owen.jvm.jstack;

import java.util.concurrent.*;

public class ThreadHeavilyUsingCPU
{
    private static Executor pool = new ThreadPoolExecutor(5, 5, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    private static Object obj = new Object();

    public static void main(String[] args)
    {
        ThreadHeavilyUsingCPU app = new ThreadHeavilyUsingCPU();
        Task task1 = app.new Task();
        Task task2 = app.new Task();
        pool.execute(task1);
        pool.execute(task2);
    }

    class Task implements Runnable
    {
        public void run()
        {
            synchronized (obj){
                calculate();
            }
        }
        private void calculate(){
            int i = 0;
            while(true){
                i++;
            }
        }
    }
}
