https://leetcode-cn.com/problems/out-of-boundary-paths/

576. 出界的路径数

思路： 依然是DP动态规划

1.这里答案不想要的坐标不是被弃之不理，而是把上一步当前位置的元素代表的可能数加到结果的总个数中，并且此题dp数组每个元素存的不是走到这里的概率，而是
走到这里的可能总路径数。注意在运算过程要取模。

```
def findPaths(self, m, n, N, i, j):
    if N == 0:
        return 0
    lastStepCount = [[0 for i in range(n)] for j in range(m)]   #对之前坐标进行遍历
    move = [[0, 1], [1, 0], [0, -1], [-1, 0]]    #一次只能移动一个上下左右
    lastStepCount[i][j] = 1        #初始化定义
    res, mod = 0, 1000000007     
    for step in range(1, N + 1):       
        currCount = [[0 for i in range(n)] for j in range(m)]    #正则表达式循环现在的坐标
        for x in range(m):
            for y in range(n):
                for direction in move:									
                    lastX, lastY = x + direction[0], y + direction[1]		#对坐标进行位置移动
                    if any([lastX < 0, lastX >= m, lastY < 0, lastY >= n]):     
                        res = (res + lastStepCount[x][y]) % mod      #如果出界了，就直接求出结果
                    else:   		#否则就继续迭移动
                        currCount[x][y] = (currCount[x][y] + lastStepCount[lastX][lastY]) % mod
        lastStepCount = currCount     #重新赋值
    return res
```
时间复杂度O(N * m * n)

2.另一种DP思想，从坐标[i, j]到其上、下、左、右都需要移动1步，剩余N-1步，那么问题转化为从上、下、左、右移动N-1步，一共有多少种方法。

```
class Solution:
    def findPaths(self, m, n, N, i, j):
        tmp=[[[0 for i in range(n)] for j in range(m)] for k in range(N+1)]  #坐标和移动次数的三维数组
        for k in range(1,N+1):																			
            for p in range(m):				
                for q in range(n):					
                    if 0==p:	#如果横坐标是0
                        up=1	#就可以向上走
                    else:
                        up=tmp[k-1][p-1][q]			#否则非0就要走N-1步
                    if m-1==p:		#如果横坐标已经是左移动1了，那么我们向下移动1
                        down=1
                    else:
                        down=tmp[k-1][p+1][q]	#否则就是横坐标+1的地方走N-1步
                    if 0==q:
                        left=1
                    else:
                        left=tmp[k-1][p][q-1]			#同理左右也是一样
                    if n-1==q:
                        right=1
                    else:
                        right=tmp[k-1][p][q+1]
                    tmp[k][p][q]=(up+down+left+right)%1000000007   #注意最后的结果要mod那个数
        return tmp[N][i][j]
```
时间复杂度是O(N * m * n)

其实这两种方法大体上都是一样的，都是想要求到上一步的情况，那么就直接用DP状态转移来进行递归。

参考：https://unclegem.cn/2018/11/01/Leetcode%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0-576-%E5%87%BA%E7%95%8C%E7%9A%84%E8%B7%AF%E5%BE%84%E6%95%B0/
https://www.smwenku.com/a/5c220ee2bd9eee16b4a76a6d/zh-cn/
