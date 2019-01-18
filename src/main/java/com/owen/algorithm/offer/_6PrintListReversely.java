package com.owen.algorithm.offer;

import com.owen.ModelFactory;
import com.owen.algorithm.ListNode;

/**
 * print the list element from tail to head
 */
public class _6PrintListReversely
{
    public static void main(String[] args)
    {
        printList(ModelFactory.getList());
    }

    // method call itself is stack
    public static void printList(ListNode<Integer> root)
    {
        if(root == null){
            return;
        }
        printList(root.getNext());
        System.out.println(root.getValue());
    }
}
