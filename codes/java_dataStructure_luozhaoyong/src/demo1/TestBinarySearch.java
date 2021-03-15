package demo1;

/**
 * 测试二分查找
 * @author admin
 */
public class TestBinarySearch {
    public static void main(String[] args) {
        // 目标数组
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        // 目标元素
        int target = 3;
        // 记录开始位置
        int begin = 0;
        // 记录结束位置
        int end = arr.length - 1;
        // 记录中间位置
        int mid = begin + (end - begin) / 2;
        // 目标元素在数组中的下标，初始值为 -1
        int index = -1;

        // 当开始位置小于或等于结束位置时，循环继续
        // begin == end 时，即二者重合时
        // mid = begin + (end - begin)/2 == begin
        // 即 begin 位置本身还没有检查，需要再进入循环一次
        while (begin <= end) {
            // 如果 mid 位置刚好等于目标值
            if (arr[mid] == target) {
                // 结束循环
                index = mid;
                break;
            }
            // 如果 mid 位置元素大于目标值
            if (arr[mid] > target) {
                // 将结束位置左移到 mid 位置前一个位置
                end = mid - 1;
            } else {
                // 如果 mid 位置元素小于目标值
                // 说明目标值在 mid 的右边
                // 将开始位置右移到 mid 后一个位置
                begin = mid + 1;
            }
            // 重新计算 mid 位置的值
            mid = begin + (end - begin) / 2;
        }
        System.out.println(index);
    }
}
