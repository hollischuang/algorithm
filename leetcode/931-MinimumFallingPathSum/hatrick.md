**931. 下降路径最小和**  
---
[https://leetcode-cn.com/problems/minimum-falling-path-sum/](https://leetcode-cn.com/problems/minimum-falling-path-sum/)  

解决方案   
**思路**  
开二维数组,存第一行的所有数,从第二行开始,找每个位置能从上一行哪些位置下降过来,将其中的最小值赋值就可以了。其实就是上面的图,将指向反过来看就可以了:
那么除了两端的特殊情况,其他都是能从3个位置下降过来,有状态转移方程：
dp[i][j] = A[i][j] + Min(dp[i-1][j-1],dp[i-1][j],dp[i-1][j+1])
注意判定最左边和最右边两种情况就行了。另外,竟然破天荒给了数据范围,当n = 1的时候只有一个下降数组就是本身,这个判定一下就行。
最终答案要for循环遍历一下最下面一层,看最小值是多少。最小值就是答案。

```
class Solution {
    public static int[][] dp;
    
    public static int Min(int a,int b){
        return a < b ? a : b;
    }
    
    public int minFallingPathSum(int[][] A) {
        int len = A[0].length;
        if(len == 1) return A[0][0];
        dp = new int[len][len];
        for(int i = 0;i < len;i++){
            dp[0][i] = A[0][i];
        }
        for(int i = 1;i < len;i++){
            for(int j = 0;j < len;j++){
                if(j == 0){
                    dp[i][j] = A[i][j] + Min(dp[i-1][j],dp[i-1][j+1]);
                }else{
                    if(j == len-1){
                        dp[i][j] = A[i][j] + Min(dp[i-1][j],dp[i-1][j-1]);
                    }else{
                        dp[i][j] = A[i][j] + Min(Min(dp[i-1][j],dp[i-1][j+1]),dp[i-1][j-1]);
                    }
                }
            }
        }
        int ans = 20000;
        for(int i = 0;i < len;i++){
            ans = Min(ans,dp[len-1][i]);
        }
        return ans;
    }
}

```  


**参考资料**  
[https://www.itbox.info/p/139142/leetcode-minimum-falling-path-sum](https://www.itbox.info/p/139142/leetcode-minimum-falling-path-sum)