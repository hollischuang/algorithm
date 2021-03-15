package demo2.test;

import demo2.LoopNode;

/**
 * 测试循环链表
 *
 * @author admin
 */
public class TestLoopNode {
    public static void main(String[] args) {
        LoopNode n1 = new LoopNode(1);
        LoopNode n2 = new LoopNode(2);
        LoopNode n3 = new LoopNode(3);
        LoopNode n4 = new LoopNode(4);

        // 增加节点
        n1.after(n2);
        n2.after(n3);
        n3.after(n4);

        // 打印结果是 2
        System.out.println(n1.next().getData());
        // 打印结果是 3
        System.out.println(n2.next().getData());
        // 打印结果是 4
        System.out.println(n3.next().getData());
        // 打印结果是 1，循环链表首尾相连
        System.out.println(n4.next().getData());
    }
}
