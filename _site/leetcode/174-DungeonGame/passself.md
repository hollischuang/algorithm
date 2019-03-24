#174. 地下城游戏

Leetcode 地址 [https://leetcode-cn.com/problems/dungeon-game/](https://leetcode-cn.com/problems/dungeon-game/)

**题目分析**

基本一看就是动态规划的题目, 有几个前提条件一定得注意。

* 1.骑士的初始健康点数为一个正整数。
* 2.如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。即无论骑士到达哪个位置健康值必须大于等于1

**思路：** 

* 思路一 正向递推从左上角(0,0)到(row-1,row-1)，这样效率一般会比反递推效率低很多
* 思路二 到达最后一个房间的时候健康值至少剩下1，因此可以设置最后的状态为初始状态，由后向前依次决定在每一个位置至少需要多少健康值，这样一个位置的状态是由其下面一个和和右边一个的较小状态决定 ．因此一个基本的状态方程是:

```
int down = Math.max(dp[i + 1][j] - dungeon[i][j], 1);
int right = Math.max(dp[i][j + 1] - dungeon[i][j], 1);
dp[i][j] = Math.min(right, down);
```
还有一个条件就是在每个房间里面的健康值都大于等于1 ```dp[i][j] = max(dp[i][j], 1)```

**具体代码**

```
public int calculateMinimumHP(int[][] dungeon) {
    if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;
    int m = dungeon.length;
    int n = dungeon[0].length;
    int[][] dp = new int[m][n];
    for (int i = m - 1; i >= 0; i--) {
        for (int j = n - 1; j >= 0; j--) {
            if(i==m-1 && j==n-1) {//考虑边界
               dp[i][j]=Math.max(1 - dungeon[i][j], 1);
            }else if(i==m-1) {
                dp[i][j]=Math.max(dp[i][j + 1] - dungeon[i][j], 1);
            }else if(j==n-1) {
                 dp[i][j]=Math.max(dp[i + 1][j] - dungeon[i][j], 1);
            }else{
                int down = Math.max(dp[i + 1][j] - dungeon[i][j], 1);
                int right = Math.max(dp[i][j + 1] - dungeon[i][j], 1);
                dp[i][j] = Math.min(right, down);
            }
        }
    }
    return dp[0][0];
}
```

**时间复杂度** O(M*N)

**空间复杂度** O(M*N) 

leetcode 代码提交后发现击败了26%的commit

**第二种解法**

用一位数组来记录数据，空间复杂度变为o(n)，执行效率和速度大幅提升 

**具体代码**

```
public int calculateMinimumHP(int[][] dungeon) {
    int m = dungeon.length, n = dungeon[0].length;
    int[] dp = new int[n + 1];
    dp[n]  = 1;
    for (int i = m - 1; i >= 0; i--) {
        for (int j = n - 1; j >= 0; j--) {
            int health = 0;
            if (i == m - 1) health = dp[j + 1] - dungeon[i][j];
            else if (j == n - 1) health = dp[j] - dungeon[i][j];
            else health = Math.min(dp[j + 1], dp[j]) - dungeon[i][j];
            dp[j] = health <= 0 ? 1 : health;
        }
    }
    return dp[0];                  
}
```


