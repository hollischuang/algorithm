package demo4;

import java.util.Arrays;

/**
 * 冒泡排序
 * <p>
 * 多次遍历数组
 * 每次逐个比较相邻两个元素
 * 如果没有按照指定顺序排列，就互换元素
 * 直到遍历结束或者全部有序为止
 *
 * @author admin
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 7, 2, 9, 4, 1, 0, 5, 7};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 冒泡排序
     * <p>
     * 时间复杂度：O(n^2)
     * 外循环执行了 n 次
     * 内循环每次都排除上一轮已排好序的末尾元素，因此每轮递减 1
     * (n-1) + (n-2) + ... 1 = (n-1+1)/2 = n/2
     * 外循环 * 内循环 = n*(n/2) = n^2/2
     * O(n^2/2) = O(n^2)
     * <p>
     * 空间复杂度：O(1)，没有使用额外空间
     */
    public static void bubbleSort(int[] arr) {
        // 如果数组长度小于等于 1，不用排序
        if (arr.length <= 1) {
            return;
        }
        // 临时变量，用于两数交换时做临时存储
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {

            // 每轮排序，最大的值都会排到末尾
            // i = 1 时，arr[arr.length-1-1] 已经在上一轮排好序了
            // i = 2 时，arr[arr.length-1-2] 已经在上一轮排好序了
            // 因此内循环控制变量 j 的值，最大应小于 arr.length - 1 - i
            // 可以避免重复检查数组末尾已经排好序的部分
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 临时变量存储 arr[j] 的值
                    temp = arr[j];
                    // 将 arr[j+1] 的值赋给 arr[j]
                    arr[j] = arr[j + 1];
                    // 将原 arr[j] 的值赋给 arr[j]
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
