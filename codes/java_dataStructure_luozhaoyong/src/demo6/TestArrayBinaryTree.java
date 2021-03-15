package demo6;

/**
 * 测试顺序存储二叉树
 * @author admin
 */
public class TestArrayBinaryTree {
    public static void main(String[] args) {
        // 创建数组
        int[] data = new int[]{1, 2, 3, 4, 5, 6, 7};
        // 创建顺序存储二叉树对象
        ArrayBinaryTree binTree = new ArrayBinaryTree(data);

        // 调用前序遍历方法
        // 打印结果是 1 2 4 5 3 6 7
        binTree.frontShow();
    }
}
