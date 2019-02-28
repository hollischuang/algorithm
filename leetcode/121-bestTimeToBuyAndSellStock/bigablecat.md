**121. 买卖股票的最佳时机**  
---
[https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/)  

* 官方题解1：暴力法  

```java  

    /**
     * https://leetcode-cn.com/articles/best-time-to-buy-and-sell-stock/
     * 官方题解1：暴力法
     *
     * @param prices
     * @return
     */
    public int maxProfit(int prices[]) {
        //定义整数变量maxprofit缓存最大利润
        int maxprofit = 0;
        //遍历价格数组prices
        for (int i = 0; i < prices.length - 1; i++) {
            // 对于每一天的价格prices[i]
            // 逐天查看在剩余的交易日里卖出的利润，从而找出最大值
            for (int j = i + 1; j < prices.length; j++) {
                //prices[j]是第i天以后某一天的价格
                //prices[j] - prices[i]得到第i天买入，第j天卖出所得利润
                int profit = prices[j] - prices[i];
                //如果利润大于最大利润maxprofit
                if (profit > maxprofit)
                    //将新的最大利润缓存
                    maxprofit = profit;
            }
        }
        //返回一次交易能获得的最大利润
        return maxprofit;
    }

```  

**复杂度分析**  

时间复杂：O(n^2)，
本解法使用了嵌套循环，
内循环的迭代次数随着外循环控制变量i的递增而递减，
即内循环的迭代次数是(n-1)+...+2+1 = ((n-1)+1)/2，
外循环遍历数组一次，时间复杂度是n，
所以总的时间复杂度是n*((n-1)+1)/2 = n^2/2，
消去常数系数1/2，最终时间复杂度是O(n^2)

空间复杂度：O(1)，
只使用了两个变量maxprofit和profit，
空间复杂度为O(1)

---

* 官方题解2：峰谷法一次遍历  

```java  

    /**
     * https://leetcode-cn.com/articles/best-time-to-buy-and-sell-stock/
     * 官方解法2：峰谷法
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int prices[]) {
        //定义一个最低买入价格minprice，默认值为Integer的取值上限
        int minprice = Integer.MAX_VALUE;
        //定义一个最大利润maxprofit，默认值为0
        int maxprofit = 0;
        //遍历价格数组prices
        for (int i = 0; i < prices.length; i++) {
            //当价格小于最低买入价格minprice时
            if (prices[i] < minprice)
                //prices[i]缓存到minprice
                minprice = prices[i];
                //prices[i] - minprice得到第i天卖出时的利润
            else if (prices[i] - minprice > maxprofit)
                //如果利润大于最大利润maxprofit，将其缓存到maxprofit
                maxprofit = prices[i] - minprice;
        }
        //返回最大利润
        return maxprofit;
    }

```  

**复杂度分析**  

时间复杂度：O(n)，
遍历了数组一次

空间复杂度：O(1)，
只使用了minprice和maxprofit两个整数变量

---

**参考资料**  

* 官方题解：  
[https://leetcode-cn.com/articles/best-time-to-buy-and-sell-stock/](https://leetcode-cn.com/articles/best-time-to-buy-and-sell-stock/)  
