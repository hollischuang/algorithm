package demo2;

/**
 * 数组实现一个队列
 * @author admin
 */
public class MyQueue {
    /**
     * 存储数据的数组
     */
    int[] elements;

    /**
     * 构造方法
     */
    public MyQueue() {
        // 初始化数组
        elements = new int[0];
    }

    /**
     * 入队
     * @param element
     */
    public void add(int element) {
        // 新建一个数组，长度是原数组的长度+1
        int[] newArr = new int[elements.length + 1];

        // 将原数组中的元素赋值逐个给新数组
        for (int i = 0; i < elements.length; i++) {
            newArr[i] = elements[i];
        }

        // 将新元素赋值给新数组的最后一个位置
        newArr[newArr.length - 1] = element;
        // 新旧数组替换
        elements = newArr;
    }

    /**
     * 出队
     * @return
     */
    public int poll() {
        if (elements.length == 0) {
            throw new RuntimeException("queue is empty");
        }
        // 取出数组的第一个元素
        int element = elements[0];

        // 创建一个新数组，长度比原数组长度少 1
        int[] newArr = new int[elements.length - 1];

        // 将原数组除第一个元素以外的所有元素赋值给新数组
        for (int i = 1; i < elements.length; i++) {
            // 取原数组元素时，i 从 1开始
            // 新数组下标从 0 开始，所以对应每个下标要在 i 的基础上 -1
            newArr[i - 1] = elements[i];
        }
        // 新旧数组替换
        elements = newArr;

        // 返回出队的元素
        return element;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty() {
        // 判断队列长度是否为 0
        return elements.length == 0;
    }


}
