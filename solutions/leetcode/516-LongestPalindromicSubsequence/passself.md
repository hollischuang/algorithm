##516. 最长回文子序列

LeetCode 地址 [https://leetcode-cn.com/problems/longest-palindromic-subsequence/](https://leetcode-cn.com/problems/longest-palindromic-subsequence/)

**解法一 暴力求解:**

找到字符串的所有子串，遍历每一个子串以验证它们是否为回文串。一个子串由子串的起点和终点确定，因此对于一个长度为n的字符串，共有n^2个子串。这些子串的平均长度大约是n/2，因此这个解法的时间复杂度是O(n^3)。

这样的方式很容易造成超时，比较不可取。


**解法二 动态规划**

回文字符串的子串也是回文，比如P[i,j]（表示以i开始以j结束的子串）是回文字符串，那么dp[i+1,j-1]也是回文字符串。这样最长回文子串就能分解成一系列子问题了。

核心思路就是从左开始遍历，然后不断的从原字符串中拿出1到length-1长度的字串，进行判断
这里用一个二维数组来表示回文字符串的起始位置和结束位置

时间复杂度 O(n^2)

```
public static int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[][] dp = new int[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; --i) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); ++j) {
                if (s.charAt(j) == s.charAt(i)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][s.length() - 1];
    }
```

