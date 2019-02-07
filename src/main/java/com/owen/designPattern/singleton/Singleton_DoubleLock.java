package com.owen.designPattern.singleton;

public class Singleton_DoubleLock
{
    private Singleton_DoubleLock(){}
    private static Singleton_DoubleLock singleton;

    public static Singleton_DoubleLock getSingleton(){
        if(singleton == null){
            synchronized (Singleton_DoubleLock.class){
                if(singleton == null){
                    singleton = new Singleton_DoubleLock();
                }
            }
        }
        return singleton;
    }
}
