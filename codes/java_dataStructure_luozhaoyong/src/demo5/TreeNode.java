package demo5;

/**
 * 二叉树节点
 * @author admin
 */
public class TreeNode {
    /**
     * 节点的权值
     */
    int value;
    /**
     * 左节点
     */
    TreeNode leftNode;
    /**
     * 右节点
     */
    TreeNode rightNode;

    /**
     * 构造方法
     *
     * @param value 权值参数
     */
    public TreeNode(int value) {
        this.value = value;
    }

    public void setLeftNode(TreeNode node) {
        leftNode = node;
    }

    public void setRightNode(TreeNode node) {
        rightNode = node;
    }

    /**
     * 前序遍历
     * <p>
     * 当前节点-->左子节点-->右子节点
     */
    public void frontShow() {
        // 获取当前节点的值
        System.out.print(value + " ");
        // 获取左子节点的值
        if (leftNode != null) {
            leftNode.frontShow();
        }
        // 获取右子节点的值
        if (rightNode != null) {
            rightNode.frontShow();
        }
    }

    /**
     * 中序遍历
     * <p>
     * 左子节点-->当前节点-->右子节点
     */
    public void midShow() {
        // 获取左子节点的值
        if (leftNode != null) {
            leftNode.midShow();
        }

        // 获取当前节点的值
        System.out.print(value + " ");

        // 获取右子节点的值
        if (rightNode != null) {
            rightNode.midShow();
        }
    }

    /**
     * 后序遍历
     * <p>
     * 左子节点-->右子节点-->当前节点
     */
    public void afterShow() {
        // 获取左子节点的值
        if (leftNode != null) {
            leftNode.afterShow();
        }

        // 获取右子节点的值
        if (rightNode != null) {
            rightNode.afterShow();
        }

        // 获取当前节点的值
        System.out.print(value + " ");
    }

    /**
     * 前序查找
     *
     * @return
     */
    public TreeNode frontSearch(int i) {
        // 定义一个变量作为返回值
        TreeNode target = null;
        // 查看当前值是否与目标值相等
        if (value == i) {
            return this;
        }
        // 如果左子节点不为空
        if (leftNode != null) {
            // 在左子节点递归调用当前查找方法
            target = leftNode.frontSearch(i);
        }
        // 如果左子节点查找结果不为空
        if (target != null) {
            // 返回结果
            return target;
        }
        // 如果右子节点不为空
        if (rightNode != null) {
            // 在右子节点递归调用当前查找方法
            target = rightNode.frontSearch(i);
        }
        // 返回目标结果
        return target;
    }

    /**
     * 递归删除子树
     *
     * @param i
     */
    public void delete(int i) {
        // 将当前节点作为父节点赋值给变量 parent
        TreeNode parent = this;
        // 左子节点的值等于指定值，则删除左子节点
        if (parent.leftNode != null && parent.leftNode.value == i) {
            // 将左子节点赋值为空，即删除了左子节点
            parent.leftNode = null;
            return;
        }
        // 右子节点的值等于指定值，则删除左子节点
        if (parent.rightNode != null && parent.rightNode.value == i) {
            // 将右子节点赋值为空，即删除了左子节点
            parent.rightNode = null;
            return;
        }
        // 将左子节点赋值给父节点变量
        parent = leftNode;
        // 如果节点不为空
        if (parent != null) {
            // 递归调用删除方法
            parent.delete(i);
        }
        // 将右子节点赋值给父节点变量
        parent = rightNode;
        // 如果节点不为空
        if (parent != null) {
            // 递归调用删除方法
            parent.delete(i);
        }
    }
}
