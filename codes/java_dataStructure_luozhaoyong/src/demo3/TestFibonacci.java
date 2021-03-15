package demo3;

/**
 * 测试斐波那契数列
 * @author admin
 */
public class TestFibonacci {
    public static void main(String[] args) {
        // 斐波那契数列 1 1 2 3 5 8 13
        int i = fibonacci(3);
        // 打印结果为 2
        System.out.println(i);

        i = fibonacci(6);
        // 打印结果为 8
        System.out.println(i);
    }

    /**
     * 斐波那契数列求值函数
     * @param i
     * @return
     */
    public static int fibonacci(int i) {
        // 递归函数停止条件，当 i 等于 1 或者 2 时返回数字 1
        if (i == 1 || i == 2) {
            return 1;
        }
        // 其他情况递归调用当前函数
        // 即第 i 项等于前两项之和
        return fibonacci(i - 1) + fibonacci(i - 2);
    }
}
