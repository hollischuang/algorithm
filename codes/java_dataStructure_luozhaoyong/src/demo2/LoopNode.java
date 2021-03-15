package demo2;

/**
 * 定义节点类
 *
 * @author admin
 */
public class LoopNode {
    /**
     * 节点数据
     */
    int data;
    /**
     * 后继节点
     * 尾节点指向头节点
     */
    LoopNode next = this;

    /**
     * 构造方法
     *
     * @param data
     */
    public LoopNode(int data) {
        this.data = data;
    }

    /**
     * 获取后继节点
     *
     * @return
     */
    public LoopNode next() {
        // 直接返回后继节点
        return this.next;
    }

    /**
     * 获取节点数据
     *
     * @return
     */
    public int getData() {
        return this.data;
    }


    /**
     * 删除当前节点的下一个节点
     */
    public void removeNext() {
        // 如果当前节点的后继节点为空，直接返回
        if (next == null) {
            return;
        }
        // 将当前节点的后继节点指向下下个节点
        this.next = next.next;
    }

    /**
     * 插入一个新节点
     *
     * @param node
     */
    public void after(LoopNode node) {
        // 获取当前节点的后继节点
        LoopNode nextNext = this.next;
        // 当前节点的后继节点指向新加入的节点
        this.next = node;
        // 新节点的后继节点指向原先的后继节点
        node.next = nextNext;
    }
}
