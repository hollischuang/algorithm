package demo1;

/**
 * 计算从 1 加到 100 的和
 * @author admin
 */
public class AddOneToHundred {
    public static void main(String[] args) {
        int total = 0;
        int end = 100;

        // 使用 for 循环计算
        for (int i = 1; i <= end; i++) {
            // total 记录总和
            // 每次都累加当前自然数 i 的值
            // 这条语句执行了 end 次
            total += i;
        }
        //打印结果
        System.out.println("普通 for 循环：" + total);

        // 将 end 变量还原为初始值
        end = 100;
        // 通过等差数列求和公式计算 1 加到 100 的总和
        // 这条语句执行了 1 次
        total = (1 + end) * end / 2;

        //打印结果
        System.out.println("等差数列求和公式：" + total);
    }
}
