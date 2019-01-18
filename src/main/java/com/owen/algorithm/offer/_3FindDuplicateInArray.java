package com.owen.algorithm.offer;

/**
 * numbers in array of length n are ranging from 0 to n - 1, find the duplicate
 * 1. sort first, then find from head to tail;
 * 2. use HashMap;
 * 3. move value m on position i to position m till value on position m is
 *    the same as value on position i;
 */
public class _3FindDuplicateInArray
{
    public static void main(String[] args)
    {
        int[] array = new int[]{4, 1, 3, 9, 8, 8, 7, 2, 5, 0, 6};
        System.out.println(findDuplicateNumBinaryCount(array, 0, array.length - 1));
    }

    // solution 3
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

    // don't change original array when finding the duplication,
    // provide extra array
    private static int findDuplicateNumWithoutChangeOrig(int[] array){
        if(array == null || array.length == 0){ return -1; }

        int[] array_1 = new int[array.length];
        boolean initializedZero = false;
        for(int i = 0; i < array.length; i++){
            if(array[i] != 0){
                if(array_1[array[i]] != array[i]){
                    array_1[array[i]] = array[i];
                }
                else{
                    return array[i];
                }
            }
            else{
                if(initializedZero){
                    return 0;
                }
                else{
                    initializedZero = true;
                }
            }
        }
        return -1;
    }

    // binary count
    private static int findDuplicateNumBinaryCount(int[] array, int start, int end) {
        if(array == null || array.length == 0){ return -1; }

        int count = 0, middle = (start + end) / 2;
        for(int i = 0; i < array.length; i++){
            if(array[i] >= start && array[i] <= middle){
                count++;
            }
        }

        if(start == end){
            return start;
        }

        if(count == middle - start + 1){
            return findDuplicateNumBinaryCount(array, middle + 1, end);
        }
        else{
            return findDuplicateNumBinaryCount(array, start, middle);
        }
    }

    private static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
