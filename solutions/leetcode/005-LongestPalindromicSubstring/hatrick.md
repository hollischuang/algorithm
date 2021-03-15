**5. 最长回文子串**  
---
[https://leetcode-cn.com/problems/longest-palindromic-substring/](https://leetcode-cn.com/problems/longest-palindromic-substring/)  

解决方案   
**思路**  
从1到字符串长度开始遍历,找每个长度可能存在的字符串。
注意:每个长度只要找到一个即可,并且注意当前长度存在回文串的条件为上次迭代存在回文串或者上上次.
注意剪枝操作,不然的话有可能会超时
```
   public static String longestPalindrome(String s) {
          //用来记录上次最长的回文串长度
          int max = 1;
          //存储当前长度对应的回文串
          Map<Integer, String> subStringMap = new HashMap<>(); 
          subStringMap.put(1, Character.toString(s.charAt(0)));
          int len = s.length();
          //当前迭代回文串存在的条件是,要么上次迭代存在回文串,要么上上次存在
          boolean flag = false;
          for (int i = 2; i <= len; i++) {
              for (int t = 0; t + i <= len; t++) {
                  if ((i - 1) != max && (i - 2) != max)
                      break;
                  if (flag) {
                      flag = false;
                      continue;
                  }
                  String subString = s.substring(t, t + i);
                  if (check(subString)) {
                      //注意,只要当前长度找到一个回文串就可以了,不需要再找了
                      max = i;
                      if (!subStringMap.containsKey(i)) {
                          subStringMap.put(i, subString);
                      } else
                          flag = true;
                      break;
                  }
              }
          }
          return subStringMap.get(max);
      }
  
      private static boolean check(String subString) {
          int len = subString.length();
          int mid = len / 2;
          for (int i = 0; i < mid; i++) {
              //从两遍向中间靠拢对比
              if (subString.charAt(i) != subString.charAt(len - 1 - i))
                  return false;
          }
          return true;
      }
```  
**复杂度分析**      
时间复杂度：O(N*N) 
空间复杂度：O(N) 额外用了一个存储结构


**参考资料**    
[https://blog.csdn.net/cserwangjun/article/details/80878797](https://blog.csdn.net/cserwangjun/article/details/80878797)  
