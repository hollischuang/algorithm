**790. 多米诺和托米诺平铺**  
---
[https://leetcode-cn.com/problems/domino-and-tromino-tiling/](https://leetcode-cn.com/problems/domino-and-tromino-tiling/](https://leetcode-cn.com/problems/domino-and-tromino-tiling/)  

解决方案   
**思路**  
思路1:此道题目根据相关数据来推到结论，得到计算每一个的公式，在根据公式来计算相应的结果。
公式推到如下：
dp[n]=dp[n-1]+dp[n-2]+ 2*(dp[n-3]+...+d[0])
         =dp[n-1]+dp[n-2]+dp[n-3]+dp[n-3]+2*(dp[n-4]+...+d[0])
         =dp[n-1]+dp[n-3]+(dp[n-2]+dp[n-3]+2*(dp[n-4]+...+d[0]))
         =dp[n-1]+dp[n-3]+dp[n-1]
         =2*dp[n-1]+dp[n-3]

```
 public int numTilings(int N){
        //result: dp[i] = 2*dp[i-1] + dp[i-3];
        int md = 1000000007;
        //map to save value
        Map<Integer, Long> valueMap = new HashMap<>(1001);
        valueMap.put(1,1L);
        valueMap.put(2,2L);
        valueMap.put(3,5L);
        if (N <=3){
            return valueMap.get(N).intValue();
        }
        for (int i = 4; i <= N;++i) {
            //根据来计算值
            Long tmp = 2 * valueMap.get(i - 1)  + valueMap.get(i - 3);
            //取余
            Long value = tmp % md;
            valueMap.put(i,Long.valueOf(value));
        }
        return valueMap.get(N).intValue();
    }
```

**复杂度分析**      
时间复杂度：O(N) ,由于计算N的值需要得到之前的数据，因此循环计算，时间复杂度位o(N)
空间复杂度：O(M*N),空间使用dp


**参考资料**  
[https://leetcode.com/problems/domino-and-tromino-tiling/discuss/116581/Detail-and-explanation-of-O(n)-solution-why-dpn2*dn-1%2Bdpn-3](https://leetcode.com/problems/domino-and-tromino-tiling/discuss/116581/Detail-and-explanation-of-O(n)-solution-why-dpn2*dn-1%2Bdpn-3) 