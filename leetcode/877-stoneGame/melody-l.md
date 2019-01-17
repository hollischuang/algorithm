**877. stoneGame**  
---
[https://leetcode-cn.com/problems/stone-game/](https://leetcode-cn.com/problems/stone-game/)  

方法一：数学知识

因为总堆数是偶数，所以对于先手，其总能保证自己的选择是最优的。所以亚历克斯总是能够赢得比赛。

```java  
class Solution {
    public boolean stoneGame(int[] piles) {
        return true;
    }
}
```  

方法二：动态规划
从动态规划的角度看问题。  
设stone[i][j]表示从石子堆第i堆到石子堆第j堆，最终亚力克斯比李多出的石子数。由于只能取piles[i]或者piles[j]，所以有如下两种情况：
1. 若此时轮到亚力克斯取石子，则此时stone[i][j] = Max{piles[i]+stone[i+1][j], piles[j]+stone[i][j-1]}
2. 若此时轮到李取石子，则此时stone[i][j] = Min{stone[i+1][j]-piles[i], stone[i][j-1]-piles[j]}

即若(i, j)是亚力克斯，则采用方案1；若(i, j)是李，则采用方案2。

而亚力克斯和李是轮着来的，所以奇数轮是亚力克斯，偶数轮是李。根据题意分析可知，若总堆数为Num，则当前轮数为Num-(j-i+1)+1 = Num-j+i。我们可以根据Num-j+i来判断当前的轮数。  

又，stone[i][j]的值是依赖与stone[i+1][j]或者stone[i][j-1]的，即大堆的计算是依赖于小堆的计算的。这里也可以理解为，初始状态堆的结果是依赖于最终状态堆的结果，所以需要倒着往前推理，先从最后取的那个堆开始计算。因此递推的算法思路是：  
计算从size为1的堆数开始计算，按照以上递归式子计算小堆数的结果。然后不断的调高size值，直至size==num。

```java  

class Solution {
    public boolean stoneGame(int[] piles) {
        int num = piles.length; // 石子堆总数
        int[][] stone = new int[num][num]; // 存储从石子堆第i堆到石子堆第j堆，最终亚力克斯比李多出的石子数

        // 从小堆开始计算，计算到最终大堆的size为止
        // 即计算最终堆数为1，然后到最终堆数为2，依次类推
        for (int size = 1; size <= num; size++) {
            // 最终堆数一定的情况下， 从i=0开始，逐步计算所有的情况
            // 假设最终堆数为2，计算所有情况，即逐步计算(0,1)，(1,2)...
            for (int i = 0; i + size <= num; i++) {
                int j = i + size - 1; // 根据当前的size求j所在位置
                if (size == 1) { // size为1特殊处理，条件也可以为i==j
                    // 特殊处理的原因是：size=1若采用递推式，则数组越界
                    // 所以此处采用直接赋值
                    // size为1，肯定是最后一轮，由李来取石子
                    stone[i][j] = -piles[i];
                } else {
                    int parity = (num - j + i) % 2; // 求出当前的轮数
                    if (parity == 1) {// 奇数轮，由亚力克斯取石子
                        stone[i][j] = Math.max(piles[i] + stone[i + 1][j], piles[j] + stone[i][j - 1]);
                    } else {// 偶数轮，由李取石子
                        stone[i][j] = Math.min(stone[i + 1][j] - piles[i], stone[i][j - 1] - piles[j]);
                    }
                }
            }
        }

        // 判断第一堆到最后一堆，亚力克斯取出的石子数能否比李的多
        return stone[0][num - 1] > 0;
    }
}

```  

---

**参考资料**  
* 官方题解：  
[https://leetcode-cn.com/articles/stone-game/](https://leetcode-cn.com/articles/stone-game/)  
