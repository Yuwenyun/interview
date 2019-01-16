package com.owen.jvm.jstack;

/**
 * state of thread: NEW, RUNNABLE, RUNNING, BLOCKED, DEAD
 * state of thread in JVM: RUNNABLE, BLOCKED, WAITING, TIMED_WAITING
 */
public class JVMStateDemo {

    public static void main(String[] args)
    {
        if(args != null){
            switch (args.length){
                case 1:
                    runnable();
                    break;
                case 2:
                    blocked();
                    break;
                case 3:
                    timedWaiting();
                    break;
                case 4:
                    waiting();
                    break;
                default:
                    break;
            }
        }
    }

    // "main" #1 prio=5 os_prio=31 tid=0x00007fd4ff804000 nid=0x2503 runnable [0x0000700009727000]
    //   java.lang.Thread.State: RUNNABLE
    //        at com.owen.jvm.jstack.JVMStateDemo.runnable(JVMStateDemo.java:34)
    //        at com.owen.jvm.jstack.JVMStateDemo.main(JVMStateDemo.java:14)
    private static void runnable(){
        int i = 0;
        while(true){
            i++;
        }
    }

    // "Thread-0" #10 prio=5 os_prio=31 tid=0x00007fcfa00bc800 nid=0x4c03 waiting on condition [0x0000700004f82000]
    //   java.lang.Thread.State: TIMED_WAITING (sleeping)
    //        at java.lang.Thread.sleep(Native Method)
    //        at com.owen.jvm.jstack.JVMStateDemo$1.run(JVMStateDemo.java:49)
    //        - locked <0x00000007956c7838> (a java.lang.Object)
    private static void blocked(){
        Object obj = new Object();
        new Thread(){
            public void run(){
                synchronized (obj){
                    try {
                        // won't release the lock
                        Thread.sleep(1000 * 60);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        try{
            Thread.sleep(100);
        } catch (Exception e){
            e.printStackTrace();
        }

        // "main" #1 prio=5 os_prio=31 tid=0x00007fcfa0008800 nid=0x2703 waiting for monitor entry [0x0000700004055000]
        //   java.lang.Thread.State: BLOCKED (on object monitor)
        //        at com.owen.jvm.jstack.JVMStateDemo.blocked(JVMStateDemo.java:64)
        //        - waiting to lock <0x00000007956c7838> (a java.lang.Object)
        //        at com.owen.jvm.jstack.JVMStateDemo.main(JVMStateDemo.java:17)
        synchronized (obj){
            System.out.println("Blocked");
        }
    }

    // "main" #1 prio=5 os_prio=31 tid=0x00007f81ff805800 nid=0x2603 in Object.wait() [0x000070000c4dd000]
    //   java.lang.Thread.State: TIMED_WAITING (on object monitor)
    //        at java.lang.Object.wait(Native Method)
    //        - waiting on <0x00000007956c7888> (a java.lang.Object)
    //        at com.owen.jvm.jstack.JVMStateDemo.timedWaiting(JVMStateDemo.java:82)
    //        - locked <0x00000007956c7888> (a java.lang.Object)
    //        at com.owen.jvm.jstack.JVMStateDemo.main(JVMStateDemo.java:20)
    private static void timedWaiting(){
        Object obj = new Object();
        synchronized (obj){
            try {
                // release the lock
                obj.wait(1000 * 60);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static void waiting(){
        Object obj = new Object();
        synchronized (obj){
            try {
                obj.wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
