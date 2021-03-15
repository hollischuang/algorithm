package demo1;

/**
 * 数组的基本使用
 * @author admin
 */
public class TestArray {
    public static void main(String[] args) {
        // 创建数组
        // int 是数组内元素的类型；3 是数组的大小
        int[] arr1 = new int[3];

        // 获取数组长度
        int length1 = arr1.length;
        // 打印结果 3
        System.out.println("arr1's length：" + length1);

        // 访问数组中的元素：数组名[下标]
        // 注意：下标从 0 开始，下标最大可以取到 (数组长度 - 1)
        // 获取数组第 1 个元素，即下标为 0 的元素
        int element0 = arr1[0];
        // 打印结果 0
        System.out.println("element0: " + element0);

        // 为数组中的元素赋值
        arr1[0] = 99;
        element0 = arr1[0];
        // 打印结果 99
        System.out.println("element0: " + element0);

        arr1[1] = 98;
        arr1[2] = 97;

        // 遍历数组，从下标 0 开始，到 (数组长度 - 1) 结束
        for (int i = 0; i < length1; i++) {
            // 打印第 i 个元素
            System.out.println("arr1 element " + i + ": " + arr1[i]);
        }

        // 创建数组的同时为元素赋值
        int[] arr2 = new int[]{90, 80, 70, 60, 50};
        // 打印结果 5
        System.out.println("arr2's length：" + arr2.length);
    }
}
