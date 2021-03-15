package demo10;

import java.io.*;
import java.util.*;

/**
 * 测试赫夫曼编码
 *
 * @author admin
 */
public class TestHuffmanCode {

    public static void main(String[] args) {
        String msg = "can you can a can as a can canner can a can.";
        // 1. 字符串 --> 字符数组
        byte[] bytes = msg.getBytes();
        // 使用赫夫曼编码压缩（视频第 45 课）
        byte[] b = huffmanZip(bytes);
        // 使用赫夫曼编码进行解码
        byte[] newBytes = decode(huffCodes, b);
        System.out.println(new String(newBytes));

        // 源文件，根目录下的 1.bmp 文件
        String src = "1.bmp";
        // 目标路径
        String dst = "2.zip";
        try {
            // 压缩文件
            // 成功压缩后会在根目录生成 2.zip 文件
            zipFile(src, dst);
        } catch (IOException e) {
            e.printStackTrace();
        }

        src = "2.zip";
        dst = "3.bmp";
        try {
            // 解压文件
            // 成功解压后会在根目录生成 3.bmp 文件
            // 3.bmp 文件的内容和大小与 1.bmp 文件一致
            // 说明压缩和解压方法正确
            unzip(src, dst);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解压文件
     *
     * @param src
     * @param dst
     */
    public static void unzip(String src, String dst) throws IOException, ClassNotFoundException {
        // 1. 创建输入流，读取源文件数据
        FileInputStream is = new FileInputStream(src);
        // 2. 创建反序列化流对象
        ObjectInputStream ois = new ObjectInputStream(is);
        // 3. 从反序列化流获取 bytes 数组
        byte[] b = (byte[]) ois.readObject();
        // 4. 从发序列化流获取赫夫曼编码表
        Map<Byte, String> codes = (Map<Byte, String>) ois.readObject();
        // 5. 关闭输入流
        is.close();
        // 6. 关闭反序列化流
        ois.close();
        // 7. 通过赫夫曼编码表解码 byte 数组
        byte[] bytes = decode(codes, b);
        // 8. 创建输出流对象
        FileOutputStream os = new FileOutputStream(dst);
        // 9. 将解码后的 byte 数组写入文件
        os.write(bytes);
        // 10. 关闭输出流
        os.close();
    }

    /**
     * 压缩文件方法
     *
     * @param src
     * @param dst
     * @throws IOException
     */
    public static void zipFile(String src, String dst) throws IOException {
        // 1. 创建输入流对象
        InputStream is = new FileInputStream(src);
        // 2. 创建与输入流指向文件大小一致的 byte 数组
        byte[] b = new byte[is.available()];
        // 3. 读取文件到 byte 数组
        is.read(b);
        // 4. 关闭输入流
        is.close();

        // 5. 创建输出流对象
        FileOutputStream os = new FileOutputStream(dst);
        // 基于输出流创建的序列化流对象
        ObjectOutputStream oos = new ObjectOutputStream(os);
        // 6. 调用赫夫曼方法，将原 byte 数组压缩为新的 byte 数组
        byte[] byteZip = huffmanZip(b);
        // 7. 将压缩后的字符数组写入目标路径
        oos.writeObject(byteZip);
        // 8. 将使用到的赫夫曼编码表写入目标路径
        oos.writeObject(huffCodes);
        // 9. 关闭输入流
        os.close();
        // 10. 关闭序列化流
        oos.close();
    }

    /**
     * decode 1. 压缩后的字符数组 --> 二进制字符串
     * decode 2. 二进制字符串 + 赫夫曼编码表 --> 原字符数组
     *
     * @param huffCodes 赫夫曼编码表
     * @param bytes     压缩后的字符数组
     * @return
     */
    private static byte[] decode(Map<Byte, String> huffCodes, byte[] bytes) {
        // 1. 压缩后的字符数组 --> 二进制字符串
        // 1.1 创建一个可变字符串变量 StringBuilder 对象
        StringBuilder sb = new StringBuilder();
        // 1.2 遍历字符数组
        // 数组的最后一个元素有可能不足 8 位，循环中只处理到倒数第二个
        // 所以 i <= bytes.length - 2
        for (int i = 0; i < bytes.length - 1; i++) {
            // 1.3 将每个字符都转为 8 位的二进制字符串
            String str = byteToBitStr(bytes[i]);
            sb.append(str);
        }
        // 最后一个字符单独处理
        String str = Integer.toBinaryString(bytes[bytes.length - 1]);
        sb.append(str);
        // 打印还原的二进制字符串
        System.out.println(sb);

        // 2. 二进制字符串 + 赫夫曼编码表 --> 原字符数组
        // 2.1 将赫夫曼编码表键值互换
        // 得到新的编码表，键是二进制字符串，值是字符
        Map<String, Byte> map = new HashMap<>(huffCodes.size());
        huffCodes.forEach((key, value) -> {
            map.put(value, key);
        });
        // 创建一个列表用于存储字符
        List<Byte> list = new ArrayList<>();
        // 2.2 遍历二进制字符串，不断截取子串，从编码表中找出对应的字符
        for (int i = 0; i < sb.length(); ) {
            // 临时变量 count 用于确定可用的二进制字符数
            // 初始值从 1 开始
            int count = 1;
            // 临时变量存储获取到的字符
            Byte b;
            // 视频中 while 循环判断条件为 true
            // 应该考虑到 i 不能超出 sb.length() 的限制
            while (i < sb.length()) {
                // 在二进制字符串中截取相应长度的键
                String key = sb.substring(i, i + count);
                // 通过键查找对应的值
                b = map.get(key);
                if (b == null) {
                    // 如果没有相应的值
                    // count 递增，扩大截取的范围
                    count++;
                } else {
                    // 将获取到的 byte 字符存入字符列表
                    list.add(b);
                    // 跳出当前循环
                    break;
                }
            }
            // 改变控制变量 i 的值，继续下一轮循环
            i += count;
        }
        // 创建一个新数组
        byte[] b = new byte[list.size()];
        // 将列表的值逐个赋值给新数组
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        // 返回数组
        return b;
    }

    /**
     * 将字符补全为 8 位并转成二进制字符串
     *
     * @param b
     * @return
     */
    private static String byteToBitStr(byte b) {
        // 将 byte 转为 int，赋值给临时变量 temp
        int temp = b;
        // 256 的二进制字符最后 8 位是 0000 0000
        // temp |= 256 可以补全 temp 字符 8 位以内的 0
        temp |= 256;

        // 将补全 0 的 8 位字符 temp 转为二进制字符串
        // 返回二进制字符串
        String str = Integer.toBinaryString(temp);
        // 截取后 8 位
        return str.substring(str.length() - 8);
    }

    /**
     * 进行赫夫曼编码压缩的方法
     *
     * @param bytes 待编码的字符数组
     */
    public static byte[] huffmanZip(byte[] bytes) {
        // 2. 字符数组 --> 二叉树节点列表
        List<Node> nodes = getNodes(bytes);

        // 3. 二叉树节点列表 --> 赫夫曼树
        Node tree = createHuffmanTree(nodes);
        // 打印结果 Node{data=null, weight=44}
        System.out.println(tree);
        // 打印结果 Node{data=null, weight=19}
        System.out.println(tree.left);
        // 打印结果 Node{data=null, weight=25}
        System.out.println(tree.right);
        System.out.println("===================");
        // 4. 赫夫曼树 --> 赫夫曼编码表
        // 编码表的 key 是当前节点对应的字符
        // 编码表的 value 是当前节点的路径
        Map<Byte, String> huffCodes = getCodes(tree);
        System.out.println(huffCodes);

        // 5. 字符数组 + 赫夫曼编码表 --> 二进制字符串
        // 6. 二进制字符串 --> 压缩后的新字符数组
        byte[] b = zip(bytes, huffCodes);
        System.out.println(bytes.length);
        System.out.println(b.length);

        // 返回字符数组
        return b;
    }

    /**
     * 5. 字符数组 + 赫夫曼编码表 --> 二进制字符串
     * 6. 二进制字符串 --> 压缩后的新字符数组
     *
     * @param bytes     原字符数组
     * @param huffCodes 赫夫曼编码表
     * @return
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffCodes) {
        // 5. 字符数组 + 赫夫曼编码表 --> 二进制字符串

        // 5.1 创建一个可变字符串
        StringBuilder sb = new StringBuilder();
        // 5.2 遍历原有字符数组
        // 从赫夫曼编码表中逐个获取字符对应的编码
        // 将编码拼接到可变字符串
        for (byte b : bytes) {
            String code = huffCodes.get(b);
            sb.append(code);
        }
        // 5.3 最后得到一个二进制字符串
        System.out.println(sb);

        // 6. 二进制字符串 --> 压缩后的新字符数组

        // 6.1 根据能否整除 8 得到新字符数组的长度 len
        // 定义一个常量记录字节长度 8
        final int byteLength = 8;
        // 定义一个变量记录新数组长度
        int len;
        if (sb.length() % byteLength == 0) {
            len = sb.length() / 8;
        } else {
            len = sb.length() / 8 + 1;
        }
        // 6.2 创建长度为 len 的字符数组，用于存储压缩后的字符
        byte[] by = new byte[len];
        // 定义新字符数组的索引
        int index = 0;

        // 6.3 对二进制字符串按 8 位一组进行截取，并转为二进制字符
        int i;
        for (i = 0; i < (len - 1) * byteLength; i += byteLength) {
            // 截取 8 位字符，得到一个字节
            String strByte = sb.substring(i, i + byteLength);
            // 将 8 位字节转为二进制整数，再将二进制整数转为字符
            byte byt = (byte) Integer.parseInt(strByte, 2);
            // 将字符加入字符数组
            by[index++] = byt;
        }
        // 截取剩余部分的字符，从 i 起到结尾
        String strByte = sb.substring(i);
        // 将剩余部分转为二进制整数，再将二进制整数转为字符
        byte byt = (byte) Integer.parseInt(strByte, 2);
        // 将字符加入字符数组
        by[index] = byt;
        // 6.4 返回新的字符数组
        return by;
    }

    /**
     * 可变字符串，用于拼接根结点到达某个节点的路径
     */
    static StringBuilder sb = new StringBuilder();
    /**
     * 哈希表，用于存储赫夫曼编码表
     */
    static Map<Byte, String> huffCodes = new HashMap<>();

    /**
     * 4. 赫夫曼树 --> 赫夫曼编码表
     *
     * @param tree 赫夫曼树
     * @return
     */
    private static Map<Byte, String> getCodes(Node tree) {
        if (tree == null) {
            return null;
        }
        // 对左右孩子节点分别调用编码函数
        getCodes(tree.left, "0", sb);
        getCodes(tree.right, "1", sb);
        return huffCodes;
    }

    /**
     * 4. 赫夫曼树 --> 赫夫曼编码表
     *
     * @param node 当前节点
     * @param code 当前节点的编码，"0" 代表左节点，"1" 代表右节点
     * @param sb   到达当前节点前所经路径
     */
    private static void getCodes(Node node, String code, StringBuilder sb) {
        if (node == null) {
            return;
        }
        // 4.1 创建一个新的可变字符串
        // 创建时拼接上一次的结果，即到达当前节点之前的路径
        StringBuilder sb2 = new StringBuilder(sb);
        // 4.2 将当前节点的编码拼接到可变字符串，即形成了到达当前节点的完整路径
        sb2.append(code);
        // 4.3 如果当前节点的 data 属性为空，即当前变量没有对应的字符
        // 说明当前变量还有孩子节点，继续递归调用当前方法
        if (node.data == null) {
            // 递归调用当前方法，求出左右孩子节点的编码
            getCodes(node.left, "0", sb2);
            getCodes(node.right, "1", sb2);
        } else {
            // 4.4 当前节点有对应的字符，说明已经是叶子节点
            // 将当前节点对应的字符作为 key，到达当前节点所经的路径作为 value，存入赫夫曼编码表
            huffCodes.put(node.data, sb2.toString());
        }
    }


    /**
     * 3. 二叉树节点列表 --> 赫夫曼树
     *
     * @param nodes
     * @return
     */
    private static Node createHuffmanTree(List<Node> nodes) {
        // 列表为空时返回 null
        if (nodes == null || nodes.size() == 0) {
            return null;
        }
        // 当列表元素数量大于 1 个时，循环执行
        while (nodes.size() > 1) {
            // 3.1 将节点按照权值倒序排列
            Collections.sort(nodes);
            // 3.2 取出权值最小的两个节点
            Node left = nodes.get(nodes.size() - 1);
            Node right = nodes.get(nodes.size() - 2);
            // 3.3 将这两个节点作为孩子节点，创建一个父节点，父节点的权值是两个孩子节点的权值之和
            Node parent = new Node(null, left.weight + right.weight);
            // 3.4 将新创建的父节点与孩子节点建立连接
            parent.left = left;
            parent.right = right;
            // 3.5 从原有列表中删除权值最小的两个节点
            nodes.remove(left);
            nodes.remove(right);
            // 3.6 将新建的节点加入列表
            nodes.add(parent);
        }

        // 3.7 循环结束时，列表只剩下 1 个节点
        // 返回列表中的唯一节点
        // 这个节点就是赫夫曼树的根结点
        return nodes.get(0);
    }

    /**
     * 2. 字符数组 --> 二叉树节点列表
     *
     * @param bytes 字符数组
     * @return
     */
    private static List<Node> getNodes(byte[] bytes) {
        // 2.1 创建一个列表，用于存储二叉树节点
        List<Node> nodes = new ArrayList<>();
        // 2.2 创建一个 HashMap，键是字符，值是该字符出现的次数
        // bytes.length 指定 HashMap 大小，原教程视频中没有传入这个参数
        Map<Byte, Integer> counts = new HashMap<>(bytes.length);
        // 2.3 遍历字符数组，统计字符出现的次数
        for (byte b : bytes) {
            // 获取当前字符 b 的计数 count
            Integer count = counts.get(b);
            // 如果 count 为空
            if (count == null) {
                // 当前字符计数为 1
                counts.put(b, 1);
            } else {
                // 当前字符的计数在原有基础上加 1
                counts.put(b, count + 1);
            }
        }
        // 2.4 将统计数据转为二叉树节点并存入列表
        // 遍历哈希表中的元素
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            // 将字符和字符出现的次数作为参数传入构造函数，创建一个二叉树节点
            Node node = new Node(entry.getKey(), entry.getValue());
            // 将新创建的节点存入列表
            nodes.add(node);
        }
        System.out.println();
        // 2.5 返回节点列表
        return nodes;
    }

}
