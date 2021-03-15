#467. 环绕字符串中唯一的子字符串

Leetcode 地址 [https://leetcode-cn.com/problems/unique-substrings-in-wraparound-string/](https://leetcode-cn.com/problems/unique-substrings-in-wraparound-string/)

**题目分析**

该题的一个非常重要的隐藏条件是，改字符串里面所有的字母都是由26个字母的顺序前后相连的。比如xyzabc，子字符串就是xyz,或者abc 这里只是穷举部分。所以题目的意思是，找出字符串p所有子串中，每个字母按照字母表顺序相连的子串。按照字母表顺序相连，即意味着前字符的ascii码比后字符小1，或者后字符比前字符小25(字符z与字符a的情况)。

**思路：** 

1.符合条件的子串中字符是顺序相连的，所以如果子串的前n个字符符合条件，那么第n+1个字符和第n个字符也是相连的，那么这n+1个字符肯定也是符合条件的

2.由于符合条件的子串中字符是顺序相连的,那么这个子串的长度有多长，就有多少种以该子串最后一个字符为结尾的小子串

3.题目只是找出唯一的子串数量，那么以某个字符为结尾的子串，无论该字符出现在哪，它所可能组成的子串都是一样的，所以我们只需要找到能组成最长子串的那个位置就行了。

4.找出每个字符所能组成的唯一子串数量，然后求和

**具体代码**

```
public int findSubstringInWraproundString(String p) {
    int[] count = new int[26];
    int maxLength = 0;

    for (int i = 0; i < p.length(); i++) {
        if (i > 0 && (p.charAt(i) - p.charAt(i - 1) == 1 || (p.charAt(i - 1) - p.charAt(i) == 25))) {//
            maxLength++;
        }
        else {
            maxLength = 1;
        }
		 
        int index = p.charAt(i) - 'a';
        count[index] = Math.max(count[index], maxLength);
    }

    // Sum to get result
    int sum = 0;
    for (int i = 0; i < 26; i++) {
        sum += count[i];
    }
    return sum;
}

```
**时间复杂度** O(N) 

**空间复杂度** O(1)

leetcode 代码提交后发现只击败了38%的用户有待优化

后来发现双指针解法，感叹大牛的解法代码如下

```
public int findSubstringInWraproundStringPoint(String p) {
    if (p == null || p.length() == 0) {
        return 0;
    }
    int[] ways = new int[125];
    char[] cs = p.toCharArray();
    int left = 0;
    int right = 1;
    // NOTE: even if right == cs.length, can still go into the loop, to handle the "a" case (single char)
    while (right <= cs.length) {
        while (right < cs.length && ((cs[right] - cs[right-1] == 1) || (cs[right] == 'a' && cs[right-1] == 'z'))) {
            right++;
        }
        while (left < right) {
            ways[cs[left]] = Math.max(ways[cs[left]], right - left);
            left++;
        }
        right++;
    }
    int sum = 0;
    for (int way : ways) {
        sum += way;
    }
    return sum;
}

```
[具体地址](https://leetcode.com/problems/unique-substrings-in-wraparound-string/discuss/95440/Two-pointers-Java-solution-beats-100)


