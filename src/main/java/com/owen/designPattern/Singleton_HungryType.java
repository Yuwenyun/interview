package com.owen.designPattern;

public class Singleton_HungryType
{
    private Singleton_HungryType(){}
    private static Singleton_HungryType singleton = new Singleton_HungryType();

    public static Singleton_HungryType getSingleton()
    {
        return singleton;
    }
}
