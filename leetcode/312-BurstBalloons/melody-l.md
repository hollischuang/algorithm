**312. burst-balloons**  

---
[https://leetcode-cn.com/problems/burst-balloons/](https://leetcode-cn.com/problems/burst-balloons/)  

* 该问题主要难推导出状态转移方程。  
1. 如果是贪心的思路，即每次选择都是最大值，是无法得到最优解的。例如本题中的例子，第一步选择的是1而不是5，如果按照贪心的思路应该选择的是5。
2. 如果死dp的思路，中间态的定义是：设`dp[i][j]`表示的是 **序列nums在从i到j都戳破的情况下，得到的硬币的最大值**。因此，`dp[1][n]`表示的是都戳破得到硬币的最大值，即为我们所求。此时，**假设nums从i到j，所有的气球都戳破了而最后戳破的是k**，则此时的硬币分数为`nums[i-1] * nums[k] * nums[j+1]` 加上之前的分数。而之前的分数是`dp[i][k-1]`和`dp[k+1][j]`，即`dp[i][j] = nums[i-1] * nums[k] * nums[j+1] + dp[i][k-1] + dp[k+1][j]`。因此，若dp表示的是最大值，则算法思路为从i到j遍历，求出来的最大值即为所求。故递推式为： `dp[i][j] = Max{ nums[i-1] * nums[k] * nums[j+1] + dp[i][k-1] + dp[k+1][j] }， 其中k表示从i到j`。

* 有了递推式后，需要确定遍历顺序。根据递推式可以看出，递推的顺序是按照序列的长度递增来的，即先遍历序列长度为1的组合，后遍历序列长度为2的组合。

---

方法一：动态规划  

```java  

public class Solution {
    public int maxCoins(int[] nums) {
        // 为了计算方便，使用新的数组来代替之前的数组
        // 新的数组在nums的前面加上一个1，在末尾加上了一个1，
        // 这样能够统一处理，
        // 因此下面遍历的时候，是从1开始，到nums.length结束，包括nums.length
        int[] newNum = new int[nums.length + 2];
        System.arraycopy(nums, 0, newNum, 1, nums.length);
        newNum[0] = 1;
        newNum[nums.length+1] = 1;
        // 用来存储所有的dp结果
        // dp[i][j]表示从i到j所有的气球都戳破所得到的最大值
        int[][] dp = new int[nums.length + 2][nums.length + 2];

        // 由上文分析可知，从nums序列长度为1的开始遍历，
        // 注意：长度为1，此处length为0。
        for (int length = 0; length < nums.length; length++) {
            // 开始计算当nums长度一定的情况下，所有的可能组合的dp值
            for (int i = 1; i <= nums.length - length; i++) {
                // 计算长度为length+1的时候，末尾j应该处于的位置，
                // 此处不需要担心j超过数组长度，
                // 因为i的遍历进行了限制，i只会遍历到最后一个满足length+1长度的起始索引位置
                int j = i + length;
                // 确定了i与j的范围，
                // 根据递推式从i到j顺序遍历，找到dp[i][j]的最大值
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], newNum[i - 1] * newNum[k] * newNum[j+1 ] + dp[i][k - 1] + dp[k + 1][j]);
                }
            }
        }

        return dp[1][nums.length];
    }
}

```  

**参考资料**  
* 网友答案：   
[https://www.cnblogs.com/grandyang/p/5006441.html](https://www.cnblogs.com/grandyang/p/5006441.html)  
