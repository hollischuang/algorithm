#546. 移除盒子

Leetcode 地址 [https://leetcode-cn.com/problems/remove-boxes/](https://leetcode-cn.com/problems/remove-boxes/)

**题目分析**

dp思路，涉及子串的动态规划一般从子串两端进行递推，即(i,j)子串。即子数组，既然是子数字一般需要二维dp数组,其中**dp[i][j]表示在子数组[i, j]范围内所能得到的最高的分数** 最后返回结果dp[0][n-1] 即可。但是缺少了重复数字个数变量，假设移除了k个连续的相同颜色的箱子，可以获得k * k的分数，k大于等于1，于是数组应该是一个三维的数组标示：dp[i][j][k]。

通过用 dp[i][j][k] 来表示通过移除boxes[i, j]中的箱子，且此时在boxes[i]前有k个箱子的颜色与boxes[i]的颜色相同时，可以获得的最大分数。

**递推分解：** 
boxes数组的长度是n，可以将结果表示为：

```
dp[0][n - 1][0]
```

定义一些初始值:

```
dp[i][i][k] = (k + 1) * (k + 1)

dp[i][j][k] = 0;    //i < j
```
对于dp[i][j][k]表示的是通过移除boxes[i,j]中的箱子，且此时在boxes[i]前面有k个与boxes[i]颜色相同的箱子。因此，对于i个箱子而言，如果将其和前面的k个箱子一起移除，那么此时可以获得的分数，可以表示为：

```
(k + 1) * (k + 1) + dp[i + 1][j][0]
```
同时对于第i个箱子，还有其他的方案移除，即可以将boxes[i,j]中的某一个箱子一起移除，这个箱子可以表示boxes[m]，此时boxes[m] == boxes[i]。此时可以获得的分数是：

```
dp[i+1][m-1][0] + dp[m][j][k+1]
```
而此时的dp[i][j][k]就是这些情况下可以取得的最大值。因此转移方程是:

```
temp1 = (k + 1) * (k + 1) + dp[i + 1][j][0]

temp2 = max(dp[i + 1][m - 1][0] + dp[m][j][k + 1])  //i <= m <= j && boxes[m] == boxes[i]

dp[i][j][k] = max(temp1, temp2)
```

**具体代码**

```
public class Leet546 {
    int[][][] res;
    int[] boxes;

    private int removeStub(int start, int end, int k) {
        if (start > end) return 0;
        if (res[start][end][k] > 0) return res[start][end][k];
        int ans = removeStub(start, end - 1, 0) + (k + 1) * (k + 1);
        for (int i = start; i < end; i++)
            if (boxes[i] == boxes[end]) {
                ans = Math.max(ans, removeStub(start, i, k + 1) + removeStub(i + 1, end - 1, 0));
            }
        res[start][end][k] = ans;
        return ans;
    }

    public int removeBoxes(int[] boxes) {
        if (boxes == null || boxes.length == 0) return 0;
        int n = boxes.length;
        this.res = new int[n][n][n + 1];
        this.boxes = boxes;
        return removeStub(0, boxes.length - 1, 0);
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1};
        Leet546 leet546 = new Leet546();
        System.out.println(leet546.removeBoxes(array));
    }
}
```
**时间复杂度** O(N^4) 

leetcode 代码提交后发现击败了28%的commit

思路类似，后来优化后

```
class Solution {
    public int removeBoxes(int[] boxes) {
        if(boxes == null || boxes.length == 0) return 0;
        int length = boxes.length;
        int[][][] dp = new int[100][100][100];
		return calculatePoints(boxes, dp, 0, boxes.length - 1, 0);
    }
    
    public int calculatePoints(int[] boxes, int[][][] dp, int l, int r, int k) {
		if (l > r) {
			return 0;
        }
		if (dp[l][r][k] != 0) {
			return dp[l][r][k];
        }
		while (r > l && boxes[r] == boxes[r - 1]) {
            r--;
            k++;
        }
        dp[l][r][k] = calculatePoints(boxes, dp, l, r - 1, 0) + (k + 1) * (k + 1);
		for (int i = l; i < r; i++) {
		if (boxes[i] == boxes[r]) {
                dp[l][r][k] = Math.max(dp[l][r][k],
                        calculatePoints(boxes, dp, l, i, k + 1) + calculatePoints(boxes, dp, i + 1, r - 1, 0));
            }
        }
		return dp[l][r][k];
    }
}

```
[参考资料1](https://blog.csdn.net/Wuzihui___/article/details/78714313)

[参考资料2](https://github.com/lydxlx1/LeetCode/blob/master/src/_546.java)

