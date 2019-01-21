279.完全平方数

https://leetcode-cn.com/problems/perfect-squares/

给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

示例 1:

输入: n = 12
输出: 3 
解释: 12 = 4 + 4 + 4.
示例 2:

输入: n = 13
输出: 2
解释: 13 = 4 + 9.

**思路：**
1.DP动态规划，关键在于状态的定义和状态方程，我们要知道12最少有多少个数构成，实际上如果我们走了一步的话，要知道11，8，3对应的步数，如果我们不走，
就需要知道12的步数，我们只要通过比较是走0步小，还是走1步那个更小即可。

状态转移方程：
num[n] = min(num[n],num[n-i**2]+1)

所以可以先定一个n大小的数组(static类型)，需要使数组初始化为无穷大

```
class Solution:
    _dp = list()    #放到全局，能节省很多时间
    def numSquares(self, n):
        """
        :type n: int
        :rtype: int
        """
        dp = self._dp
        dp = [float('inf') for i in range(n+1)]   #状态定义
        dp[0] = 0
        for i in range(n+1):
            j = 1
            while i + j**2 <= n:
                dp[i + j**2] = min(dp[i + j**2],dp[i] + 1)     #状态转移方程
                j+=1
        return dp[n]
```
但是这种方法时间复杂度O(n^2)超时了，参考了别人的代码：

```
class Solution:
    _dp = [0]
    def numSquares(self, n):
        dp = self._dp
        while len(dp) <= n:
            dp += list((min(dp[-i*i] for i in range(1,int(len(dp)**0.5+1)))+1,)) #这里的int无法初始化list，我们只有通过加上一个','，
            将int变成tuple才可以初始化。
        return dp[n]
```
这个时候时间复杂度是O(NlogN)，时间大大减少

参考：https://www.codetd.com/article/2640989
