**410. 分割数组的最大值**  
---
[https://leetcode-cn.com/problems/split-array-largest-sum/](https://leetcode-cn.com/problems/split-array-largest-sum/)  

解决方案   
**思路**  
使用二分法,首先可以发现,难点在于怎么判断分割是否可行,可以发现,当m=1的时候肯定可行（和最大,全部元素在一块）,
当m=nums.length的时候也可行（和最小,为全部元素中的最大值）,那么就二分这个最大和最小值就可以了,判断是否可分,
可分就将和缩小,使得需要的m值变小;反之则扩大
```
//使用二分法进行动态查找
public int splitArray(int[] nums, int m) {
        long left = 0, right = 0;
        for (int n: nums) {
            right += n;
        }
        if (m == 1) {
            return (int)right;
        }
        long result = 0;
        long mid;
        while (left <= right) {
            mid = left+right >> 1;
            if (judge(mid, nums, m)) {
                result = mid;
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        return (int)result;
    }

    private boolean judge(long mid, int[] nums, int m) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > mid) {
                return false;
            }
            if (sum + nums[i] > mid) {
                sum = nums[i];
                m--;
            } else {
                sum += nums[i];
            }
        }
        return m >= 1;
    }
```  
**复杂度分析**      
平均时间复杂度:O(nlogn)
空间复杂度:O(1)

**参考资料**  
[https://blog.csdn.net/zhangjingao/article/details/86607677](https://blog.csdn.net/zhangjingao/article/details/86607677)