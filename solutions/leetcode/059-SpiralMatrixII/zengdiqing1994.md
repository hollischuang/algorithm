59.给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。

示例:

输入: 3
输出:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]

思路：和之前的那道螺旋矩阵题类似，只不过这次要自己生成一个矩阵

```py
class Solution:
    def generateMatrix(self, n: int) -> List[List[int]]:
        curNum = 0
        matrix = [[0 for i in range(n)] for j in range(n)]      #生成一个矩阵
        maxUp = maxLeft = 0
        maxDown = maxRight = n - 1
        direction = 0
        while True:
            if direction == 0:
                for i in range(maxLeft,maxRight+1):
                    curNum += 1
                    matrix[maxUp][i] = curNum                 #依次按顺序递增赋值
                maxUp += 1
            elif direction == 1:
                for i in range(maxUp,maxDown+1):
                    curNum += 1
                    matrix[i][maxRight] = curNum
                maxRight -= 1
            elif direction == 2:
                for i in reversed(range(maxLeft,maxRight+1)):
                    curNum += 1
                    matrix[maxDown][i] = curNum
                maxDown -= 1
            else:
                for i in reversed(range(maxUp,maxDown+1)):
                    curNum += 1
                    matrix[i][maxLeft] = curNum
                maxLeft += 1
            if curNum >= n*n:
                return matrix
            direction = (direction + 1) % 4
```
时间复杂度O（N^2）
空间复杂度O(N)
