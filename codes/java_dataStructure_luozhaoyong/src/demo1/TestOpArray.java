package demo1;

import java.util.Arrays;

/**
 * 数组动态扩容
 * 解决数组长度不可变的问题
 * @author admin
 */
public class TestOpArray {
    public static void main(String[] args) {
        // 原数组
        int[] arr = new int[]{9, 8, 7};

        // 直接调用 java.util.Arrays 中的方法，将数组元素转为 String 格式用于打印和查看
        System.out.println(Arrays.toString(arr));

        // 要加入数组的新元素
        int dst = 6;

        // 1. 创建一个新数组，长度是原数组长度 + 1
        int[] newArr = new int[arr.length + 1];
        // 2. 把原数组中的元素复制到新数组中
        for (int i = 0; i < arr.length; i++) {
            // 将原数组中的第 i 个元素 arr[i]
            // 赋值给新数组第 i 个位置
            newArr[i] = arr[i];
        }
        // 打印新数组中的所有元素
        System.out.println(Arrays.toString(newArr));
        // 3. 将目标元素放入新数组的最后
        // 新数组最后一个位置的下标是 newArr.length - 1
        // 教学视频中使用了 arr.length，两者是相等的，newArr.length - 1 更容易理解
        newArr[newArr.length - 1] = dst;
        // 4. 新数组替换原数组，即原数组的变量 arr，指向新数组
        arr = newArr;
        System.out.println(Arrays.toString(arr));
    }


}
