package com.owen.designPattern.event;

import java.util.EventObject;

public class Student implements HomeworkEventListener
{
    private String name;
    public Student(String name){
        this.name = name;
    }

    @Override
    public void getHomework(EventObject object, Object arg) {
        System.out.println(this.name + " get notified from " +
                ((Teacher)object.getSource()).getName() + " for homework: " + arg.toString());
    }
}
