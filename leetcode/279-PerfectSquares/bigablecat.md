**279. 完全平方数**  
---
[https://leetcode-cn.com/problems/perfect-squares/](https://leetcode-cn.com/problems/perfect-squares/)  

* 网友高票Java解法  

```java  

    /**
     * 1. 英文站网友高票Java解法
     * https://leetcode.com/problems/perfect-squares/discuss/71495/An-easy-understanding-DP-solution-in-Java
     *
     * 2. 中文站网友高票Java解法
     * https://leetcode-cn.com/problems/perfect-squares/solution/javati-jie-dong-tai-gui-hua-qiu-jie-by-pphdsny/
     *
     * @param n
     * @return
     */
    public static int numSquares(int n) {
        //定义一个长度为 n + 1 的整型数组
        //其中每个元素 dp[i] 都存了整数i所需的最少完全平方数的个数
        //比如dp[12] = 3，表示组成整数12最少需要3个完全平方数
        int[] dp = new int[n + 1];
        //用Java中整数的最大取值上限Integer.MAX_VALUE填充数组
        Arrays.fill(dp, Integer.MAX_VALUE);
        //将数组dp的首个元素赋值为0
        dp[0] = 0;
        //遍历数组
        for (int i = 1; i <= n; ++i) {
            // 将整数上限赋值给临时变量 min 作为初始值
            // min最终会记录组成整数i最少需要多少个完全平方数
            int min = Integer.MAX_VALUE;
            //内循环控制变量 j 从 1 开始
            int j = 1;
            // j*j 得到一个完全平方数
            // i - j * j >= 0 找到 i 这个数里最大的完全平方数
            while (i - j * j >= 0) {
                // dp[i - j * j] 的值表示组成 i - j * j 这个整数，最少需要多少个完全平方数
                // dp[i - j * j] + 1 表示在dp[i - j * j]原有个数的基础上
                // 加上 j*j 这个完全平方数，得到 dp[i] 所需的完全平方数
                // 每次while循环都比较min和dp[i - j * j] + 1的大小，取较小值
                min = Math.min(min, dp[i - j * j] + 1);
                ++j;
            }
            //经过while循环，从组成i的完全平方数的所有可能中
            //得到最少的个数 min 赋值给 dp[i]
            dp[i] = min;
        }
        // 经过for循环，n所包含的每个整数都取得了最少完全平方数的个数
        // dp[n]得到的也是最少个数
        return dp[n];
    }

```  

---

**参考资料**  

* 1. 英文站网友高票Java解法  
[https://leetcode.com/problems/perfect-squares/discuss/71495/An-easy-understanding-DP-solution-in-Java](https://leetcode.com/problems/perfect-squares/discuss/71495/An-easy-understanding-DP-solution-in-Java)  

* 2. 中文站网友高票Java解法  
[https://leetcode-cn.com/problems/perfect-squares/solution/javati-jie-dong-tai-gui-hua-qiu-jie-by-pphdsny/](https://leetcode-cn.com/problems/perfect-squares/solution/javati-jie-dong-tai-gui-hua-qiu-jie-by-pphdsny/)  
