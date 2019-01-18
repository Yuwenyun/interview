package com.owen.algorithm.offer;

/**
 * given an asc bi-array and a number check whether exists
 */
public class _4CheckExistingInAscBiArray {

    public static void main(String[] args){
        int[][] array = new int[][]{
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        System.out.println(checkFromNorthEast(array, 7));
    }

    private static boolean checkFromNorthEast(int[][] array, int num){
        if(array == null || array.length == 0 || array[0].length == 0){
            return false;
        }

        int rows = array.length, cols = array[0].length;
        for(int i = 0; i < rows; i++){
            for(int j = cols - 1; j >= 0; j--){
                if(array[i][j] == num){
                    return true;
                }
                else if(array[i][j] > num){
                    continue;
                }
                else{
                    break;
                }
            }
        }
        return false;
    }
}
