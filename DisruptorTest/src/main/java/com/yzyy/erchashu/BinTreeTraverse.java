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
        private Node leftChild;
        private Node rightChild;
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
    private void offerValue(){

    }
}
