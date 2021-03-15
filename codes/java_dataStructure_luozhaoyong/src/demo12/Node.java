package demo12;

/**
 * 平衡二叉树的节点
 * 重用了 demo11 二叉排序树的节点
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
     * 获取当前树的高度
     *
     * @return
     */
    public int height() {
        // 取左右子树中高度的最大值，在最大值基础上增加 1
        return Math.max(this.left == null ? 0 : this.left.height(), this.right == null ? 0 : this.right.height()) + 1;
    }

    /**
     * 获取指定树的高度
     * <p>
     * 课程视频中分别为左右子树高度写了重复的方法
     * 此处为同一段代码复用
     *
     * @param node
     * @return
     */
    public int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height();
    }

    /**
     * 右旋
     */
    public void rightRotate() {
        // 1. node --> newNode
        Node newRight = new Node(this.value);
        // 2. node.right --> newNode.right
        newRight.right = this.right;
        // 3. node.left.right --> newNode.left
        newRight.left = left.right;
        // 4. node.left --> node
        this.value = left.value;
        // 5. node.left.left --> node.left
        this.left = left.left;
        // 6. newNode --> node.right
        this.right = newRight;
    }

    /**
     * 左旋
     */
    public void leftRotate() {
        // 1. node --> newNode
        Node newRight = new Node(this.value);
        // 2. node.left --> newNode.left
        newRight.left = this.left;
        // 3. node.right.left --> newNode.right
        newRight.right = right.left;
        // 4. node.right --> node
        this.value = right.value;
        // 5. node.right.right --> node.right
        this.right = right.right;
        // 6. newNode --> node.left
        this.left = newRight;
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

        // 判断是否为平衡二叉树，如果不是平衡树，需要重新调整
        if (height(left) - height(right) > 1) {
            // 如果 left.left 高度 < left.right 高度
            // 要进行双旋转
            if (left.left != null && height(left.left) < height(left.right)) {
                // 首先对 left 左旋
                left.leftRotate();
            }
            // 调用右旋方法
            rightRotate();
        } else if (height(right) - height(left) > 1) {
            // 如果 right.right 高度 < right.left 高度
            // 要进行双旋转
            if (right.right != null && height(right.right) < height(right.left)) {
                // 首先对 right 右旋
                right.rightRotate();
            }
            // 调用左旋方法
            leftRotate();
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
