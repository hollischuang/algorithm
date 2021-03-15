**264. 丑数 II**  
---
[https://leetcode-cn.com/problems/ugly-number-ii/](https://leetcode-cn.com/problems/ugly-number-ii/)  

* 网友高票Java解法  

```java  

    /**
     * https://leetcode.com/problems/ugly-number-ii/discuss/69362/O(n)-Java-solution
     * 网友高票Java解法
     * <p>
     * 思路：
     * 丑数从大到小依次为 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, …
     * 因为丑数只能被2,3,5整除
     * 可以将丑数拆分为3组
     * (factor2) 1×2, 2×2, 3×2, 4×2, 5×2, 6x2, 8x2 …
     * (factor3) 1×3, 2×3, 3×3, 4×3, 5×3, 6x3, 8x3 …
     * (factor5) 1×5, 2×5, 3×5, 4×5, 5×5, 6x5, 8x5 …
     * <p>
     * 即丑数在自身基础上不断累乘2,3,5中的一个数
     * 从这三组中依次选取最小的数存入丑数数组
     * 就得到了丑数从小到大排列的所有丑数
     *
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        //创建一个大小为n的整数数组ugly
        int[] ugly = new int[n];
        //丑数从1开始，所以ugly数组的第一个元素赋值为1
        ugly[0] = 1;
        //将丑数分为3组，factor2, factor3, factor5 分别与2,3,5累乘
        //定义下标index2,index3,index5获取丑数数组ugly中相应位置的数字
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        //从下标1，即ugly第二个元素开始循环，依次为ugly数组所有元素赋值
        for (int i = 1; i < n; i++) {
            //获取累乘2,3,5的三组数中的最小值
            int min = Math.min(Math.min(factor2, factor3), factor5);
            //让数组当前位置等于三者中最小值
            ugly[i] = min;
            //下列代码
            //首先查看 ugly[i] = min 是从factor2,factor3,factor5三组中哪一组里取走数字
            // (factor2) 1×2, 2×2, 3×2, 4×2, 5×2, 6x2, 8x2 …
            // (factor3) 1×3, 2×3, 3×3, 4×3, 5×3, 6x3, 8x3 …
            // (factor5) 1×5, 2×5, 3×5, 4×5, 5×5, 6x5, 8x5 …
            //然后通过这一组数对应的下标index2,index3或index5，
            //从ugly数组中选取能够继续累乘的最小数字
            //累乘的同时，对应的索引递增，下次不会取到重复数字
            //比如ugly[1] = 2;
            //此时factor2 = 2, factor3 = 3, factor5 = 5;
            //即 ugly[1] = factor2 = ugly[0] x 2 = 1 x 2
            // factor2这一组的第一个数字被取走了
            // 接下来要获取factor2这一组的第二个数字
            // factor2 = ugly[1] x 2 = 2 x 2 = 4
            if (factor2 == min)
                factor2 = 2 * ugly[++index2];
            if (factor3 == min)
                factor3 = 3 * ugly[++index3];
            if (factor5 == min)
                factor5 = 5 * ugly[++index5];
        }
        //ugly数组从下标0开始获取第一个数字
        //那么第n个数字的下标就是n-1
        //所以最终返回ugly[n - 1]
        return ugly[n - 1];
    }

```  

**复杂度分析**  

时间复杂度：O(n)，
只有一个for循环进行了n次迭代

空间复杂度：O(n)，
创建了一个大小为n的数组ugly

---

**参考资料**  

* 网友高票Java解法：  
[https://leetcode.com/problems/ugly-number-ii/discuss/69362/O(n)-Java-solution](https://leetcode.com/problems/ugly-number-ii/discuss/69362/O(n)-Java-solution)  
