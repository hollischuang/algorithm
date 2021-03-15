package demo5;

/**
 * 二叉树
 * @author admin
 */
public class BinaryTree {
    /**
     * 根结点
     */
    TreeNode root;


    /**
     * 设置根结点
     *
     * @param node 节点参数
     */
    public void setRoot(TreeNode node) {
        root = node;
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
    public TreeNode frontSearch(int i) {
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
