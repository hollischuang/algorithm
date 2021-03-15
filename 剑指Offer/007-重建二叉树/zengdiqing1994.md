
**7.重建二叉树**

思路：我们知道前序遍历的第一个值为根节点的值，使用这个值将中序遍历结果分成两个部分，左部分为树的左子数中序遍历结果，右部分为树的右子树中序遍历的结果

```py
class TreeNode:
    def __init__(self, x):      # 初始化树
        self.val = x
        self.left = None
        self.right = None
class Solution:
    # 返回构造的TreeNode根节点
    def reConstructBinaryTree(self, pre, tin):
        # write code here
        if not pre and not tin:
            return None
        root = TreeNode(pre[0])     # 前序遍历的root
        if set(pre) != set(tin):    #判断其他情况
            return None
        i = tin.index(pre[0])          #看前序遍历的root在中序遍历的哪个位置
        root.left = self.reConstructBinaryTree(pre[1:i+1],tin[:i]) #开始递归
        root.right = self.reConstructBinaryTree(pre[i+1:],tin[i+1:])
        return root
```
时间复杂度O(n^2)
