**207. 课程表**  
---
[https://leetcode-cn.com/problems/course-schedule/](https://leetcode-cn.com/problems/course-schedule/)  

* 网友高票Java解法  

```java  

    /**
     * 
     * 网友高票Java解法
     *
     * 做这道题目首先需要明确几个知识点：
     * 《算法》第四版中文，作者Sedgewick
     * 1. 有向图（P364）：
     * 一副有向图是由一组顶点和一组有方向的边组成的，
     * 每条有方向的边都连接着有序的一对顶点
     *
     * 2. 有向路径（P364）：
     * 一副有向图中，有向路径由一系列顶点组成，
     * 对于其中的每个顶点都存在一条有向边，
     * 从它指向序列中的下一个顶点
     *
     * 3. 有向环（P364）：
     * 有向环为一条至少含有一条边且起点和终点相同的有向路径
     *
     * 4. 有向无环图（P371）：
     * 有向无环图（DAG）就是一副不含有向环的有向图
     *
     * 5. 拓扑排序（P371）：
     * 给定一副有向图，将所有顶点排序，
     * 使得所有的有向边都从排在前面的元素指向排在后面的元素
     *
     * 6. 入度（P364）：
     * 一个顶点的入度为指向该顶点的边的总数
     *
     * 本题查找有向图中是否存在环，即入度是否大于1
     *
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        //创建大小为numCourses*numCourses的矩阵
        //matrix记录有课程之间依赖关系的状态
        //如 matrix[0][1] = 1 表示课程0是课程1的先修课
        int[][] matrix = new int[numCourses][numCourses]; // i -> j
        //建立一个大小为numCourses的数组indegree
        //每门课代表有向图的一个顶点，共有numCourses个顶点
        //indegree记录每个顶点的“入度”，即指向该顶点的边的总数
        int[] indegree = new int[numCourses];

        //遍历课程列表prerequisites
        //prerequisites是一个二维数组，其中的每个元素又是一个数组
        for (int i=0; i<prerequisites.length; i++) {
            //prerequisites[i]得到一个数组
            //prerequisites[i]排在前面的元素依赖排在后面的元素
            //获取将要学习的课程0，赋值给readay
            int ready = prerequisites[i][0];
            //获取课程0的先修课程1，赋值给pre
            int pre = prerequisites[i][1];
            //判断从pre指向ready的先后关系是否已经被matrix记录
            if (matrix[pre][ready] == 0)
                //如果没有记录，将顶点ready的入度加1
                indegree[ready]++;
            //记录pre到matrix的入度已经被计算过
            //避免重复计算入度
            matrix[pre][ready] = 1;
        }


        //定义一个计数器count，初始值为0
        //count用于统计顶点的个数
        int count = 0;
        //定义一个队列queue，用于存储所有入度为0的顶点
        Queue<Integer> queue = new LinkedList();
        //遍历indegree，获取每个顶点的入度
        for (int i=0; i<indegree.length; i++) {
            //如果第i个顶点入度为0，将其添加到queue中
            if (indegree[i] == 0) queue.offer(i);
        }

        //如果队列不为空，while循环遍历队列获取入度为0的顶点
        while (!queue.isEmpty()) {
            //依次取出队列中的顶点
            int course = queue.poll();
            //计数器递增
            count++;
            //遍历所有顶点，查找当前顶点course与其他顶点的关系
            for (int i=0; i<numCourses; i++) {
                //查看course是否为i的先修课
                if (matrix[course][i] != 0) {
                    //如果course是i的先修课
                    //将i的入度减1
                    //--indegree[i]为0，即所有入度都被消去
                    if (--indegree[i] == 0)
                        //将入度为0的顶点放入queue
                        queue.offer(i);
                }
            }
            //while循环部分实际上进行了一次逆序遍历
            //从入度为0的顶点为起始点，反向回溯每一个顶点
            //while循环内的for循环消去所有顶点的入度值
            //当顶点入度为0时，将被压入queue
            //即queue进入while循环之前，存放了所有入度为0的顶点
            //queue进入while循环之后，又存放了之前剩余的入度非0的顶点
            //每进入一次while循环，count将递增1
            //所以while循环结束时，count正好是所有顶点的总数
        }
        //检查count是否与课程总数numCourses相等
        //如果相等，表示有向图中不存在有向环，存在拓扑排序
        return count == numCourses;
    }

```  

---

**参考资料**  

* 网友高票Java解法：  
[https://leetcode.com/problems/course-schedule/discuss/58516/Easy-BFS-Topological-sort-Java](https://leetcode.com/problems/course-schedule/discuss/58516/Easy-BFS-Topological-sort-Java)  
