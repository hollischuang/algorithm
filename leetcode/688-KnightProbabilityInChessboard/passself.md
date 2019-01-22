#688. “马”在棋盘上的概率

Leetcode 地址 [https://leetcode-cn.com/problems/knight-probability-in-chessboard/](https://leetcode-cn.com/problems/knight-probability-in-chessboard/)

**思路：** 

1.国际象棋“马”的走法类似中国象棋的**马走日字**走法，也就是说可以走八个方向，即可推出已知8个方向走法常量{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}

2.从K位置向后递推还在棋盘上的概率，那么根据动态规划的思维反过来考虑的话就是在board上所有位置走完K步后能到初始位置(r,c)的数目和

3.把棋盘上所有位置上经过K步还留在棋盘上的走法总和都算出来，然后计算

**具体代码**

```
public static double knightProbability(int N, int K, int r, int c) {
            int [][] moves = {{1,2},{1,-2},{2,1},{2,-1},{-1,2},{-1,-2},{-2,1},{-2,-1}};
            double [][] tempDp = new double[N][N];
            for(double [] row : tempDp){
                Arrays.fill(row, 1);
            }

            for(int step = 0; step<K; step++){
                double [][] innerDp = new double[N][N];
                for(int i = 0; i<N; i++){
                    for(int j = 0; j<N; j++){
                        for(int [] move : moves){
                            int row = i + move[0];
                            int col = j + move[1];
                            if(isInnerBoard(row, col, N)){
                                innerDp[row][col] += tempDp[i][j];
                            }
                        }
                    }
                }
                tempDp = innerDp;
            }
            return tempDp[r][c]/Math.pow(8,K);
        }

        private static boolean isInnerBoard(int row, int col, int len){
            return row>=0 && row<len && col>=0 && col<len;
        }
```
**时间复杂度** O(K*N^2) 

**空间复杂度** O(N^2)

leetcode 代码提交后发现只击败了30%的用户有待优化

其实还可以优化已经遍历过的落点，存储起来便于重复时可以获取已经计算过的，不过这样二维数组就无法满足了。需要三维的。具体代码

```
int[][] moves = {{1,2},{1,-2},{-1,2},{-1,-2},{2,-1},{2,1},{-2,-1},{-2,1}};
    public double knightProbability(int N, int K, int r, int c) {
        double[][][] dp = new double[K+1][N][N];
        return helper(dp, N, K, r, c)/Math.pow(8.0, K);
    }
    private double helper(double[][][] dp, int N, int k, int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= N) return 0.0;
        if (k == 0) return 1.0;
        if (dp[k][r][c] != 0.0) return dp[k][r][c];
        for (int i = 0; i < 8; i++)  
            dp[k][r][c] += helper(dp, N, k-1, r+moves[i][0], c+moves[i][1]);
        return dp[k][r][c]; 
    }
```
参考了leet上star比较认可的解法 [具体地址](https://leetcode.com/problems/knight-probability-in-chessboard/discuss/108187/cjava-dp-concise-solution)


