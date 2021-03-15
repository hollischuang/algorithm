**4.二维数组中的查找**

思路：

首先选取数组中右上角的数字，如果该数字等于要查找的数字，则查找过程结束；如果数字大于要查找的数字，则剔除这个数字所在的列；如果该数字小于要查找的数字，则
剔除这个数字所在的行。也就是说，如果要查找的数字不在数组的右上角，则每一次都在数组的查找范围中剔除一行或者一列，这样每一步都可以缩小查找的范围，知道找到
查找的数字。

```py
class Solution:
    # array 二维列表
    def Find(self, target, array):
        if array == []:
            return False
        num_row = len(array)
        num_col = len(array[0])
        
        i = num_col - 1
        j = 0
        while i>=0 and j<num_row:
            if array[j][i] > target:
                i-=1
            elif array[j][i] < target:
                j+=1
            else:
                return True
```
时间复杂度：O(n^2)
空间复杂度：O（n）
