package demo4;

import java.util.Arrays;

/**
 * 快速排序
 * <p>
 * 1) 从数组中找出一个基准数
 * 2) 数组定义左右两个指针分别向中间移动
 * 3) 数组左侧的值比基准值大，则移到数组右侧
 * 4) 数组右侧的值比基准值小，则移到数组左侧
 * 5) 当左右两个指针重合时，当前轮排序结束
 * 6) 指针重合的位置将数组分为两部分，分别对两部分递归调用快速排序
 * 7) 重复上述步骤，直到排序完成
 *
 * @author admin
 */
public class QuickSort {
    public static void main(String[] args) {
        // 创建数组
        int[] arr = new int[]{3, 4, 6, 7, 2, 7, 2, 8, 0, 9, 1};
        // 调用快速排序方法
        quickSort(arr, 0, arr.length - 1);
        // 打印结果
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 时间复杂度：O(NlogN)
     * 每一轮都将数组从头到尾遍历和交换，所以每轮的时间复杂度为 O(N)
     * 每轮结束时，都将数组分为左右两半，再分别递归
     * 即第一轮 n/2
     * 第二轮 n/2/2
     * 直到不能再分
     * 总共进行了 logN 次减半再分别递归的操作
     * 所以时间复杂度为 O(Nlog(N))
     * <p>
     * 快速排序
     *
     * @param arr   要排序的数组
     * @param start 起始位置
     * @param end   结束位置
     */
    public static void quickSort(int[] arr, int start, int end) {
        // 如果起始位置大于或等于结束位置，结束当前方法
        if (start >= end) {
            return;
        }

        // 将数组中第 0 个位置的数字作为基准值
        int stard = arr[start];
        // 定义一个指针 low，从起始位置向结束位置移动
        int low = start;
        // 定义一个指针 high，从结束位置向起始位置移动
        int high = end;

        // 当 low 指针小于 high 指针时
        while (low < high) {
            // 从结束位置遍历
            // 如果右侧的值大于等于基准值，符合较大的值在基准值右侧的条件
            // 则将指针 high 递减，即向左移动
            while (low < high && arr[high] >= stard) {
                high--;
            }
            // 循环结束时，说明出现了 arr[high]<stard 的情况
            // 此时将比 stard 小的 arr[high] 放到 low 的位置
            arr[low] = arr[high];

            // 从开始位置遍历
            // 如果左侧的值小于等于基准值，符合较小的值在基准值左侧的条件
            // 则将指针 low 递增，即向右移动
            while (low < high && arr[low] <= stard) {
                low++;
            }
            // 循环结束时，说明出现了 arr[low]>stard 的情况
            // 此时将比 stard 大的 arr[low] 放到 high 的位置
            arr[high] = arr[low];
        }
        // 当循环结束时，low == high，左右两侧的指针重合
        // 把基准值放到 low 和 high 重合的位置
        // 则基准值左侧的数字小于等于基准值
        // 则基准值右侧的数字大于等于基准值
        // low 和 high 重合，下标用哪个都可以
        arr[low] = stard;

        // 把 stard 左右两侧切分为 2 个数组，分别递归调用快速排序方法
        quickSort(arr, start, low);
        quickSort(arr, low + 1, end);
    }
}
