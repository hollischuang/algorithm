package demo2.test;

import demo2.DoubleNode;

/**
 * 测试双向链表
 * @author admin
 */
public class TestDoubleNode {
    public static void main(String[] args) {
        // 创建节点
        DoubleNode n1 = new DoubleNode(1);
        DoubleNode n2 = new DoubleNode(2);
        DoubleNode n3 = new DoubleNode(3);
        // 打印节点的值，结果为 1
        System.out.println(n1.pre().getData());
        // 打印结果为 1
        System.out.println(n1.getData());
        // 打印结果为 1
        System.out.println(n1.next().getData());
        System.out.println();

        // 节点之间建立连接
        n1.after(n2);
        n2.after(n3);
        // 打印节点的值，打印结果为 1
        System.out.println(n2.pre().getData());
        // 打印结果为 2
        System.out.println(n2.getData());
        // 打印结果为 3
        System.out.println(n2.next().getData());
        System.out.println();

        // 双向循环链表最后添加的节点，后继节点指向第一个节点，打印结果为 1
        System.out.println(n3.next().getData());
        // 打印结果为 3
        System.out.println(n1.pre().getData());


    }
}
