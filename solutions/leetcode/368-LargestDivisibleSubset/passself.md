#318. 最大整除子集

Leetcode 地址 [https://leetcode-cn.com/problems/largest-divisible-subset/](https://leetcode-cn.com/problems/largest-divisible-subset/)

**思路：**
 
DP 来解决的话首先是总结递归的规律，一个无序的数组无法总结出什么规律，首先要对给出的数组进行排序；排序完毕后就根据整除描述，nums[i]>nums[i+1]，这样循环就判断前面的数字能否整除后面的数字。定义一个数组dp,其中dp[i]表示数字nums[i]位置最大可整除的子集合的长度，还需要一个数组parent，来保存上一个整除的数字的位置，两个整型变量max和max_idx分别表示最大子集合的长度和起始数字位置,遍历数组。

1.数组排序

2.递归动态规划规律 如果nums[j]能整除nums[i], 且dp[i] < dp[j] + 1的话，更新dp[i]和parent[i]，如果dp[i]大于max了，更新max和max_idx

3.最后循环结束后，我们来填res数字，根据parent数组来找到每一个数字

**具体代码**

```
public List<Integer> largestDivisibleSubset(int[] nums) {

    if (nums == null || nums.length == 0) {
        return new ArrayList<>();
    }
    Arrays.sort(nums);
    int n = nums.length;
    int[] dp = new int[n];
    int[] parent  = new int[n];
    int max = 0, max_idx = 0;
    for (int i = 0; i < n; i++) {
        dp[i] = 1;
        parent[i] = -1;
        for (int j = 0; j < i; j++) {
            if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                dp[i] = dp[j] + 1;
                parent[i] = j;
            }
        }
        if (max < dp[i]) {
            max = dp[i];
            max_idx = i;
        }
    }
    List<Integer> res = new ArrayList<>();
    do {
        res.add(nums[max_idx]);
        max_idx = parent[max_idx];
    } while (max_idx != -1);
    return res;
}

```
**时间复杂度** O(N^2) 


leetcode 代码提交后执行时间48ms只击败了30%的用户有待优化

思路类似，cache一些数据优化执行效率42ms

```
public List<Integer> largestDivisibleSubset(int[] nums) {
    List<List<Integer>> list = new ArrayList();
    if(nums.length < 1) return new ArrayList();
    int max = 0;
    int p = 0;
    for(int k = 0; k < nums.length; k++){
        list.add(k, new ArrayList());
    }

    if(nums.length >= 1){
        Arrays.sort(nums);
        int[] leng = new int[nums.length];
        int j = 0;
        for(int i = 0; i < nums.length; i++){
            for(j = i - 1; j >= 0; j--){
                if(nums[i] % nums[j] == 0 && nums[i] > nums[j]){
                    if(leng[i] < leng[j] + 1){
                        leng[i] = leng[j] + 1;
                        list.set(i,new ArrayList(list.get(j)));
                        list.get(i).add(nums[i]);
                        if(max < leng[i]) {
                            max  = leng[i];
                            p = i;
                        }
                    }
                }
            }
            if(j < 0 && leng[i] == 0) {
                list.get(i).add(nums[i]);
                leng[i] = 1;
            }

        }
    }
    return list.get(p);
}
    
```
leetcode上star效率更高的解法 [具体地址](https://leetcode.com/problems/largest-divisible-subset/discuss/83999/Easy-understood-Java-DP-solution-in-28ms-with-O(n2)-time)


