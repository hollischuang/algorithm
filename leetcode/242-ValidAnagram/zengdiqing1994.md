242.有效的异位字符串

给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。

示例 1:

输入: s = "anagram", t = "nagaram"
输出: true
示例 2:

输入: s = "rat", t = "car"
输出: false
说明:
你可以假设字符串只包含小写字母。

思路：使用哈希表，通过统计出现的次数，来判断，数量一致就可以。

```py
class Solution:
    def isAnagram(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
         dic1, dic2 = {}, {}
         for item in s:
             dic1[item] = dic1.get(item, 0) + 1
         for item in t:
             dic2[item] = dic2.get(item, 0) + 1
            
         return dic1 == dic2
```
时间复杂度：O(s*t)

空间复杂度：O(n)
