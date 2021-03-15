package demo6;

/**
 * 顺序存储二叉树
 *
 * @author admin
 */
public class ArrayBinaryTree {
    /**
     * 数据以数组的形式来存储
     */
    int[] data;

    /**
     * 构造方法
     *
     * @param data 指定数组参数
     */
    public ArrayBinaryTree(int[] data) {
        this.data = data;
    }

    /**
     * 从根结点开始前序遍历
     */
    public void frontShow() {
        // 传入根结点下标 0
        frontShow(0);
    }

    /**
     * 前序遍历
     *
     * @param index 起点的下标
     */
    public void frontShow(int index) {
        // 检查边际条件
        if (data == null || data.length == 0) {
            return;
        }
        // 获取当前节点的值
        System.out.print(data[index] + " ");
        // 获取左子节点下标
        int leftIndex = index * 2 + 1;
        // 处理左子节点
        if (leftIndex < data.length) {
            // 左子节点递归调用前序遍历方法
            frontShow(leftIndex);
        }
        // 获取右子节点下标
        int rightIndex = index * 2 + 2;
        // 处理右子节点
        if (rightIndex < data.length) {
            // 右子节点递归调用前序遍历方法
            frontShow(rightIndex);
        }
    }
}
