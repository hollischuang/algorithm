package demo2.test;

import demo2.MyQueue;

/**
 * 测试队列
 * @author admin
 */
public class TestMyQueue {
    public static void main(String[] args) {
        // 创建一个队列
        MyQueue mq = new MyQueue();
        // 添加元素
        mq.add(9);
        mq.add(8);
        mq.add(7);
        // 出队，打印结果为 9
        System.out.println(mq.poll());
        mq.add(6);
        // 出队前有新元素入队，不影响出队的顺序，打印结果为 8
        System.out.println(mq.poll());
        // 判断是否为空，打印结果为 false
        System.out.println(mq.isEmpty());
        // 继续出队，打印结果为 7
        System.out.println(mq.poll());
        // 大姨结果为 8
        System.out.println(mq.poll());
        // 再判断是否为空，打印结果为 true
        System.out.println(mq.isEmpty());
    }
}
