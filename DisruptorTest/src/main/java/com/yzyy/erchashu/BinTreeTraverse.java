package com.yzyy.erchashu;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: YMGC
 * @Description: java二叉树实现
 * @Date: Created in 17:37 2017/11/28
 * @Since: 2017/11/28
 */
public class BinTreeTraverse {

    private int[] arry = {1,2,3,4,5,6,7,8,9};

    private static List<Node> nodeList = new LinkedList();

    private class Node {
        Node leftChild;
        Node rightChild;
        private int value;

        public Node(int value) {
            this.leftChild = null;
            this.rightChild = null;
            this.value = value;
        }
    }

    /**
     * 二叉树注入值
     */
    private void createBinTree(){
        nodeList = new LinkedList<Node>();
        for (int nodeIndex = 0; nodeIndex < arry.length ; nodeIndex++) {
            nodeList.add(new Node(arry[nodeIndex]));
        }

        //对于 父节点数 - 1 个父节点按照父节点与孩子节点的数字关系建立二叉树
        for (int parentIndex = 0; parentIndex < arry.length / 2 - 1 ; parentIndex++) {
            nodeList.get(parentIndex).leftChild =
                    nodeList.get(parentIndex * 2 + 1);
            nodeList.get(parentIndex).rightChild =
                    nodeList.get(parentIndex * 2 + 2);
        }

        int lastParentIndex = arry.length / 2 - 1;
        //最后一个父节点可能没有右孩子，单独拿出来处理
        nodeList.get(lastParentIndex).leftChild =
                    nodeList.get(lastParentIndex * 2 + 1);

        //只有数组长度为奇数时候才有右孩子
        if (1 == arry.length % 2) {
            nodeList.get(lastParentIndex).rightChild =
                    nodeList.get(lastParentIndex * 2 + 2);
        }
    }

    /**
     * 二叉树先序遍历
     * @param node
     */
    public static void preOrderTraverse(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
        preOrderTraverse(node.leftChild);
        preOrderTraverse(node.rightChild);
    }

    /**
     * 中序遍历
     * @param node
     */
    public static void inOrderTraverse(Node node) {
        if (node == null) {
            return;
        }
        inOrderTraverse(node.leftChild);
        System.out.print(node.value + " ");
        inOrderTraverse(node.rightChild);
    }

    /**
     * 后序遍历
     * @param node
     */
    public static void postOrderTraverse(Node node) {
        if (node == null) {
            return;
        }
        postOrderTraverse(node.leftChild);
        postOrderTraverse(node.rightChild);
        System.out.print(node.value + " ");
    }

    public static void main(String[] args) {
        BinTreeTraverse tree = new BinTreeTraverse();
        tree.createBinTree();
        Node node = nodeList.get(0);

        System.out.println("先序遍历");
        preOrderTraverse(node);
        System.out.println();

        System.out.println("中序遍历");
        inOrderTraverse(node);
        System.out.println();

        System.out.println("后序遍历");
        postOrderTraverse(node);

    }

}
