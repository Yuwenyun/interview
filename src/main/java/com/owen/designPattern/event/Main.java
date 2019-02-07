package com.owen.designPattern.event;

/**
 * 3 types roles in event model
 * 1. listener: java.util.EventListener, mark as a listener
 * 2. event source: source of the event
 * 3. event object: an event object that holds an attribute of event source.
 */
public class Main {

    public static void main(String[] args)
    {
        // construct the relationship
        // EventSource -> EventObject -> EventListener
        // Teacher -> EventObject -> Student/ClassLeader
        Teacher teacher = new Teacher("Alice");
        Student student1 = new Student("Owen");
        Student student2 = new Student("Jenney");
        ClassLeader leader1 = new ClassLeader("Ricy");
        teacher.addListener(student1);
        teacher.addListener(student2);
        teacher.addListener(leader1);

        // start an event
        teacher.setHomework("Read 5 books");
        teacher.setHouseKeeping("Cleaning up the classroom");
    }
}
