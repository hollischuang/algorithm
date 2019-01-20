**不同路径**  

[https://leetcode.com/problems/unique-paths/](https://leetcode.com/problems/unique-paths/)

方法一：**动态规划**  
**思路**  
这道题是一道特别经典的动态规划题，而且有助于理解动态规划。首先分析题意，这部是最重要的，只有认真分析题意，才可以找出正确的状态转移方程。首先题目要求从左上角，走到右下角。在每次移动的过程中，只有两个方向可以选择，要么往右走，要么往下走。这是非常关键的，也就是说，如果我们走到了`array[i][j]`这个点，那上一步要么在这个点的上边`array[i-1][j]`,要么在这个点的左边`array[i][j-1]`，只有这两种情况。换句话说，如果我们用dp[i][j]表示到array[i][j]这个点的路径数，那么`dp[i][j] = dp[i-1][j] + dp[i][j-1]`,这个肯定是正确的,因为只有上述描述的两种情况。
**算法**  
我们从上到下，从左到右遍历，然后利用公式`dp[i][j] = dp[i-1][j] + dp[i][j-1]`算出最终结果。  
```
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        //初始化，到达dp[0][j]只有1条路径，因为只能往右和下走
        for(int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        //初始化，到达dp[i][0]只有一条路径
        for(int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        //遍历
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        
        return dp[m-1][n-1];
    }
}
```
复杂度分析：  
假设矩阵的行数为M，列数为N
时间复杂度：O(M*N)  
空间复杂度：O(M*N)  

## 优化
上述代码可以进行空间优化，我们可以看到，dp[i][j]只与两个数值有关，而且这两个数值，一个是它左边的，一个是它右边的，所以我们可以只用一个大小为n的一维数组。具体代码如下  
```
class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        //初始化
        for(int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        for(int i = 1; i < m; i++) {
             for(int j = 1; j < n; j++) {
                 //右边的dp[j]其实就相当于dp[i-1][j],因为这个值是更新之前的值，dp[j-1]相当于dp[i][j-1].
            dp[j] = dp[j-1] + dp[j];
        }
        }
       
        return dp[n-1];
    }
}
```
复杂度分析：  
假设矩阵的行数为M，列数为N  
时间复杂度：O(M*N)  
空间复杂度：O(N)  