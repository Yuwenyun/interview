package com.owen.algorithm;

import java.math.BigInteger;

public class IPAdressConverter {

    private static final int BOTTOM = (int)'0';
    private static final int CAP = (int)'9';
    private static final int BLANK = (int)' ';
    private static final int DOT = (int)'.';

    public static void main(String[] args){

        System.out.println(convertToInt("172.168.5.1"));
        System.out.println(convertToInt("255.255.255.255"));
        System.out.println(convertToInt("0.0.0.0"));
    }

    /**
     * api for IP converting
     */
    public static BigInteger convertToInt(String ip){

        // create an array to store the numbers in ip
        int[] ipNumbers = new int[4];
        if(legalIPv4Address(ip, ipNumbers)){
            String result = convertToBinary(getInteger(ipNumbers));
            return new BigInteger(result, 2);
        }
        return null;
    }

    /**
     * convert an array of length 4 with numbers from ip to int number
     */
    private static int getInteger(int[] nums){
        if(nums == null || nums.length < 4){
            return -1;
        }
        int result = 0;
        for(int i = 0; i < 4; i++){
            result = result + (nums[i] << ((3 - i) * 8));
        }
        return result;
    }

    /**
     * print/return the string of binary of an integer
     */
    private static String convertToBinary(int a)
    {
        StringBuilder result = new StringBuilder();
        for(int i = 31; i >= 0; i--){
            result.append((a >> i) & 1);
            if((i + 1) % 4 == 0){
                System.out.print(" ");
            }
            System.out.print((a >> i) & 1);
        }
        System.out.println();
        return result.toString();
    }

    private static boolean legalIPv4Address(String address, int[] nums)
    {
        // need an array to hold the converted number
        if(nums == null || nums.length < 4){
            throw new IllegalArgumentException("Need an array of size 4 at least.");
        }

        // check null
        if(address == null){ return false; }

        // check len for pre-process
        int len = address.length();
        if(len < 7 || len > 15){ return false; }

        int currNum = -1; // record current number, initial value set to -1
        int dotCount = 0; // count dot, legal ip shall have only 3 dots
        boolean numIterated = false; // flag to record whether previous number finished iterating

        for(int i = 0; i < address.length(); i++)
        {
            char currChar = address.charAt(i);

            if(currChar == BLANK){
                if(!numIterated && currNum != -1){
                    numIterated = true;
                }
                continue;
            }

            if(((int)currChar) >= BOTTOM && ((int)currChar) <= CAP){
                if(!numIterated) {
                    if(currNum == -1){ currNum = 0; }
                    currNum = currNum * 10 + (currChar - BOTTOM);
                    continue;
                }
                // current number finished iterating, still comes a number char
                // this is invalid case
                else{
                    return false;
                }
            }

            if(currChar == DOT){
                if(i == 0 || ++dotCount > 3){
                    return false;
                }
                if(currNum > 255){
                    return false;
                }
                nums[dotCount - 1] = currNum;
                currNum = -1;
                numIterated = false;
            }
            else{
                return false;
            }
        }
        if(currNum > 255){
            return false;
        }
        nums[dotCount] = currNum;

        return dotCount == 3;
    }

    /**
     * api for call, this is used for test cases
     * @param address ip address that need to be validated
     * @return true if provided ip address legal
     */
    public static boolean legalIPv4Address(String address){
        return legalIPv4Address(address, new int[4]);
    }
}
