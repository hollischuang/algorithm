**650. 只有两个键的键盘**  
---
[https://leetcode-cn.com/problems/2-keys-keyboard/](https://leetcode-cn.com/problems/2-keys-keyboard/)  


解决方案   
**思路**  
思路1: 将n分解为m个数字的乘积并且m个数字的和最小，即把一个数分解为n个质数的和

```
public int minStep(int n){
        int result = 0;
        int d = 2;
        while (n >1){
            //继续将剩下的进行分解
            while (n % d == 0 ){
                //加上次数
                result += d;
                //计算剩下的n
                n =n / d;
            }
            d++;
        }
        return result;
    }
```

**复杂度分析**      
时间复杂度：O(√n)，当n是素数平方时，我们的循环耗时O(√n)
空间复杂度：O(1),空间只使用了result和d

**参考资料**  
* 本题leetCode英文官方题解：  
[https://leetcode.com/problems/2-keys-keyboard/solution/](https://leetcode.com/problems/2-keys-keyboard/solution/) 