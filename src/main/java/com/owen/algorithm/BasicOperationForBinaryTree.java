package com.owen.algorithm;

import com.owen.ModelFactory;
import com.owen.NodeUtil;

public class BasicOperationForBinaryTree {

    public static void main(String[] args)
    {
        TreeNode<Integer> root = ModelFactory.getTree();
        NodeUtil.printTree(root);

        BasicOperationForBinaryTree app = new BasicOperationForBinaryTree();
        System.out.println(app.countNodes(root));
        System.out.println(app.getDepth(root));
        System.out.println(app.countNodesInKthLevel(root, 3));
        System.out.println(app.countLeaf(root));
        System.out.println(app.sameTree(root, root));
        System.out.println(app.isAVL(root));
        NodeUtil.printTree(app.mirror(root));
        System.out.println(app.isValidBST(root));
    }

    public <T> int countNodes(TreeNode<T> root)
    {
        if(root == null){ return 0; }
        return countNodes(root.getLeft()) + countNodes(root.getRight()) + 1;
    }

    public <T> int getDepth(TreeNode<T> root){
        if(root == null){ return 0; }
        return Math.max(getDepth(root.getLeft()), getDepth(root.getRight())) + 1;
    }

    public <T> int countNodesInKthLevel(TreeNode<T> root, int k){
        if(root == null || k == 0){ return 0; }
        if(k == 1){
            return 1;
        }
        return countNodesInKthLevel(root.getLeft(), k - 1) + countNodesInKthLevel(root.getRight(), k - 1);
    }

    public <T> int countLeaf(TreeNode<T> root){
        if(root == null){ return 0; }
        if(root.getLeft() == null && root.getRight() == null){
            return 1;
        }
        return countLeaf(root.getLeft()) + countLeaf(root.getRight());
    }

    public <T> boolean sameTree(TreeNode<T> rootA, TreeNode<T> rootB){
        if(rootA == null && rootB == null){
            return true;
        }
        else if(rootA == null || rootB == null){
            return false;
        }
        else{
            return rootA.getValue().equals(rootB.getValue()) &&
                    sameTree(rootA.getLeft(), rootB.getLeft()) &&
                    sameTree(rootA.getRight(), rootB.getRight());
        }
    }

    public <T> boolean isAVL(TreeNode<T> root){
        if(root == null){
            return true;
        }
        if(Math.abs(getDepth(root.getLeft()) - getDepth(root.getRight())) > 1){
            return false;
        }
        return isAVL(root.getLeft()) && isAVL(root.getRight());
    }

    public <T> TreeNode<T> mirror(TreeNode<T> root){
        if(root == null){ return null; }
        TreeNode<T> left = mirror(root.getRight());
        TreeNode<T> right = mirror(root.getLeft());
        root.setLeft(left);
        root.setRight(right);
        return root;
    }

    public boolean isValidBST(TreeNode<Integer> root){
        if(root == null){
            return true;
        }
        if(root.getLeft() != null){
            if(root.getLeft().getValue() > root.getValue()){
                return false;
            }
        }
        if(root.getRight() != null){
            if(root.getRight().getValue() < root.getValue()){
                return false;
            }
        }
        return isValidBST(root.getLeft()) && isValidBST(root.getRight());
    }
}
