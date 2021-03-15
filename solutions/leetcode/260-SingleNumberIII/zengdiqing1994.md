给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。

示例 :

输入: [1,2,1,3,2,5]
输出: [3,5]

思路：

如果使用O(1)的空间复杂度要怎么做呢？我们很快想到通过位运算。直接对每个nums的元素做xor，最后我们得到的结果就是两个单一元素a和b的xor。因为a和b不相同，
所以它们之间必定会存在至少一个bit不同，也就是说a xor b的结果中至少有一个bit是1。我们从这么多的bit中挑选出一个，然后其余位置为0，那我们就构成了这样的
一种mask。例如00...100

这样的mask和a和b中元素与元素的话，必定有一个结果是0，另外一个结果是mask，这样我们就将a和b给分开了。那么问题就变成了，怎么构建这样的mask？我们使用一个
简单的策略就是a xor b的最右边的1作为flag。那要怎么得到最右边的1呢？这就涉及到补码的概念，我们知道负数在计算机中使用补码表示的，也就是反码加1。

num:5
原码：0101
反码：1010
---------
num:-5
补码：1011

那么我们通过num&-num就可以取出最右边的1了。现在我们就可以遍历nums然后，通过mask就可以判断nums中的那些元素的右边第一位是1（根据上面例子），我们将这些数
分成一类，将右边第一位是1的数分成为另外一类，并且我们的a和b也就被分到不同的组中。这两组数字的个数不一定相同，但是最后一定是可以相互通过xor消除，最后只剩
a和b。

```py
from operator import xor
class Solution(object):
    def singleNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        mask = 0
        for i in nums:    #遍历整个数组，求xor的结果
            mask ^= i     
        mask &= -mask     #取出最右的1
        result = [0]*2    
        for num in nums:    
            if num & mask:    #开始遍历得到需要的结果
                result[0] ^= num
            else:
                result[1] ^= num
        return result
```
时间复杂度O(n)
空间复杂度O（1）
