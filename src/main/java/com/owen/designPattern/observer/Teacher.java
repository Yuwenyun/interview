package com.owen.designPattern.observer;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Teacher extends Observable {

    private String name;
    private List<String> homeworks;
    public Teacher(String name){
        this.name = name;
        this.homeworks = new ArrayList<>();
    }

    public String getName(){
        return this.name;
    }

    public void setHomework(String homework){
        this.homeworks.add(homework);
        setChanged();
        notifyObservers(homework);
    }
}
