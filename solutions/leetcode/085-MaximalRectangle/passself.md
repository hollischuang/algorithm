#85. 最大矩形

Leetcode 地址 [https://leetcode-cn.com/problems/maximal-rectangle/](https://leetcode-cn.com/problems/maximal-rectangle/)

**题目分析**

该题目需要有两种结题方式，第一种是利用dp动态规划，第二种是用栈的思路。dp的方式[参考](https://leetcode.com/problems/maximal-rectangle/discuss/29054/share-my-dp-solution)。

**思路：** 

已知二维二进制矩阵

```
["1","0","1","0","0"],
["1","0","1","1","1"],
["1","1","1","1","1"],
["1","0","0","1","0"]
```

1.定义三个数组

**left[]：** 从左到右，连续出现"1"的string 的第一个坐标

**right[]：** 从右到左, 连续出现"1"的最后一个坐标

**height[]：** 从上到下的高度

**result：** (right[i] - left[i]) * heights[i] 其实就是计算面积 (right[i] - left[i])就是宽度

height 过程:

```
1 0 1 0 0
2 0 2 1 1
3 1 3 2 2
4 0 0 3 0
```

left :

```
0 0 2 0 0
0 0 2 2 2
0 0 2 2 2
0 0 0 3 0
```
right：

```
1 5 3 5 5
1 5 3 5 5
1 5 3 5 5
1 5 5 4 5
```
3.

**具体代码**

```
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int result = 0;
        int height[] = new int[n];
        int left[] = new int[n];
        int right[] = new int[n];
        Arrays.fill(right,n);

        for (int i = 0; i < m; i++) {
        	  //curLeft  每一行的下标 类似index
            int curLeft = 0,curRight = n;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') height[j] ++;
                else height[j] = 0;
            }

            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1'){
                    left[j] = Math.max(curLeft,left[j]);
                }else {
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }

            for (int j = n- 1; j >= 0; j--) {
                if (matrix[i][j] == '1'){
                    right[j] = Math.min(curRight,right[j]);
                }else{
                    right[j] = n;
                    curRight = j;
                }
            }

            for (int j = 0; j< n;j++){
                result = Math.max(result,(right[j] - left[j]) * height[j]);
            }
        }

        return result;
    }
}
```
**时间复杂度** O(N*N) 

leetcode 代码提交后发现击败了98%的commit

**第二种解法**

第二种解法是使用栈。基本思路来源就是84题。我们可以这样想想，从每一行来看。每一行对应的矩阵的高度其实就相当于是当前行的直方图，也就相当于求直方图中最大面积。这样一来就和84解法一样了。

思路解析

将两行加在一起，例如下面两行

```
1 0 1 0 0
1 0 1 1 1
```
加起来得到

```
2 0 2 1 1
```
那么这两行的最大长方形要不就是2，要不就是衡向的三个1，那么如果是三行的结果是怎样的

```
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
```
加起来得到

```
3 1 3 2 2
```
这个结果可以看出，这里就将上面的矩阵表现成为了一个柱状图，值就是它的高度利用leetcode 84的代码

```
private int largestRectangleArea(int[] heights) {
        int max = 0, n = heights.length;
        int[] small_left = new int[n];
        int[] small_right = new int[n];
        small_left[0] = -1;
        small_right[n-1] = n;
        for ( int i = 1; i < n; i++ ) {
            int idx = i - 1;
            while ( idx >= 0 && heights[idx] >= heights[i] )
                idx = small_left[idx];
            small_left[i] = idx;
        }
        for ( int i = n - 2; i >= 0; i-- ) {
            int idx = i + 1;
            while ( idx < n && heights[idx] >= heights[i] )
                idx = small_right[idx];
            small_right[i] = idx;
        }
        for ( int i = 0; i < n; i++ ) {
            int area = (small_right[i] - small_left[i] - 1) * heights[i];
            if ( area > max )
                max = area;
        }
        return max;
}
```

计算leetcode 85的最大长方形结果

```
public int maximalRectangle(char[][] matrix) {
        if ( matrix == null || matrix.length == 0 ) return 0;
        int m = matrix.length, n = matrix[0].length, max = 0;
        int[] row_sum = new int[n];
        for ( int i = 0; i < m; i++ ) {
            for ( int j = 0; j < n; j++ )
            	   // 遇到0重置，1累加
                row_sum[j] = ('0' == matrix[i][j]) ? 0 : row_sum[j] + 1;
            int area = largestRectangleArea(row_sum);
            if ( area > max )
                max = area;
        }
        return max;
}

```


