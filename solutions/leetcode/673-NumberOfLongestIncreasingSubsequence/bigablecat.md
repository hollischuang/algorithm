**673. 最长递增子序列的个数**  
---
[https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/](https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/)  

* 英文官方题解1：暴力法  

```java  
    
	/**
     * https://leetcode.com/articles/number-of-longest-increasing-subsequence/
     * 英文官方题解1，暴力法
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        //获取nums的长度N
        int N = nums.length;
        //如果N不大于1，直接返回N
        if (N <= 1) return N;
        //lengths用于记录以数字nums[i]结尾的子序列长度
        int[] lengths = new int[N]; //lengths[i] = length of longest ending in nums[i]
        //counts用于记录以数字nums[i]结尾的子序列总共有多少个
        int[] counts = new int[N]; //count[i] = number of longest ending in nums[i]
        // 用数字1填充lengths和counts数组
        // 即默认情况下，数组nums中每个元素都可以组成一个子序列
        // 每个子序列只有一个元素，序列长度为1，序列的个数为1
        Arrays.fill(lengths, 1);
        Arrays.fill(counts, 1);

        //外循环迭代次数是数组nums的长度N
        for (int j = 0; j < N; ++j) {
            //内循环迭代次数是外循环的控制变量j
            for (int i = 0; i < j; ++i) {
                //因为是递增子序列，只针对nums[i] < nums[j]的情况进行操作
                if (nums[i] < nums[j]) {
                    //lengths[i]和lengths[j]分别表示以nums[i]和nums[j]结尾的子序列的长度
                    if (lengths[i] >= lengths[j]) {
                        //因为nums[i]比nums[j]小，所以lengths[i]应该小于lengths[j]
                        //lengths[i] >= lengths[j]需要对lengths[j]的值进行更新
                        //以nums[j]结尾的递增子序列，包含了以nums[i]结尾的子序列的所有元素，并至少多出一个元素nums[j]
                        //所以lengths[i] + 1表示在nums[i]结尾的子序列后面添加一个元素nums[j]
                        lengths[j] = lengths[i] + 1;
                        //counts[i]和counts[j]分别代表以nums[i]和nums[j]结尾的子序列个数
                        //在所有以nums[i]结尾的子序列后面都可以添加元素nums[j]组成新的递增子序列
                        //将counts[i]赋值给counts[j]，counts[j]表示在nums[i]基础上以nums[j]结尾的子序列的数目
                        counts[j] = counts[i];
                    } else if (lengths[i] + 1 == lengths[j]) {
                        //如果lengths[i] + 1 == lengths[j]
                        //则所有以nums[i]结尾的子序列再加上一个元素nums[j]
                        //可以组成一批新的子序列，这批子序列都以nums[j]结尾，个数为counts[i]
                        //当前以num[j]结尾，长度为lengths[j]的子序列的个数为counts[j]
                        //在counts[j]的基础上，加上counts[i]
                        //即counts[j] += counts[i]得到最新的以nums[j]结尾的子序列的个数
                        counts[j] += counts[i];
                    }
                }
            }
        }
        //定义一个整数longest用于存储最长子序列的个数
        //定义一个整数ans作为最终结果返回
        int longest = 0, ans = 0;
        //遍历所有子序列长度值
        for (int length : lengths) {
            //找出其中最大的子序列长度，赋值给longest
            longest = Math.max(longest, length);
        }
        //迭代N次，N为数组nums长度
        for (int i = 0; i < N; ++i) {
            //如果以nums[i]结尾的子序列长度与最长长度相等
            if (lengths[i] == longest) {
                //将以nums[i]结尾的子序列的总数存入ans
                ans += counts[i];
            }
        }
        //返回最终结果
        return ans;
    }

```  

**复杂度分析**  

时间复杂度：O(N^2)，
嵌套for循环，最差情况迭代 N^2次

空间复杂度：O(N)，
新建数组lengths和counts，占用空间都是N

---

**参考资料**  

* 英文官方题解：  
[https://leetcode.com/articles/number-of-longest-increasing-subsequence/](https://leetcode.com/articles/number-of-longest-increasing-subsequence/)  
