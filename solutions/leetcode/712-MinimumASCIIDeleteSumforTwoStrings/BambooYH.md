**712 最小的删除和**
[https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/](https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/)  
方法一：**动态规划**  
**思路**  
首先，这个题最暴力的解法是将所有的情况都考虑一遍，然后选出最小值。但是这种做法的时间复杂度是不能接受的。我们可以仔细分析一下这个题。假设有字符串A和字符串B，在寻找的过程中，我们最多有三种情况  
- 当前字符a和字符b相等，这种情况，无需处理，接着遍历即可
- 删除字符a，用字符a的下一个字符跟字符b比较
- 删除字符b，用字符b的下一个字符跟字符a比较

根据上面分析的情况，我们可以用动态规划来处理。我们用dp[i][j]表示A[i:]和B[j:]的最小删除和，所以我们最后要求的值就是dp[0][0].  
**算法**  
根据上面的分析，我们用公式来表示上述的三种情况，因为最终要求的是dp[0][0]，所以我们应该从后向前遍历。假设我们要求dp[i][j]，如果`s1[i] == s2[j]`,那么很明显不用删除，此时`dp[i][j] == dp[i+1][j+1]`.如果`s1[i] != s2[j]`,我们就要删除一个字符，要么删除s1[i],要么删除s2[j],此时该如何决定呢？根据题意，我们应该选择值小的那个，也就是`dp[i][j] = Math.min(s1[i]+dp[i+1][j],s2[j]+dp[i][j+1])`。  
**代码**  
```
class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        //获取两个字符串的长度
        int m = s1.length(), n = s2.length(), MAX = Integer.MAX_VALUE;
        //将字符串转换成字符数组，数组更好处理一些，直接用字符串进行处理，会更麻烦一些，但是也可以。
        char[] a = s1.toCharArray(), b = s2.toCharArray();
        //初始化辅助数组
        int[][] dp = new int[m + 1][n + 1];
        //从后向前遍历
        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                //当i==m或者j==n的时候，不用处理，因为s1[m]和s2[n]字符不存在。
                if (i < m || j < n)
                    //下面这个公式，就是算法部分说明的公式，只不过将他们写在一起，需要注意的是，要注意边界条件，因为上面if的条件是 ||,所以下面还要检测一下。
                    dp[i][j] = i < m && j < n && a[i] == b[j] ?
                        dp[i + 1][j + 1] : Math.min((i < m ? a[i] + dp[i + 1][j] : MAX), (j < n ? b[j] + dp[i][j + 1] : MAX));
            }
        }
        return dp[0][0];
    }
}
```
复杂度分析：  
假设字符串的长度分别为M,N  
空间复杂度：O(M*N),辅助数组 
时间复杂度：O(M*N),两重循环  

参考资料：  
[LeetCode Discuss](https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/discuss/108814/JavaC%2B%2B-Clean-Code)