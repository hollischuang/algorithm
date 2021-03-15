package demo14;

import demo2.MyStack;

/**
 * 图
 *
 * @author admin
 */
public class Graph {
    /**
     * 顶点数组
     */
    Vertex[] vertex;
    /**
     * 数组下标，作为哈希表地址
     */
    int currentSize;
    /**
     * 二维数组定义的邻接矩阵
     */
    int[][] adjMat;
    /**
     * 创建栈用于存储顶点下标
     */
    MyStack stack = new MyStack();
    /**
     * 遍历时记录当前访问下标
     */
    int currentIndex;

    /**
     * 构造函数
     *
     * @param size
     */
    public Graph(int size) {
        // 定义顶点数组的容量
        this.vertex = new Vertex[size];
        // 根据顶点数量定义邻接矩阵
        this.adjMat = new int[size][size];
    }

    /**
     * 添加节点
     *
     * @param v
     */
    public void addVertex(Vertex v) {
        // 直接向数组中添加元素
        this.vertex[currentSize++] = v;
    }

    /**
     * 添加边
     * 方法与视频课程中稍有不同
     * 在同一个 for 循环里直接查找符合 v1 和 v2 的两个顶点
     * 加上各种判断是否为空的边界条件
     *
     * @param v1
     * @param v2
     */
    public void addEdge(String v1, String v2) {
        // 矩阵行坐标
        int index1 = -1;
        // 矩阵列坐标
        int index2 = -1;
        // 遍历数组，查找与指定值 v1 和 v2 相等的顶点
        for (int i = 0; i < vertex.length; i++) {
            // 获取当前位置的顶点对象
            Vertex vertex = this.vertex[i];
            // 顶点非空
            if (vertex != null) {
                // 找出符合 v1 和 v2 值的顶点
                // 将符合条件的对象在数组的下标赋值给 index1 和 index2
                if (vertex.getValue().equals(v1)) {
                    index1 = i;
                }
                if (vertex.getValue().equals(v2)) {
                    index2 = i;
                }
            }
        }
        // 同时找到了两个 index 值再去邻接矩阵查找元素
        if (index1 != -1 && index2 != -1) {
            // 将交叉位置赋值为 1，表示两个顶点之间的连接关系
            adjMat[index1][index2] = 1;
            adjMat[index2][index1] = 1;
        }
    }

    /**
     * 深度优先遍历方法
     */
    public void dfs() {
        // 0. 将访问下标初始值设为 0
        currentIndex = 0;
        // 1. 将第 0 个顶点压入栈中，标记为已访问
        stack.push(currentIndex);
        vertex[currentIndex].visited = true;
        // 5. 重复步骤 2~4，直到栈为空
       while (!stack.isEmpty()) {
            // 2. 从当前下标后一个位置起，遍历顶点数组，按序查找当前顶点与其后顶点之间的连接关系
            for (int i = currentIndex + 1; i < vertex.length; i++) {
                // adjMat[currentIndex][i] == 1 表示下标为 currentIndex 的顶点和下标为 i 的顶点相通
                // !vertex[i].visited 表示访问时遇到已访问的顶点则跳过
                if (adjMat[currentIndex][i] == 1 && !vertex[i].visited) {
                    // 打印顶点之间的连接关系
                    System.out.println(vertex[currentIndex].getValue() + " --> " + vertex[i].getValue());
                    // 将当前顶点下标入栈
                    stack.push(i);
                    // 访问过的顶点标记为已访问
                    vertex[i].visited = true;
                    // A --> B 连通时，currentIndex 对应 A，i 对应 B
                    // 继续查找时，让 currentIndex 顺延到当前 i 对应的顶点 B 即可
                    // 将 i 的值赋给当前下标 currentIndex
                    currentIndex = i;
                    /*
                    此处对课程视频中的代码做了改进，用 currentIndex = i 代替课程视频中的 continue out
                    按照课程视频中的代码
                    不对 currentIndex 修改，直接 continue out 跳出当前循环
                    再次进入 while 循环时，因为 currentIndex 没有变化，会重复执行 for 循环语句
                    执行到上一轮的 i 时，i 已在上一轮被标识位 visited，此时才跳过 i 继续执行
                    即从 continue out 到下一轮执行到 i 位置的操作都是不必要的重复
                    */
                }
            }
            // 3. 查询不到后续顶点的连接关系时，从栈中弹出栈顶元素
            stack.pop();
            // 步骤 4 弹出了栈顶元素，为保证栈非空，需要单独做判断
            if (!stack.isEmpty()) {
                // 4. 以新的栈顶元素为顶点继续查找连接关系
                currentIndex = stack.peek();
            } else {
                // 否则跳出循环
                break;
            }
        }
    }
}
