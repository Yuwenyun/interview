package com.owen.algorithm.offer;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * Replace blank in string with %20
 *
 * exp: "We are happy"
 * result: "We%20are%20happy"
 */
public class _5ReplaceCharInString
{
    public static void main(String[] args)
    {
        System.out.println(replace("We are happy"));
    }

    public static String replace(String str)
    {
        assert StringUtils.isNotBlank(str);

        // get count of blank char
        char[] strChars = str.toCharArray();
        int blankCount = 0;
        for(int i = 0; i < strChars.length; i++){
            if(strChars[i] == ' '){
                blankCount++;
            }
        }

        if(blankCount == 0){
            return str;
        }

        char[] resChars = new char[strChars.length + blankCount * 2];
        int posIdx = resChars.length - 1;
        for(int i = strChars.length - 1; i >= 0; i--){
            if(strChars[i] != ' '){
                resChars[posIdx--] = strChars[i];
            }
            else{
                resChars[posIdx--] = '0';
                resChars[posIdx--] = '2';
                resChars[posIdx--] = '%';
            }
        }
        return new String(resChars);
    }
}
