package com.owen.algorithm.offer;

/**
 * n stairs, 1 or 2 stairs each step
 *
 * solution: let f(n) be the number of methods to reach stair n
 * 1. if we jump 1 stair, there will be f(n - 1) methods for left stairs
 * 2. if we jump 2 stairs, there will be f(n - 2) methods for left stairs
 * so f(n) = f(n - 1) + f(n - 2)
 */
public class JumpStairs_B
{
    public static void main(String[] args)
    {
        System.out.println(numberOfMethods(5));
        System.out.println(numberOfMethods(4));
        System.out.println(numberOfMethods(3));
        System.out.println(numberOfMethods(2));
        System.out.println(numberOfMethods(1));
    }

    private static int numberOfMethods(int stairs){
        if(stairs <= 0){ return 0; }
        if(stairs == 1){ return 1; }
        if(stairs == 2){ return 2; }
        return numberOfMethods(stairs - 1) + numberOfMethods(stairs - 2);
    }
}
