## **91. Decode Ways**

https://leetcode.com/problems/decode-ways/



思路：这个边界条件比较多，然后需要储存已经计算过的值。

```java
class Solution {
        public int numDecodings(String s) {
        if (s.startsWith("0") || s.length() == 0) {
            return 0;
        }
        //用于储存已算出值的数组。
        int[] cc = new int[s.length()+1];
        //先填充
        Arrays.fill(cc, -1);
        return numDecodings2(s,cc);
    }
    private static int numDecodings2(String s,int[] cc) {
        //首先查询这个值是否算出
        if (cc[s.length()] != -1) {
            return cc[s.length()];
        }
        int length = s.length();
        if (length == 2) {
            //拼接剩下的两位数
            if (Integer.valueOf(s) > 26) {
                //大于26，且个位数为0，则没有对应的字母，拆开来也没有，就返回0
                if (0 == Integer.valueOf(s.substring(1,2))) {
                    return 0;
                }
                //大于26，个位数不为0，则虽然没有对应的字母，但是可以拆开来当作两个一位数字，返回1
                return 1;
            }
            //小于26，但是个位数为0，则有对应的字母，但是不可以拆开来当作两个一位数字，返回1
            if (0 == Integer.valueOf(s.substring(1,2))) {
                return 1;
            }
            //小于26，个位数不为0，则有对应的字母，也可以拆开来当作两个一位数字，返回2
            return 2;
        }
        if (length == 1) {
            //只剩下一个数字，如果是0则返回0；
            if ("0".equals(s)) {
                return 0;
            }
            return 1;
        }
        //对于字符串"123456",可以分成两种情况
        //R["123456"] = R["12345"]* R["6"] +R["1234"]*R["56"]
        String sDel1 = s.substring(0,length-1);
        String sDel2 = sDel1.substring(0,sDel1.length()-1);
        //取两位数的情况（注意这里的两位数是不可拆的来计算）
        int count2 = 1;
        String sum = s.substring(length-2);
        if (Integer.valueOf(sum) > 26) {
            count2 = 0;
        }
        if (0 == Integer.valueOf(s.substring(length-2,length-1))) {
            count2 = 0;
        }
        //取一位数的情况
        int count1 = 1;
        if ("0".equals(s.substring(length-1))) {
            count1 = 0;
        }
        //向下计算
        int sDel2Count;
        if (cc[length-2] != -1) {
            sDel2Count = cc[length-2];
        } else {
            sDel2Count = numDecodings2(sDel2,cc);
            cc[length-2] = sDel2Count;
        }
        int sDel1Count = numDecodings2(sDel1,cc);

        return sDel1Count * count1 + count2 * sDel2Count;
    }
}
```



**参考资料**

无