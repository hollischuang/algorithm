package demo5;

/**
 * 测试二叉树
 * @author admin
 */
public class TestBinaryTree {
    public static void main(String[] args) {
        /* 第 30 课，创建二叉树*/
        // 创建一棵二叉树
        BinaryTree binTree = new BinaryTree();

        // 创建一个节点作为根结点
        TreeNode root = new TreeNode(1);
        // 设置根结点
        binTree.setRoot(root);

        // 创建一个左节点
        TreeNode leftNode = new TreeNode(2);
        // 设置左节点
        root.setLeftNode(leftNode);

        // 创建一个右节点
        TreeNode rightNode = new TreeNode(3);
        // 设置右节点
        root.setRightNode(rightNode);

        /* 第 31 课，遍历二叉树*/

        // 为第二层的左节点创建左右两个子节点
        leftNode.setLeftNode(new TreeNode(4));
        leftNode.setRightNode(new TreeNode(5));

        // 为第二层的右节点创建左右两个子节点
        rightNode.setLeftNode(new TreeNode(6));
        rightNode.setRightNode(new TreeNode(7));

        // 调用前序遍历方法
        binTree.frontShow();

        // 调用中序遍历方法
        binTree.midShow();

        // 调用后序遍历方法
        binTree.afterShow();

        // 调用前序查找方法，查找节点值为 2 的节点
        TreeNode result = binTree.frontSearch(2);
        // 检查当前结果是否为根结点的左子节点
        System.out.println(result == leftNode);

        // 测试删除节点的方法
        binTree.delete(5);
        // 前序遍历显示节点是否被删除
        // 打印结果是 1 2 4 3 6 7
        binTree.frontShow();
    }
}
