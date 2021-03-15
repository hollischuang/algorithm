**684. 冗余连接**  
---
[https://leetcode-cn.com/problems/redundant-connection/](https://leetcode-cn.com/problems/redundant-connection/)  

* 英文官方题解2：Union-Find  

```java  

  int MAX_EDGE_VAL = 1000;

    /**
     * https://leetcode.com/articles/redundant-connection/
     * 英文官方题解2：Union-Find
     *
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        //创建一个大小为1000+1的并查集
        DSU dsu = new DSU(MAX_EDGE_VAL + 1);
        //遍历所有的边
        for (int[] edge: edges) {
            //对每条边的两个顶点进行合并
            //如果union方法返回false，说明两个顶点已经在同个集合中
            //此时当前的边即为冗余边，返回edge
            if (!dsu.union(edge[0], edge[1])) return edge;
        }
        //遍历完成后仍然未找到冗余边，返回Error
        throw new AssertionError();
    }

    /**
     * 定义一个并查集类
     */
    class DSU {
        //父节点数组
        int[] parent;
        //树深度
        int[] rank;

        /**
         * 构造函数
         * 创建一个大小为size的DSU实例
         *
         * @param size
         */
        public DSU(int size) {
            //初始化父节点数组，大小为size
            parent = new int[size];
            //遍历数组，初始化并查集，让每个父节点指向自己
            for (int i = 0; i < size; i++) parent[i] = i;
            //节点高度，或者说节点的辈分
            //rank值越高，节点越靠近根节点
            rank = new int[size];
            //初始化完成后，得到这样一个并查集
            //所有节点的高度一致
            //所有节点的父节点等于自身，即每个节点自成一组
            //初始化分组数量为size
        }

        /**
         * 查找指定节点x的根节点
         * @param x
         * @return
         */
        public int find(int x) {
            //如果当前节点的父节点不等于自身
            //递归调用find方法向上查找根节点
            //同时进行了路径压缩
            // parent[x] 不断指向更高层的节点
            if (parent[x] != x) parent[x] = find(parent[x]);
            //返回最终结果parent[x]
            return parent[x];
        }

        /**
         * 合并两个节点x和y
         *
         * @param x
         * @param y
         * @return
         */
        public boolean union(int x, int y) {
            //分别找到x和y的根节点
            int xr = find(x), yr = find(y);
            //如果两个根节点相等，返回false，无须合并
            if (xr == yr) {
                return false;
            } else if (rank[xr] < rank[yr]) {
                //当y的根节点yr rank更高时
                //让x的根节点xr指向yr
                parent[xr] = yr;
            } else if (rank[xr] > rank[yr]) {
                //同理，让y的根节点yr指向更高的xr
                parent[yr] = xr;
            } else {
                // 当两者的rank值相等时
                // 其中一个指向另一个即可
                parent[yr] = xr;
                //同时被指向的节点rank值递增1
                rank[xr]++;
            }
            return true;
        }
    }

```  

**复杂度分析**  

时间复杂度：O(N·α(N))≈O(N),
其中N是边的数目，
α表示Inverse-Ackermann方程，
因为对dsu.union进行了N次调用，
所以时间复杂度是α(N)，
而α(N)近似于1，所以最终时间复杂度是O(N)

空间复杂度：O(N),
构造DSU对象时，
其中的数组只使用了大小为N的额外空间

---

**参考资料**  

* 英文官方题解：  
[https://leetcode.com/articles/redundant-connection/](https://leetcode.com/articles/redundant-connection/)  
