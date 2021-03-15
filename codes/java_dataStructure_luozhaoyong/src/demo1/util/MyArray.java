package demo1.util;

import java.util.Arrays;

/**
 * 可变的数组
 *
 * @author admin
 */
public class MyArray {
    /**
     * 用于存储数据的数组
     */
    private int[] elements;

    /**
     * 构造方法
     */
    public MyArray() {
        // 初始化元素数量为 0
        elements = new int[0];
    }

    /**
     * 获取数组长度
     */
    public int size() {
        return elements.length;
    }

    /**
     * 向数组末尾添加元素
     *
     * @param element
     */
    public void add(int element) {
        int[] newArr = new int[elements.length + 1];

        // 将原数组中的元素逐个赋值给新数组
        for (int i = 0; i < elements.length; i++) {
            newArr[i] = elements[i];
        }
        // 在新数组末尾添加新元素
        newArr[newArr.length - 1] = element;

        // 用新数组替换原始数组
        elements = newArr;
    }

    /**
     * 显示数组中的所有元素到控制台
     */
    public void show() {
        System.out.println(Arrays.toString(elements));
    }

    /**
     * 删除指定位置的元素
     *
     * @param index
     */
    public void delete(int index) {
        // 判断下标是否越界
        if (index < 0 || index > elements.length - 1) {
            throw new RuntimeException("下标越界");
        }
        // 创建新数组，长度比原数组小 1
        int[] newArr = new int[elements.length - 1];

        // 将要删除元素下标之前的元素拷贝给新数组
        for (int i = 0; i < index; i++) {
            newArr[i] = elements[i];
        }

        // 将要删除元素下标之后的元素拷贝给新数组
        for (int i = index; i < newArr.length; i++) {
            // 跳过要删除的元素 index，原数组 elements 中每个下标都 +1
            newArr[i] = elements[i + 1];
        }

        // 新数组替换老数组
        elements = newArr;
    }

    /**
     * 获取指定位置的元素
     *
     * @param index
     * @return
     */
    public int get(int index) {
        // 判断下标是否越界
        if (index < 0 || index > elements.length - 1) {
            throw new RuntimeException("下标越界");
        }
        // 从数组中取出指定下标的元素
        return elements[index];
    }

    /**
     * 插入一个元素到指定位置
     *
     * @param index
     * @param element
     */
    public void insert(int index, int element) {
        // 判断下标是否越界
        if (index < 0 || index > elements.length - 1) {
            throw new RuntimeException("下标越界");
        }
        // 创建新数组，长度是原数组长度+1
        int[] newArr = new int[elements.length + 1];
        // 将指定下标之前的元素复制给新数组
        for (int i = 0; i < index; i++) {
            newArr[i] = elements[i];
        }
        // 将指定下标之后的元素赋值给新数组
        for (int i = index; i < elements.length; i++) {
            // 跳过了指定下标 index，新数组每个下标都 +1
            newArr[i + 1] = elements[i];
        }
        // 将目标值赋给新数组 index 位置
        newArr[index] = element;
        // 新数组替换原数组
        elements = newArr;
    }

    /**
     * 为数组中指定位置赋值
     *
     * @param index
     * @param element
     */
    public void set(int index, int element) {
        // 判断下标是否越界
        if (index < 0 || index > elements.length - 1) {
            throw new RuntimeException("越界");
        }
        // 直接赋值
        elements[index] = element;
    }

    /**
     * 线性查找
     *
     * @param target
     * @return
     */
    public int search(int target) {
        // 遍历数组
        for (int i = 0; i < elements.length; i++) {
            // 如果当前元素与目标值相等
            if (elements[i] == target) {
                // 返回当前元素下标
                return i;
            }
        }
        // 否则返回 -1
        return -1;
    }

    /**
     * 二分查找
     *
     * @param target
     * @return
     */
    public int binarySearch(int target) {
        // 起始位置，默认值为数组第一个元素下标
        int begin = 0;
        // 结束位置，默认值为数组最后一个元素下标
        int end = elements.length - 1;
        // 中间位置，begin + (end-begin)/2 代替 (begin+end)/2
        // 如果 (begin + end) > Integer.MAX_VALUE 时，会造成程序溢出
        int mid = begin + (end - begin) / 2;

        // 当开始位置小于或等于结束位置时，循环继续
        // 当 begin == end 时，end - begin = 0
        // 则 mid = begin + (end - begin)/2 = begin + 0/2 = begin
        // 如果 begin 位置还没有被查看
        // begin <= end 需要考虑 begin = end 的情况
        while (begin <= end) {
            // 如果 mid 位置刚好等于目标值
            if (elements[mid] == target) {
                // 返回下标 mid
                return mid;
            }
            // 如果 mid 位置元素大于目标值
            if (elements[mid] > target) {
                // 将结束位置左移到 mid 位置前一个位置
                end = mid - 1;
            } else {
                // 如果 mid 位置元素小于目标值
                // 说明目标值在 mid 的右边
                // 将开始位置右移到 mid 后一个位置
                begin = mid + 1;
            }
            // 重新计算 mid 位置的值
            mid = begin + (end - begin) / 2;
        }
        // 如果没有查询到对应的下标，返回 -1
        return -1;
    }


}
