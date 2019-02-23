package com.owen.algorithm.nomura;

import java.math.BigInteger;

public class _4Count1InBits {

    public static void main(String[] args){
        System.out.println(calculateOneInBinary(3, 7));
    }

    private static int calculateOneInBinary(int x, int y)
    {
        BigInteger a = new BigInteger(x + ""), b = new BigInteger(y + "");
        String result = a.multiply(b).toString(2);

        int count = 0;
        for(int i = 0; i < result.length(); i++){
            if(result.charAt(i) == '1'){
                count++;
            }
        }
        return count;
    }
}
