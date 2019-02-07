package com.owen.designPattern.event;

import java.util.EventObject;

public class ClassLeader implements HouseKeepingEventListener
{
    private String name;
    public ClassLeader(String name){
        this.name = name;
    }

    @Override
    public void getHouseKeepingTask(EventObject eventObject, Object arg) {
        System.out.println(this.name + " get notified from " +
                ((Teacher)eventObject.getSource()).getName() + " for house keeping task: " + arg.toString());
    }
}
