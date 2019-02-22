213.打家劫舍2
---
https://leetcode.com/problems/house-robber-ii/

这道题其实很有意思，属于动态规划的典型题目，其拓展于[打家劫舍1](https://leetcode.com/problems/house-robber/)。  
这里我们还是先说下打家劫舍1的解法。给定一个数组，小偷可以偷数组中任意元素，但要遵循以下几点约束：
- 小偷不可以偷相邻元素，只能隔开偷
- 满足以上，小偷可以偷任意多个

所以问题可以转化为第i个元素，小偷偷与不偷问题。
### 思路一
我们以截止到第i个元素，小偷偷到的最大值来建立转移方程：
```math
dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1])
```
以上方程很简单，就是说截止到第i个元素为止，小偷偷到最大值为 **第i个元素偷** 与 **第i个元素不偷的最大值**
- 第i个元素偷：也就是说我们只能从第i - 2个跳过来偷这个
- 第i个元素不偷：也就说我们只能继续保持截止到第 i - 1的状态

很容易，我们有如下方程：
```
    /**
     * 发现dp[i]仅依赖dp[i - 2], dp[i - 1]
     * 所以用2个变量
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int prev1 = 0, prev2 = 0;
        for (int num : nums) {
            int temp = prev1;
            prev1 = Math.max(prev2 + num, prev1);
            prev2 = temp;
        }
        return prev1;
    }

```
回到**打家劫舍2**问题，我们的数组现在变成一个环了，也就是说最后一个房子现在与第一个房子相邻。那么我们在考虑最后一个房子时还要看看第1个房子是否被偷，但是我们的状态转移方程定义的是截止到第i个元素的值，所以是无法得知第1个元素是否被偷。在处理最后一个元素时会有点麻烦，但是只要我们打破环，就简单多了。  

那如何打破环呢，可以这样考虑，问题其实分为第i个元素偷与不偷问题，显然只要在任意一个元素处以这样的原则，就可以打破环，分成第i个元素偷与不偷2种情况。这里为了简单我们以第1个（也就是数组中第0个）元素为准，
- 第0个元素被偷，那么我们问题变为打家劫舍1中[0, n - 2]问题，0被偷，注定n - 1不能偷
- 第1个元素被偷，那么我们问题变为打家劫舍1中[1, n - 1]问题，0不能被偷，那么n - 1就可以被偷了

所以现在我们只需求以上2个子问题的最大值就好了。
```java
    public int rob(int[] nums) {
        return Math.max(rob(nums, 0, nums.length - 2),
                rob(nums, 1, nums.length - 1));
    }
```
#### 复杂度
- 时间复杂度：O(n)
- 空间复杂度：O(1)

参考：https://leetcode.com/problems/house-robber-ii/discuss/59934/Simple-AC-solution-in-Java-in-O(n)-with-explanation