package demo13;

import java.util.Arrays;

/**
 * 自定义哈希表
 *
 * @author admin
 */
public class HashTable {
    /**
     * 存储学生数据的数组
     */
    private StuInfo[] data = new StuInfo[100];

    /**
     * 向散列表中添加元素
     *
     * @param stuInfo
     */
    public void put(StuInfo stuInfo) {
        // 调用散列函数获取存储位置
        int index = stuInfo.hashCode();
        // 在指定位置存入对象
        data[index] = stuInfo;
    }

    /**
     * 从散列表中获取元素
     *
     * @param stuInfo
     * @return
     */
    public StuInfo get(StuInfo stuInfo) {
        return data[stuInfo.hashCode()];
    }

    @Override
    public String toString() {
        return "HashTable{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
