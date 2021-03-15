package demo9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 测试赫夫曼树
 * @author admin
 */
public class TestHuffmanTree {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 7, 8, 29, 5, 11, 23, 14};
        Node node = createHuffmanTree(arr);
        System.out.println(node);
    }

    /**
     * 创建赫夫曼树
     *
     * @param arr 整数数组
     * @return
     */
    public static Node createHuffmanTree(int[] arr) {
        // 创建一个列表用于存储二叉树节点
        List<Node> nodes = new ArrayList<>();
        // 1. 将整数数组中的元素转化为二叉树
        for (int value : arr) {
            // 用 value 作为权值
            // 创建二叉树节点并存入 nodes 列表中
            nodes.add(new Node(value));
        }

        // 当 nodes 列表的元素数目大于 1 时循环执行
        while (nodes.size() > 1) {
            // 2. 根据二叉树根结点的权值进行倒序排列
            Collections.sort(nodes);
            // 3. 从 nodes 列表中取出根结点权值最小的两个元素
            // 因为是倒序排列，依次取出倒数第 1 个和倒数第 2 个
            // 倒数第 1 个元素作为左子节点
            Node left = nodes.get(nodes.size() - 1);
            // 倒数第 2 个元素作为右子节点
            Node right = nodes.get(nodes.size() - 2);
            // 4. 创建一个新二叉树，新二叉树根结点的权值是上述两个节点权值的和
            Node parent = new Node(left.value + right.value);
            // 5. 新节点与孩子节点建立连接
            // 这一步骤在本节课的课程视频中没有出现，不影响最终结果
            parent.left = left;
            parent.right = right;
            // 6. 从原有列表中移除刚才使用过的两个权值最小节点
            nodes.remove(left);
            nodes.remove(right);
            // 7. 将新元素加入 nodes 列表
            nodes.add(parent);
        }
        // 循环中每次移除 2 个元素，增加 1 个元素
        // 总体元素数量随着循环递减
        // 循环结束后，nodes 列表中只剩下 1 个元素
        // 返回 nodes 列表中的元素
        return nodes.get(0);
    }
}
