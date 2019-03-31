## **557. Reverse Words in a String III**

https://leetcode.com/problems/reverse-words-in-a-string-iii/



方法一：字符串按照空格分成数组，然后对每个数组内的字符串反向，最后再拼成一个字符串

```java
class Solution {
    public String reverseWords(String s) {
        String[] sList = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String ss : sList) {
            sb.append(getFan(ss)).append(" ");
        }
        return sb.toString().substring(0,sb.length()-1);
    }
    //获取反向的字符串
    private static String getFan(String a) {
        String[] s = a.split("");
        StringBuilder sb = new StringBuilder();
        for (int i = s.length - 1; i > -1; i--) {
            sb.append(s[i]);
        }
        return sb.toString();
    }
}
```

方法二：

依旧先对字符串按照空格分成数组，然后从头开始算，指针指向第一个字符串的尾部，往前遍历直到这个字符串的头部，然后指针再指向第二个字符串的尾部，再往前便利，依此循环，直到遍历完全部的字符串。

```java
class Solution {
    public String reverseWords(String s) {
        if (s.length() == 0){
            return s;
        }
        //首先分组
        String[] sList = s.split(" ");
        StringBuilder sb = new StringBuilder();
        //指针的位置
        int count = sList[0].length();
        //此时为第一个单词
        int currentStringNum = 0;
        //第一个单词的长度
        int currentStringLength = count;
        for (int i = 0;;i++){
            //i增加，指针count-i-1则在往前移动
            sb.append(s.charAt(count-i-1));
            if (currentStringLength-1 == i){
                //一个单词的字符已经添加完毕
                currentStringNum++;
                if (currentStringNum == sList.length){
                    return sb.toString();
                }
                //获取当前单词的长度
                currentStringLength = sList[currentStringNum].length();
                //补上空格
                sb.append(" ");
                //加上之前已有的长度，和空格的一个长度，指针指向当前单词的最后一个值
                count+=(currentStringLength+1);
                //i重置
                i = -1;
            }
        }
    }
}
```

方法三：

在之前的基础上优化，其实分组就是为了获取每个空格之间的长度，使用indexOf也可以达到这种效果

```java
class Solution {
    public String reverseWords(String s) {
        if (s.length() == 0){
            return s;
        }
        StringBuilder sb = new StringBuilder();
        //indexOf如果没有对应的字符串的时候会返回-1
        int count = s.indexOf(" ");
        if (count < 0){
            count = s.length();
        }
        //当前单词的长度
        int currentStringLength = count;
        for (int i = 0;;i++){
            sb.append(s.charAt(count-i-1));
            if (currentStringLength-1 == i){
                if (count == s.length()){
                    return sb.toString();
                }
                //查找下一个空格所在的位置
                int newCount=s.indexOf(" ",count+1);
                if (newCount < 0){
                    newCount = s.length();
                }
                //下一个单词的长度
                currentStringLength = newCount-count-1;
                //移动指针
                count = newCount;
                //添加空格
                sb.append(" ");
                i = -1;
            }}
    }
}
```



**参考资料**

无