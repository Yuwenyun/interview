package com.owen.algorithm;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * get max k numbers from a large amount of numbers,
 * build min-top heap, the (k+1)th number bigger than the root, add in and heapify.
 *
 * this won't resolve the duplicate problem
 */
public class GetMaxKNums {

    private static int RANGE = 1_0000_0000;
    private static String FILE = "random.txt";
    private static int K = 24;

    public static void main(String[] args) throws IOException
    {
        int[] array;
        try(BufferedReader stdIn = new BufferedReader(new InputStreamReader(new FileInputStream(FILE)))){
            array = buildHeapOfK(K, stdIn);
            String input;
            while((input = stdIn.readLine()) != null){
                int number = Integer.parseInt(input);
                if(number > array[0]){
                    array[0] = number;
                    buildHeap(array, array.length);
                }
            }
        }
        Arrays.stream(array).forEach(System.out::println);
    }

    private static int[] buildHeapOfK(int k, BufferedReader reader) throws IOException
    {
        Set<Integer> set = new HashSet<>();
        int[] array;
        while(set.size() < k)
        {
            int number = Integer.parseInt(reader.readLine());
            set.add(number);
        }
        array = set.stream().mapToInt(Integer::intValue).toArray();
        buildHeap(array, k);
        return array;
    }

    private static void buildHeap(int[] array, int size)
    {
        for(int i = size / 2 - 1; i >= 0; i--){
            heapify(array, i, size);
        }
    }

    private static void heapify(int[] array, int i, int size)
    {
        if(i >= size){
            return;
        }
        int left = 2 * i + 1, right = 2 * i + 2, min = i;
        if(left < size && array[left] < array[min]){
            min = left;
        }
        if(right < size && array[right] < array[min]){
            min = right;
        }
        if(min != i){
            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
            heapify(array, min, size);
        }
    }

    private static void generateNums() throws IOException
    {
        try(FileWriter fos = new FileWriter(new File(FILE));
            BufferedWriter stdOut = new BufferedWriter(fos))
        {
            Random random = new Random(System.currentTimeMillis());
            for (int i = 0; i < RANGE; i++) {
                stdOut.write(random.nextInt(RANGE) + "\r\n");
            }
        }
    }
}
