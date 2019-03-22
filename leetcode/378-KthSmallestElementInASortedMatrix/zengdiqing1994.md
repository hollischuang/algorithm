给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
请注意，它是排序后的第k小元素，而不是第k个元素。

示例:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

返回 13。

思路：

二分查找及其优化，由于是有序矩阵，那么左上角的数字一定是最小的，右下角的数字一定是最大的，所以这是我们搜索的范围，算出中间数字mid，由于矩阵中不同行之间的
元素不是严格有序的，但是每一列是有序的，可以利用这个性质，从数组的左下角开始查找，如果比目标值小，我们就向右移动一位，而且我们知道当列的当前位置的上面
的所有数字都小于目标值，那么cnt+=1，反之则向上移一位，这样能算出cnt值，然后和target数字比较，进行二分查找。left和right最终会相等。


```py
class Solution(object):
    def kthSmallest(self, matrix, k):
        """
        :type matrix: List[List[int]]
        :type k: int
        :rtype: int
        """
        left,right = matrix[0][0],matrix[-1][-1]
        while left < right:
            mid = left + (right - left)/2   #进行二分
            cnt = self.search_less_equal(matrix,mid)
            if cnt < k:     #进行比较
                left = mid+1
            else:
                right = mid
        return left
                
    def search_less_equal(self,matrix,target):
        n = len(matrix)
        i,j,res = n-1,0,0
        while i >= 0 and j < n:
            if matrix[i][j] <= target:
                res += i+1      #如果左下角的数比target小，那么就向右移
                j += 1
            else:     #反之向上移动
                i -= 1
                
        return res
```

时间复杂度O(nlgX)，X为最大值和最小值的差值
