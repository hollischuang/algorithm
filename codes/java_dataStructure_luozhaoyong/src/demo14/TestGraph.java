package demo14;

import java.util.Arrays;

/**
 * 测试图
 *
 * @author admin
 */
public class TestGraph {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");
        Vertex v4 = new Vertex("D");
        Vertex v5 = new Vertex("E");

        Graph g = new Graph(5);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);

        g.addEdge("A", "C");
        g.addEdge("B", "C");
        g.addEdge("A", "B");
        g.addEdge("B", "D");
        g.addEdge("B", "E");

        // 遍历打印邻接矩阵，查看添加边的操作是否正确
        // 打印结果：
        // [0, 1, 1, 0, 0]
        // [1, 0, 1, 1, 1]
        // [1, 1, 0, 0, 0]
        // [0, 1, 0, 0, 0]
        // [0, 1, 0, 0, 0]
        for (int[] a : g.adjMat) {
            System.out.println(Arrays.toString(a));
        }

        // 执行深度优先遍历
        // 打印结果：
        // A --> B
        // B --> C
        // B --> D
        // B --> E
        g.dfs();

    }
}
