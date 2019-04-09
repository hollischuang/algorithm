53.给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:

输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。

思路：用DP来求解，只关注：当前值和当前值+过去的状态，是变好还是变坏

状态定义方程：maxSum = [nums[0] for i in range(n)]

状态转移：maxSum[i] = max(maxSum[i-1] + nums[i],nums[i])，一个是加上nums[i]的，另一个是从a[i]起头，重新开始。

```py
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        n = len(nums)
        maxSum = [nums[0] for i in range(n)]
        for i in range(1,n):
            maxSum[i] = max(maxSum[i-1] + nums[i],nums[i])
        return max(maxSum)
```

时间复杂度O（n）
空间复杂度O（1）
