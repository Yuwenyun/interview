package com.owen.javaBasic;

public class MyEnumSet<E extends Enum<E>> {

    private long container;

    public void add(E e){
        if(e == null){
            throw new IllegalArgumentException("Null enum element not allowed");
        }
        container = container | (1 << e.ordinal());
    }

    public boolean hasElement(E e){
        if(e == null){
            throw new IllegalArgumentException("Null enum element not allowed");
        }
        return (container & (1 << e.ordinal())) == (1 << e.ordinal());
    }

    public void remove(E e){
        if(e == null){
            throw new IllegalArgumentException("Null enum element not allowed");
        }
        container = container & (1 << e.ordinal());
    }

    public static void main(String[] args){
        MyEnumSet<Number> set = new MyEnumSet<>();
        System.out.println(set.hasElement(Number.THREE));
        set.add(Number.THREE);
        System.out.println(set.hasElement(Number.THREE));
    }
}

enum Number{
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4);

    private String numString;
    private int value;
    Number(String numString, int value){
        this.numString = numString;
        value = value;
    }

    public String getNumString() {
        return numString;
    }

    public int getValue() {
        return value;
    }
}
