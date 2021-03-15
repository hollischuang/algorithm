package demo1;

import demo1.util.MyArray;

/**
 * 测试可变数组
 * @author admin
 */
public class TestMyArray {

    public static void main(String[] args) {
        // 创建一个可变数组
        MyArray ma = new MyArray();
        // 获取数组长度
        int size = ma.size();
        System.out.println(size);
        // 显示可变数组中的所有元素到控制台
        ma.show();

        // 向可变数组末尾添加一个元素
        ma.add(99);
        ma.add(98);
        ma.add(97);
        ma.show();

        // 测试删除元素，删除下标为 1 的元素
        ma.delete(1);
        ma.show();

        // 获取指定下标的元素
        int element = ma.get(1);
        System.out.println(element);

        System.out.println("=============");
        ma.add(96);
        ma.add(95);
        ma.add(94);
        ma.show();

        // 向指定位置插入元素
        ma.insert(3, 33);
        ma.show();
        System.out.println("=============");

        // 测试替换指定位置的值
        ma.set(0, 100);
        ma.show();

        // 打印数组长度，set 方法对原数组操作，数组长度没有变化
        // 打印结果为 6
        System.out.println(ma.size());

        // 测试下标越界
        // 会抛出越界异常
        ma.set(-1,100);
    }
}
