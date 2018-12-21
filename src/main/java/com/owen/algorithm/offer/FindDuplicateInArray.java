package com.owen.algorithm.offer;

/**
 * numbers in array of length n are ranging from 0 to n - 1, find the duplicate
 * 1. sort first, then find from head to tail;
 * 2. use HashMap;
 * 3. move value m on position i to position m till value on position m is
 *    the same as value on position i;
 */
public class FindDuplicateInArray
{
    public static void main(String[] args)
    {
        int[] array = new int[]{4, 1, 3, 9, 8, 8, 7, 2, 5, 0, 6};
        System.out.println(findDuplicateNum(array));
    }

    private static int findDuplicateNum(int[] array){
        if(array == null || array.length == 0){ return -1; }
        int index = 0;
        while(index < array.length){
            if(array[index] != index){
                if(array[index] == array[array[index]]){
                    return array[index];
                }
                swap(array, index, array[index]);
            }
            else{
                index++;
            }
        }
        return -1;
    }

    private static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
