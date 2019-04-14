513.给定一个二叉树，在树的最后一行找到最左边的值。

示例 1:

输入:

    2
   / \
  1   3

输出:
1
 

示例 2:

输入:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

输出:
7
 

注意: 您可以假设树（即给定的根节点）不为 NULL。

思路：可以用队列来层次遍历，

```py
class Solution:
    def findBottomLeftValue(self, root: TreeNode) -> int:
        result, queue = [], [root]
        while queue:
            temp = []
            result = queue[0].val       #取出每一层根的val
            for node in queue:      
                if node.left:               
                    temp.append(node.left)  #层次遍历
                if node.right:
                    temp.append(node.right)
            queue = temp
        return result
```
时间复杂度：O(n^2)
空间复杂度：O（n）
