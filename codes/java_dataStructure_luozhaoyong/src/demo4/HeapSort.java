package demo4;

import java.util.Arrays;

/**
 * 堆排序
 * <p>
 * 假设有大小为 n 的顺序排列二叉树
 * 1. 首先将顺序排列二叉树调整为大顶堆
 * 2. 交换堆顶元素和最后一个叶子节点，即数组的第 0 个元素和第 n 个元素交换
 * 3. 数组第 n 个元素已经排好序，数组递减 1，对递减后的数组重复步骤 1~2
 *
 * @author admin
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = new int[]{9, 6, 8, 7, 0, 1, 10, 4, 2};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 堆排序方法
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        // 1. 获取最后一个非叶子节点下标
        // 最后一个叶子节点的下标为 arr.length - 1
        // 根据公式，最后一个叶子节点的父节点下标为 (arr.length - 1) / 2
        // 最后一个叶子节点的父节点，就是最后一个非叶子节点
        int start = (arr.length - 1) / 2;
        // 2. 从最后一个非叶子节点开始，将整个顺序存储二叉树调整为大顶堆
        for (int i = start; i >= 0; i--) {
            // 调用方法将当前位置 i 的子树调整为大顶堆
            maxHeap(arr, arr.length, i);
        }

        // 3. 遍历数组，每轮都将大顶堆的堆顶移动到当前轮的最后
        for (int i = arr.length - 1; i > 0; i--) {
            // 将堆顶元素 arr[0] 交换到数组当前的末尾位置 i
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // 交换后堆顶的结构被破坏
            // 重新调用方法将堆顶调整为大顶堆
            maxHeap(arr, i, 0);
        }
    }

    /**
     * 将顺序排列二叉树调整为大顶堆的方法
     *
     * @param arr 数组
     * @param size 数组大小
     * @param index 要操作的节点在数组中 arr 中的下标
     */
    public static void maxHeap(int[] arr, int size, int index) {
        // 1. 获取当前节点的左右子节点下标
        int leftNode = index * 2 + 1;
        int rightNode = index * 2 + 2;
        // 定义一个变量 max，用于存储节点中最大节点的下标
        // 初始值为当前元素下标 index
        int max = index;

        // 2. 比较当前节点和左右子节点并找出最大值下标
        // 比较 max 节点和左子节点，左子节点下标 leftNode 要小于数组长度
        if (leftNode < size && arr[max] < arr[leftNode]) {
            // 如果 leftNode 对应的值更大，将 leftNode 赋值给 max
            max = leftNode;
        }

        // 比较 max 节点和右子节点，右子节点下标 rightNode 要小于数组长度
        if (rightNode < size && arr[max] < arr[rightNode]) {
            // 如果 rightNode 对应的值更大，将 rightNode 赋值给 max
            max = rightNode;
        }

        // 3. 如果最大值下标 max 与 当前元素下标不相等
        // 说明当前节点不是最大值，需要将最大值交换到当前节点的位置
        if (max != index) {
            // 交换 max 位置和 index 位置的元素
            int temp = arr[max];
            arr[max] = arr[index];
            arr[index] = temp;

            // 4. 交换元素后，如果 max 位置的元素是非叶子节点
            // 需要重新调整它的结构，重新调用 maxHeap 方法
            maxHeap(arr, size, max);
        }

    }

}
