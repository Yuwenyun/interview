package com.owen.designPattern;

public class Singleton_StaticInnerClass
{
    private Singleton_StaticInnerClass(){}
    private static class StaticInnerClass
    {
        private static Singleton_StaticInnerClass singleton = new Singleton_StaticInnerClass();
    }

    public static Singleton_StaticInnerClass getSingleton(){
        return StaticInnerClass.singleton;
    }
}
