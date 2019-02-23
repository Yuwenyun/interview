package com.owen.algorithm.nomura;

public class _8GetMaxDiff {

    public static void main(String[] args){
        int[] array = new int[]{3, 6, 1, 9, 2, -5, 0, -5};
        System.out.println(new _8GetMaxDiff().solution(array));
    }

    public int solution(int[] A)
    {
        if(A == null || A.length <= 1){ return 0; }

        int max_l = A[0]; // max value of left part
        int max_r = A[1]; // max value of right part
        int max_diff = Math.abs(max_l - max_r); // max diff
        int result = 0; // result during the iteration

        for(int i = 0; i < A.length - 1; i++)
        {
            // refresh the max value of left part when add new element
            if(A[i] > max_l){
                max_l = A[i];
            }

            // refresh the max value of right part when consume a new element
            max_r = A[i + 1];
            for(int j = i + 1; j < A.length; j++)
            {
                if(A[j] >= max_r){
                    max_r = A[j];
                    result = Math.abs(max_r - max_l);
                    if(result > max_diff){
                        max_diff = result;
                    }
                }
            }
        }
        return max_diff;
    }
}
