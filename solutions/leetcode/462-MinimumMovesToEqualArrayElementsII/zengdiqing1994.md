给定一个非空整数数组，找到使所有数组元素相等所需的最小移动数，其中每次移动可将选定的一个元素加1或减1。 您可以假设数组的长度最多为10000。

例如:

输入:
[1,2,3]

输出:
2

说明：
只有两个动作是必要的（记得每一步仅可使其中一个元素加1或减1）： 

[1,2,3]  =>  [2,2,3]  =>  [2,2,2]

思路：

本题主要是通过查找中间位置的数，然后进行移动步数，最后将移动步数全部求和.先排序，把中间位置的数拿出来，然后计算数组中每个数字与中间数的差值的绝对值
相加即可

```py
class Solution(object):
    def minMoves2(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        temp = []
        nums.sort()
        mid = len(nums)/2       #二分
        mid_num = nums[mid]     #取出中间的数
        nums.remove(nums[mid])
        for i in nums:
            if mid_num >= i:    #数组中每个数字与中间数的差值
                step = mid_num - i
                temp.append(step)
            else:
                step = i - mid_num
                temp.append(step)
        return sum(temp)
```
时间复杂度：O(nlogn)
空间复杂度：O(n)
