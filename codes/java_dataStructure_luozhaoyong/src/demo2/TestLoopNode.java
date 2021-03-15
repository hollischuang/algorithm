package demo2;

/**
 * 测试循环链表
 */
public class TestLoopNode {
    public static void main(String[] args) {
        // 创建新节点
        LoopNode n1 = new LoopNode(1);
        LoopNode n2 = new LoopNode(2);
        LoopNode n3 = new LoopNode(3);
        LoopNode n4 = new LoopNode(4);
        // 插入节点
        n1.after(n2);
        // 显示结果
        System.out.println(n1.next().getData()); // 2
        System.out.println(n2.next().getData()); // 1
    }
}
