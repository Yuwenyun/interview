package com.owen.javaBasic;

/**
 * what is the print out?
 *
 * 1. apply for space for all the static members in jvm method area(方法区)
 * 2. set initial value for static members
 * 3. call static block
 * 4. apply for space for all the instance members in java heap
 * 5. set intial value for instance members
 * 6. call instance block, constructor
 */
public class LoadClass {

    public static int k = 0;
    public static LoadClass test1 = new LoadClass("test1");
    public static LoadClass test2 = new LoadClass("test2");
    public static int i = print("i");
    public static int n = 99;
    public int j = print("j");

    static {
        print("static block");
    }

    public LoadClass(String str){
        System.out.println((++k) + ":" + str + ",i = " + i + ",n = " + n);
        ++i;
        ++n;
    }

    {
        print("constructor block");
    }

    public static int print(String str)
    {
        System.out.println((++k) + ":" + str + ",i = " + i + ",n = " + n);
        ++n;
        return ++i;
    }

    public static void main(String[] args){
        LoadClass loadClass = new LoadClass("init");
    }
}

/**
 * 1. apply space in method area for k=0, test1=null, test2=null, i=0, n=0
 * 2. set initial value for static
 *   a. k=0
 *   b. test1 = new LoadClass()
 *     b1. apply for space in heap for instance members, j=print("j"),
 *         print: "1:j,i = 0,n = 0"
 *         thus: k = 1, n = 1, i = 1, j = 1
 *     b2. call the instance block
 *         print: "2:constructor block,i = 1,n = 1
 *         thus: k = 2, n = 2, i = 2, j = 1
 *     b3. call the constructor
 *         print: "3:test1,i = 2,n = 2
 *         thus: k = 3, i = 3, n = 3, j = 1
 *     ...
 * 3. final print out:
 *   "1:j,i = 0,n = 0"
 *   "2:constructor block,i = 1,n = 1"
 *   "3:test1,i = 2,n = 2"
 *   "4:j,i = 3,n = 3"
 *   "5:constructor block,i = 4,n = 4"
 *   "6:test2,i = 5,n = 5"
 *   "7:i,i = 6,n = 6"
 *   "8:static block,i = 7,n = 99"
 *   "9:j,i = 8,n = 100"
 *   "10:constructor block,i = 9,n = 101"
 *   "11:init,i = 10,n = 102"
 */