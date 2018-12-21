package com.owen.algorithm.offer;

/**
 * n stairs, m stairs each time(0 < m <= n)
 *
 * solution: let f(n) be the number of methods for n stairs
 * a. if we jump 1 stair to reach stair n, there are f(n - 1) methods
 * b. if we jump 2 stair to reach stair n, there are f(n - 2) methods
 * ...
 * so: f(n) = f(n - 1) + f(n - 2) + ... + f(1) + 1,
 * note: the extra 1 at the end is for jump n stairs to reach stair n
 */
public class JumpStairs_A
{
    public static void main(String[] args)
    {
        System.out.println(numberOfMethods(1));
        System.out.println(numberOfMethods(2));
        System.out.println(numberOfMethods(3));
        System.out.println(numberOfMethods(4));
        System.out.println(numberOfMethods(5));
    }

    private static int numberOfMethods(int stairs){
        if(stairs <= 0){ return 0; }
        if(stairs == 1){ return 1; }
        int result = 0;
        for(int i = stairs; i > 0; i--){
            result += numberOfMethods(i - 1);
        }
        return result + 1;
    }
}
