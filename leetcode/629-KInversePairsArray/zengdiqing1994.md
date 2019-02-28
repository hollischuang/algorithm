![629. K个逆序对数组](https://leetcode-cn.com/problems/k-inverse-pairs-array/submissions/)

给出两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个逆序对的不同的数组的个数。

逆序对的定义如下：对于数组的第i个和第 j个元素，如果满i < j且 a[i] > a[j]，则其为一个逆序对；否则不是。

由于答案可能很大，只需要返回 答案 mod 109 + 7 的值。

示例 1:

输入: n = 3, k = 0
输出: 1
解释: 
只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。
示例 2:

输入: n = 3, k = 1
输出: 2
解释: 
数组 [1,3,2] 和 [2,1,3] 都有 1 个逆序对。

思路：

求递推式，时间复杂度O(n * k)

观察下列推导过程：

当n=1时，k的取值范围是[0, 0]

k   c

0 1 1

当n=2时，k的取值范围是[0, 1]

k   c

0 1 1

1 1 1

当n=3时，k的取值范围是[0, 3]

k       c

0 1     1

1 1 1   2

2   1 1 2

3     1 1

当n=4时，k的取值范围是[0, 6]

k         c

0 1       1

1 2 1     3

2 2 2 1   5

3 1 2 2 1 6

4   1 2 2 5

5     1 2 3

6       1 1

当n=5时，k的取值范围是[0, 10]

k            c

0  1         1

1  3 1       4

2  5 3 1     9

3  6 5 3 1   15

4  5 6 5 3 1 20

5  3 5 6 5 3 22

6  1 3 5 6 5 20

7    1 3 5 6 15

8      1 3 5 9

9        1 3 4

10         1 1

这个递推的过程很难想到，就借鉴别人的

![思路代码](http://bookshadow.com/weblog/2017/06/25/leetcode-k-inverse-pairs-array/)

```py
class Solution:
    def kInversePairs(self, n: 'int', k: 'int') -> 'int':
        MOD = 10**9 + 7
        dp = [1]
        for x in range(2, n + 1):   
            ndp = []
            num = 0
            for y in range(min(1 + x * (x - 1) // 2, k + 1)): #分两种情况
                if y < len(dp): num = (num + dp[y]) % MOD
                if y >= x: num = (MOD + num - dp[y - x]) % MOD
                ndp.append(num) 
            dp = ndp
        return k < len(dp) and dp[k] or 0
```

