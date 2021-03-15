package demo7;


/**
 * 测试线索二叉树
 * @author admin
 */
public class TestThreadedBinaryTree {
    public static void main(String[] args) {
        /* 第 30 课，创建二叉树*/
        // 创建一棵二叉树
        ThreadedBinaryTree binTree = new ThreadedBinaryTree();

        // 创建一个节点作为根结点
        ThreadedNode root = new ThreadedNode(1);
        // 设置根结点
        binTree.setRoot(root);

        // 创建一个左节点
        ThreadedNode leftNode = new ThreadedNode(2);
        // 设置左节点
        root.setLeftNode(leftNode);

        /* 第 31 课，遍历二叉树*/
        // 创建一个右节点
        ThreadedNode rightNode = new ThreadedNode(3);
        // 设置右节点
        root.setRightNode(rightNode);

        // 为第二层的左节点创建左右两个子节点
        leftNode.setLeftNode(new ThreadedNode(4));
        ThreadedNode fiveNode = new ThreadedNode(5);
        leftNode.setRightNode(fiveNode);

        // 为第二层的右节点创建左右两个子节点
        rightNode.setLeftNode(new ThreadedNode(6));
        rightNode.setRightNode(new ThreadedNode(7));

        // 调用中序遍历方法
        // 执行结果：4 2 5 1 6 3 7
        binTree.midShow();

        // 中序线索化二叉树
        binTree.threadNodes();

        // 找到节点 5 的后继节点
        ThreadedNode afterFive = fiveNode.rightNode;
        // 打印后继节点的值
        // 执行结果为 1
        System.out.println(afterFive.value);

        // 线索化二叉树之后，遍历所有节点
        // 执行结果：4 2 5 1 6 3 7
        binTree.threadIterate();

    }
}
