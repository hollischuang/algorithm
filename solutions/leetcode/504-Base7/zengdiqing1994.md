给定一个整数，将其转化为7进制，并以字符串形式输出。

示例 1:

输入: 100
输出: "202"
示例 2:

输入: -7
输出: "-10"
注意: 输入范围是 [-1e7, 1e7] 。

思路：

直接把获取到的数据的模对7取余，再加在后面成为字符串。每次取余之后都要除以7.注意最后要判断正负

```py
class Solution(object):
    def convertToBase7(self, num):
        """
        :type num: int
        :rtype: str
        """
        if num == 0:
            return "0"
        else:
            res = str()
            n = abs(num)
            while n:
                res = str(n%7) + res
                n = n//7
            return res if num>0 else '-'+res
```
时间复杂度：O(n)
空间复杂度：O(n)
