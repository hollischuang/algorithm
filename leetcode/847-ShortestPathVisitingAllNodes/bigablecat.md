**847. 访问所有节点的最短路径**  
---
[https://leetcode-cn.com/problems/shortest-path-visiting-all-nodes/](https://leetcode-cn.com/problems/shortest-path-visiting-all-nodes/)  


```java  

    public int shortestPathLength2(int[][] graph) {
        //定义一个整数N，N是图graph节点的数目
        int N = graph.length;
        // 1<<N是位运算操作，即将1带符号向左位移N位
        // 1的二进制数最后8位是0000 0001
        // 如1<<2表示将0000 0001右移2位，得到0000 0100，位移后的二进制数用十进制表示等于4=2^2
        // 如1<<3表示将0000 0001右移3位，得到0000 1000，位移后的二进制数用十进制表示等于8=2^3
        // 可以看出将1右移N位等价于1*2^N
        // 定义一个二维数组dist用于记录所有可能的最短路径
        // [1 << N][N]表示二维数组的行数是1 << N = 2^N，列数是N
        int dist[][] = new int[1 << N][N];
        //遍历二维数组dist的每一行
        for (int[] row : dist) {
            //为整数数组row填充初始值N*N
            Arrays.fill(row, N * N);
        }
        //迭代N次
        for (int x = 0; x < N; ++x) {
            //将第2^x行的x列赋值为0
            dist[1 << x][x] = 0;
        }

        //for循环迭代(1 << N)次，即2^N次，遍历graph的所有行
        //控制变量cover用于获取二维数组的每一行
        //使用dist[cover]得到一个整数数组，该数组存放了当前路线经过的所有节点
        for (int cover = 0; cover < 1 << N; ++cover) {
            //定义boolean值repeat用于判断是否为重复访问
            boolean repeat = true;
            //当重复访问时进入while循环
            while (repeat) {
                //将重复访问标识repeat定义为false
                repeat = false;
                //设graph的每个节点的编号为head，迭代N次，遍历所有节点
                for (int head = 0; head < N; ++head) {
                    //dist[cover]得到当前路径经过的所有节点
                    //dist[cover][head]存放当前路径到节点head的最短路径
                    //定义一个整数d作为dist[cover][head]的临时变量
                    int d = dist[cover][head];
                    //graph[head]获得当前节点head连通的所有其他节点
                    for (int next : graph[head]) {
                        //对当前与head连通的节点next进行位运算操作
                        //首先将1向右位移next个单位，即求2^next
                        //再将cover与(1 << next)的结果进行按位或运算
                        //cover2就是当前路径到节点head经过的最短路径
                        int cover2 = cover | (1 << next);
                        if (d + 1 < dist[cover2][next]) {
                            dist[cover2][next] = d + 1;
                            //如果cover == cover2，表示两个节点编号相等，即进行了重复访问
                            if (cover == cover2) {
                                repeat = true;
                            }
                        }
                    }
                }
            }
        }

        //定义一个初始值为N*N的整数ans作为最终返回结果
        int ans = N * N;
        //遍历二维数组dist的最后一行
        for (int cand : dist[(1 << N) - 1]) {
            //对比当前列的数值cand与ans，将较小值赋给ans
            ans = Math.min(cand, ans);
        }
        //返回最终结果ans即是最短路线
        return ans;
    }

```  

**复杂度分析**  

空间复杂度：O(2^N*N)，
本方法定义了二维数组dist[][]空间复杂度为2^N*N，

---

**参考资料**  

* 本题leetCode英文官方题解：  
[https://leetcode.com/articles/shortest-path-visiting-all-nodes/](https://leetcode.com/articles/shortest-path-visiting-all-nodes/)  
