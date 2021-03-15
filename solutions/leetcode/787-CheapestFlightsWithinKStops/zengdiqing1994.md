https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/

787. K 站中转内最便宜的航班

思路：

**动态规划思想**

1.状态转移方程：

ans = min(ans, costs[k] + prices[k][dst])

其中costs[k]表示到达位置k时的最小花费，prices[k][dst]表示从k到达dst的航班价格。

```
class Solution:
    def findCheapestPrice(self, n, flights, src, dst, K):
        """
        :type n: int
        :type flights: List[List[int]]
        :type src: int
        :type dst: int
        :type K: int
        :rtype: int
        """
        INF = 0x7FFFFFFF
        prices = collections.defaultdict(lambda: collections.defaultdict(int)) #内置collection得到数组
        for s, t, p in flights:
            prices[s][t] = p
        ans = prices[src][dst] or INF     #从src到dst的prices
        queue = [src]							#src的队列列表
        costs = {src : 0}					#费用的字典 key：src，value：数字
        for x in range(K + 1):
            nset = set()							#一个集合
            for loc in queue:                  #在当前的位置                                
                ans = min(ans, costs[loc] + (prices[loc][dst] or INF))
                for next in prices[loc]:              #下一段转的航班需要花费的费用
                    costs[next] = min(costs.get(next, INF), costs[loc] + (prices[loc][next] or INF))
                    nset.add(next)  			   #next为prices的一维数组，加入集合当中
            queue = list(nset)			#最后我们可以得到一个队列，存着每一个出发点，也就是航班
        return ans if ans < INF else -1    

```
时间复杂度是O(K * s * n)

2.另一种动态规划的思路

用一个二维的dp数组，dp[i][j]表示在不超过i次转机的情况下，从j到达dst的最少费用。dp[i][j]=min(dp[i−1][k]+Pk−>j,Pk−>j)

（从自己到自己为0，Pk−>k=0，若是没有这个线路则Pk−>j=inf）

```
class Solution(object):
    def findCheapestPrice(self, n, flights, src, dst, K):
        """
        :type n: int
        :type flights: List[List[int]]
        :type src: int
        :type dst: int
        :type K: int
        :rtype: int
        176ms dp
        """
        import collections
        # 记录同一个终点的不同起点和价格
        flights_dict_end = collections.defaultdict(list)
        for flight in flights:
            flights_dict_end[flight[1]].append([flight[0], flight[2]])
        # 用来记录各种情况，n个站点，还剩K次，到达目的地最少价格
        dp = [[float('inf') for i in range(n)] for i in range(K + 1)]
        # 初始化能直达的
        for i in flights_dict_end[dst]:
            dp[0][i[0]] = i[1]
        # 反向推回去
        for k in range(1, K + 1):
            for pos in range(n):
                for before in flights_dict_end[pos]:       #遍历整个线路
                    dp[k][before[0]] = min(dp[k][before[0]], dp[k-1][pos] + before[1]) #把转航班的加上去
                dp[k][pos] = min(dp[k][pos], dp[k-1][pos])      #状态转移方程
        ans = dp[K][src]
        return ans if ans != float('inf') else -1

```
时间复杂度是O(n * K * d)
