6.将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

L   C   I   R
E T O E S I I G
E   D   H   N
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

请你实现这个将字符串进行指定行数变换的函数：

string convert(string s, int numRows);
示例 1:

输入: s = "LEETCODEISHIRING", numRows = 3
输出: "LCIRETOESIIGEDHN"
示例 2:

输入: s = "LEETCODEISHIRING", numRows = 4
输出: "LDREOEIIECIHNTSG"
解释:

L     D     R
E   O E   I I
E C   I H   N
T     S     G

思路：idx从0开始，自增直到numRows-1，此后又一直自减到0，重复执行。

从第一行开始往下，走到第四行又往上走，这里用 step = 1 代表往下走， step = -1 代表往上走

因为只会有一次遍历，同时把每一行的元素都存下来，所以时间复杂度和空间复杂度都是 O(N)

```py
class Solution:
    def convert(self, s: str, numRows: int) -> str:
        if numRows==1 or numRows>=len(s):
            return s                #判断情况
        res = [''] * numRows        #初始化res结果
        idx, step = 0, 1            
        for c in s:             
            res[idx] += c           #把字符加入res中
            if idx == 0:                
                step = 1            #step向下加一
            elif idx == numRows-1:  #一直到最后一行为止
                step = -1           #向上操作
            idx += step             #idx代表第几行
        return ''.join(res)         
```
时间复杂度： O(n)
空间复杂度： O（1）
