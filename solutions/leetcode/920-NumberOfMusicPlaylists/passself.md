#920. 播放列表的数量

Leetcode 地址 [https://leetcode-cn.com/problems/number-of-music-playlists/](https://leetcode-cn.com/problems/number-of-music-playlists/)

**题目分析**

你的音乐播放器里有 N 首不同的歌，在旅途中，你的旅伴想要听 L 首歌（不一定不同，即，允许歌曲重复）。请你为她按如下规则创建一个播放列表，dp的方式[参考](https://blog.csdn.net/qq_17550379/article/details/82992083)。

**思路：** 

可以暴力枚举所有集合，然后对这些集合中相同元素的位置比较，如果<k，那么就剔除，最后剩下的就是想要的结果。但是这样的暴力方式是最无奈的解决方式。

我们来看下动态规划的思路，定义函数f(N,L,k)表示N首歌填充L个位置，相同歌的间隔超过k。于是对于第L个位置来说只有两个情况:

* 1.之前没有相同的歌，也就是N-1首歌填充了前面L-1个位置，即f(N-1,L-1,k)
* 之前有相同的歌，也就是说N首歌填充了前面L-1个位置，即f(N,L-1,k)

对于第一种情况，因为有n首歌，所以会出现n次。而对于第二种情况，由于相同的歌不能间隔k，所以会出现n-k次，那么

```
f(N,L,k)=f(N-1,L-1,k)*N+f(N,L-1,k)*(N-k)
```

**具体代码**

```
class Solution {
	public int numMusicPlaylists(int N, int L, int K) {
        int mod = (int)Math.pow(10, 9) + 7;
        long[][] dp = new long[L+1][N+1];
        dp[0][0] = 1;
        for (int i = 1; i <= L; i++){
            for (int j = 1; j <= N; j++){
                dp[i][j] = (dp[i-1][j-1] * (N - (j-1)))%mod;
                if (j > K){
                    dp[i][j] = (dp[i][j] + (dp[i-1][j] * (j-K))%mod)%mod;
                }
            }
        }
        return (int)dp[L][N];
    }
}
```
**时间复杂度** O(L*N) 

**空间复杂度** O(L*N) 


