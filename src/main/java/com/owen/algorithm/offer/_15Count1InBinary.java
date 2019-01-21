package com.owen.algorithm.offer;

/**
 * count numbers of 1 in binary of a provided number
 */
public class _15Count1InBinary {

    public static void main(String[] args){

//        System.out.println(turnToBinary(-1));
        System.out.println(countOneInBinarySupportNegative(-1));
    }

    // won't support negative integer
    // binary of negative integer: (say -1)
    // 1. get binary of positive: 0000 0000 0000 0001
    // 2. get one's complement(反码): 1111 1111 1111 1110
    // 3. plus 1 to get complement(补码): 1111 1111 1111 1111
    private static int countOneInBinary(int number)
    {
        int count = 0;
        while(number != 0){
            if((number & 1) == 1){
                count++;
            }
            number = number >> 1;
        }
        return count;
    }

    // support negative integer
    private static int countOneInBinarySupportNegative(int number)
    {
        int count = 0, pivot = 1;
        while(pivot != 0){
            if((number & pivot) == pivot){
                count++;
            }
            pivot = pivot << 1;
        }
        return count;
    }

    private static String turnToBinary(int number){
        return Integer.toBinaryString(number);
    }
}
