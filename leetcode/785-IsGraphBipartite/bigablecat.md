**785. 判断二分图**  
---
[https://leetcode-cn.com/problems/is-graph-bipartite/](https://leetcode-cn.com/problems/is-graph-bipartite/)  

* 网友高票Java解法：  

```java  

    /**
     * 网友高票Java解法
     *
     * 本题的思路是分别用两种颜色中的一种为每个节点染色
     * 查看相邻节点是否被染了相同的颜色
     * 0: 为染色
     * 1: 蓝色
     * -1: 红色
     *
     * @param graph
     * @return
     */
    public boolean isBipartite(int[][] graph) {
        //获取图的大小n
        int n = graph.length;
        // 创建一个大小为n的数组存储颜色
        int[] colors = new int[n];

        //遍历图中的所有节点
        for (int i = 0; i < n; i++) {
            //colors[i] == 0 获取当前位置的颜色并判断是否未染色
            //validColor(graph, colors, 1, i)查看i位置的节点，是否是蓝色
            if (colors[i] == 0 && !validColor(graph, colors, 1, i)) {
                //未染色且节点是蓝色则返回false
                return false;
            }
        }
        //符合条件，返回true
        return true;
    }

    /**
     * 为图中的节点染色
     *
     * @param graph
     * @param colors
     * @param color
     * @param node
     * @return
     */
    public boolean validColor(int[][] graph, int[] colors, int color, int node) {
        //colors[node] 获取当前位置node的颜色
        //colors[node] != 0表示当前节点已经染色
        if (colors[node] != 0) {
            //判断已染颜色是否与指定颜色值color相同
            return colors[node] == color;
        }
        //没染色则将当前节点用指定颜色值color染色
        colors[node] = color;
        //遍历graph[node]位置下的每个节点
        for (int next : graph[node]) {
            //判断next是否是红色
            if (!validColor(graph, colors, -color, next)) {
                //不是红色则直接返回false
                return false;
            }
        }
        //返回true
        return true;
    }


```  

**参考资料**  

* 网友高效答案：  
[https://leetcode.com/problems/is-graph-bipartite/discuss/115487/Java-Clean-DFS-solution-with-Explanation](https://leetcode.com/problems/is-graph-bipartite/discuss/115487/Java-Clean-DFS-solution-with-Explanation)  
