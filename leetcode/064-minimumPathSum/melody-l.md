**064. MinimunPathSum**  
---
[https://leetcode-cn.com/problems/minimum-path-sum/](https://leetcode-cn.com/problems/minimum-path-sum/)  

方法一：动态规划

由于题目规定，每次只能向下或者向右移动一步。因此，对于(i,j)的最小路径，只能从左边(i, j-1)和上边(i-1, j)中选择最小的路径。所以递推式为：
设(i,j)所在位置的权重为V(i,j)，最小路径为A(i,j)，则：
* 若i=0 且 j!=0， A(i, j) = A(i-1, j) + V(i, j)
* 若i!=0 且 j=0， A(i, j) = A(i, j-1) + V(i, j)
* 若i!=0 且 j!=0， A(i ,j) = Min{A(i-1, j) , A(i, j-1)} + V(i, j)
* 若i=0 且 j=0， A(i, j) = V(i, j)

```java  

public class Solution {
    // 复用grid，
    // gird中数组的数值经过计算保存由<0,0>到达该点的最小路径和
    public int minPathSum(int[][] grid) {
        int row = grid.length;// 行
        int column = grid[0].length; // 列
        // 按照顺序遍历
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i == 0 && j != 0) { // 如果是第一行的
                    // 路径只能是从左边出发的
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                } else if (i != 0 && j == 0) { //如果是第一列的
                    // 路径只能是从上面出发的
                    grid[i][j] = grid[i][j] + grid[i - 1][j];
                } else if (i == 0 && j == 0) { //如果是起点
                    grid[i][j] = grid[i][j];
                } else { // 非第一行和第一列的
                    // 能到达<i, j>这个位置，
                    // 只能由这个位置上面<i, j-1>，或者这个位置左边<i-1, j>到达了
                    // 因此选择上面路径和最小的相加
                    grid[i][j] = Math.min(grid[i][j - 1], grid[i - 1][j]) + grid[i][j];
                }
            }
        }
        // 遍历完后，返回最后一个的值
        return grid[row - 1][column - 1];
    }
}


```  

---

**参考资料**  
* 网友推荐题解：  
[https://leetcode.com/problems/minimum-path-sum/discuss/23471/My-java-solution-using-DP-and-no-extra-space](https://leetcode.com/problems/minimum-path-sum/discuss/23471/My-java-solution-using-DP-and-no-extra-space)  
