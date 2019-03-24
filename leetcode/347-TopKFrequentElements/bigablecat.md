**347. 前K个高频元素**
---
[https://leetcode-cn.com/problems/top-k-frequent-elements/](https://leetcode-cn.com/problems/top-k-frequent-elements/)

* 网友高票答案(桶排序)  

```java  
   /**
     * https://leetcode.com/problems/top-k-frequent-elements/discuss/81602/Java-O(n)-Solution-Bucket-Sort
     * 网友高票答案：桶排序
     *
     * @param nums
     * @param k
     * @return
     */
    public static List<Integer> topKFrequent(int[] nums, int k) {
        //定义一个整型列表的数组bucket，长度是数组nums长度+1
        //下面会解释为什么bucket的长度要在nums.length的基础上加1
        List<Integer>[] bucket = new List[nums.length + 1];
        //定义一个map用于存储数组中元素出现的频率
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        // 遍历数组nums
        for (int n : nums) {
            //将每个数字出现的频率存入map
            //frequencyMap.getOrDefault(n, 0)表示通过当前整数n，从frequencyMap中value，如果value为空就使用默认值0
            //frequencyMap.getOrDefault(n, 0)+1表示当前元素n每出现一次，在现有频率的基础上加1
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        //遍历frequencyMap的所有键值
        //这个键值包括了nums中的所有元素，其中重复的元素都合并为一个值
        for (int key : frequencyMap.keySet()) {
            //获取当前键值key对应的频率frequency
            int frequency = frequencyMap.get(key);
            //获取bucket中frequency对应位置的分桶
            if (bucket[frequency] == null) {
                //如果当前位置的分桶为空，新建一个ArrayList对象
                bucket[frequency] = new ArrayList<>();
            }
            // 向当前分桶添加Map中的key值
            // key对应nums中的元素，且经过map过滤，key是去重的
            // frequency对应key在nums中出现的频率
            // 如key=2，frequency=3，即nums中的元素2，总共出现了3次
            // 在bucket[frequency]这个分桶中，保存了所有频率为frequency的key值
            bucket[frequency].add(key);
        }

        //新建一个整型列表res用于存储返回结果
        List<Integer> res = new ArrayList<>();

        //从大往小遍历bucket
        //res.size() < k限制了出现频率前k高的元素
        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            //从bucket的当前位置pos取出分桶
            if (bucket[pos] != null) {
                //bucket[pos]得到一个由nums中所有频率为pos的元素组成的列表
                //res.addAll将列表中的所有元素加入res中
                res.addAll(bucket[pos]);
            }
        }
        //返回最终结果
        return res;
    }
```  

**复杂度分析**  

时间复杂度：O(n)，  
方法内的三个循环都在n的复杂度内  

空间复杂度：O(n)，  
建立了一个桶，占用空间为n+1  

**参考资料**  

* 网友高效答案：  
[https://leetcode.com/problems/top-k-frequent-elements/discuss/81602/Java-O(n)-Solution-Bucket-Sort](https://leetcode.com/problems/top-k-frequent-elements/discuss/81602/Java-O(n)-Solution-Bucket-Sort)  
