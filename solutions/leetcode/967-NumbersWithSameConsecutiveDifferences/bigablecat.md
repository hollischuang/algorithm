**967. 连续差相同的数字**  
---
[https://leetcode-cn.com/problems/numbers-with-same-consecutive-differences/](https://leetcode-cn.com/problems/numbers-with-same-consecutive-differences/)  

* 官方DP题解  

```java  
    
    /**
     * https://leetcode-cn.com/articles/numbers-with-same-consecutive-differences/
     * 官方题解
     *
     * @param N
     * @param K
     * @return
     */
    public static int[] numsSameConsecDiff(int N, int K) {
        //新建一个HashSet对象cur，用于存储数字1到9
        Set<Integer> cur = new HashSet();
        //根据题意1 <= N <= 9
        //将数字1到9存入HashSet对象cur中
        for (int i = 1; i <= 9; ++i)
            cur.add(i);

        //对1到N-1的每一个数字进行相同的操作
        for (int steps = 1; steps <= N - 1; ++steps) {
            //另新建一个HashSet对象cur2，用于存储最新结果并排重
            Set<Integer> cur2 = new HashSet();
            //遍历cur中的元素
            for (int x : cur) {
                // x % 10 得到 x的个位数字d
                int d = x % 10;

                //根据题意，d与下一位数字的差的绝对值为K
                //那么有两种情况:
                // 如果d比下一位数字大，有d - K >= 0
                // 如果d比下一位数字小，有d + K <= 9

                //d - K >= 0表示d比下一位数字大的情况
                if (d - K >= 0) {
                    // (d - K)得到下一位数字
                    // 10 * x 将x扩大一个10进制，(d - K)作为10 * x的个位，相加后得到新数字
                    cur2.add(10 * x + (d - K));
                }

                //d + K <= 9表示d比下一位数字小的情况
                if (d + K <= 9) {
                    // 10 * x 将x扩大一个10进制，(d + K)作为10 * x的个位，相加后得到新数字
                    cur2.add(10 * x + (d + K));
                }
            }
            //将cur指向最新结果cur2
            cur = cur2;
        }
        //如果N为1，根据题意，单独一个数字0是有效的
        if (N == 1)
            //将0加入HashSet对象cur
            cur.add(0);
        //创建一个与HashSet对象cur同样大小的int数组
        int[] ans = new int[cur.size()];
        //定义一个整数t作为数组ans下标
        int t = 0;
        //遍历cur中的元素
        for (int x : cur)
            //将cur的值存入数组ans
            ans[t++] = x;
        //返回数组ans
        return ans;
    }

```  


**复杂度分析**  

时间复杂度：O(2^N)
每一位数字都有2种可能，N个数字有2^N种可能

空间复杂度：O(2^N)
用HashSet开辟了额外的空间，
每一位数字都有2种可能，
HashSet对象的空间最大为2^N

---

**参考资料**  

* 英文官方题解：  
[https://leetcode-cn.com/articles/numbers-with-same-consecutive-differences/](https://leetcode.com/articles/number-of-longest-increasing-subsequence/)  
