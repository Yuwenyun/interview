package com.owen.algorithm;

/**
 *
 */
public class BinarySearch {

    public static void main(String[] args){
        BinarySearch app = new BinarySearch();
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(app.binarySearch(array, 9));
    }

    private boolean binarySearch(int[] array, int number){
        if(array == null || array.length == 0){
            return false;
        }
        return binarySearch(array, number, 0, array.length - 1);
    }

    private boolean binarySearch(int[] array, int number, int start, int end){
        if(start == end){
            return false;
        }

        int middleIdx = (start + end) / 2;
        if(middleIdx == start){
            middleIdx = end;
        }
        if(array[middleIdx] < number){
            return binarySearch(array, number, middleIdx, end);
        }
        else if(array[middleIdx] > number){
            return binarySearch(array, number, start, middleIdx);
        }
        else{
            return true;
        }
    }
}
