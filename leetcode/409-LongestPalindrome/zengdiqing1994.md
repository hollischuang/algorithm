**409. 最长回文串**  
---
[https://leetcode-cn.com/problems/longest-palindrome/](https://leetcode-cn.com/problems/longest-palindrome/)  

给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。

在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。

注意:
假设字符串的长度不会超过 1010。

示例 1:

```  
输入:
"abccccdd"

输出:
7

解释:
我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
```  

思路：这道回文字符串包括以前的回文字符串的题目都比较重要，由于这里的字符串可以打乱，所以问题就转化成了求偶数个字符的个数，我们了解的回文字符串都知道，
回文串主要有两种形式，一个是左右完全对称的，比如noon，还有一种是以中心字符为中心，左右对称，比如bob，那么统计出来所有偶数个字符的出现总和，然后如果有
奇数个字符的化，我们取出其最大偶数，然后最后结果加上1就可以啦。

```py
class Solution:
    def longestPalindrome(self, s: str) -> int:
        dict1 = {} #用来存储出现过的字符和出现的次数
        j = 0   #存储回文长度
        z = 0   #统计单数次字符的个数
        for i in range(len(s)):
            if s[i] in dict1:
                dict1[s[i]] += 1 #出现的字符作为键，次数作为value值
            else:
                dict1[s[i]] = 1
        for v in dict1: #对构建好的字典进行遍历
            if (dict1[v] + 1) % 2==0:  #出现单数次字符次数减一
                j+=(dict1[v]-1)
                z+=1
            if dict1[v] % 2 == 0: #出现偶数次字符，一定可以构造
                j+=dict1[v]
        if z > 0: 
            return j+1
        else:
            return j
```
这个时间复杂度较高:O(n^2)
空间复杂度O(n)
