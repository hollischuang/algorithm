package demo9;

/**
 * 定义赫夫曼树的节点
 *
 * @author admin
 */
public class Node implements Comparable<Node> {
    /**
     * 权值
     */
    int value;
    /**
     * 左子节点
     */
    Node left;
    /**
     * 右子节点
     */
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 覆写 compareTo 方法
     *
     * @param o 用于对比的目标节点
     * @return
     */
    @Override
    public int compareTo(Node o) {
        // 返回倒序结果，让根结点权值较大的二叉树排在前面
        return o.value - this.value;
    }

    /**
     * 覆写 toString 方法
     * @return
     */
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
