63.不同的路径Ⅱ
---
https://leetcode.com/problems/unique-paths-ii/  
题目的意思是给定一个网格，机器人从左上角开始走，走到右下角一共有多少种步数，同时网格中可能有障碍物。机器人的方向只能向右和向左两种走法，显然当前位置的可以由它的上和左方向走来，所以这是经典的动态规划问题。
### 思路一
因为考虑障碍物，所以状态方程如下：
```math
dp[i][j] = dp[i][j - 1] + dp[i]- 1][j], \;
\;if grid[i][j] == 0

dp[i][j] = 0, \;\; if grid[i][j] = 1
```
我们这里额外引入空间来存放到第i行，第j列的走法
```
    /**
     * 当前格子只能由它的上面和左边过来
     * 所以状态转移方程为：
     * dp[i][j] = dp[i][j - 1] + dp[i - 1][j]
     * dp[i][j] = 0, if obstacle
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        //额外空间，用于方便处理边界
        int[][] dp = new int[m + 1][n + 1];
        dp[1][0] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (obstacleGrid[i - 1][j - 1] == 0) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }
```
#### 复杂度
- 时间复杂度：O(m `$\times$` n)
- 空间复杂度：O(m `$\times$` n)
### 思路二
对状态方程进行优化，压缩状态
```
    /**
     * dp[i][j]仅依赖它的上方和左边两个状态
     * 所以压缩状态，dp[i - 1]对应dp[i][j - 1], dp[i]对应dp[i - 1][j]
     *
     * 注意每一行的第0列特殊处理一下
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        //额外空间，用于方便处理边界
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (obstacleGrid[i - 1][j - 1] == 0) {
                    dp[j] = (i != 1 && j == 1 ? 0 : dp[j - 1]) + dp[j];
                } else {
                    //这里要归0，因为会被下一个行作为头部使用
                    dp[j] = 0;
                }
            }
        }
        return dp[n];
    }
```
#### 复杂度
- 时间复杂度：O(m `$\times$` n)
- 空间复杂度：O(n)

### 思路三
我们不需要额外的空间，用网格本身来作dp状态。  
- 首先我们对第0行和第0列进行特殊处理，判断每一个位置之前值是否为1（表示路径可达），然后看当前位置是否是障碍物。若不是障碍物，则当前值设置为1，表示可达；否则设置为0，表示不可达。我们可以看到对于为1的障碍物我们都清0，因为此时0代表路径数，其实就是不可达。
- 对第0列也进行同样的处理
- 从第1行开始，从每行的第1列开始，遍历判断当前位置的值是不是障碍物，若是，则设置0，表示不贡献路径数，否则为头部和左边的和

```java
    /**
     * 用网格自身作为dp
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        obstacleGrid[0][0] = 1;
        for (int i = 1; i < n; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i - 1] == 1 && obstacleGrid[0][i] == 0) ? 1 : 0;
        }
        for (int i = 1; i < m; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i - 1][0] == 1 && obstacleGrid[i][0] == 0) ? 1 : 0;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i][j - 1] + obstacleGrid[i - 1][j];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }
        return obstacleGrid[m - 1][n - 1];
    }
```
#### 复杂度
- 时间复杂度：O(m `$\times$` n)
- 空间复杂度：O(1)