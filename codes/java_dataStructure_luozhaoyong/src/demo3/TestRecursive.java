package demo3;

/**
 * 测试递归
 * @author admin
 */
public class TestRecursive {
    public static void main(String[] args) {
        // 调用递归函数
        print(3);
    }

    /**
     * 递归打印
     * @param i
     */
    public static void print(int i) {
        if (i > 0) {
            System.out.println(i);
            print(i - 1);
        }
    }
}
