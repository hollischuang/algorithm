**740.DeleteAndEarn**  
---
[https://leetcode-cn.com/problems/delete-and-earn/](https://leetcode-cn.com/problems/delete-and-earn/)  

方法一：动态规划  
我们在这个地方将相同的值重复多个的序列称为`值序列`，用a表示。使用A表示多个值序列组成的集合。使用a(num, size)表示该值序列的求和值，即a(num, size)=num*size。  

原问题如果从增加的角度考虑的话，则问题转为：已知一个值序列组成的集合{a1, a2, a3, ... , an}，求哪些`值序列`元素值组合相加结果最大，其中值num相邻的`值序列`是不允许相加的。  

根据新的问题，假设存在已知的值序列集合A，其符合题意的相加结果最大值为`firstMax`，第二大值为`secondMax`，则当在该值序列集合后插入一个比之前的值都大的值序列b，则插入之后所组成的新的值序列集合B所最大值分布有两种情况：  
* 若`firstMax`所对应的组合的最大num为`i-1`，由于题目要求相邻值序列不可相加，因此B的`firstMax`为Max{`A.firstMax`, `A.secondeMax+i*size`}；
* 若`fistMax`所对应的组合的最大num不为`i-1`，则B的`firstMax`为`A.firstMax+i*size`；

因此由上分析可知，对于不断增加的值序列集合X，我们需要记录firstMax，secondMax，最大num所在的index。

```java  
class Solution {
    public int deleteAndEarn(int[] nums) {
        // 值序列使用数组表示，
        // 数组索引表示值序列中的num，
        // 数组值表示size
        int[] count = new int[10001];
        for (int x : nums)
            count[x]++;

        // 记录初始值序列集合A的最大值firstMax，第二大值secondMax，
        // 最优解对应的组合的最大索引 maxValueIndex，
        int firstMax = 0, secondMax = 0, maxValueIndex = 0;
        for (int i = 0; i < count.length; i++) { // 初始值序列集合A为空，不断插入值序列
            if (count[i] > 0) { // 表示此处存在值序列
                if (i - 1 == maxValueIndex) { // 最优解所对应的最大Index与插入值序列的值num相邻
                    // 先求出第二大的值与插入的值序列求和值
                    int temp = secondMax + count[i] * i;
                    // 与之前的值序列集合最大值进行比较
                    if (temp > firstMax) {
                        // 若新插入的值使得firstMax变化，则firstMax变为第二大值
                        // 第一大值改变，索引也需要改变
                        secondMax = firstMax;
                        firstMax = temp;
                        maxValueIndex = i;
                    } else {
                        // 若新插入的值使得firstMax没有变化，则仅仅修改第二大值
                        secondMax = temp;
                    }
                } else {
                    // 最优解所对应的最大Index与插入值序列的值num不相邻
                    // 则直接拿来相加，并修改对应索引
                    secondMax = firstMax;
                    firstMax = i * count[i] + firstMax;
                    maxValueIndex = i;
                }
            }
        }

        // 返回最大值
        return firstMax;
    }
}
```  

---


**参考资料**  

* 官方题解：  
[https://leetcode.com/articles/delete-and-earn/](https://leetcode.com/articles/delete-and-earn/)  
