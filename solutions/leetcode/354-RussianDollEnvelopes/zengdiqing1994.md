354. 俄罗斯套娃信封问题

[354. 俄罗斯套娃信封问题](https://leetcode-cn.com/problems/russian-doll-envelopes/)

思路1：

这道题和[最长上升子序列](https://blog.csdn.net/u010712012/article/details/86532426)很像，只不过从一维变成了二维，DP解法实际上是一种暴力解法，
首先要给所有的信封按从小到大排序，首先根据宽度从小到大排，如果宽度相同，那么高度小的在前面，然后开始遍历，对于每一个信封，我们都遍历其前面所有的信封，
如果当前信封的长和宽都比前面那个信封的大，那么我们更新DP数组，通过dp[i] = max(dp[i],dp[j]+1)。然后我们每遍历完一个信封，都更新一下结果。



```py
class Solution:
    def maxEnvelopes(self, envelopes: List[List[int]]) -> int:
        if not envelopes:
            return 0
        nums = sorted(envelopes,key = lambda x:x) #先排好序
        dp = [1]*len(nums)  #状态初始化一个dp，也就是最长子序列的长度
        for i in range(1,len(nums)):  #i从第二个子列表到最后一个列表
            for j in range(i-1,-1,-1):  #j从第倒数第二个列表到第一个字列表遍历
                if nums[i][0]>nums[j][0] and nums[i][1]>nums[j][1]: #比较
                    dp[i] = max(dp[i],dp[j]+1)  #更新dp
        return max(dp)  #返回
```

但是上面的时间复杂度是O(n^2),lc提交超时。。。

于是就有了思路2：

可以用二分查找来优化速度，首先要做的还是给信封排序，但是这次排序和上面有些不同，信封的宽度还是从小到大排，但是宽度相等时，我们让高度大的在前面。现在的问题
就简化成了找高度数字中的最长上升子序列

```
from functools import cmp_to_key

def maxEnvelopes(envelopes) -> int:
     if not envelopes:
          return 0
     nums = sorted(envelopes,key=cmp_to_key(lambda x, y: x[0] - y[0] if x[0] != y[0] else y[1] - x[1]))
     size = len(nums)
     dp = []
     for x in range(size):
          low, high = 0, len(dp) - 1
          while low <= high:
               mid = (low + high) // 2
               if dp[mid][1] < nums[x][1]:
                    low = mid + 1
               else:
                    high = mid - 1
          if low < len(dp):
               dp[low] = nums[x]
          else:
               dp.append(nums[x])
     return len(dp)
def main():

     envelopes = [[5,4],[6,4],[6,7],[2,3]]
     print(maxEnvelopes(envelopes))
main()
```
时间复杂度O(nlogn)
