package com.owen.designPattern.observer;

import java.util.EventListener;

/**
 * Observer(观察者): an interface
 * Observable(被观察者): a class
 *
 * 1. call below methods when start a task for observer to get notified
 *    setChanged();
 *    notifyObservers(task);
 * 2. the Observable holds a list of Observer to call each update() as notification
 */
public class Main {

    public static void main(String[] args)
    {
        // construct the structure
        Teacher teacher = new Teacher("Alice");
        Student student1 = new Student("Owen");
        Student student2 = new Student("Jenney");
        // add the observer to observable
        teacher.addObserver(student1);
        teacher.addObserver(student2);

        // start a task
        teacher.setHomework("Read at least 5 books");
    }
}
