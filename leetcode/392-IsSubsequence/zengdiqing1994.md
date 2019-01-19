##### 392. 判断子序列

给定字符串 s 和 t ，判断 s 是否为 t 的子序列。

你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。

字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

示例 1:
s = "abc", t = "ahbgdc"

返回 true.

示例 2:
s = "axc", t = "ahbgdc"

返回 false.

后续挑战 :

如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？

**思路：**

这里又用到了双指针：

s: a  b  c

     |
   
   s_p


t: a  h  b  g  c  k

     |
   
  t_p
  
  
这里我们不断移动t_p指针，看t_p指向的元素是否和s_p指向的相等，如果不相等的话继续移动t_p，如果相等的话也一并移动s_p，直到t_p到达了t的边界。在这期间，
如果s_p已经到达了s的边界的话，就直接返回True。若整个循环结束，就是t遍历完都没有返回true的话，就说明不存在，返回false

代码：
```
class Solution:
    def isSubsequence(self, s, t):
        if s == None or t == None:  #判断字符串的是否为空
        return False
        
        len_s = len(s)    #长度获取
        len_t = len(t)    
        if len_t < len_s:   #判断长度的真实性
            return False
        if len_s == 0:
            return True
        j=0
        for i in range(len_t):    #若对于t串来讲，若和s相等，就继续移动
            if s[j] == t[i]:
                j+=1              
            if j == len_s:    #最终如果移动的次数和s的长度相等就返回True
                return True
        return False
```
这里的时间复杂度是O(t*s)

python内置了find()函数可以快速定位字符的位置
```
class Solution:
    def isSubsequence(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        for seq_s in s:
            s_index = t.find(seq_s)
            if s_index == -1:
                return False
            if s_index == len(t) - 1: #如果找到的匹配的s达到了t的长度
                t = str()             #字符串长度赋给t
            else:                   
                t = t[s_index+1:]       #若还没匹配完，从下一个开始继续
        return True
```
这里时间复杂度稍微低一些，为O(t*logs)
