package demo4;

import java.util.Arrays;

/**
 * 归并排序
 * <p>
 * 将数组分为两部分
 * 对每部分都递归使用归并排序方法
 * 两部分排好序后
 * 再将数组的两部分合并到一起
 *
 * @author admin
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 2, 4, 6, 8, 10};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 归并排序方法
     * 将原数组折半划分为两部分
     * 依次对两部分递归调用递归算法
     * 直到数组不可再分
     * 对排好序的两部分调用归并方法合并为一个数组
     * <p>
     * 时间复杂度：
     * 每轮都将数组折半，直到不可再分，总共是 logN 轮
     * 每一轮的合并方法最多执行 n 次循环
     * 总共的时间复杂度是 O(NlogN)
     *
     * @param arr  待排序数组
     * @param low  要排序部分的起始位置
     * @param high 要排序部分的结束位置
     */
    public static void mergeSort(int[] arr, int low, int high) {
        // 如果起始位置不小于结束位置，结束排序
        if (low >= high) {
            return;
        }
        // 获取中间位置
        int middle = low + (high - low) / 2;
        // 为左半部分数组排序
        mergeSort(arr, low, middle);
        // 为右半部分数组排序
        mergeSort(arr, middle + 1, high);
        // 将排好序的两部分数组合并
        merge(arr, low, middle, high);
    }

    /**
     * 归并数组的合并方法
     * <p>
     * 原数组已经被分为两部分，每部分都各自有序
     * 创建一个与原数组等长的临时数组
     * 依次从两部分中取出元素进行对比，按照顺序放入临时数组
     * 所有元素都放入新数组后，整个数组已经排好序
     * 将临时数组重新赋值给原有数组
     *
     * @param arr
     * @param low
     * @param middle
     * @param high
     */
    public static void merge(int[] arr, int low, int middle, int high) {
        // 创建一个新数组，用于存储合并后的元素
        int[] temp = new int[high - low + 1];
        // 临时变量 i 和 j 分别指向两部分数组的起始位置
        // 第一部分数组从 low 开始
        int i = low;
        // 第二部分数组从 middle + 1开始
        int j = middle + 1;

        // 定义一个下标用于遍历新数组
        int index = 0;
        // 遍历原数组的两部分
        while (i <= middle && j <= high) {
            // 归并排序的条件之一就是要合并的两部分数组是各自排好序的
            // 即数组两部分满足 arr[i] <= arr[i+1] 和 arr[j] <= arr[j+1]
            // 当 arr[i] <= arr[j] 时，将较小的 arr[i] 放入新数组后
            // 继续向后遍历，数组两部分都不会出现比 arr[i] 更小的数字
            // 同理，如果 arr[j] 较小，继续遍历也不会出现比 arr[j] 更小的数字
            // 所以，这种方式排列出的新数组是有序的
            // 通过比较，将 arr[i] 和 arr[j] 中较小的数字放入新数组
            if (arr[i] <= arr[j]) {
                // arr[i] 放入新数组 index 位置后
                // index 和 i 都要递增
                temp[index++] = arr[i++];
                // 上述写法是简便写法，等价于下面的写法
                // newArr[index] = arr[i];
                // index++;
                // i++;
            } else {
                // 同理，将 arr[j] 放入新数组后
                // index 和 j 都递增
                temp[index++] = arr[j++];
            }
        }
        // 上一个循环结束时，可能会出现 i 或 j 没有遍历到各自结尾的情况
        // 将没有被遍历到的部分依次放入新数组
        while (i <= middle) {
            temp[index++] = arr[i++];
        }
        while (j <= high) {
            temp[index++] = arr[j++];
        }

        // 将合并好的新数组元素，逐个赋值给原数组对应的位置
        for (int k = 0; k < temp.length; k++) {
            arr[low + k] = temp[k];
        }
    }
}
