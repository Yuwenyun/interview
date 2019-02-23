package com.owen.algorithm.nomura;

public class _9GetLongestIndexDiff {

    public static void main(String[] args){
        int[] array = new int[]{4, 6, 2, 2, 6, 6};
        System.out.println(new _9GetLongestIndexDiff().solution(array));
    }

    public int solution(int[] A)
    {
        if(A == null || A.length <= 1){ return 0; }
        int[] array = new int[A.length];

        int max_diff = 0;
        for(int i = 0; i < A.length; i++){
            if(array[A[i] - 1] == 0){
                if(i == 0) {
                    array[A[i] - 1] = -1;
                }
                else{
                    array[A[i] - 1] = i;
                }
            }
            else if(array[A[i] - 1] == -1){
                max_diff = Math.max(max_diff, i);
            }
            else{
                max_diff = Math.max(max_diff, i - array[A[i] - 1]);
            }
        }
        return max_diff;
    }
}
