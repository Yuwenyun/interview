package com.owen.javaBasic.interviewed;

public class StartThreadTwice {

    public static void main(String[] args) throws InterruptedException
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("starting task ...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end task ...");
            }
        });
        thread.start();
        thread.join();

        // thread state from NEW -> RUNNABLE -> RUNNING -> DEAD
        // start again won't make it to RUNNABLE
        thread.start();
    }
}
