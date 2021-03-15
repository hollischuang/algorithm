3.给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
示例 2:

输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
示例 3:

输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

思路：

```py
    def lengthOfLongestSubstring(self, s):
        lookup = collections.defaultdict(int)
        l, r, counter, res = 0, 0, 0, 0 # counter 为当前子串中 unique 字符的数量
        while r < len(s):
            lookup[s[r]] += 1
            if lookup[s[r]] == 1: # 遇到了当前子串中未出现过的字符
                counter += 1
            r += 1
            # counter < r - l 说明有重复字符出现，否则 counter 应该等于 r - l
            while l < r and counter < r - l:
                lookup[s[l]] -= 1
                if lookup[s[l]] == 0: # 当前子串中的一种字符完全消失了
                    counter -= 1
                l += 1
            res = max(res, r - l) # 当前子串满足条件了，更新最大长度
        return res
```
时间复杂度：O(n^2)

空间复杂度：O(n)
