**091. DecodeWays**  

---
[https://leetcode-cn.com/problems/decode-ways/](https://leetcode-cn.com/problems/decode-ways/)  

* 该问题细节太多，需要对0参与的情况进行特殊考虑。  
1. 求递推式  
设result[i]表示前i+1项字符能构成的解码个数总数。  
* 若第i-1项与第i项组合，不能够小于26，则result[i]的加入不能够增加解码个数的总数，因为其只能作为单项列出来然后加入到之前的所有组合里面，因此result[i] = result[i-1]；  
* 若第i-1项与第i项组合，能够小于等于26，则result[i]的加入能够增加解码个数。增加的解码个数是将i-1与i作为一个整体插入到i-2序列中会产生的解码个数。即result[i] = result[i-1] + result[i-2]；  
2. 特殊情况说明  
因为提供的数据中存在有0的情况，这个需要单独拿出来看。题目示例没有给0所对应的例子。此处列举几例：`10解码只有1种，即10`，`100解码只有0种，因为10,0并不能被解码`，`101解码只有1中，因为01不能被当作1来看待`，`301解码只有0种，因为30没有对应的值，01不能被当作1来看待`。  
3. 算法思路  
递推式中的i>=2，所以先确定i=0，i=1的情况。然后顺序遍历数组后面的值，根据递推式相加即可。唯一需要针对有0的进行特殊考虑。


---

方法一：动态规划  

```java  

public class Solution {
    public int numDecodings(String s) {
        // String转为char数组进行计算
        char[] problem = s.toCharArray();
        // result[i]表示索引为i时，
        // 前i+1项char数组所构成的String字符串的解码总数
        int[] result = new int[problem.length];

        // 如果第一个字母为0，则一定不能解码，
        // '01'是不能作为'1'解码的
        if (problem[0] == '0')
            return 0;
        // 如果第一个字母不为0，长度为1，则解码的方式有1种
        else if (problem.length == 1)
            return 1;

        // 下面探讨：长度>=2 且 首字母不为'0'的情况
        // 索引为0的解码总数为1
        result[0] = 1;
        // result[1]的值进行分类探讨，此时首字母肯定不为0，
        if (problem[1] == '0' && problem[0] >= '3') {
            // 类似于'30'开头，解码总数为0
            return 0;
        } else if (problem[1] != '0' && problem[1] + problem[0] * 10 <= 554) {
            // 类似于'12'，解码总数为2
            result[1] = 2;
        } else {
            // 类似于'34'和'20'
            result[1] = 1;
        }

        for (int i = 2; i < problem.length; i++) {
            if (problem[i] == '0' && (problem[i - 1] >= '3' || problem[i - 1] <= '0'))
                // 带0，且与前面的组合不能够小于26，类似于'00'或者'30'这种
                return 0;
            else if (problem[i] == '0' && problem[i - 1] < '3')
                // 带0，且与前面的组合能够小于26，类似于'20'这种
                result[i] = result[i - 2];
            else if (problem[i - 1] != '0' && problem[i] + problem[i - 1] * 10 <= 554)
                // 不带0，且与前面的组合能够小于26
                result[i] = result[i - 1] + result[i - 2];
            else
                // 不带0，且与前面的组合不能够小于26
                result[i] = result[i - 1];
        }

        return result[problem.length - 1];
    }
}

```  
