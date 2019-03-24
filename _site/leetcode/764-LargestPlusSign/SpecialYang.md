**764.最大加号标志**
---
https://leetcode.com/problems/largest-plus-sign/

题目意思是给一个`$N \times N$`的矩阵，然后再告诉你其中某些单元格的值为0。让你求出值可以为1的单元格为中心，它的四臂上，下，左，右都要为1，组成加号标志，四个方向同时都要全部为1，这样的情况下，加号的长度最大为多少。显然加号的最大的长度取决于四个方向最短全为1。其实就是**木桶效应**了。  
 
 - 加号中心要为1
 - 加号的四臂都要为1，且长度要一致，显然由最短的决定整体的长度
 
### 思路一
暴力法。遍历所有的单元格，以所有的可以为1的单元格为中心，一步一步同时向四周扩散，直到某个臂为0为止，继续对下一个可以为1的单元格作同样的处理，期间维护一个最大臂长即可。

```java
    /**
     * 暴力解法
     * @param N
     * @param mines
     * @return
     */
    public int orderOfLargestPlusSign1(int N, int[][] mines) {
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = N;
            }
        }
        for (int[] mine : mines) {
            dp[mine[0]][mine[1]] = 0;
        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int k = 0;
                while (i - k >= 0 && i + k < N && j - k >= 0 && j + k < N
                        && dp[i - k][j] == 1
                        && dp[i + k][j] == 1
                        && dp[i][j - k] == 1
                        && dp[i][j + k] == 1) {
                    k++;
                }
                max = Math.max(max, k);
            }
        }
        return max;
    }
```
#### 复杂度
- 时间复杂度：O(n^3)
- 空间复杂度：O(n^2)

### 思路二动态规划
我们发现第i个单元格的左臂长度其实不用在重头开始计算，利用第i-1个单元格的值就可以确定第i个单元格的左臂长度。
```math
left[i] = Math.min(left[i], left[i] == 0 ? 0  : left[i - 1] + 1)
```
如果是这样的思路岂不是再申请3个其他方向的数组，显然不太合理，好在我们只需对每个单元格求四个方向中最短的那一长度，这就可以重用了啊，我们先求左，然后统一求右，再上，下，每次都要求最小即可，最后必然是四个方向的重叠的最小结果。

那就有了下面的这个代码：
```java
    //以行单位
    for (int i = 0; i < N; i++) {
                //求该行中所有单元格的最大左臂长
                for (int j=0, l=0; j < N; j++) {
                    // j is a column index, iterate from left to right
                    // every time check how far left it can reach.
                    // if grid[i][j] is 0, l needs to start over from 0 again, otherwise increment
                    grid[i][j] = Math.min(grid[i][j], l = (grid[i][j] == 0 ? 0 : l + 1));
                }
                //求该行中所有单元格的最大右臂长
                for (int k = N-1, r=0; k >= 0; k--) {
                    // k is a column index, iterate from right to left
                    // every time check how far right it can reach.
                    // if grid[i][k] is 0, r needs to start over from 0 again, otherwise increment
                    grid[i][k] = Math.min(grid[i][k], r = (grid[i][k] == 0 ? 0 : r + 1));
                }
                //求该行中所有单元格的最大上臂长
                for (int j = 0, u=0; j < N; j++) {
                    // j is a row index, iterate from top to bottom
                    // every time check how far up it can reach.
                    // if grid[j][i] is 0, u needs to start over from 0 again, otherwise increment
                    grid[j][i] = Math.min(grid[j][i], u = (grid[j][i] == 0 ? 0 : u + 1));
                }
                //求该行中所有单元格的最大下臂长
                for (int k = N-1, d=0; k >= 0; k--) {
                    // k is a row index, iterate from bottom to top
                    // every time check how far down it can reach.
                    // if grid[k][i] is 0, d needs to start over from 0 again, otherwise increment
                    grid[k][i] = Math.min(grid[k][i], d = (grid[k][i] == 0 ? 0 : d + 1));
                }
                
                // after four loops each time taking Math.min over the grid value itself
                // all grid values will eventually take the min of the 4 direcitons.
            }
```

很显然我们可以合并这个loop，那么就有了下面的代码：
```java
    /**
     * 动态规划
     *
     * 对每个方向都取最小，那么最终以这个为中心的就是最小长度
     * @param N
     * @param mines
     * @return
     */
    public int orderOfLargestPlusSign2(int N, int[][] mines) {
        int[][] dp = new int[N][N];
        //初始化大于等于N就行，因为我们每次求的是4臂的最小值
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j] = N;
            }
        }
        for (int[] mine : mines) {
            dp[mine[0]][mine[1]] = 0;
        }
        for (int i = 0; i < N; i++) {
            //充分利用了j, k的值
            for (int j = 0, k = N - 1, l = 0, r = 0, u = 0, d = 0; j < N; j++, k--) {
                //左
                dp[i][j] = Math.min(dp[i][j], l = (dp[i][j] == 0 ? 0 : l + 1));
                //右
                dp[i][k] = Math.min(dp[i][k], r = (dp[i][k] == 0 ? 0 : r + 1));
                //上
                dp[j][i] = Math.min(dp[j][i], u = (dp[j][i] == 0 ? 0 : u + 1));
                //下
                dp[k][i] = Math.min(dp[k][i], d = (dp[k][j] == 0 ? 0 : d + 1));
            }
        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(0, dp[i][j]);
            }
        }
        return max;
    }
```
#### 复杂度
- 时间复杂度：O(n^2)
- 空间复杂度：O(n^2)

参考：
- https://leetcode.com/problems/largest-plus-sign/discuss/113314/JavaC%2B%2BPython-O(N2)-solution-using-only-one-grid-matrix
- https://leetcode.com/problems/largest-plus-sign/solution/