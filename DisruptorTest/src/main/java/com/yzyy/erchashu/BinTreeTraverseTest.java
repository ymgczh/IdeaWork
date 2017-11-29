package com.yzyy.erchashu;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: YMGC
 * @Description: 二叉树测试i
 * @Date: Created in 9:21 2017/11/29
 * @Since: 2017/11/29
 */
public class BinTreeTraverseTest {

    private int[] arry = {1,2,3,4,5,6,7,8,9};
    private static List<Node> nodeList = new LinkedList<>();

    private class Node {
        Node leftChild;
        Node rightChild;
        public int value;

        public Node(int value) {
            this.leftChild = null;
            this.rightChild = null;
            this.value = value;
        }
    }

    public void createBinTree(){
        for (int nodeIndex = 0; nodeIndex < arry.length; nodeIndex++) {
            nodeList.add(new Node(arry[nodeIndex]));
        }

        for (int parentIndex = 0; parentIndex < arry.length / 2 - 1; parentIndex++) {
            nodeList.get(parentIndex).leftChild = nodeList
                    .get(parentIndex * 2 + 1);
            nodeList.get(parentIndex).rightChild = nodeList
                    .get(parentIndex * 2 + 2);
        }

        int lastParentIndex = arry.length / 2 - 1;
        nodeList.get(lastParentIndex).leftChild = nodeList
                .get(lastParentIndex * 2 + 1);
        if (1 == arry.length % 2) {
            nodeList.get(lastParentIndex).rightChild = nodeList
                    .get(lastParentIndex * 2 + 2);
        }
    }

    public static void preOrderTraverse(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
        preOrderTraverse(node.leftChild);
        preOrderTraverse(node.rightChild);
    }

    public static void inOrderTraverse(Node node) {
        if (node == null) {
            return;
        }
        inOrderTraverse(node.leftChild);
        System.out.print(node.value + " ");
        inOrderTraverse(node.rightChild);
    }

    public static void postOrderTraverse(Node node) {
        if (node == null) {
            return;
        }
        postOrderTraverse(node.leftChild);
        postOrderTraverse(node.rightChild);
        System.out.print(node.value + " ");
    }

    public static void main(String[] args) {
        BinTreeTraverseTest tree = new BinTreeTraverseTest();
        tree.createBinTree();
        Node root = nodeList.get(0);

        System.out.println("先序遍历");
        preOrderTraverse(root);
        System.out.println();

        System.out.println("中序遍历");
        inOrderTraverse(root);
        System.out.println();

        System.out.println("后序遍历");
        postOrderTraverse(root);
        System.out.println();
    }
}
