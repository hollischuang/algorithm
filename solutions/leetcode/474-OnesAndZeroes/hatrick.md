**474. 一和零**  
---
[https://leetcode-cn.com/problems/ones-and-zeroes/](https://leetcode-cn.com/problems/ones-and-zeroes/)  

解决方案   
**思路**  
和01背包是很相似的题目,只不过背包问题是装一种东西,而我们这道题要求的是装上两种东西,也就是1和0。
我们的1和0相当于两类物品,n和m就是它们所对应的容量。
我们的到第i个字符串时, 它所对应的可以组成最多的字符串个数则就对应为:
dp[m][n]=MAX(dp[m][n],dp[m-count0][n-count1]+1)
所以我们要做的就是在迭代字符串的时候,即时更新dp[m][n];也就是说每多一个字符串,我就计算一下加进来这个字符串之后,我所能拼接成的最大字符串数量。
也就是说 当我只有一个字符串的时候,我求出我的dp[m][n],当我有两个字符串的时候,我根据上面的情况,在继续求出我现在的dp[m][n], 每多一个,就更新一下。
注意dp[m][n]是随着迭代而更新的
```
class Solution {
    //0-1背包问题，优化存储空间
    public int findMaxForm(String[] strs, int m, int n) {
        int l = strs.length;
        int zeros, ones;
        // dp[i][j]代表遍历到当前字符串时使用i个0和j个1所能组成的最大字符串数量
        int[][] dp = new int[m+1][n+1];
        for(int i = 0; i < l ; i++){
            zeros = 0;
            ones = 0;
            for(int j = 0; j < strs[i].length(); j++){
                if(strs[i].charAt(j) == '0'){
                    zeros++;
                }else{
                    ones++;
                }
            }
            for(int j = m; j >= zeros; j--){
                for(int k = n; k >= ones; k--){
                    dp[j][k] = Math.max(dp[j][k], dp[j-zeros][k-ones] + 1);
                }
            }
        }
        return dp[m][n];
    }
}
```  
**复杂度分析**      
时间复杂度:O(n)
空间复杂度:O(n)

**参考资料**  
[https://blog.csdn.net/qq_38595487/article/details/84235304](https://blog.csdn.net/qq_38595487/article/details/84235304)