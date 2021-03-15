package demo7;

/**
 * 线索二叉树
 * @author admin
 */
public class ThreadedBinaryTree {
    /**
     * 根结点
     */
    ThreadedNode root;

    /**
     * 临时存储前驱节点
     */
    ThreadedNode pre;

    /**
     * 中序遍历线索化二叉树
     */
    public void threadIterate() {
        // 定义临时变量 node 记录当前节点
        ThreadedNode node = root;
        // node 不为空时
        while (node != null) {
            // 1. 中序遍历，向左查找第一个被线索化的节点
            // 跳过所有没有线索化的节点，即所有 leftType == 0 的节点
            // 直至找到第一个线索化的节点，即 leftType == 1 的节点
            while (node.leftType == 0) {
                // node 指针前移
                node = node.leftNode;
            }

            // 2. 通过线索化不断打印后继节点的值
            // while 循环结束后， node 指向的节点就是当前第一个线索化的节点
            // 打印节点的值
            System.out.print(node.value + " ");

            // 循环查找后继节点
            while (node.rightType == 1) {
                // 指针后移
                node = node.rightNode;
                // 打印后继节点的值
                System.out.print(node.value + " ");
            }
            // 上个 while 循环结束后
            // 当前有效范围内的所有线索化的节点都已经遍历过
            // 3. 指针后移到右子节点，在下一个有效范围内查找线索化的节点
            node = node.rightNode;
        }
    }

    /**
     * 设置根结点
     *
     * @param node 节点参数
     */
    public void setRoot(ThreadedNode node) {
        root = node;
    }

    /**
     * 对根结点应用线索化二叉树方法
     */
    public void threadNodes() {
        threadNodes(root);
    }

    /**
     * 中序遍历
     * 线索化二叉树方法
     *
     * @param node
     */
    public void threadNodes(ThreadedNode node) {
        // 如果节点为空
        if (node == null) {
            // 返回不做处理
            return;
        }

        // 对左节点递归调用当前方法
        threadNodes(node.leftNode);

        // 对当前节点进行处理
        // 如果左子树为空
        if (node.leftNode == null) {
            // 将左指针指向前驱节点
            node.leftNode = pre;
            // 改变标识，1 表示 leftNode 指向前驱节点
            node.leftType = 1;
        }

        // 中序遍历时，pre 的后继节点就是当前节点
        // 如果前驱节点的右子树为空
        if (pre != null && pre.rightNode == null) {
            // 将前驱节点的右指针指向当前节点
            pre.rightNode = node;
            // 改变标识，1 表示 rightNode 指向后继节点
            pre.rightType = 1;
        }

        // 将当前节点的值赋给前驱节点变量 pre
        pre = node;

        // 对右节点递归调用当前方法
        threadNodes(node.rightNode);
    }


    /**
     * 前序遍历
     */
    public void frontShow() {
        if (root == null) {
            System.out.println("树为空");
            return;
        }
        // 调用根结点的前序遍历方法
        root.frontShow();
        // 打印一个空行，与主逻辑无关
        System.out.println();
    }

    /**
     * 中序遍历
     */
    public void midShow() {
        if (root == null) {
            return;
        }
        root.midShow();
        System.out.println();
    }

    /**
     * 后序遍历
     */
    public void afterShow() {
        if (root == null) {
            return;
        }
        root.afterShow();
        System.out.println();
    }

    /**
     * 前序查找
     *
     * @param i
     * @return
     */
    public ThreadedNode frontSearch(int i) {
        return root.frontSearch(i);
    }

    /**
     * 删除方法
     *
     * @param i
     */
    public void delete(int i) {
        if (root.value == i) {
            root = null;
            return;
        }
        root.delete(i);
    }
}
