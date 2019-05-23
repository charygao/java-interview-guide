package com.example.java.interview.guide.chapter1.tree.binary;

/**
 * @Author : zhouguanya
 * @Project : java-interview-guide
 * @Date : 2019-04-30 11:04
 * @Version : V1.0
 * @Description :   二叉查找树实现
 */
public class BinarySearchTree {
    private class Node {
        /** 保存节点数据 */
        public int data;
        /** 当前节点的左孩子指针 */
        public Node left;
        /** 当前节点的右孩子指针 */
        public Node right;
        /** 当前节点的父节点 */
        public Node parent;

        /**
         * Node构造器
         *
         * @param value     节点值
         */
        public Node(int value){
            data = value;
            left = null;
            right = null;
            parent = null;
        }
    }
    /** 二叉查找树根节点 */
    private Node root;
    /**
     * 二叉查找树构造器
     */
    public BinarySearchTree(){
        root = null;
    }

    /**
     * 查找指定的元素
     *
     * @param key   待查找的元素
     * @return      返回待查找的元素
     *              如果不存在则返回其父节点
     */
    public Node find(int key) {
        // 从根节点开始遍历
        Node current = root;
        while (current != null) {
            // 如果查找元素小于当前节点的元素
            // 从当前节点的左子树上搜索指定元素
            if (key < current.data) {
                // 当前节点左孩子为空
                if (current.left == null) {
                    return current;
                }
                current = current.left;
                // 如果查找元素大于当前节点的元素
                // 从当前节点的右子树上搜索指定元素
            } else if (key > current.data) {
                // 当前节点右孩子为空
                if (current.right == null) {
                    return current;
                }
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }

    /**
     * 添加元素
     *
     * @param value     待插入的元素
     */
    public void put(int value) {
        Node newNode = new Node(value);
        // 如果当前二叉查找树不存在
        if (root == null) {
            root = newNode;
            return;
        }
        Node parent = find(value);
        // 当前值小于父节点值
        if (value < parent.data) {
            parent.left = newNode;
            parent.left.parent = parent;
            return;
        }
        // 当前元素值大于等于父节点的值
        parent.right = newNode;
        parent.right.parent = parent;
    }

    /**
     * 从二叉查找树上删除指定元素
     *
     * 删除元素分为以下情况：
     * 情况1.待删除的节点没有左右子节点，可以直接删除
     * 情况2.待删除的节点存在左子节点或者右子节点，删除后需要对子节点移动
     * 情况3.待删除的节点存在左右2个子节点，可以通过和待删除的节点后继节点交换后转换为情况1或2
     *
     * @param value     待删除元素
     * @return          删除结果
     */
    public boolean remove(int value) {
        Node temp = find(value);
        if (temp.data != value) {
            return false;
        }
        //首先处理第3种情况，删除的节点同时存在左右子节点
        if (temp.left != null && temp.right != null) {
            // 待删除节点的后继节点
            Node successor = findSuccessor(temp);
            // 转移待删除节点的后继节点值到当前节点
            temp.data = successor.data;
            // 把待删除的当前节点指向后继结点
            temp = successor;
        }
        //经过上一步处理，下面只有前2种情况，待删除的节点只有1个子节点或者没有节点
        //不管待删除的节点是否有子节点，都获取待删除的节点的子节点
        Node child;
        // 待删除的节点的子节点
        child = temp.left != null ? temp.left : temp.right;
        if (child != null) {
            // 将待删除子节点和待删除节点的父节点关联上
            child.parent = temp.parent;
        }
        // 如果当前待删除的节点没有父节点（后继节点的情况到这儿时一定有父节点）
        // 说明要待删除的就是根节点
        if (temp.parent == null) {
            root = child;
        } else if (temp == temp.parent.left) {
            //当前待删除的节点存在父节点，并且当前待删除的节点是其父节点的一个左节点
            temp.parent.left = child;
        } else if (temp == temp.parent.right) {
            //当前待删除的节点存在父节点，并且当前待删除的节点是其父节点的一个右节点
            temp.parent.right = child;
        }
        return true;
    }

    /**
     * 查询当前节点的后继节点
     * 1.若当前节点没有右孩子，返回当前节点
     * 2.若当前节点有右孩子，返回右子树中最小的大于当前节点的节点
     *
     * @param node  当前节点
     * @return      后继节点
     */
    public Node findSuccessor(Node node) {
        if (node.right == null) {
            return node.parent;
        }
        Node current = node.right;
        Node parent = node.right;
        while (current != null) {
            parent = current;
            current = current.left;
        }
        return parent;
    }

    /**
     * 获取根节点
     *
     * @return  根节点
     */
    public Node getRoot() {
        return root;
    }

    /**
     * 中序遍历
     * 左孩子---->父节点---->右孩子
     *
     * @param node  起始节点
     */
    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    /**
     * 先序遍历
     * 父节点---->左孩子---->右孩子
     *
     * @param node  起始节点
     */
    public void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 后序遍历
     * 左孩子---->右孩子---->父节点
     *
     * @param node  起始节点
     */
    public void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }
}
