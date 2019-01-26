**376.WiggleSubsequence**  
---
[https://leetcode-cn.com/problems/wiggle-subsequence/](https://leetcode-cn.com/problems/wiggle-subsequence/)  

方法一：贪心算法
官方题解的dp算法实际上也是再找波峰与波谷的个数，所以个人倾向于贪心的思想更多一点。  

贪心的思想是：对于摆动序列，只要有序列存在摆动的地方，那么这个摆动处的元素就得加入到结果集中。我们就是要计算摆动的个数。  
所以算法思路为：记录第一次出现波峰（nums[i+1]-nums[i]>0）或者波谷（nums[i+1]-nums[i]<0）的位置和状态，序列长度加一。按顺序查找序列，寻找一个与之前状态相反的状态，（即若之前状态是波峰则当前状态应该是波谷），找到后改变当前状态，序列长度加一，继续寻找。

```java  
class Solution {
    public int wiggleMaxLength(int[] nums) {
        // 如果数组长度小于2，则不会有波动
        if (nums.length < 2) return nums.length;
        // 如果没有波动序列，那么值应该为1，所以初始值设为1
        int size = 1;
        // 下一个应该正（波峰）还是负（波谷）的标识量
        int nextSignal = nums[0]-nums[1];
        // 由于刚开始就计算了两个，因此判断起初计算的两个有没有波峰或波谷，
        // 若有，则序列长度加一
        if(nextSignal!=0) size++;

        // 从第二个开始，向后面看，
        // 如果发现存在波峰波谷交替出现，那么就保存到结果中
        for (int i=1; i<nums.length-1; i++) {
            // 判断当前位置的状态与应该存在的状态是否一致
            // 若一致，则序列长度加一
            // 首先，计算当前的状态
            int temp = nums[i+1] - nums[i];
            // 根据当前的符号是否与标识量符号相同来判断状态是否一致
            if (nextSignal * temp > 0) {
                // 如果当前的状态（即当前是波峰或者波谷）和标识量（下一个应该是波峰还是波谷）是一致的，
                // 则将结果加一，
                // 当前状态改变为相反的状态
                size++;
                nextSignal = -temp;
            } else if (nextSignal==0 && temp!=0){
                // 若状态码为0，则说明之前一直都是相同的数字，没有起伏
                // 若temp不为0，则说明此时索引处的数字有起伏
                // 因此，下一个状态为此时索引处的相反状态，
                // 然后结果加一
                nextSignal = -temp;
                size++;
            }

            // 备注：两个条件可以合并，这里是为了方便理解，所以分开写
        }

        return size;
    }
}
```  

---


**参考资料**  

* 官方题解：  
[https://leetcode.com/articles/wiggle-subsequence/](https://leetcode.com/articles/wiggle-subsequence/)  
