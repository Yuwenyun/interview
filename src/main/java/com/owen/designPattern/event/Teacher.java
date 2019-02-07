package com.owen.designPattern.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.HashSet;
import java.util.Set;

public class Teacher {

    private String name;
    private Set<EventListener> listeners;
    public Teacher(String name){
        this.name = name;
        this.listeners = new HashSet<>();
    }

    /**
     * create event of homework, only listeners for homework can get notified
     * @param homework
     */
    public void setHomework(String homework){
        EventObject eventObject = new EventObject(this);
        for(EventListener listener : listeners){
            if(listener instanceof HomeworkEventListener){
                ((HomeworkEventListener) listener).getHomework(eventObject, homework);
            }
        }
    }

    /**
     * create event of house keeping
     * @param houseKeeping
     */
    public void setHouseKeeping(String houseKeeping){
        EventObject eventObject = new EventObject(this);
        for(EventListener listener : listeners){
            if(listener instanceof HouseKeepingEventListener){
                ((HouseKeepingEventListener) listener).getHouseKeepingTask(eventObject, houseKeeping);
            }
        }
    }

    public void addListener(EventListener listener){
        this.listeners.add(listener);
    }

    public String getName(){
        return this.name;
    }
}
