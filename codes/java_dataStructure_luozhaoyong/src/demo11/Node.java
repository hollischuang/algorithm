package demo11;

/**
 * 二叉排序树的节点
 *
 * @author admin
 */
public class Node {
    /**
     * 数据
     */
    int value;
    /**
     * 左子树
     */
    Node left;
    /**
     * 右子树
     */
    Node right;

    /**
     * 构造函数
     *
     * @param value
     */
    public Node(int value) {
        this.value = value;
    }

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(Node node) {
        // 如果 node 值小于当前节点
        if (node.value < this.value) {
            // 如果左子节点为空
            if (this.left == null) {
                // 赋值给左子节点
                this.left = node;
            } else {
                // 否则调用左子节点的添加方法
                this.left.add(node);
            }
        } else {// 如果 node 值大于当前节点
            // 如果右子节点为空
            if (this.right == null) {
                // 赋值给右子节点
                this.right = node;
            } else {
                // 否则调用右子节点的添加方法
                this.right.add(node);
            }
        }
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    public void midShow(Node node) {
        if (node == null) {
            return;
        }
        midShow(node.left);
        System.out.print(node.value + " ");
        midShow(node.right);
    }

    /**
     * 查找节点
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        if (this.value == value) {
            return this;
        }
        if (this.left != null && this.left.value == value) {
            return left;
        }
        if (this.right != null && this.right.value == value) {
            return right;
        }
        return null;
    }


    /**
     * 查找双亲节点
     *
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        // 如果左子节点非空
        if (this.left != null) {
            // 左子节点的值正好等于目标值
            if (this.left.value == value) {
                // 当前节点是目标值的双亲节点，返回当前节点
                return this;
            }
            // 如果目标值小于当前节点的值
            // 按照二叉查找树的性质，左子节点比双亲节点小
            // 在左子树继续查找
            if (value < this.value) {
                return this.left.searchParent(value);
            }
        }
        // 如果右子节点非空
        if (this.right != null) {
            // 由子节点的值正好等于目标值
            if (this.right.value == value) {
                // 当前节点是目标值的双亲节点，返回当前节点
                return this;
            }
            // 如果目标值大于当前节点的值
            // 按照二叉查找树的性质，右子节点比双亲节点大
            // 在右子树继续查找
            if (value > this.value) {
                return this.right.searchParent(value);
            }
        }
        // 都不符合说明目标值不存在树中，也没有双亲节点
        // 返回 null
        return null;
    }
}
