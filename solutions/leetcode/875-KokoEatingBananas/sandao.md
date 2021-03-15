## **875. Koko吃香蕉**

https://leetcode.com/problems/koko-eating-bananas/

解决方案
**思路**

假设piles为[A,B,C,D....]，最终求出来的值为K，我们可以得出下列公式：
$$
Math.ceil({A \over K})+Math.ceil({B \over K})+Math.ceil({C \over K})+...<=H
$$
可以简略为
$$
{A \over K}+{B \over K}+{C \over K}+...<=H
$$
此时我们能大致得出K=（A+B+C+……）/H

此时的出来的K应该是小于我们的最终值的，递增套入公式，求出消耗时间小于H的K的最大值

```java
public static int minEatingSpeed(int[] piles, int H) {
        //step1:求出相近值
        double sum  = 0;
        for (int i: piles){
            sum += (double)i/H;
        }
        int k = (int)sum;
        //step2:从相似值开始往上找，套入公式
        for (;;k++){
            int h = 0;
            for (int i: piles){
                double s = Math.ceil((double)i/k);
                h += s;
            }
            //求出了当前情况下需要消耗的时间h，这个时间必须小于规定的H
            if (h <= H ){
                return k;
            }
        }
    }
```

**参考资料**

无