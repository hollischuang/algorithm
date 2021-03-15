package demo2;

/**
 * 定义节点类
 * @author admin
 */
public class Node {
    /**
     * 节点数据
      */
    int data;
    /**
     * 后继节点
     */
    Node next;

    /**
     * 构造方法
     * @param data
     */
    public Node(int data) {
        this.data = data;
    }

    /**
     * 追加节点
     * @param node
     * @return
     */
    public Node append(Node node) {
        // 定义一个变量 currentNode 指向当前节点
        Node currentNode = this;

        // 教学视频中的写法
//        while (true){
//            // 获取当前节点的后继节点
//            Node nextNode = currentNode.next;
//            // 如果后继节点为空，跳出循环
//            if(nextNode==null){
//                break;
//            }
//            // 当前节点指向后继节点，循环继续
//            currentNode = nextNode;
//        }
        // 如果后继节点非空，循环继续
        while (currentNode.next != null) {
            // 当前节点 currentNode 指向后继节点
            currentNode = currentNode.next;
        }
        // 循环结束时，currentNode 已经指向链表的尾节点
        // 将参数 node 赋值给 currentNode 的后继节点
        currentNode.next = node;
        // 返回当前节点
        return this;
    }

    /**
     * 获取后继节点
     * @return
     */
    public Node next() {
        // 直接返回后继节点
        return this.next;
    }

    /**
     * 获取节点数据
     * @return
     */
    public int getData() {
        return this.data;
    }

    /**
     * 判断当前节点是否是最后一个节点
     * @return
     */
    public boolean isLast() {
        // 判断当前节点的后继节点是否为空
        return this.next == null;
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
     * @param node
     */
    public void after(Node node) {
        // 获取当前节点的后继节点
        Node nextNext = this.next;
        // 当前节点的后继节点指向新加入的节点
        this.next = node;
        // 新节点的后继节点指向原先的后继节点
        node.next = nextNext;
    }

    /**
     * 打印所有节点的值
     */
    public void show() {
        Node currentNode = this;
        while (currentNode != null) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }
}
