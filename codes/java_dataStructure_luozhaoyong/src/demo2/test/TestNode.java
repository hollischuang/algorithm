package demo2.test;

import demo2.Node;

/**
 * 测试单链表
 *
 * @author admin
 */
public class TestNode {
    public static void main(String[] args) {
        // 创建节点
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        // 追加节点
        n1.append(n2).append(n3).append(new Node(4));
        // 获取后继节点，打印结果为 3
        System.out.println(n1.next().next().getData());
        // 判断节点是否为最后一个节点，打印结果为 false
        System.out.println(n1.isLast());
        // 打印结果为 true
        System.out.println(n1.next().next().next().isLast());

        // 显示已有节点，打印结果 1 2 3 4
        n1.show();
        // 删除 n3
        n1.next().removeNext();
        // 显示删除后剩余的节点，打印结果 1 2 4
        n1.show();

        // 创建一个新节点
        Node node = new Node(3);
        // 将新节点插入 n2 之后
        n1.next().after(node);
        // 重新显示所有节点，打印结果 1 2 3 4
        n1.show();

        // 再来一次
        // 创建一个新节点
        node = new Node(5);
        // 将新节点插入 n2 之后
        n1.next().after(node);
        // 重新显示所有节点，打印结果 1 2 5 3 4
        n1.show();
    }
}
