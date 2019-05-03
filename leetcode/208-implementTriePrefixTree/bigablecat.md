**208. 实现 Trie (前缀树)**  
---
[https://leetcode-cn.com/problems/implement-trie-prefix-tree/](https://leetcode-cn.com/problems/implement-trie-prefix-tree/)  

* 英文官方题解：    

```java  
	
public class Trie {
    /**
     * 英文官方题解
     *
     */
    class TrieNode {

        // 节点的R个连接，将用于存放英文26个小写字母
        private TrieNode[] links;

        // R = 26 表示英文26个小写字母的个数
        private final int R = 26;

        //判断Trie树是否已经到底
        private boolean isEnd;

        //构造函数，创建一个大小为R的TrieNode
        public TrieNode() {
            links = new TrieNode[R];
        }

        //是否包含某个字符ch
        public boolean containsKey(char ch) {
            //ch -'a'得到字符ch为英文字母表第几个字符
            //从links中获取第ch -'a'个字符对应的节点
            // 并判断节点是否为null
            return links[ch -'a'] != null;
        }

        //获取第ch -'a'个字符对应的节点
        public TrieNode get(char ch) {
            return links[ch -'a'];
        }

        //将当前字符ch存入节点node
        public void put(char ch, TrieNode node) {
            links[ch -'a'] = node;
        }

        //设置Trie树的终点
        public void setEnd() {
            isEnd = true;
        }

        //判断是否到达终点
        public boolean isEnd() {
            return isEnd;
        }
    }

    //私有变量根结点root
    private TrieNode root;

    //构造方法，获得一个Trie树
    public Trie() {
        //根结点赋值为新的TrieNode
        root = new TrieNode();
    }

    // 在Trie中插入一个词word
    public void insert(String word) {
        //将root节点赋值给一个临时变量node
        TrieNode node = root;
        //遍历word的每一个字符
        for (int i = 0; i < word.length(); i++) {
            //获取当前位置的字符，赋值给currentChar
            char currentChar = word.charAt(i);
            //如果node不包含当前字符
            if (!node.containsKey(currentChar)) {
                //将当前字符赋值给一个新的TrieNode，并存入node节点
                node.put(currentChar, new TrieNode());
            }
            //获取包含当前字符的节点并赋值给临时变量node
            node = node.get(currentChar);
        }
        //为node设置结束标识
        node.setEnd();
    }

    // 在Trie树中搜索包含输入word的节点，并返回字符终结的节点
    private TrieNode searchPrefix(String word) {
        //将根结点root赋值给临时变量node
        TrieNode node = root;
        //遍历word的每一个字符
        for (int i = 0; i < word.length(); i++) {
            //获取当前位置的字符，赋值给curLetter
            char curLetter = word.charAt(i);
            //查看节点node是否包含curLetter
            if (node.containsKey(curLetter)) {
                //get方法获取包含curLetter的节点并赋值给临时变量node
                node = node.get(curLetter);
            } else {
                //否则返回null
                return null;
            }
        }
        //返回终点node
        return node;
    }

    // 查找Trie是否包含当前输入word
    public boolean search(String word) {
        //获取包含当前输入word的节点
        TrieNode node = searchPrefix(word);
        //判断node是否为null且是否为终结点
        return node != null && node.isEnd();
    }

    // 查找是否有以当前输入prefix为前缀的节点
    public boolean startsWith(String prefix) {
        //查找包含前缀的节点
        TrieNode node = searchPrefix(prefix);
        //判断节点是否为null并返回真值
        return node != null;
    }

}

	
```  

---

**参考资料**  

* 英文官方题解：  
[https://leetcode.com/articles/implement-trie-prefix-tree/](https://leetcode.com/articles/implement-trie-prefix-tree/)  
