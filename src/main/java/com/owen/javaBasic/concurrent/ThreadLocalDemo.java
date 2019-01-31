package com.owen.javaBasic.concurrent;

import com.owen.common.Student;

import java.util.Random;

/**
 * Shared variable for threads are not thread-safe if not locked in multi-thread env.
 * ThreadLocal, however, is thread safe when creating same initial value
 *
 * ThreadLocal has static inner class ThreadLocalMap and Thread has attribute of type ThreadLocalMap.
 * The ThreadLocalMap is an array of Entry objects which extends WeekReference. The ThreadLocalMap.Entry class's
 * key is WeekReference<ThreadLocal> while the value is of type Object
 *
 * Since key of Entry is WeekReference, whenever the strong reference broke, the ThreadLocal object won't survive GC,
 * leaving plenty of value with key null in Entry array. We shall call ThreadLocal.remove to clean up the array
 */
public class ThreadLocalDemo {

    // if the shared balance variable is not ThreadLocal, in multi-thread env, results
    // is unpredictable
    private ThreadLocal<Student> tlStudent;
    private Student student;
    private Random random;

    public ThreadLocalDemo()
    {
        this.student = new Student();
//        this.student.setAge(25);
        this.random = new Random();

        this.tlStudent = new ThreadLocal<Student>(){
            @Override
            protected Student initialValue() {
                // the return value is the key to answer whether the two objects
                // of the two threads that hold are the same, each thread hold a
                // copy of value with same ThreadLocal as key because they(threads)
                // have the same initial value
//                return student;
                return new Student();
            }
        };
    }

    public static void main(String[] args) throws InterruptedException
    {
        ThreadLocalDemo app = new ThreadLocalDemo();

        // no lock/synchronization for shared student.age
        // making the result unpredictable
//        Thread counter1 = new Thread(new AgeCounter(app.student));
//        Thread counter2 = new Thread(new AgeCounter(app.student));
//        counter1.start();
//        counter2.start();
//        Thread.sleep(10000);

        Thread counter3 = new Thread(new ThreadLocalAgeCounter(app.tlStudent));
        Thread counter4 = new Thread(new ThreadLocalAgeCounter(app.tlStudent));
        counter3.start();
        counter4.start();
        Thread.sleep(10000);
    }
}

class AgeCounter implements Runnable
{
    private Student student;
    public AgeCounter(Student student){
        this.student = student;
    }

    @Override
    public void run()
    {
        // need i to be larger to see the unsafe result
        for(int i = 0; i < 200; i++){
            this.student.setAge(this.student.getAge() + 1);
            System.out.println(Thread.currentThread().getName()
                    + ": " + this.student.getAge());
            try
            {
                Thread.sleep(i);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadLocalAgeCounter implements Runnable
{
    private ThreadLocal<Student> tlStudent;
    public ThreadLocalAgeCounter(ThreadLocal<Student> tlStudent){
        this.tlStudent = tlStudent;
    }

    @Override
    public void run()
    {
        for(int i = 0; i < 200; i++){
            this.tlStudent.get().setAge(this.tlStudent.get().getAge() + 1);
            System.out.println(Thread.currentThread().getName()
                    + ": " + this.tlStudent.get().getAge());
            try
            {
                Thread.sleep(i);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}