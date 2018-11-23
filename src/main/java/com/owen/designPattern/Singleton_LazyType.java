package com.owen.designPattern;

public class Singleton_LazyType
{
    private Singleton_LazyType(){}
    private static Singleton_LazyType singleton;

    public static Singleton_LazyType getSingleton()
    {
        if(singleton == null){
            singleton = new Singleton_LazyType();
        }
        return singleton;
    }
}
