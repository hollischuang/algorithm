package demo11;

/**
 * 测试二叉排序树
 * @author admin
 */
public class TestBinarySortTree {
    public static void main(String[] args) {
        int[] arr = new int[]{7, 3, 10, 12, 5, 1, 9};
        BinarySortTree bst = new BinarySortTree();
        // 添加节点
        for (int i : arr) {
            bst.add(new Node(i));
        }
        // 中序遍历
        bst.midShow();

        // 查找节点
        Node node1 = bst.search(10);
        Node node2 = bst.search(20);
        System.out.println(node1);
        System.out.println(node2);

        // 删除叶子节点，值为 12 的节点没有孩子节点
        bst.delete(12);
        bst.midShow();

        // 要删除的节点只有一个子节点
        // 上个测试删除的节点 12 就是 10 的子节点
        // 目前 10 只剩下一个子节点 9
        bst.delete(10);
        bst.midShow();

        // 删除的节点有两个孩子节点
        bst.delete(7);
        bst.midShow();
    }
}
