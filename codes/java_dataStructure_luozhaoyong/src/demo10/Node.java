package demo10;

/**
 * 定义赫夫曼树的节点
 *
 * @author admin
 */
public class Node implements Comparable<Node> {
    /**
     * 字符数据
     */
    Byte data;
    /**
     * 权重
     */
    int weight;
    /**
     * 左子节点
     */
    Node left;
    /**
     * 右子节点
     */
    Node right;

    /**
     * 构造方法
     *
     * @param data
     * @param weight
     */
    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    /**
     * 覆写原有比较方法
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Node o) {
        // 返回倒序结果
        return o.weight - this.weight;
    }

    @Override
    public String toString() {
        return "Node{data=" + data + ", weight=" + weight + "}";

    }

}
