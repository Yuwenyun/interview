package com.owen.javaBasic;

import java.util.Random;

public class RandomGenerator
{
    public static void main(String[] args)
    {
        // same seeds generate same random number
        Random random1 = new Random(123456);
        Random random2 = new Random(123456);
        System.out.println(random1.nextInt());
        System.out.println(random2.nextInt());

        // normally we can use current time milli-seconds as seed
        Random random = new Random(System.currentTimeMillis());
        System.out.println(random.nextInt());

        // produce ranged number
        random = new Random(45);
        System.out.println(random.nextInt(45));

        // produce ranged number 5 - 10
        random = new Random();
        int a = 10, b = 5;
        for(int i = 0; i < 10; i++)
        {
            System.out.println(random.nextInt(a - b + 1) + b);
        }
    }
}
