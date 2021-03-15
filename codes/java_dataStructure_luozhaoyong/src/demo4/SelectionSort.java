package demo4;

import java.util.Arrays;

/**
 * 选择排序
 * <p>
 * 将数组看作有序和无序两部分
 * 每次都从无序部分找出最小的元素，与无序部分的第一个元素交换位置
 * 直到数组完全排序为止
 *
 * @author admin
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 5, 7, 1, 2, 0, 3, 6, 8};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序方法
     * 时间复杂度：
     * 内循环每次都会从无序部分找出最小的元素
     * 依次比较了 n-1 次，n-2 次，n-3 次 ... 1 次
     * 总共比较的次数是 (n-1) + (n-2) + ...+ 1 = (n-1 + 1) /2 = n/2 次
     * 外循环执行了 n 次，总共的时间复杂度是 O(n*n/2) = O(n^2/2) = O(n^2)
     *
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        // 当数组元素小于等于 1 时，天然有序，无需进行排序
        if (arr.length <= 1) {
            return;
        }
        // 遍历数组
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                // 如果内循环当前元素 arr[j] 比已知最小值还小
                // 则将最小值下标 minIndex 替换为 j
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 内循环结束时
            // 未排序部分第一个元素是 arr[i]
            // 未排序部分最小元素是 arr[minIndex]
            // 如果 i 与 minIndex 不相等
            // 则需要交换两者的值
            // 让未排序部分的最小元素排到未排序部分的第一个元素位置
            if (i != minIndex) {
                // 交换两者的位置
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
}
