package demo1;

import java.util.Arrays;

/**
 * 删除数组中的元素
 * @author admin
 */
public class TestOpArray2 {
    public static void main(String[] args) {
        // 原数组
        int[] arr = new int[]{9, 8, 7, 6, 5, 4};

        // 要删除元素的下标
        // 即 arr 中下标为 3 的元素，arr[3] = 6
        int dst = 3;

        // 1. 创建一个新数组，长度是原数组的的长度 -1
        int[] newArr = new int[arr.length - 1];

        // 2. 复制原数组中除了要删除的元素以外的其他元素
        // 方法1：视频中老师使用的方法，一个循环中加判断
//        for (int i = 0; i < arr.length - 1; i++) {
//            if (i < dst) {
//                // 下标比要删除的下标小，可以直接一对一拷贝
//                newArr[i] = arr[i];
//            } else {
//                // 下标大于要删除的下标，需要在原下标 i 的基础上 + 1
//                // 即跳过了要删除的下标
//                newArr[i] = arr[i + 1];
//            }
//        }
        // 方法2：将数组分为两段，分别用循环遍历，正好跳过了要删除的下标 dst
        // 数组中下标小于要删除的下标
        for (int i = 0; i < dst; i++) {
            // 直接通过相等的下标赋值
            newArr[i] = arr[i];
        }
        // 数组中下标大于要删除的下标
        for (int i = dst; i < arr.length - 1; i++) {
            // 在原有下标的基础上 +1，因为跳过了要删除的下标，之后每个下标都要 +1
            newArr[i] = arr[i + 1];
        }

        // 3. 用新数组替换原数组
        arr = newArr;

        // 打印结果
        System.out.println(Arrays.toString(arr));
    }
}
