package demo4;

import java.util.Arrays;

/**
 * 希尔排序
 * <p>
 * 取某个数字作为步长，按步长对数组进行插入排序
 * 排序完成后，步长按规律递减
 * 用新步长进行下一轮插入排序
 * 重复上述步骤，直到步长变成 1，进行最后一轮普通的插入排序为止
 *
 * @author admin
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 2, 7, 8, 1, 2, 0, 4, 7, 4, 3, 8};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 希尔排序方法
     * 数组完全逆序时，接近插入排序的时间复杂度 O(n^2)
     * 最优时间复杂度，约为 O(n^1.3)
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {
        // 用于记录当前排序次数，与主逻辑无关，仅用于打印结果
        int k = 1;

        // 除数设定为 2，每次步长都是上一次的 1/2
        int divisor = 2;
        // 遍历所有步长的情况
        for (int d = arr.length / divisor; d > 0; d /= divisor) {
            // 外循环控制变量 i 起始位置为 d
            // d 是规定的步长，当 d ==1 时，就变成了插入排序
            for (int i = d; i < arr.length; i++) {
                // 内循环控制变量 j
                for (int j = i - d; j >= 0; j -= d) {
                    // 如果 arr[j] 比更靠后的元素 arr[j+d] 大
                    // 则交换两者位置，保持前面数字更小的顺序
                    if (arr[j] > arr[j + d]) {
                        // 交换 arr[j] 和 arr[j+d] 的位置
                        int temp = arr[j];
                        arr[j] = arr[j + d];
                        arr[j + d] = temp;
                    }
                }
            }
            System.out.println("第 " + k + " 次排序结果 " + Arrays.toString(arr));
            k++;
        }
    }
}
