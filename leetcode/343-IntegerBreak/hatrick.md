**343. 整数拆分**  
---
[https://leetcode-cn.com/problems/integer-break/](https://leetcode-cn.com/problems/integer-break/)  

解决方案   
**思路**  
建立一个乘积数组,数组的下标i存放这i所能拆分之后的最大乘积,然后下标为n的数的最大乘积可以表示为两个更小的数所能拆分的乘积之和,
而这两个更小的数可以进一步拆分,不过这一步已经被记录在乘积数组中了,我们不必再考虑进一步的拆分
```
class Solution {
    public int integerBreak(int n) {
        int[] product  =new int[n+1];
        //product数组用来存放数i所能拆分的最大乘积
        product[1]=1;
        for(int i=1;i<=n;i++)
        {
            int a=1,b=i-1;
            while(a<=b&&a+b==i)
            {
                int multi =(product[a]>a?product[a]:a)*(product[b]>b?product[b]:b);
                //将数i拆分成a和b,要想产生最大乘积,我们需要选出a所拆分出的乘积和a本身中较大的一个数
                if(product[i]<multi)
                    product[i]=multi;
                ++a;
                --b;
            }
        }
        return product[n];
    }
}

```  
**复杂度分析**      
时间复杂度：O(nlogn)
空间复杂度：O(n)

**参考资料**  
   [https://blog.csdn.net/shine10076/article/details/83795282](https://blog.csdn.net/shine10076/article/details/83795282)