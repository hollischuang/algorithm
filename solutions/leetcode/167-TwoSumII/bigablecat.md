**167. 两数之和 II - 输入有序数组**  
---

[https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/)  

* 双指针法  

```java  

    /**
     * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/discuss/51239/Share-my-java-AC-solution.
	 * 双指针法
     *
     * 时间复杂度：O(N)，
     * 遍历数组1次
     *
     * 空间复杂度：O(1)，
     * 只定义了一个长度为2的整型数组变量，
     * 空间复杂度为O(1)
     *
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers, int target) {
        //定义一个长度为2的整数数组，用于存储返回的index1和index2
        int[] indexArr = new int[2];
        //如果输入的数组numbers为空或者长度小于2，直接返回空数组indexArr
        if (numbers == null || numbers.length < 2) return indexArr;
        //定义整型变量index1，从numbers初始下标0开始
        int index1 = 0;
        //定义整数index2，从numbers最后一个下标numbers.length - 1开始
        int index2 = numbers.length - 1;
        //从下标0开始遍历数组
        for (int index = 0; index < numbers.length; index++) {
            //求得下标index1和下标index2对应元素的和sum
            int sum = numbers[index1] + numbers[index2];
            //查看sum是否等于目标值target
            if (sum == target) {
                //符合条件则跳出for循环
                break;
            }
            // 如果sum不等于目标值，分别对index1和index2进行增减操作
            if (sum > target) { // 当两数相加大于目标值
                //将index2递减，右移获取更小的值
                index2--;
            } else if (sum < target) { // 当两数相加小于目标值
                // 将index1递增，左移获取更大的值
                index1++;
            }
        }
        //返回的下标从1开始计数，所以index1和index2分别加1
        indexArr[0] = index1 + 1;
        indexArr[1] = index2 + 1;
        return indexArr;
    }

```  

**复杂度分析**  

时间复杂度：O(N)，
遍历数组1次

空间复杂度：O(1)，
只定义了一个长度为2的整型数组变量，
空间复杂度为O(1)

---

**参考资料**  

* 网友高票Java解法：  
[https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/discuss/51239/Share-my-java-AC-solution.](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/discuss/51239/Share-my-java-AC-solution.)  