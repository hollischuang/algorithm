package demo12;

/**
 * 测试平衡二叉树
 * @author admin
 */
public class TestBinarySortTree {
    public static void main(String[] args) {
        int[] arr = new int[]{8, 9, 6, 7, 5, 4};
        BinarySortTree bst = new BinarySortTree();
        // 添加节点
        for (int i : arr) {
            bst.add(new Node(i));
        }
        // 打印结果为 3
        System.out.println(bst.root.height());
        // 打印结果为 6
        System.out.println(bst.root.value);

        System.out.println("=======================");

        // 重新创建一棵平衡二叉树，测试左旋
        arr = new int[]{2, 1, 4, 3, 5, 6};
        bst = new BinarySortTree();
        // 添加节点
        for (int i : arr) {
            bst.add(new Node(i));
        }
        // 打印结果为 3
        System.out.println(bst.root.height());
        // 打印结果为 4
        System.out.println(bst.root.value);

        System.out.println("=======================");

        // 重新创建一棵平衡二叉树，测试双旋转
        arr = new int[]{8, 9, 5, 4, 6, 7};
        bst = new BinarySortTree();
        // 添加节点
        for (int i : arr) {
            bst.add(new Node(i));
        }
        // 打印结果为 3
        System.out.println(bst.root.height());
        // 打印结果为 6
        System.out.println(bst.root.value);

    }
}
