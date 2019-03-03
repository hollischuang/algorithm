**514. 自由之路**  
---
[https://leetcode-cn.com/problems/freedom-trail/](https://leetcode-cn.com/problems/freedom-trail/)  
解决方案   
**思路**  
路径搜索问题,或者字符串匹配问题,可以正向或者逆向匹配. 
动态规划记录当前的状态result[i][j],即当前匹配到key的第i个字母,ring的第j个字母在12点方向,
要匹配key的下一个字母时,可以从上一个状态顺时针或者逆时针转移到现在的状态.
```
class Solution {
    public int findRotateSteps(String ring, String key) {
        if (ring == null || key == null) return 0;
        int m = ring.length();
        int n = key.length();
        int[][] result = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (key.charAt(i) == ring.charAt(j)) {
                    if (i == 0) {
                        result[i][j] = Math.min(j, m - j);
                    } else {
                        for (int k = 0; k < m; k++) {
                            if (result[i - 1][k] != Integer.MAX_VALUE)
                                result[i][j] = Math.min(result[i][j], result[i - 1][k] + Math.min(Math.abs(j - k), m - Math.abs(j - k)));
                        }
                    }
                }
            }
        }
        int ans = result[n - 1][0];
        for (int j = 1; j < m; j++) {
            if (ans > result[n - 1][j])
                ans = result[n - 1][j];
        }
        return ans + n;
    }
}
```  
**复杂度分析**      
平均时间复杂度:O(n*n*n)
空间复杂度:O(n)

**参考资料**  
[https://www.cnblogs.com/kexinxin/p/10372522.html](https://www.cnblogs.com/kexinxin/p/10372522.html)