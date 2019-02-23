package com.owen.algorithm.nomura;

public class _2FindSmallestPositiveInteger {

    public static void main(String[] args){
        System.out.println(findSmallestPositiveIntegerNotInArray(new int[]{1, 3, 6, 4, 1, 2}));
        System.out.println(findSmallestPositiveIntegerNotInArray(new int[]{1, 2, 3}));
        System.out.println(findSmallestPositiveIntegerNotInArray(new int[]{-1, -3}));
    }

    private static int findSmallestPositiveIntegerNotInArray(int[] array){
        if(array == null || array.length == 0){
            return 1;
        }

        int left = 0, right = array.length;
        while(left < right){
            if(array[left] == left + 1){
                left++;
            }
            else if(array[left] <= left || array[left] > right || array[array[left] - 1] == array[left]){
                array[left] = array[--right];
            }
            else{
                swap(array, left, array[left] - 1);
            }
        }
        return left + 1;
    }

    private static void swap(int[] array, int x, int y){
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}
