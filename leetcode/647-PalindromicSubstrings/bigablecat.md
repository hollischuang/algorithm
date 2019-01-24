**647. 回文子串**  
---
[https://leetcode-cn.com/problems/palindromic-substrings/](https://leetcode-cn.com/problems/palindromic-substrings/)  

* 英文官方题解1：Expand Around Center  

```java  
    /**
     * https://leetcode.com/articles/palindromic-substrings/
     * 英文官方解法1：Expand Around Center

     *
     * @param S
     * @return
     */
    public int countSubstrings(String S) {
        //定义N为数组的长度，ans是回文子串的个数
        int N = S.length(), ans = 0;
        // 回文子串是以某点为中心左右对称的
        // 比如aba的中心是字母b
        // abba的中心在两个字母b的中间
        // 字符串的长度为N，最多有多少个这样的中心点？
        // 如果以单个字符为中心，
        // 即以S[i]为中心,0<=i<N，一共有N个中心点
        // 如果以两个字符的中间为中心，
        // 即以S[i-1,i]为中心，1<=i<N，
        // N个字符，两两之间有N-1个间隙，所有共有N-1个中心点
        // 那么中心点的总数是N + (N-1) = 2N-1

        //循环遍历所有 2*N-1个中心点
        for (int center = 0; center <= 2 * N - 1; ++center) {
            //获取中心点左侧第一个位置
            int left = center / 2;
            //获取中心点右侧第一个位置
            //center%2有0和1两种结果
            //当center%2=0时，表示left和right都从中心点开始算起
            //当center%2=1时，中心点在left和right两个字符的中间
            int right = left + center % 2;
            //left >= 0，因为left依次递减，所以left的下限是0
            //right < N，同理right依次递增，所以right的上限是数组S的长度N
            //S.charAt(left) == S.charAt(right)中心点两边的字符相等
            //说明扩展到left和right当前所在位置时，符合回文的条件
            while (left >= 0 && right < N && S.charAt(left) == S.charAt(right)) {
                //发现一个回文子串，ans递增1位
                ans++;
                //从中心向外扩散
                //left递减，right递增
                left--;
                right++;
            }
        }
        //返回最终累加的回文子串数目
        return ans;
    }

```  

**复杂度分析**  

时间复杂度：O(n^2)，
外循环运行2*N-1次，时间复杂度为n，
每个内循环最多运行n次，时间复杂度为n，
嵌套循环的时间复杂度是O(n^2)，

空间复杂度：O(1)，
没有使用额外的存储空间

---  

* 英文官方题解2：Manacher  

```java  
    /**
     * https://leetcode.com/articles/palindromic-substrings/
     * 英文官方解法2：Manacher
     *
     * @param S
     * @return
     */
    public int countSubstrings2(String S) {
        //创建字符数组A，数组长度是S长度的2倍加3
        //为什么长度定义为2 * S.length() + 3会在下方代码中体现
        char[] A = new char[2 * S.length() + 3];
        //以'@'作为数组A的开头，防止越界
        A[0] = '@';
        //在字符之前加入'#'
        A[1] = '#';
        //以'$'作为数组A的结尾，防止越界
        A[A.length - 1] = '$';
        //定义数组A的长度时+ 3，即加了上述3个字符
        //数组A的下标0和1都已定义，变量t从2开始记录下标
        int t = 2;
        //S.toCharArray()将字符串S转为字符数组
        //遍历字符数组的每一个元素
        for (char c : S.toCharArray()) {
            //将当前字符存入字符数组，同时下标t递增
            A[t++] = c;
            //在每个字符后存入一个符号'#'
            A[t++] = '#';
            //定义数组A的长度时，用2*S.length()
            //因为每个字符后都有一个对应的'#'，即S的长度扩充了2倍
        }
        //经过上述处理后，字符串S中的每个字符，左右两侧都有'#'
        //即'aba'变成了'#a#b#a#'

        //定义一个新数组Z，长度与数组A相等
        // Z中每个整数元素与A中的字符元素一一对应
        // 记录A在该位置上字符的回文半径
        int[] Z = new int[A.length];
        //定义整数变量center，记录字符串中最长回文子串的中心位置，初始值为0
        //定义整数变量right，记录最长回文子串的右侧边界，初始值为0
        int center = 0, right = 0;
        //从下标1开始遍历数组Z，因为第一个字符'@'作为下界，不用考虑
        for (int i = 1; i < Z.length - 1; ++i) {
            //当中心i小于最长回文子串的半径右边界right时
            //说明当前中心处于已经计算过的回文子串范围内
            //那么以i为中心的回文半径可以直接获取
            if (i < right) {
                // 2 * center - i = center - ( i - center)
                // 得到中心i相对于最长回文中心center，在数组Z上的对称位置
                // 即以i为中心，或者以 2 * center - i 为中心，
                // 以 (right-i)为半径，两个对称中心点的回文子串数量是相同的
                // 而以i为中心，目前能获得的最长回文子串半径不能超出右边界right
                // 所以需要从right - i和Z[2 * center - i]中取较小值
                Z[i] = Math.min(right - i, Z[2 * center - i]);
            }
            //A[i + Z[i] + 1] == A[i - Z[i] - 1]
            //这行代码表示以i为中心，比较i两侧对称位置的字符是否相等
            //其中 i + Z[i] + 1 表示以i为中心，半径的右侧边界所在位置
            //因为Z[i]记录了i位置上已存在的回文半径，比如aba，其回文半径为1
            // i + 已有半径 + 1 表示在已知半径递增1位后，测试是否仍然是回文
            //同理 i - Z[i] - 1 表示以i为中心，半径的左边界所在位置
            while (A[i + Z[i] + 1] == A[i - Z[i] - 1]) {
                //如果中心i向左右两侧各延伸1位后，所在位置字符仍然相等
                //则Z[i]++表示以i为中心的回文半径长度递增1位
                Z[i]++;
            }
            //经过上述处理，i + Z[i] 表示以i为中心，最新的半径右边界
            //如果更新后的半径右边界比已知的最长回文半径右边界right更大
            if (i + Z[i] > right) {
                //更新最长回文子串中心center的值
                center = i;
                //更新最长回文子串的右侧边界right的值
                right = i + Z[i];
            }
        }
        //定义整数变量ans记录最长回文的总数
        int ans = 0;
        //遍历数组Z
        for (int v : Z) {
            //v记录了当前位置回文子串的半径
            //即当前位置回文子串的总数
            //因为原字符串被前后插入了'#'
            //所以回文子串的半径长度是实际的2倍
            //所以需要 (v + 1) / 2 进行减半处理
            //ans需要累加所有中心点的回文子串数量
            ans += (v + 1) / 2;
        }
        //返回最终结果
        return ans;
    }


```  

**复杂度分析**  

时间复杂度：O(n)，
代码中总共有2个独立的for循环，
和1个for循环嵌套while循环，
独立的for循环分别遍历了字符串S一次，数组Z一次，
两次遍历的时间复杂度都是2N，
嵌套循环中，外循环遍历数组Z，
而内循环while，
只有在中心两侧字符相等的情况下才会进入循环体，
即有多少个中心点，就有多少次进入循环体，
中心点的个数是2*N-1，
所以外循环结束时，
while循环的最坏时间复杂度是2*N-1，
外循环for的时间复杂度是2N，
再加上另外两个独立的for循环，
总的时间复杂度是6N-1，
约去常数系数6和常数项1，
最终时间复杂度是O(N)

空间复杂度：O(n)，
创建了数组A和数组Z，
A和Z的大小都是2n+3，
所以总的空间复杂度最终为4n+6，
去掉常数项6和n的常数项系数4，
最终的空间复杂度是O(n)

---

**参考资料**  

* 英文官方题解：  
[https://leetcode.com/articles/palindromic-substrings/](https://leetcode.com/articles/palindromic-substrings/)  
