**689. 三个无重叠子数组的最大和**  
---
[https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays/](https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays/)  

```java  

   public int[] maxSumOfThreeSubarrays(int[] nums, int K) {
        // W是由数组nums中每K个元素的和组成的整数数组
        // 把间隔K看成滑动窗口的长度
        // 窗口在nums数组上从第1个元素开始滑动
        // 窗口每滑动一次，就计算一次窗口内所有元素的和，放入数组W
        // 滑动窗口到nums的第nums.length - K个元素时，共计算了nums.length - K次
        // 此时nums数组中还剩K个元素，可以计算最后一次
        // 所以W的长度是nums.length - K + 1
        int[] W = new int[nums.length - K + 1];
        //定义一个整数sum用于存储每K个元素的和
        int sum = 0;
        //遍历数组nums并求得W数组的所有值
        for (int i = 0; i < nums.length; i++) {
            //用+=对nums中连续的元素累加求和
            sum += nums[i];
            // 如果i>=K，说明前K个元素的值已经累加完毕，窗口开始向右滑动
            // 从K个元素之后，每次滑动1个元素
            // 需要从sum里减去上一次滑动窗口的首个元素nums[i - K]
            // sum -= nums[i - K]得到从第[i-K+1]个元素起到第i个元素为止的K个元素之和
            if (i >= K) sum -= nums[i - K];
            // 第一组K个元素在nums中的下标从0到K-1
            // 所以W的第一个元素是 W[i-K+1] = W[(K-1)-K+1] = W[0]
            if (i >= K - 1) W[i - K + 1] = sum;
        }

        // 首先需要明白本解法中nums、W和left(或right)这三个数组索引的对应关系
        //
        // 先看W[i]和nums[i]的关系：
        // 在前一个给W赋值的for循环代码中可以看出，W[i]对应着nums中从nums[i]算起，到nums[i+K-1]，共K个元素的和
        // 如题目示例，nums=[1,2,1,2,6,7,5,1]，K=2，W=[3,3,3,8,13,12,6]
        // W[0]=nums[0]+nums[1]=1+2=3，即W[0]等于nums[0]到nums[0+2-1]=nums[1]，共2个元素的和
        //
        // 再看left[i]和W[i]的关系：
        // 给left[i](或right[i])赋值时，需要找到从W[0]到W[i]为止，元素值且索引最小那个元素
        // 然后将W中这个元素的索引赋给left[i]
        // 即给left[0]赋值时，比较W[0]到W[0]共1个元素，
        // 给left[1]赋值时，比较W[0]到W[1]共2个元素，
        // 给left[2]赋值时，比较W[0]到W[2]共3个元素...以此类推
        // 因为W[0]=w[1]=W[2]=3，而W[0]、W[1]、W[2]三个元素中W[0]的索引最小
        // 所以left[0]=left[1]=left[2]=0，都取最小的那个索引0
        //
        // 同时，由于到nums[nums.length - K + 1]为止，W和nums中的索引是一一对应的
        // 而left[i]记录的又是W中的索引，所以left[i]与nums中的元素索引一一对应
        // 索引的对应关系清楚了，下面关于left和right的代码就容易理解了

        //题目要求返回nums中和最大的3个子序列的起始索引
        //我们将这3个子序列中的第1个子序列的可能索引都存入一个整数数组left
        int[] left = new int[W.length];
        //定义一个整数best，记录每次迭代时，从数组W中获取到的相对最大值在W中的索引
        //best默认值为0
        int best = 0;
        //按索引从小到大遍历数组W
        for (int i = 0; i < W.length; i++) {
            //W[i] > W[best]表示当前第i个元素W[i]，大于W中已知的最大元素W[best]
            //将索引i赋值给best
            if (W[i] > W[best]) best = i;
            //将当前为止W中最大值的索引best赋给left[i]
            left[i] = best;
        }

        //题目要求返回nums中和最大的3个子序列的起始索引
        //我们将这3个子序列中的第3个子序列的可能索引都存入一个整数数组right
        int[] right = new int[W.length];
        //best默认值为W数组的末尾索引
        best = W.length - 1;
        //按索引从大到小遍历数组W
        for (int i = W.length - 1; i >= 0; i--) {
            //W[i] > W[best]表示当前第i个元素W[i]，大于等于W中已知的最大元素W[best]
            //将索引i赋值给best
            if (W[i] >= W[best]) best = i;
            //将当前为止W中最大值的索引best赋给right[i]
            right[i] = best;
        }

        // 定义一个整数数组ans用于返回最终结果，即3个子序列的初始索引
        int[] ans = new int[]{-1, -1, -1};
        // for循环的控制变量j在循环体中要进行j-K和j + K的操作
        // j-K要大于等于0，j+K要小于W.length
        // 所以j的初始值为K，j的上限值W.length - K
        for (int j = K; j < W.length - K; j++) {
            // 定义3个子序列的中间序列的索引为j
            // 那么W[j]等于nums[j]到nums[j+K-1]共K个元素的和
            // 因为3个子序列不重叠，所以：
            // 第1个序列的索引应该小于或等于j-K
            // 第3个序列的索引应该大于或等于j+K
            // left[j - K]记录了到从nums[0]到nums[j-K]为止，子序列和最大初始索引最小的那个索引
            // right[j - K]记录了到nums[nums.length]到nums[j+K]为止，子序列和最大初始索引最小的那个索引
            int i = left[j - K], k = right[j + K];
            //当ans[0] == -1时，ans还没有赋值
            //当W[i] + W[j] + W[k] > W[ans[0]] + W[ans[1]] + W[ans[2]]时
            //说明找到了更大的3个子序列之和
            //满足上述两个条件之一，即对ans[0]、ans[1]、ans[2]重新赋值
            if (ans[0] == -1 || W[i] + W[j] + W[k] >
                    W[ans[0]] + W[ans[1]] + W[ans[2]]) {
                //分别将初始索引i、j、k赋值给ans[0]、ans[1]、ans[2]
                ans[0] = i;
                ans[1] = j;
                ans[2] = k;
            }
        }
        //返回最终结果
        return ans;
    }

```  

**复杂度分析**  

时间复杂度：O(N)，
方法中使用了4个for循环，迭代次数都在nums的长度范围内，
所以时间复杂度为4N，消去常数项，时间复杂度为O(N)

空间复杂度：O(N)，
定义了3个整数数组W、left、right，
占用空间都在nums数组的长度范围内，
还定义了一个数组ans，长度为常数3，
所以空间复杂度是3N+3，消去常数项最终空间复杂度为O(N)  

---

**参考资料**  

* 本题leetCode英文官方题解：  
[https://leetcode.com/articles/maximum-sum-of-3-non-overlapping-intervals/](https://leetcode.com/articles/maximum-sum-of-3-non-overlapping-intervals/)  
