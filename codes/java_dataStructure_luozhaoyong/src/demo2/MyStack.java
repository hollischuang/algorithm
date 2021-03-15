package demo2;


/**
 * 数组实现栈
 * @author admin
 */
public class MyStack {

    /**
     * 使用数组存储数据
      */
    int[] elements;

    /**
     * 构造函数
     */
    public MyStack() {
        // elements 初始化为空数组
        elements = new int[0];
    }

    /**
     * 压入元素
     * @param element
     */
    public void push(int element) {
        // 新建数组，比原数组长度大 1
        int[] newArr = new int[elements.length + 1];

        // 遍历原数组
        for (int i = 0; i < elements.length; i++) {
            // 将元素逐个赋值给新数组
            newArr[i] = elements[i];
        }

        // 将新元素赋值给新数组最后一个位置
        newArr[newArr.length - 1] = element;

        // 新旧数组替换
        elements = newArr;
    }

    /**
     * 取出栈顶元素
     * @return
     */
    public int pop() {
        // 如果栈为空，抛出异常
        if (elements.length == 0) {
            throw new RuntimeException("stack is empty");
        }

        // 取出数组中最后一个元素
        int element = elements[elements.length - 1];

        // 创建一个新数组，比原数组小 1
        int[] newArr = new int[elements.length - 1];

        // 遍历原数组，但是不包括最后一个元素
        // 所以控制变量 i 的最大值小于 elements.length -1
        for (int i = 0; i < elements.length - 1; i++) {
            // 将原数组中的值逐个赋值给新数组
            newArr[i] = elements[i];
        }
        // 新旧数组替换
        elements = newArr;
        // 返回取出的栈顶元素
        return element;
    }

    /**
     * 查看栈顶元素
     * @return
     */
    public int peek() {
        // 如果栈为空，抛出异常
        if (elements.length == 0) {
            throw new RuntimeException("stack is empty");
        }
        // 返回数组最后一个元素，即栈顶元素
        return elements[elements.length - 1];
    }

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty() {
        // 判断数组的长度是否为 0
        return elements.length == 0;
    }

}
