54.给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

示例 1:

输入:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
输出: [1,2,3,6,9,8,7,4,5]
示例 2:

输入:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
输出: [1,2,3,4,8,12,11,10,9,5,6,7]

思路：用四个变量来控制辩解，方向总是“左右上下”，这个和Z字形变换很像。

```py
def spiralOrder(matrix):
    if matrix == []:
        return []
    res = []
    maxUp = maxLeft = 0
    maxDown = len(matrix) - 1
    maxRight = len(matrix[0]) - 1
    direction = 0               # 0 go right , 1 go down, 2 go left, 3 up
    while True:
        if direction == 0:        # go right
            for i in range(maxLeft,maxRight+1):
                res.append(matrix[maxUp][i])
            maxUp += 1
        elif direction == 1:        # 1 go down
            for i in range(maxUp,maxDown+1):
                res.append(matrix[i][maxRight])
            maxRight -= 1
        elif direction == 2: # go left
            for i in reversed(range(maxLeft,maxRight+1)):
                res.append(matrix[maxDown][i])
            maxDown -= 1
        else:           # go up
            for i in reversed(range(maxUp,maxDown+1)):
                res.append(matrix[i][maxLeft])
            maxLeft += 1
        if maxUp > maxDown or maxLeft > maxRight:
            return res
        direction = (direction + 1) % 4         # direction = 3之后就是0重新开始
```
时间复杂度:O(m*n)

空间复杂度：O（1）
