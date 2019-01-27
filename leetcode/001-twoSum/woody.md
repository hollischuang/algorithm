>详尽版答案示例，摘自本题的leetCode官方题解

**001.两数之和**  
---
[https://leetcode-cn.com/problems/two-sum/](https://leetcode-cn.com/problems/two-sum/) 

摘要  

本文适用于初学者。  


```java  

class Solution {
    //这道题虽然不难，但在解决的过程中会发现有更优质的方法去解决
   public int[] twoSum(int[] nums, int target) {
    //第一眼看到这个题的时候，很像冒泡排除，选择排序
		           int i=0; int j=i+1;
				   //其实这个效率低的方法直接冒泡就可以
				   // 确定要循环几次，来走完所有的情况
		        for(i=0;i<nums.length-1;i++){ 
				     //确定在上面定义的每一次循环中遍历所有的情况
		            for(j=i+1;j<nums.length;j++){
					    //进行比对，更优质的应该是二维数组，考虑有多个值的情况
		                if(nums[i]+nums[j]==target) {
		                    return new int[] {i,j};
		                 }	
		             }		  		          
	              } 	
	            return new int[] {i,j};
     }
	  //做完这道题，会发现如果我用map，以健值对的形式放进去，所以你就将下标作为键，值作为value
      // 但是后面你会发现，无法根据值来获得键的位置，所以当时应该吧值作为键，下标作为值可以解决
 }
```  

**复杂度分析**  

时间复杂度：O(n2)O(n^2)O(n2)， 对于每个元素，我们试图通过遍历数组的其余部分来寻找它所对应的目标元素，
这将耗费 O(n)O(n)O(n) 的时间。因此时间复杂度为 O(n2)O(n^2)O(n2)。  

空间复杂度：
空间复杂度：O(1)O(1)O(1)。   

---


**参考资料**  

* 本题leetCode官方题解：  
[https://leetcode-cn.com/articles/two-sum/](https://leetcode-cn.com/articles/two-sum/)  

* 本题leetCode英文官方题解：  
[https://leetcode.com/articles/two-sum/](https://leetcode.com/articles/two-sum/)  
