package com.owen.algorithm.nomura;

public class _5MaxAscendingSlice {

    public static void main(String[] args){
        System.out.println(maxAscSlice(new int[]{2, 2, 2, 1, 2, -1, 2, 3}));
    }

    private static int maxAscSlice(int[] array){
        if(array == null || array.length == 0){
            return 0;
        }
        if(array.length == 1){
            return 1;
        }

        int max = 1, count = 1;
        for(int i = 0; i < array.length - 1; i++){
            if(array[i] < array[i + 1]){
                count++;
                if(max < count){
                    max = count;
                }
            }
            else{
                count = 1;
            }
        }
        return max;
    }
}
