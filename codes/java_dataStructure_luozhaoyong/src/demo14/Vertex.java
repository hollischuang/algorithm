package demo14;

/**
 * 顶点类
 *
 * @author admin
 */
public class Vertex {
    /**
     * 顶点数据内容
     */
    String value;
    /**
     * 顶点是否已经访问过
     */
    boolean visited;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Vertex(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
