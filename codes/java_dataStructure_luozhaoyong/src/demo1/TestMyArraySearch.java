package demo1;

import demo1.util.MyArray;

/**
 * 测试查找方法
 * @author admin
 */
public class TestMyArraySearch {
    public static void main(String[] args) {
        MyArray ma = new MyArray();
        ma.add(1);
        ma.add(2);
        ma.add(3);
        ma.add(4);
        ma.add(5);
        // 线性查找
        int index = ma.search(4);
        System.out.println("index: " + index);

        // 调用二分法查找
        int index2 = ma.binarySearch(-1);
        System.out.println("index2: " + index2);
    }
}
