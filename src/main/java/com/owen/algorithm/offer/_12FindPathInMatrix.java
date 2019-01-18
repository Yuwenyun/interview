package com.owen.algorithm.offer;

import org.apache.commons.lang3.StringUtils;

/**
 * find path in matrix
 * back-tracking(回溯法)
 */
public class _12FindPathInMatrix {

    public static void main(String[] args)
    {
        char[][] matrix = new char[][]{
                {'a', 'b', 't', 'g'},
                {'c', 'f', 'c', 's'},
                {'j', 'd', 'e', 'h'}
        };
        System.out.println(hasPath(matrix, "bfce"));
    }

    private static boolean hasPath(char[][] matrix, String path)
    {
        if(matrix == null || StringUtils.isBlank(path)){
            return false;
        }

        int rows = matrix.length, cols = matrix[0].length;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                boolean[][] visited = new boolean[rows][cols];
                if(hasPath(matrix, path, i, j, 0, visited)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasPath(char[][] matrix, String path, int row, int col, int charIdx, boolean[][] visited)
    {
        char currChar;
        if(charIdx < path.length()){
            currChar = path.charAt(charIdx);
        }
        else{
            return true;
        }
        if(row < 0 || col < 0){
            return false;
        }

        if(matrix[row][col] == currChar && !visited[row][col]){
            visited[row][col] = true;
            return hasPath(matrix, path, row + 1, col, charIdx + 1, visited) ||
                    hasPath(matrix, path, row - 1, col, charIdx + 1, visited) ||
                    hasPath(matrix, path, row, col + 1, charIdx + 1, visited) ||
                    hasPath(matrix, path, row, col - 1, charIdx + 1, visited);
        }
        return false;
    }
}
