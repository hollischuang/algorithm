package demo2;

/**
 * 定义双向链表的节点
 * @author admin
 */
public class DoubleNode {
    /**
     * 前置节点
     */
    DoubleNode pre = this;

    /**
     * 后继节点
     */
    DoubleNode next = this;

    /**
     * 节点数据
      */
    int data;

    /**
     * 构造函数
     * @param data
     */
    public DoubleNode(int data){
        this.data = data;
    }

    /**
     * 插入节点方法
     * @param node
     */
    public void after(DoubleNode node){
        // 获取当前节点的后继节点
        DoubleNode nextNext = this.next;
        // 将当前节点的后继节点指向新节点
        this.next = node;
        // 新节点的前置节点指向当前节点
        node.pre = this;

        // 新节点的后继节点指向原后继节点
        node.next = nextNext;
        // 原后继节点的前置节点指向新节点
        nextNext.pre = node;

        // 上述过程将新节点插入到当前节点和原后继节点之间
    }

    /**
     * 获取后继节点
     * @return
     */
    public DoubleNode next(){
        // 返回后继节点
        return this.next;
    }

    /**
     * 获取前置节点
     * @return
     */
    public DoubleNode pre(){
        // 返回前置节点
        return this.pre;
    }

    /**
     *  获取数据
     * @return
     */
    public int getData(){
        // 返回当前节点数据
        return this.data;
    }
}

