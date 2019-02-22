![903. DI 序列的有效排列](https://leetcode-cn.com/problems/valid-permutations-for-di-sequence/)

我们给出 S，一个源于 {'D', 'I'} 的长度为 n 的字符串 。（这些字母代表 “减少” 和 “增加”。）
有效排列 是对整数 {0, 1, ..., n} 的一个排列 P[0], P[1], ..., P[n]，使得对所有的 i：

如果 S[i] == 'D'，那么 P[i] > P[i+1]，以及；
如果 S[i] == 'I'，那么 P[i] < P[i+1]。
有多少个有效排列？因为答案可能很大，所以请返回你的答案模 10^9 + 7.

 

示例：

输入："DID"
输出：5
解释：
(0, 1, 2, 3) 的五个有效排列是：
(1, 0, 3, 2)
(2, 0, 3, 1)
(2, 1, 3, 0)
(3, 0, 2, 1)
(3, 1, 2, 0)

思路：

为了能进行状态转移，定义dp[i][j]表示：使用1-i这些数字的情况下，以j结尾的合理数组个数，计算dp[i][j]的过程如下：

1. 如果s[i-2]=='D'，说明第i-1位的数要比j大，第i-1位的数据范围是[j+1,i]，j在第i位上，所以就把大于等于j的数都往左shift一位（这样2者是等价的，满足A一
定满足B，满足B一定满足A），这样前i-1位就又是连续的[1,i-1]，就可以继续用DP数组的含义。具体到代码就是，k的范围是range(j,i)，而不是range(j+1,i)

2. 如果s[i-2]=='I'，数字i不在前i-1位，不用shift

```py
class Solution:
    def numPermsDISequence(self, S):
        mod = 10**9 + 7
        n = len(S)+1  #n设置为字符数列长度加1
        dp = [[0 for _ in range(n+1)] for _ in range(n+1)]  #设置状态定义
        dp[1][1]=1
        for i in range(2,n+1):    
            for j in range(1,i+1):
                if S[i-2] == 'D':     #对于D来说
                    for k in range(j,i):    #列出状态转移方程
                        dp[i][j]+=dp[i-1][k]  
                        dp[i][j]%=mod   #得到最后结果
                else:                   #对于I来说
                    for k in range(1,j):
                        dp[i][j]+=dp[i-1][k]  #同样的操作
                        dp[i][j]%=mod
        return sum(dp[n])%mod   
```
时间复杂度：显然是O(N^3)

空间复杂度：O(n)

参考：

![](https://blog.csdn.net/zjucor/article/details/82557070)
