package com.owen.algorithm.nomura;

public class _1FindSaddlePoint {

    public static void main(String[] args){
        int[][] array = new int[][]{{0, 1, 9, 3},
                {7, 5, 8, 3},
                {9, 2, 9, 4},
                {4, 6, 7, 1}};
        findSaddlePointNumber(array);
    }

    public static int findSaddlePointNumber(int[][] array){
        if(array == null || array.length < 3 || array[0].length < 3){
            return 0;
        }

        int rows = array.length, columns = array[0].length;
        int result = 0;
        for(int i = 1; i < rows - 1; i++)
        {
            for(int j = 1; j < columns - 1; j++){
                int ahead = array[i][j - 1], below = array[i][j + 1];
                int left = array[i - 1][j], right = array[i + 1][j];
                if((array[i][j] > ahead && array[i][j] > below && array[i][j] < left && array[i][j] < right) ||
                   (array[i][j] < ahead && array[i][j] < below && array[i][j] > left && array[i][j] > right)){
                    System.out.println(i + ", " + j + " = " + array[i][j]);
                    result++;
                }
            }
        }
        return result;
    }
}
