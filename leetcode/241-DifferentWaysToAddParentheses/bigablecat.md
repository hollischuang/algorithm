**241. 为运算表达式设计优先级**  
---  
[https://leetcode-cn.com/problems/different-ways-to-add-parentheses/](https://leetcode-cn.com/problems/different-ways-to-add-parentheses/)  

* 网友高票Java解法：  

```java  
    /**
     * https://leetcode.com/problems/different-ways-to-add-parentheses/discuss/66328/A-recursive-Java-solution-(284-ms)
     * 网友高票答案：分治
     *
     *
     * @param input
     * @return
     */
    public static List<Integer> diffWaysToCompute(String input) {
        //创建一个链表ret用于存储当前input经过运算后所有可能的结果
        List<Integer> ret = new LinkedList<>();
        //遍历输入字符串的每个字符
        for (int i = 0; i < input.length(); i++) {
            //如果当前字符为运算符
            if (input.charAt(i) == '-' ||
                    input.charAt(i) == '*' ||
                    input.charAt(i) == '+') {
                //将字符串拆分为两部分
                //part1截取自input起始位置到当前位置i
                String part1 = input.substring(0, i);
                //part2从input第i+1个位置到input末尾
                String part2 = input.substring(i + 1);
                //递归调用diffWaysToCompute方法
                //分别得到part1和part2中所有的运算结果part1Ret和part2Ret
                List<Integer> part1Ret = diffWaysToCompute(part1);
                List<Integer> part2Ret = diffWaysToCompute(part2);
                //分别遍历part1Ret和part2Ret
                for (Integer p1 : part1Ret) {
                    for (Integer p2 : part2Ret) {
                        //定义一个整数变量c用于存储p1和p2的运算结果
                        //c的初始值为0
                        int c = 0;
                        //判断当前运算符号
                        //根据运算符号进行不同的运算
                        switch (input.charAt(i)) {
                            case '+':
                                c = p1 + p2;
                                break;
                            case '-':
                                c = p1 - p2;
                                break;
                            case '*':
                                c = p1 * p2;
                                break;
                        }
                        //将运算结果c存入最终结果ret中
                        ret.add(c);
                    }
                }
            }
        }
        //如果ret.size() == 0表示input中没有运算符号
        if (ret.size() == 0) {
            //将不包含运算符的input转为整数后存入ret
            ret.add(Integer.valueOf(input));
        }
        //返回最终结果ret
        return ret;
    }

```  

**参考资料**  

* 网友高票Java解法：  
[https://leetcode.com/problems/different-ways-to-add-parentheses/discuss/66328/A-recursive-Java-solution-(284-ms)](https://leetcode.com/problems/different-ways-to-add-parentheses/discuss/66328/A-recursive-Java-solution-(284-ms))  
