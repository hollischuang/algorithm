110.给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

示例 1:

给定二叉树 [3,9,20,null,null,15,7]

    3
   / \
  9  20
    /  \
   15   7
返回 true 。

示例 2:

给定二叉树 [1,2,2,3,3,null,null,4,4]

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
返回 false 。

思路：递归,判断左右子树最大高度差不超过1且左右子树均为平衡树

```py
class Solution(object):
    def isBalanced(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        def getDepth(root):
            if not root:
                return 0
            return 1 + max(getDepth(root.left), getDepth(root.right)) #左右子树最大的深度，记住加一
    
        if not root:
            return True
        if abs(getDepth(root.left) - getDepth(root.right))>1:   #判断左右子树的最大深度差是否超过1
            return False
        return self.isBalanced(root.left) and self.isBalanced(root.right)
```
时间复杂度：O(n)
