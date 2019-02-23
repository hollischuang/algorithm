**416. 分割等和子集**  
---
[https://leetcode-cn.com/problems/partition-equal-subset-sum/](https://leetcode-cn.com/problems/partition-equal-subset-sum/)  

* 网友高票Java解法  

```java  

    /**
     * https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation
     * 网友高票Java解法
     *
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        int sum = 0;

        //遍历数组，求得所有数字之和
        for (int num : nums) {
            sum += num;
        }

        //sum & 1位运算用于判断数字的奇偶
        //1的二进制是0000...0001 即前面31位都是0，第32位是1
        //偶数的二进制末尾是0，奇数的二进制末尾是1
        //其他任何二进制数与1进行位与运算，结果只有0和1两种
        //如果sum是奇数，不能再分为相等的两个整数，不符合题意
        if ((sum & 1) == 1) {
            return false;
        }
        //假设当前数组符合题意，即有两个子集的元素之和相等
        //sum /= 2得到其中一个子集的所有元素之和
        sum /= 2;

        //定义一个boolean数组dp
        //数组的长度是sum+1
        //数组中的每一个元素dp[i]表示数字i能否由数组nums中的元素求和得到
        boolean[] dp = new boolean[sum + 1];
        //当和等于0时，必定有0相加得0，所以dp[0]为true
        //dp[0]是整个dp数组的基数，其他元素的真值由dp[0]得到
        dp[0] = true;

        //再次遍历数组nums
        for (int num : nums) {
            //根据sum的大小进行sum次迭代
            for (int i = sum; i > 0; i--) {
                //i的值由nums中的元素相加得到
                //下面的语句判断i是否包含了num
                //当 i >= num 时，i可能包含了num
                if (i >= num) {
                    //如果 i 包含num
                    //那么 i-num 是由num之外的另外若干元素相加得到
                    //dp[i]表示i是否由数组中的元素相加得到
                    //dp[i]的真值应该和dp[i - num]保持一致
                    //只要dp[i]和dp[i - num]中有一个为真，说明dp[i]为真
                    dp[i] = dp[i] || dp[i - num];
                    //实际上所有真值都是通过基础值dp[0]推算而来
                    //例如，当i=num时，必定有i-num = 0
                    //那么dp[i] = dp[i] || dp[i-num] = dp[i] || dp[0] = dp[i] || true = true
                    //nums中的元素num必定可以通过自身的值求和得到，所以dp[i]为真是正确的
                }
            }
        }
        //dp[sum]即表示sum是否可以通过数组nums的值相加而来
        return dp[sum];
    }

```  

**复杂度分析**  

时间复杂度：O(n^2)，
根据题设，nums是正整数非空数组，
那么nums的任意一个元素nums[i]>=1，
sum是所有nums元素的和，
在进入循环前经过了折半处理，
所以sum >= nums.length()/2，
将sum看做n，那么nums.length()<=n*2，
本解法中有1个独立的for循环，
遍历nums数组1次，
时间复杂度<=O(n*2)，
另有一对嵌套for循环，
嵌套for循环的外循环遍历数组nums一次，
时间复杂度<=O(n*2)，
嵌套for循环的内循环与sum的大小一致，
内循环的复杂度为O(n)，
嵌套循环的时间复杂<=O(n*2*n)=O(2n^2)，
再加上独立的for循环，
总的时间复杂度<=O(n*2 + 2n^2)，
消去低阶项n*2和常数系数2，
最终的时间复杂度<=O(n^2)

空间复杂度：O(n)，
创建了一个sum+1大小的boolean数组，
占用了n+1的空间，
空间复杂度为O(n)

---

**参考资料**  

* 网友高票Java解法：  
[https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation](https://leetcode.com/problems/partition-equal-subset-sum/discuss/90592/01-knapsack-detailed-explanation)  
