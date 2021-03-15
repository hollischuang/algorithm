package demo1;

/**
 * 线性查找
 * @author admin
 */
public class TestSearch {
    public static void main(String[] args) {
        // 目标数组
        int[] arr = new int[]{2, 3, 5, 6, 8, 4, 9, 0};
        // 目标值
        int target = 0;
        // 目标值的下标，初始值为 -1
        int index = -1;
        // 遍历数组，查找目标值在数组中的位置
        for (int i = 0; i < arr.length; i++) {
            // 如果当前值与目标值相等
            if (arr[i] == target) {
                // 将目标值下标指向当前位置
                index = i;
                break;
            }
        }
        // 打印目标值下标
        System.out.println(index);
    }
}
