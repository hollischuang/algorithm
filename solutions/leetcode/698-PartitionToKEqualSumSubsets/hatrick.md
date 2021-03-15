**698. 划分为k个相等的子集**  
---
[https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets/](https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets/)  

解决方案   
**思路**  
先求出平均数avg,假如平均数avg不为整数,也就是说数组的数字总和不能平均的分为k份,那么直接返回false。
创建一个布尔数组flag,来记录nums数组中数字的状态（已用还是未用）,temp初始为avg,temp的作用为记录当前子集的数字总和,
当temp=0的时候,也就是新一个子集求解完,那么继续求解下一个子集,k-1,temp重新置为avg;当temp!=0时,就是子集还未求解完,
那么继续求解子集,继续从数组中取数字,递归求解
```
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        //定义临时变量,求出当前数组的和
        int sum = 0;
        //当前数组的长度
        int len = nums.length;
        //对数组进行遍历,拿到当前的数组元素的和
        for (int i = 0; i < len; i++) 
            sum += nums[i];
        //如果数组的的综合不能均分则返回false
        if(sum % k != 0 ) return false;
        //初始化tmp为avg,用来记录当前子集的数字总和
        int avg = sum / k;
        //可以均分的时候,定义布尔数组的flag,来记录nums数组中的状态
        boolean[] flag = new boolean[len];
        //index是为了在遍历数组的位置起始位置,放置前面的数字重新计算
        return help(nums,flag,avg,k,avg,0);
    }
    public static boolean help(int[] nums, boolean[] flag, int avg, int k, int temp, int index ){
        if (k == 0 ) return true;
        //当前avg为0的时候,子集就已经确定了
        if (temp == 0)
            return help(nums,flag,avg,k-1,avg,0);
        for (int i = index; i < nums.length; i++) {
            //如果数组状态为true的时候,继续
            if (flag[i] == true) continue;
            flag[i] = true;
            //对比子集的总和当前数组的元素的大小,从数组继续取数字,给index值加1,放置重新计算
            if(temp-nums[i] >= 0 && help(nums,flag,avg,k,temp-nums[i],index+1)){
                return true;
            }
            flag[i] = false;
        }
        return false;
    }
}
```  
**复杂度分析**      
平均时间复杂度:O(nlogn)
空间复杂度:O(n)

**参考资料**  
   [https://blog.csdn.net/qq_38595487/article/details/81535891](https://blog.csdn.net/qq_38595487/article/details/81535891)