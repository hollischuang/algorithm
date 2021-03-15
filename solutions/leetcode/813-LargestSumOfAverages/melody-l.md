**813.LargestSumOfAverage**  
---
[https://leetcode-cn.com/problems/largest-sum-of-averages/](https://leetcode-cn.com/problems/largest-sum-of-averages/)  

方法一：动态规划  
本题的dp思路是：设result[i][k]表示序列A[0]到A[i]分成k份的最优解。假设，现在需要划分最后一组，则设第k-1份的终点为j（即第k份是从A[j]到A[i]）。此时`result[i][k]=result[j-1][k-1]+{sum(j...i)/(i-j+1)}`。  
因此，算法思路为：固定i，固定k，判断此时j最合适的位置。然后扩大i，判断不同的i的时候j最适合的位置。最后扩大k。这样递推式所需要的前项结果是能够提供的。  
下面代码优化的点为：提前前i项的和sum[i]求出，这样在计算从j到i的和的平均值为：`(sum[i]-sum[j])/(i-j+1)`。对于sum[i]，i表示的是序列的长度，非索引。  

```java  
class Solution {
    public double largestSumOfAverages(int[] A, int K) {
        int N = A.length;

        // sum[i]表示前i项的和，i表示的是序列的长度，非索引
        // sum[0]没有使用，所以长度为N+1
        double[] sum = new double[N + 1];
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + A[i];
        }

        // result[i][j]保存长度为i+1的序列，分割为j份的结果
        double[][] result = new double[A.length][K+1];
        for (int k = 1; k <= K; k++) {// 将序列分成k份，k取值为1到K
            for (int i = 0; i < N; i++) { // 序列从i开始遍历
                if (k == 1) {
                    // 只分割为1份，因此直接求平均值
                    result[i][k] = sum[i + 1] / (double) (i + 1);
                } else if (k > i + 1) {
                    // 如果要分割的份数比此时的序列长度还大，
                    // 就不进行计算
                    continue;
                } else {
                    // 对于固定的i（此时序列长度为i+1），
                    // 如果要分割为固定的k份，
                    // 现在，需要知道第k-1份的终止点为哪里时，效果最好，
                    // 因此，遍历所有的j，最大值的点即是k-1份的终止点
                    for (int j = k - 1; j <= i; j++) {// 从k-1开始遍历（前面至少要有k-1份能够被平分，不然没必要遍历），遍历到i为止（此时序列长度为i+1）
                        double temp = (sum[i+1] - sum[j]) / (i - j + 1) + result[j - 1][k - 1];// 若k-1份的终止点为j，则根据递推式求出当前值temp
                        result[i][k] = Math.max(result[i][k], temp);// 比较temp选最大
                    }
                }
            }
        }

        return result[N - 1][K]; //返回长度为N，分割为K份的值
    }
}
```  

---


**参考资料**  

* 官方题解：  
[https://leetcode.com/articles/delete-and-earn/](https://leetcode.com/articles/delete-and-earn/)  
* 网友题解：  
[https://blog.csdn.net/magicbean2/article/details/79893634](https://blog.csdn.net/magicbean2/article/details/79893634)
