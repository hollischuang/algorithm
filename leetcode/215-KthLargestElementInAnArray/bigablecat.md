**215. 找数组中第K大的数**
---
[https://leetcode-cn.com/problems/kth-largest-element-in-an-array/](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/)

* 《程序员面试金典(第5版)》第8章：排序与查找，结合leetCode上网友高效解法：  

```java  

    /**
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        //调用递归方法找到第k个最大值
        // 第k个最大元素在数组nums从右向左数的第k个位置
        // 即从左往右数第(nums.length - k + 1)个位置
        // 数组下标从0计数，第k个最大元素的下标为 (nums.length - k + 1) - 1 = nums.length - k
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    /**
     * 《程序员面试金典(第5版)》第8章：排序与查找
     * https://leetcode-cn.com/submissions/api/detail/215/java/3/
     * <p>
     * 快速选择算法
     *
     * @param nums  数组
     * @param left  最左侧元素下标
     * @param right 最右侧元素下标
     * @param K     目标位置
     * @return 第k个最大值
     */
    public int quickSelect(int[] nums, int left, int right, int K) {
        //如果起始下标left和结尾下标right重合，即left和right所在位置即基准值
        if (left == right) {
            //返回下标start在数组中对应的值nums[start]
            return nums[right];
        }
        // 调用分割方法partition，返回结果index是当前排序之后基准值pivot的下标
        // pivot左侧元素小于pivot，pivot右侧元素大于pivot
        int index = partition(nums, left, right);
        // 本方法内的大写字母K代表数组nums中第k大的值，距离当前左边界left有多远
        // (index - left)得到本轮求得的基准值坐标index距离当前左边界left有多远
        // K与(index - left)比较大小，判断K在基准值坐标index的左侧还是右侧
        if (K >= (index - left)) {
            // K >= (index - left)表示nums中第k大的值在基准值右侧
            // 取值范围nums[index]到nums[right]
            // K值是到左边界left的距离
            // 左边界更新为index时，当前K值减去index到左边界left的距离得到新的K值
            return quickSelect(nums, index, right, K - (index - left));
        } else {
            //第k大的值比当前基准值小，在基准值左侧，取值范围nums[left]到nums[index-1]之间
            //因为左边界left没有改变，所以仍然使用当前K值
            return quickSelect(nums, left, index - 1, K);
        }
    }

    /**
     * 使用QuickSelection快速选择算法，分割数组
     *
     * @param nums  数组
     * @param left  数组最左侧元素的下标
     * @param right 数组最右侧元素的下标
     * @return
     */
    public int partition(int[] nums, int left, int right) {
        // 先定义一个基准值pivot
        // 本方法中选用数组最左侧和最右侧下标的平均数
        // 取得一个位于数组中间位置的元素作为基准值
        int pivot = nums[(left + right) / 2];
        //在循环体中，left递增，right递减，两个下标不断靠近
        //当left和right交叉(left>right)时，当前一轮完成了排序，循环结束
        while (left <= right) {
            // while循环自左向右不断检索数组nums
            // 在到达或越过pivot之前，所有nums[left]都在pivot左侧
            // 当不满足条件nums[left] < pivot时
            // 得到了一个应该被放到pivot右侧的元素，它的下标为left
            while (nums[left] < pivot) {
                // left不断递增
                // 即指针不断向数组右侧移动
                // 直至到达或越过基准值pivot
                left++;
            }
            // while循环自右向左不断检索数组nums
            // 在到达或越过pivot之前，所有nums[right]都在pivot右侧
            // 当不满足条件nums[right] > pivot时
            // 得到了一个应该被放到pivot左侧的元素，它的下标为right
            while (nums[right] > pivot) {
                // right不断递增
                // 即指针不断向数组左侧移动
                // 直至到达或越过基准值pivot
                right--;
            }
            //经过上两轮while循环，此时nums[left]>=pivot>=nums[right]
            //可以推出nums[left]>=nums[right]
            //如果此时left<=right，需要交换两个元素的值，保证数组按照从小到大的次序排列
            if (left <= right) {
                // 调用swap方法
                // 交换数组nums中，下标left和right对应的两个元素
                swap(nums, left, right);
                //交换后下标left继续递增1次，right继续递减1次
                left++;
                right--;
            }
        }
        //返回更新后的left值
        return left;
    }

    /**
     * swap方法，交换数组中两个元素的位置
     *
     * @param nums  数组
     * @param left  左侧元素的下标
     * @param right 右侧元素的下标
     * @return
     */
    public int[] swap(int[] nums, int left, int right) {
        //定义一个临时变量存放右侧元素
        int temp = nums[right];
        //将左侧元素赋值给右侧元素
        nums[right] = nums[left];
        //将临时变量存储的原右侧元素赋值给左侧元素
        nums[left] = temp;
        //返回交换后的数组
        return nums;
    }

```  

**复杂度分析**  

空间复杂度：O(1)，
没有使用额外空间，空间复杂度是O(1)

**参考资料**  

* 《程序员面试金典(第5版)》第8章：排序与查找  

* 网友高效答案：  
[https://leetcode-cn.com/submissions/api/detail/215/java/3/](https://leetcode-cn.com/submissions/api/detail/215/java/3/)  
