**75. 颜色分类**  
---
[https://leetcode-cn.com/problems/sort-colors/](https://leetcode-cn.com/problems/sort-colors/)  

* 网友高票答案：  

```java  

    /**
     * https://leetcode.com/problems/sort-colors/discuss/26472/Share-my-at-most-two-pass-constant-space-10-line-solution
     * 网友高票答案
     *
     * @param A
     */
    public void sortColors(int A[]) {
        // 定义整数second代表数字2蓝色，zero代表数字0红色
        // 本方法的思路是将数字2蓝色后移到数组的右侧
        // 数字0红色前移到数组左侧
        // 剩余数字1白色在移动过程中也聚集到了中间
        // second初始值为n-1，即数组A下标的上限
        // zero初始值为0，即数组A下标的下限
        int second = A.length - 1, zero = 0;
        //从左向右遍历数组A
        for (int i = 0; i <= second; i++) {
            //如果当前元素A[i]为2蓝色，且下标i比second小
            //交换当前元素A[i]和A[second]在数组A中的位置
            //second--作为参数传入swap方法，递减是在swap方法结束之后才进行的
            //所以swap方法中操作的是A[second]
            while (A[i] == 2 && i < second) swap(A, i, second--);
            //如果当前元素A[i]为0白色，且下标i比zero大
            //交换当前元素A[i]和A[zero]在数组A中的位置
            //zero++作为参数传入swap方法，递增是在swap方法结束之后才进行的
            //所以swap方法中操作的是A[zero]
            while (A[i] == 0 && i > zero) swap(A, i, zero++);
        }
    }

    /**
     * swap方法，交换数组中两个元素的位置
     *
     * @param nums 数组
     * @param i    左侧元素的下标
     * @param j    右侧元素的下标
     * @return
     */
    public int[] swap(int[] nums, int i, int j) {
        //定义一个临时变量存放右侧元素
        int temp = nums[j];
        //将左侧元素赋值给右侧元素
        nums[j] = nums[i];
        //将临时变量存储的原右侧元素赋值给左侧元素
        nums[i] = temp;
        //返回交换后的数组
        return nums;
    }

```  

**复杂度分析**  

空间复杂度：O(1)，
只定义了3个整型变量，没有使用更多额外空间，空间复杂度是O(1)

**参考资料**  

* 网友高票答案：  
[https://leetcode.com/problems/sort-colors/discuss/26472/Share-my-at-most-two-pass-constant-space-10-line-solution](https://leetcode.com/problems/sort-colors/discuss/26472/Share-my-at-most-two-pass-constant-space-10-line-solution)  
