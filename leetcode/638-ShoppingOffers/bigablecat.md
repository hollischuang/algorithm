**638. 大礼包**  
---
[https://leetcode-cn.com/problems/shopping-offers/](https://leetcode-cn.com/problems/shopping-offers/)  

```java  

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        //新建一个map用于记录每次的结果
        Map<List<Integer>, Integer> map = new HashMap();
        return shopping(price, special, needs, map);
    }

    /**
     * https://leetcode.com/articles/shopping-offers/
     * 英文官方题解
     *
     *
     * @param price   商品价格列表
     * @param special 商品大礼包
     * @param needs   待购清单
     * @param map
     * @return
     */
    public int shopping(List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<List<Integer>, Integer> map) {
        //如果map包含当前待购清单，不再重复计算，直接返回结果
        if (map.containsKey(needs))
            return map.get(needs);
        //定义一个内循环控制变量j，用于列表needs
        //调用dot方法获取不使用大礼包时，购买清单的总价格res
        int j = 0, res = dot(needs, price);
        //遍历大礼包，获得各种购买组合
        for (List<Integer> s : special) {
            //克隆待购清单clone，可以直接对该数组操作
            ArrayList<Integer> clone = new ArrayList<>(needs);
            //遍历待购清单
            for (j = 0; j < needs.size(); j++) {
                //clone.get(j)表示待购清单中第j个商品的数量
                //s.get(j)表示礼包中当前商品的数量
                //diff是二者的差
                int diff = clone.get(j) - s.get(j);
                //如果diff<0表示礼包中的商品数量大于待购清单，不合题意，排除
                if (diff < 0)
                    //跳出本次循环，继续下一次循环
                    break;
                //在克隆待购清单中当前商品对应位置存储差值
                //存储的差值表示余下还有多少量可以购买，用于传入下方的递归函数做参数
                clone.set(j, diff);
            }
            //j == needs.size()表示j是整数列表的长度，即needs中最后一个元素的下标
            if (j == needs.size())
                //s.get(j) 获取大礼包整数列表s第j个下标的元素，即当前大礼包的价格
                //shopping(price, special, clone, map) 递归调用shopping函数
                //第三个参数clone，记录了每种商品余下可购买的数量
                //递归调用的shopping返回余下可购买数量所能获得的最优总价
                //所以s.get(j) + shopping(price, special, clone, map)
                //就是包含当前礼包所能得到的最优价格
                //Math.min比较已有结果和包含当前礼包的最优价格，取其中较小值
                res = Math.min(res, s.get(j) + shopping(price, special, clone, map));
        }
        //将待购清单对应的结果存入map
        map.put(needs, res);
        return res;
        //上述方法是如何计算礼包组合之外，单独购买的那部分总价？
        //关键点有2个：
        // 1) clone：
        // 经过购买组合之后，待购清单的克隆数组clone里的商品数量会减少，
        // 当所有可能的大礼包组合都用尽之后，
        // clone里剩余的待购数量就是只能单独购买的那部分商品
        // 2) dot方法：
        // dot方法获得不使用大礼包时待购清单的总价，
        // 大礼包组合用尽之后的clone待购清单，
        // 经过dot方法就能得到单独购买的那部分商品的总价
    }

    /**
     * 两个整数列表对应下标的数值相乘，并将结果累加
     *
     * @param a
     * @param b
     * @return
     */
    public int dot(List<Integer> a, List<Integer> b) {
        int sum = 0;
        //遍历整数列表a
        for (int i = 0; i < a.size(); i++) {
            //将整数列表a和整数列表b对应位置的数值相乘并累加
            sum += a.get(i) * b.get(i);
        }
        //返回最终结果
        return sum;
    }


```  

**复杂度分析**  

时间复杂度：O(n^2)，
设数组special的长度为m，
数组needs的长度为k，
外循环遍历special数组时间复杂度为 m，
内循环遍历needs时间复杂度为 k，
两个循环嵌套时间复杂度为 m*k，
外循环中有递归函数，
每次调用的时间复杂度也是 m*k，共调用m次，
总的时间复杂度是 m*k + m*(m*k) = m*k + m^2，
舍去低阶项 m*k，得到时间复杂度m^2*k
根据题意，商品的种类比起礼包的数量相当于一个常数，
即k的值非常小，可以看做常数，
所以最终的时间复杂度大致为O(n^2)

空间复杂度：O(n^2)，
设数组special的长度为m，
数组needs的长度为k，
使用了map存储计算结果，
每存储一个键值对使用O(1)的空间复杂度
最多有m次递归，每次递归可能会产生一个键值对，
所以map的空间复杂度为O(m)；
另外在每次外循环都克隆了一个最大为needs长度k的数组，
外循环遍历special的循环次数是m，
每次都有递归函数再次使用了m次循环，
所以有m*m次克隆一个最大长度为k的数组，
空间复杂度为 m^2*k，
因为商品种类k的值相对较小，可以看做常数，
所以克隆数组占用的空间可以看做 m^2，
总的空间复杂度为map占用的O(m)+数组占用的O(m^2)，
省去低阶项，最终的空间复杂度为O(n^2)

---

**参考资料**  

* 英文官方题解：  
[https://leetcode.com/articles/shopping-offers/](https://leetcode.com/articles/shopping-offers/)  
