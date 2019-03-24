**968. Binary Tree Cameras**

[Binary Tree Cameras](https://leetcode.com/problems/binary-tree-cameras/)

**思路：**

最小点覆盖和最大独立集都比较简单，只有2个状态，分别是标记和不标记

对于最小支配集，每个子树3个状态：

状态0：根被标记，整个子树都被覆盖的最小标记数目

状态1：根未被标记，整个子树被覆盖，且至少有一个子节点被标记

状态2：根未被标记，整个子树被覆盖，且没有子节点被标记

每个状态如何递归：

1.状态0：每个子树的3个状态的最小值之和+1：

dp[root][0] = min(dp[root.left])+min(dp[root.right])+1

2.状态1：【如果根没有孩子，dp[root][1]为INF】根未被标记时子树不可以是状态3。所以是前两个状态取最小值之和。但是如果每个子节点的最小值都是状态1，那么就
和根是状态1的假设矛盾了。所以要挑一个节点取状态0，这必然会使结果增加，那么选增加得最少的那个，即dp[u][0]-dp[u][1]最小的那个指定为状态0。

不可能是状态3是因为如果根没有标记，儿子没有标记，根还被覆盖了，可能是根的父亲标记了，但是如果孙子也没有标记，那么儿子就不可能被标记，矛盾。

2.状态2：此时子树只可能是状态1。

dp[root][2] = dp[root.left][1]+dp[root.right][1]

最后取根节点的状态1和状态0里最小的那个

```py
class Solution:
    def minCameraCover(self, root: TreeNode) -> int:
        INF = 0x7fffffff
        def solve(root):
            if root.left and root.right:    
                left = solve(root.left)     #左右子树递归
                right = solve(root.right)
                return min(left)+min(right)+1, min(left[0]+min(right[:-1]), min(left[:-1])+right[0]),left[1]+right[1]
            res = None
            if root.left:       #求解满足状态0,1的情况
                res = solve(root.left)
            elif root.right:
                res = solve(root.right)
            if res!=None:
                return min(res)+1, res[0], res[1]
            return 1, INF, 0
        return min(solve(root)[:-1])
```
时间复杂度是O(nlogn)

[参考](https://blog.csdn.net/lemonmillie/article/details/87825550)
