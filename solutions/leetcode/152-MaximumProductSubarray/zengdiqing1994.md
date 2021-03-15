152.给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。

示例 1:

输入: [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
示例 2:

输入: [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。

思路：乘积最大子序列，和53是不一样的，因为乘积可正可负，之前的DP方程不能保证结果。而且要用两个dp数组，其中maxdp[i]表示子数组[0, i]范围内并且一定包
含nums[i]数字的最大子数组乘积，mindp[i]表示子数组[0, i]范围内并且一定包含nums[i]数字的最小子数组乘积，初始化时maxdp[0]和mindp[0]都初始化为
nums[0]，其余都初始化为0。那么从数组的第二个数字开始遍历，那么此时的最大值和最小值只会在这三个数字之间产生，即maxdp[i-1]*nums[i]，mindp[i-
1]*nums[i]，和nums[i]。所以我们用三者中的最大值来更新maxdp[i]，用最小值来更新mindp[i]，然后用maxdp[i]来更新结果res即可，由于最终的结果不一定会包
括nums[n-1]这个数字，所以maxdp[n-1]不一定是最终解，不断更新的结果res才是，参见代码如下：

```py
def solution(nums):
    maxdp = [nums[0]]*len(nums)
    mindp = [nums[0]]*len(nums)

    for i in range(1,len(nums)):
        maxdp[i] = max(mindp[i-1]*nums[i],maxdp[i-1]*nums[i],nums[i])
        mindp[i] = min(maxdp[i-1]*nums[i],mindp[i-1]*nums[i],nums[i])
    return max(maxdp)
```
时间复杂度O(n)
空间复杂度O(n)
