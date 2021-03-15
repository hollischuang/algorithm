**思路：**

使用链表头插法为逆序的特点

头结点和第一个结点的区别：

头结点是头插法中使用的一个额外结点，这个结点不存储值。

第一个节点就是链表的第一个真正存储值得结点。

这道题也可以使用递归和栈来解决。

```py
class Solution:
    # 返回从尾部到头部的列表值序列，例如[1,2,3]
    def printListFromTailToHead(self, listNode):
        # write code here
        if not listNode:
            return []
        result = []
        
        while listNode:
            result.insert(0,listNode.val)
            listNode = listNode.next
        return result
```
