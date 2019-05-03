思路1：

先排序，再从排好序的数组中找出重复的数字，O(nlogn)

```py
class Solution:
    # 这里要特别注意~找到任意重复的一个值并赋值到duplication[0]
    # 函数返回True/False
    def duplicate(self, numbers, duplication):
        # 列表排序
        numbers.sort()
        for i in range(len(numbers)):
            # 判断是否扫描到列表末尾。防止溢出。
            if i == len(numbers) - 1:
                return False
            if numbers[i] == numbers[i+1]:
                duplication[0] = numbers[i]
                return True
        return False
```

思路2：

用python中的字典，从头到尾按顺序扫描数组的每个数字，每扫描到一个数字的时候，判断哈希表里是否已经包含了该数字。如果哈希表里还没有这个数字，就把它加入
哈希表。如果哈希表里已经存在该数字，就找到一个重复的数字。

```py
class Solution:
    # 这里要特别注意~找到任意重复的一个值并赋值到duplication[0]
    # 函数返回True/False
    def duplicate(self, numbers, duplication):
        # write code here
        dict = {}
        for num in numbers:
            if num not in dict:
                dict[num] = 0
            else:
                duplication[0] = num
                return True
        return False
```

思路3：

重新排列这个数组，从头到尾依次扫描数字，当扫描搭配下标为i的数字时，首先比较这个数字numbers[i]是不是等于i，如果是，就扫描下一个数字；如果不是，就再
拿他和第number[i]个数字进行比较。如果他和第numbers[i]个数字相等，就找到了一个重复的数字(该数字在下标为i和numbers[i]的位置都出现了。)。如果他和
第numbers[i]个数字不等，就把第i个数字和第numebrs[i]个数字交换，把numbers[i]放在属于他的位置。接下来重复这个比较，交换的过程，直到找到一个重复的
数字。

```py
class Solution:
    # 这里要特别注意~找到任意重复的一个值并赋值到duplication[0]
    # 函数返回True/False
    def duplicate(self, numbers, duplication):
        # write code here
        if numbers == None or len(numbers)<0:
            return False
        for i in range(len(numbers)):
            while numbers[i] != i:
                if numbers[i] == numbers[numbers[i]]:
                    duplication[0] = numbers[i]
                    return True
                temp = numbers[i]
                numbers[i] = numbers[temp]
                numbers[temp] = temp
                #numbers[i],numbers[numbers[i]]=numbers[numbers[i]],numbers[i]
        return False
```
注意这里不能直接用python的直接赋值代替交换swap，会超时。
