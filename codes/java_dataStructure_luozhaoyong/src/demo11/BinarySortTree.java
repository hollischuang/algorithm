package demo11;

/**
 * 二叉排序树
 *
 * @author admin
 */
public class BinarySortTree {
    /**
     * 根结点
     */
    Node root;

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void midShow() {
        if (root != null) {
            root.midShow(root);
            System.out.println();
        }
    }

    /**
     * 查找节点
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        if (root != null) {
            return root.search(value);
        }
        return null;
    }

    /**
     * 删除节点
     *
     * 课程视频中删除节点的代码判断逻辑比较多，且代码没有复用
     * 这里用另一种思路重新实现了删除节点方法
     *
     * @param value
     */
    public void delete(int value) {
        if (root == null) {
            return;
        }
        // 如果正好等于根结点，则删除根结点
        if (root.value == value) {
            // 找到 root 被删除后的替代节点赋值给 root
            root = findSuccessor(root);
            return;
        }
        // 1. 查找父节点
        Node parent = root.searchParent(value);
        // 如果父节点为空，说明目标值不在树中
        if (parent == null) {
            return;
        }

        // 2. 删除目标节点
        if (parent.left != null && parent.left.value == value) {
            // 如果目标节点是父节点的左子节点
            // 为父节点的左子节点建立新连接
            parent.left = findSuccessor(parent.left);
        } else {
            // 如果目标节点是父节点的右子节点
            // 为父节点的右子节点建立新连接
            parent.right = findSuccessor(parent.right);
        }
    }

    /**
     * 寻找目标节点被删除后的替代节点
     *
     * @param target
     */
    private Node findSuccessor(Node target) {
        // 3. 分三种情况删除目标节点
        // 3.1 如果目标节点没有孩子节点
        if (target.left == null && target.right == null) {
            // 返回 null，直接断开与父节点的连接
            return null;
        }
        // 3.2 如果目标节点有两个孩子节点
        if (target.left != null && target.right != null) {
            // 删除目标节点的最小子节点，并返回最小子节点的值
            // 将最小子节点的值赋给要删除的目标节点
            target.value = deleteMin(target);
            // 仍然返回目标节点
            return target;
        }
        // 3.3 如果目标节点只有一个孩子节点
        // 只有一个左子节点时
        if (target.left != null) {
            // 将目标节点的左子节点与父节点建立连接
            return target.left;
        } else { // 只有一个右子节点时
            // 将目标节点的左子节点与父节点建立连接
            return target.right;
        }
    }

    /**
     * 删除目标节点的最小子节点
     * 并返回最小子节点的值
     *
     * @param target
     * @return
     */
    private int deleteMin(Node target) {
        // 定义临时变量 minNode 用于缓存 target 的最小子节点
        Node minNode = target;
        // 定义临时变量 parent
        // 用于存储 target 最小子节点的父节点
        Node parent = minNode;
        // 查找 target 的最小子节点
        while (minNode.left != null) {
            parent = minNode;
            minNode = minNode.left;
        }
        // 循环结束时，minNode 是最小子节点
        // parent 是最小子节点的父节点
        // 如果最小子节点的右子节点为空
        if (minNode.right == null) {
            // 直接删除最小子节点
            parent.left = null;
            // 最小子节点本身就没有左子节点，无需检查左子节点是否存在
        } else {
            // 否则将父节点的左指针指向最小子节点的右子节点
            parent.left = minNode.right;
        }
        // 返回最小子节点的值
        return minNode.value;
    }
}
