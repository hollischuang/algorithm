给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在众数。

示例 1:

输入: [3,2,3]
输出: 3
示例 2:

输入: [2,2,1,1,1,2,2]
输出: 2

思路：

1.最容易想到的就是众数的定义，先排序，然后取出中间的数，那个数一定是众数。

```py
class Solution(object):
    def majorityElement(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        nums.sort()
        return nums[len(nums)//2]
```
时间复杂度：O(nlogn)
空间复杂度：O(1)

2.摩尔投票法

我们先将数组中的第一个数假设为众数，然后进行统计其出现的次数，如果遇到同样的数，则计数器自动加一，否则计数器减一，如果计数器减到了0，则更换下一个数字为
候选者。一定会有超过半数的数字存在，如果计数器减到0之后，说明目前不是候选数字的个数已经跟候选者的出现个数相同了，那么这个候选者已经很weak，不一定能出现
超过半数，但是如果后面又大量出现之前的候选者的话，又会被重新变为候选者，直到最终验证为正确的众数。

```py
class Solution(object):
    def majorityElement(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        res = cnt = 0
        for num in nums:
            if cnt == 0:
                res = num
                cnt += 1
            elif res == num:
                cnt += 1
            else:
                cnt -= 1
        return res
```
时间复杂度：O（n）
空间复杂度：O（1）


