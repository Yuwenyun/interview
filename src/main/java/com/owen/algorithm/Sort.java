package com.owen.algorithm;

import java.util.Arrays;

public class Sort {

    public static void main(String[] args){
        int[] array = new int[]{3, 9, 6, 8, 4, 1, 2, 5, 7, 0};
        quick(array);
        Arrays.stream(array).forEach(System.out::println);
    }

    public static void bubble(int[] array){
        if(!legalArray(array)){ return; }

        for(int i = 1; i < array.length; i++){
            for(int j = 0; j < array.length - i; j++){
                if(array[j] > array[j + 1]){
                    swap(array, j, j + 1);
                }
            }
        }
    }

    public static void select(int[] array){
        if(!legalArray(array)){ return; }

        for(int i = 0; i < array.length - 1; i++){
            int k = i;
            for(int j = i + 1; j < array.length; j++){
                if(array[j] < array[k]){
                    k = j;
                }
            }
            swap(array, i, k);
        }
    }

    public static void insert(int[] array){
        if(!legalArray(array)){ return; }

        for(int i = 0; i < array.length - 1; i++){
            for(int j = i + 1; j > 0; j--){
                if(array[j] < array[j - 1]){
                    swap(array, j, j - 1);
                }
                else{
                    break;
                }
            }
        }
    }

    public static void shell(int[] array){
        if(!legalArray(array)){ return; }

        int h = 0;
        while(h <= array.length){
            h = 2 * h + 1;
        }

        while(h >= 1){
            for(int i = h; i < array.length; i++){
                int pivot = i;
                while((pivot - h) >= 0 && array[pivot] > array[pivot - h]){
                    swap(array, pivot, pivot - h);
                    pivot = pivot - h;
                }
            }
            h = (h - 1) / 2;
        }
    }

    public static void heap(int[] array){
        if(!legalArray(array)){ return; }

        for(int i = array.length; i > 0; i--){
            buildHeap(array, i);
            swap(array, 0, i - 1);
        }
    }

    public static void quick(int[] array){
        if(!legalArray(array)){ return; }

        quick(array, 0, array.length - 1);
    }

    private static void quick(int[] array, int left, int right){
        if(left >= right){ return; }

        int i = left, j = right, pivot = array[left];
        while( i != j){
            while(i < j && array[j] >= pivot){ j--; }
            if(i < j){ array[i] = array[j]; }

            while(i < j && array[i] <= pivot){ i++; }
            if(i < j){ array[j] = array[i]; }
        }
        array[i] = pivot;
        quick(array, left, i - 1);
        quick(array, j + 1, right);
    }

    private static void heapify(int[] array, int parentNodeIdx, int size){
        if(size == 0){ return; }

        int left = parentNodeIdx * 2 + 1;
        int right = parentNodeIdx * 2 + 2;
        int maxIdx = parentNodeIdx;

        if(left < size && array[left] > array[maxIdx]){
            maxIdx = left;
        }
        if(right < size && array[right] > array[maxIdx]){
            maxIdx = right;
        }

        if(maxIdx != parentNodeIdx){
            swap(array, maxIdx, parentNodeIdx);
            heapify(array, maxIdx, size);
        }
    }

    private static void buildHeap(int[] array, int size){
        for(int i = size / 2 - 1; i >= 0; i--){
            heapify(array, i, size);
        }
    }

    private static boolean legalArray(int[] array){
        return array != null && array.length != 0;
    }

    private static void swap(int[] array, int a, int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
