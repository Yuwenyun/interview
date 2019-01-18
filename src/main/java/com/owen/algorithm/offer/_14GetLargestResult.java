package com.owen.algorithm.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * 长度n的绳子剪成m段，使得所有段的长度乘积最大
 * 要使得m(n - m)最大，则f(m)和f(n - m)应该各自最大，以此细分下去
 */
public class _14GetLargestResult {

    public static void main(String[] args){
        System.out.println(getResult(8));
    }

    // 动态规划
    private static int getResult(int n){
        if(n < 2) return 0;
        if(n == 2) return 1;
        if(n == 3) return 2;

        // store the max result
        int[] products = new int[n + 1];
        products[0] = 0; products[1] = 1; products[2] = 2; products[3] = 3;

        int max = 0;
        for(int i = 4; i <= n; i++){
            max = 0;
            for(int j = 1; j <= i / 2; j++){
                int product = products[j] * products[i - j];
                if(max < product){
                    max = product;
                }
                products[i] = max;
            }
        }
        return products[n];
    }

    // n件物品编号从1~n，第i件物品价值为vi，重量为wi，给定能承受重量为w的背包，怎么装价值最大
//    private static int[] getLargestValue(int[] weights, int[] values, int bagMaxWeight) {
//        if(weights == null || values == null || weights.length != values.length || weights.length == 0){
//            return null;
//        }
//
////        List<Integer> indexes = new ArrayList<>();
//        int[] maxValues = new int[bagMaxWeight];
//        for(int i = 1; i <= bagMaxWeight; i++){
//            int max = 0, value = 0, weight = 0;
//            for(int j = 1; j <= weights.length; j++){
//                int maxValue = maxValues[j - 1] + weights[j];
//                if(max < maxValue){
//                    max = maxValue;
//                }
//                maxValues[j] = max;
//            }
//        }
//    }
}
