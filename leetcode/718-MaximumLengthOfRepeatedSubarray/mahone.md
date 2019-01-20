**718. 最长重复子数组**  
---
[https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/](https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/)  

解决方案   
**思路**  
思路1: 我们通过动态规划(dp)的方式来解决这种问题，动态规划的问题多数有重叠子问题这个特点，为减少计算，对每一个字问题，只解一次，将不同阶段的不同状态保存在一个二位数组中。

```
        int aLength = A.length;
        int bLength = B.length;
        //定义一个二维数组,如果dp[i][j]，则A数组中的第i个取值和B中的第j个值一定相等
        int[][] dp = new int[aLength + 1][bLength + 1];
        int result = 0;
        //循环比对值，并个二维数组赋值
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j] = A[i - 1] == B[j - 1] ? dp[i - 1][j - 1] + 1 : 0;
                //这个取最大的值即代表最大重复的子数组大小
                result = Math.max(result, dp[i][j]);
            }
        }
        return result;
```

**复杂度分析**      
时间复杂度：O(N) 循环所有数组元素,由于两层循环,每次查找花费O(1)时间,所以最后复杂度为O(M*N)，M，N分别是A，B数组的大小
空间复杂度：O(M*N),空间使用dp


**参考资料**  
* 本题leetCode英文官方题解：  
[https://leetcode.com/articles/maximum-length-of-repeated-subarray/](https://leetcode.com/articles/maximum-length-of-repeated-subarray/) 