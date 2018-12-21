package com.owen.algorithm.offer;

/**
 * A 2D array, elements asc from left to right, up to bottom
 * return boolean whether a given number in the array
 *
 * solution: compare from up-right
 */
public class QueryIn2DArray
{
    public static void main(String[] args)
    {
        int[][] array = new int[][]{
                {1,2,8,9},
                {2,4,9,12},
                {4,7,10,13},
                {6,8,11,15}
        };
        System.out.println(contains(array, 7));
        System.out.println(contains(array, 5));
    }

    public static boolean contains(int[][] array, int e)
    {
        assert array != null && array.length > 0 && array[0].length > 0;

        int xLen = array.length, yLen = array[0].length;
        for(int x = xLen - 1; x >= 0; x--){
            for(int y = 0; y < yLen; y++){
                if(array[y][x] > e){
                    break;
                }
                if(array[y][x] == e){
                    return true;
                }
                if(array[y][x] < e){
                    continue;
                }
            }
        }
        return false;
    }
}
