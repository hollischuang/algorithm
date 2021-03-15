package demo4;

import java.util.Arrays;

/**
 * 插入排序
 * <p>
 * 从第一个元素开始，把数组分成有序和无序两部分
 * 每轮都从无序部分取出一个元素
 * 按照指定顺序插入有序部分
 * 重复上述步骤，有序部分不断向右扩大，直到所有元素排好序
 *
 * @author admin
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 2, 8, 5, 9, 1, 0};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入排序方法
     * <p>
     * 外循环执行了 n 次
     * 内循环每次都为插入元素，将有序部分的元素移动若干次
     * 最差情况依次执行了 1 次移动、2 次移动、3 次移动，n-1 次移动
     * 但是并非每次都要移动有序部分的所有元素
     * 均摊情况可以视为大致移动了一半的元素
     * 因此内均摊情况依次进行了 1/2 次移动、2/2 次移动、3/2 次移动、 (n-1)/2 次移动
     * 总共执行了 (1/2 + 2/2 + 3/2 + ... (n-1)/2) = (1/2 + (n-1)/2))/2 = n/4 次移动
     * 所以总的时间复杂度是
     * 外循环执行次数 * 内循环时间复杂度 = n * n/4 = n^2/4
     * O(n^2/4) = O(n^2)
     *
     * @param arr 待排序的数组
     */
    public static void insertSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        // 临时变量，用于存储当前元素的值
        int temp;
        // 从下标 1，即第 2 个元素开始遍历
        for (int i = 1; i < arr.length; i++) {
            // 将当前元素 arr[i] 的值赋给临时变量 temp
            temp = arr[i];
            // 内循环控制变量 j 从 i 的前一个元素开始
            // 满足条件 j >=0 保证数组不越界
            // 同时满足 temp 比内循环当前元素 arr[j] 小
            int j;
            for (j = i - 1; j >= 0 && temp < arr[j]; j--) {
                // 将当前元素的值赋给后一个元素
                // 即所有比 temp 大的元素都不断后移
                // 直至找到比 temp 小的元素为止
                arr[j + 1] = arr[j];
            }
            // 循环结束时，arr[j] < temp
            // 此时 arr[j+1] 已经腾出了空间
            // 将 temp 值插入 arr[j + 1] 的位置
            // 满足条件 arr[j] < arr[j+1] = temp < arr[j+2]
            arr[j + 1] = temp;
        }

    }
}
