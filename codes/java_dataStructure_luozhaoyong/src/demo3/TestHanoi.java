package demo3;

/**
 * 测试汉诺塔
 * @author admin
 */
public class TestHanoi {
    public static void main(String[] args) {
        hanoi(1, 'A', 'B', 'C');
        System.out.println();

        hanoi(2, 'A', 'B', 'C');
        System.out.println();

        hanoi(3, 'A', 'B', 'C');
        System.out.println();

        hanoi(4, 'A', 'B', 'C');
    }

    /**
     * 共有 n 个盘子
     * 将上面的 n - 1 个盘子视为 1 个整体
     * 最底下的 1 个盘子视为 1 个整体
     * 3 根柱子中的空闲柱子作为中转
     * 当 n>=3 时，每次递归都将问题转化为 2 个盘子的情况
     *
     * @param n    盘子总数
     * @param from 第一根柱子
     * @param in   中间的柱子
     * @param to   最后一根柱子
     */
    public static void hanoi(int n, char from, char in, char to) {
        // 只有一个盘子的情况
        if (n == 1) {
            // 直接将当前盘子移动到目标位置
            System.out.println("第 1 个盘子从 " + from + " 移动到 " + to);

            // 其他情况都转换成处理 2 个盘子的汉诺塔问题
        } else {
            // 将上面的 n-1 个盘子视为 1 个整体，从原位置 from 移动到中间位置 in
            // to 此时为空，作为中转的柱子
            hanoi(n - 1, from, to, in);

            // 再将最底下的 1 个盘子，从原位置 from 移动到最终的目标位置 to
            System.out.println("第 " + n + " 个盘子从 " + from + " 移动到 " + to);

            // 最后将放在中间位置 in 的盘子，也移动到目标位置 to
            // from 此时为空，作为中转的柱子
            hanoi(n - 1, in, from, to);
        }
    }
}
