**523. 连续的子数组和**  
---
[https://leetcode-cn.com/problems/continuous-subarray-sum/](https://leetcode-cn.com/problems/continuous-subarray-sum/)  

解决方案   
**思路**  
1.遍历不同长度的子数组,判断是不是能被整除即可.有一个优化点在于可以用动态规划的思路.在len+1长度的子数组遍历时,可以用到len长度的子数组已经计算好的值,不需要再次计算了.
```
     public boolean checkSubarraySum(int[] nums, int k) {
          //新增一个数组保存上一轮的全部sum数据
          //这里容易出错的地方在于将sums的数据初始化为0.会造成基础数据就不对.
          int[] sums = nums.clone();
          //从len=2开始,进行长度不同的遍历
          for(int len=2;len<=nums.length;len++) {
              for(int i = 0; i<=nums.length-len; i++) {
                  //此时将sums[i]的数据与nums[i+len-1]的数据相加获得新值
                  sums[i] += nums[i+len-1];
                  //排除掉[0,0],0 的特殊情况
                  if(sums[i] ==0) {
                      return true;
                  }
                  //判断当前值是不是可以整除,可以直接返回true
                  if (k!=0&& sums[i]%k==0) {
                      return true;
                  }
              }
          }       
          //默认情况
          return false;
      }
```  
**复杂度分析**      
 时间复杂度：O(N2) 
 空间复杂度：O(N)

---
2.引入一个概念,前缀和（prefix sum）.
给定一个数组x,数组元素为x_0,x_1,x_2,...x_{n-1},x_n
如果有数组y,满足如下条件
y_0=x_0
y_1=x_0+x_1
y_2=x_0+x_1+x_2
...
y_{n-1}=x_0+x_1+x_2+...+x_{n-1}
y_n=x_0+x_1+x_2+...+x_{n-1}+x_{n}
那么称y为x的前缀和数组
此时可以发现,数组x的子序列和均可由前缀和数组y获得,如{x_a}至{x_b}子序列的和,可以由y_b-y_{a-1}得到.
而且也可以用到动态规划的思路,一次遍历生成y数组,然后接下来的遍历就可以复用y数组了.
可以直接看文后链接,才疏学浅,大家可以深入理解一下
```
    public boolean checkSubarraySum(int[] nums, int k) {
         //生成前缀和数组，此时已经可以判断一次了
         int[] presums = new int[nums.length];
         for(int i=0;i<nums.length;i++) {
             if (i==0) {
                 presums[0]=nums[0];
             } else {
                 presums[i] = presums[i-1] + nums[i];
                 if (presums[i] == 0) {
                     return true;
                 }
                 if (k!=0 && presums[i]%k==0) {
                     return true;
                 }
             }
         }
         //此时遍历连续子数组的和
         for (int i=1;i<presums.length;i++) {
             for(int len=2;len<=i;len++) {
                 //这一步是获取从i-len+1到i的子数组的和
                 int sub = presums[i]-presums[i-len];
                 if(sub ==0) {
                     return true;
                 }
                 if (k!=0 && sub%k==0) {
                     return true;
                 }
             }
         }
         //默认情况
         return false;
     }
```  
**复杂度分析**      
 时间复杂度：O(N3) 
 空间复杂度：O(N)


**参考资料**    
[https://www.jianshu.com/p/6b520157bdae](https://www.jianshu.com/p/6b520157bdae)  
