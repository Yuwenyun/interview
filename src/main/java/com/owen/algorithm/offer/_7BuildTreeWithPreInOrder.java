package com.owen.algorithm.offer;

import com.owen.NodeUtil;
import com.owen.algorithm.TreeNode;

public class _7BuildTreeWithPreInOrder
{
    public static void main(String[] args)
    {
        int[] pre = new int[]{1, 2, 4, 7, 3, 5, 6, 8};
        int[] in= new int[]{4, 7, 2, 1, 5, 3, 8, 6};
        NodeUtil.printTree(buildTree(pre, 0, pre.length, in, 0, in.length));

        pre = new int[]{3, 7, 5, 2, 6, 4, 9, 8};
        in = new int[]{5, 7, 6, 2, 3, 4, 8, 9};
        NodeUtil.printTree(buildTree(pre, 0, pre.length, in, 0, in.length));
    }

    private static TreeNode<Integer> buildTree(int[] pre, int pre_start, int pre_end,
                                               int[] in, int in_start, int in_end)
    {
        assert pre != null && pre.length > 0 && in != null && in.length > 0 && pre.length == in.length;

        // get the index of root element in in array
        int rootIdxInInArray = 0;
        for(int i = in_start; i < in_end; i++){
            if(pre[pre_start] == in[i]){
                rootIdxInInArray = i;
                break;
            }
        }

        TreeNode<Integer> root = new TreeNode<>(pre[pre_start]);
        if(rootIdxInInArray != in_start){
            root.setLeft(buildTree(pre, pre_start + 1, pre_start + (rootIdxInInArray - in_start) + 1,
                    in, in_start, rootIdxInInArray));
        }
        if(rootIdxInInArray != in_end - 1) {
            root.setRight(buildTree(pre, pre_start + (rootIdxInInArray - in_start) + 1, pre_end,
                    in, rootIdxInInArray + 1, in_end));
        }
        return root;
    }
}
