**69. x 的平方根**  
---  
[https://leetcode-cn.com/problems/sqrtx/](https://leetcode-cn.com/problems/sqrtx/)  

* 网友高票Java题解：  

```java  
    /**
     * https://leetcode.com/problems/sqrtx/discuss/25047/A-Binary-Search-Solution
     * 网友高票答案
     *
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        //如果x是0，平方根为0
        if (x == 0){
            //直接返回结果0
            return 0;
        }
        //分别设定左右边界left和right
        //left从非负整数1开始，right到java运行的整数最大值上限Integer.MAX_VALUE为止
        int left = 1, right = x;
        //while (true) 进行一个无限循环，只能在方法体内结束循环
        while (true) {
            // 通过二分法不断缩小取值范围
            // 目标是找到一个中间值mid，如果x介于mid²和(mid+1)²之间
            // 那么mid就是x平方根的整数部分
            int mid = left + (right - left) / 2;
            // mid > x/mid 等价于 mid² > x
            // 说明mid本身比x的平方根大
            if (mid > x / mid) {
                //将mid-1赋值给右边界right，在小于mid的范围内继续寻找x的平方根
                right = mid - 1;
            } else {
                // mid + 1 > x/(mid + 1) 等价于 (mid + 1)² > x
                // 说明 (mid + 1) 比 x 的平方根大
                if (mid + 1 > x / (mid + 1))
                    // mid 小于或等于 x 的平方根
                    // (mid + 1) 大于 x 的平方根
                    // mid 即是要找的数字
                    return mid;
                //如果 (mid + 1)² <
                left = mid + 1;
            }
        }
    }

```  

**参考资料**  

* 网友高票Java解法：  
[https://leetcode.com/problems/sqrtx/discuss/25047/A-Binary-Search-Solution](https://leetcode.com/problems/sqrtx/discuss/25047/A-Binary-Search-Solution)  
