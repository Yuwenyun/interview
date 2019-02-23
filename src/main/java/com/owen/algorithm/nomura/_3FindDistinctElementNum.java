package com.owen.algorithm.nomura;

import com.owen.algorithm.TreeNode;

import java.util.ArrayDeque;

public class _3FindDistinctElementNum {

    public static void main(String[] args){

        TreeNode<Integer> root = new TreeNode<>(4);
        TreeNode<Integer> node1 = new TreeNode<>(5);
        TreeNode<Integer> node2 = new TreeNode<>(6);
        TreeNode<Integer> node3 = new TreeNode<>(4);
        TreeNode<Integer> node4 = new TreeNode<>(1);
        TreeNode<Integer> node5 = new TreeNode<>(6);
        TreeNode<Integer> node6 = new TreeNode<>(5);
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node3.setLeft(node6);
        node2.setLeft(node4);
        node2.setRight(node5);
        System.out.println(distinctElementNum(root));
    }

    private static int distinctElementNum(TreeNode<Integer> root){
        if(root == null){
            return 0;
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.addFirst(0);
        preIterate(root, queue);

        return queue.removeFirst();
    }

    private static void preIterate(TreeNode<Integer> root, ArrayDeque<Integer> queue){
        if(root != null){
            queue.addLast(root.getValue());
        }
        if(root.getLeft() != null){
            preIterate(root.getLeft(), queue);
        }
        if(root.getRight() != null){
            preIterate(root.getRight(), queue);
        }

        int max = queue.removeFirst();
        int distinct = (int)queue.stream().distinct().count();
        if(max < distinct){
            max = distinct;
        }
        queue.addFirst(max);

        queue.removeLast();
    }
}
