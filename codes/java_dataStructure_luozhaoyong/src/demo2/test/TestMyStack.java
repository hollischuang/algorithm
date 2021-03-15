package demo2.test;

import demo2.MyStack;

/**
 * 测试栈
 *
 * @author admin
 */
public class TestMyStack {
    public static void main(String[] args) {
        MyStack ms = new MyStack();
        // 测试栈为空时抛出异常
        try {
            ms.pop();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        // 压入数据
        ms.push(9);
        ms.push(8);
        ms.push(7);

        // 查看栈顶元素，打印结果为 7
        System.out.println(ms.peek());

        // 取出栈顶元素，打印结果为 7
        System.out.println(ms.pop());

        // 再次查看栈顶元素，打印结果为 8
        System.out.println(ms.peek());

        // 判断栈是否为空，打印结果为 false
        System.out.println(ms.isEmpty());

        // 取出栈顶元素，打印结果为 8
        System.out.println(ms.pop());
        // 打印结果为 9
        System.out.println(ms.pop());

        // 判断栈是否为空，打印结果为 true
        System.out.println(ms.isEmpty());
    }
}
