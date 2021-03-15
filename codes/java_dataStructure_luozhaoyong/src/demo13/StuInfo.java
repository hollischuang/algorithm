package demo13;

/**
 * 学生信息类
 *
 * @author admin
 */
public class StuInfo {
    int age;
    int count;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 自定义散列函数
     *
     * @return
     */
    @Override
    public int hashCode() {
        // 1. 直接定址法
        // 将年龄直接返回
        // 2. 取余法
        // 将 age 取模后返回余数
        return age%10;
    }

    public StuInfo(int age, int count) {
        super();
        this.age = age;
        this.count = count;
    }

    public StuInfo(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "StuInfo{" +
                "age=" + age +
                ", count=" + count +
                '}';
    }
}
