**230. 二叉搜索树中第K小的元素**  
---
[https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/](https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/)  

* 网友高票Java解法：    

```java  
	
    /**
     *
     * 网友高票Java解法1：递归查找二叉搜索树
     *
     */
    public int kthSmallest(TreeNode root, int k) {
        //计算左子树的节点总数
        int count = countNodes(root.left);
        //如果k小于或等于左子树的节点总数
        if (k <= count) {
            //直接计算并放回左子树中的第k个最小值
            //因为二叉搜索树的左子节点值小于根结点值
            return kthSmallest(root.left, k);
        } else if (k > count + 1) {
            //如果k大于左子树节点总数
            //返回右子树的第k - 1 - count个最小值
            //k -1 减去了当前节点
            return kthSmallest(root.right, k - 1 - count);
        }
        //返回当前节点的值
        return root.val;
    }

    /**
     * 计算当前树的节点总数
     *
     * @param n
     * @return
     */
    public int countNodes(TreeNode n) {
        //如果树根结点为空，返回0
        if (n == null) return 0;
        //否则分别计算左子树和右子树的节点总数
        // 1+表示将当前节点计入总数
        return 1 + countNodes(n.left) + countNodes(n.right);
    }

	
```  

---

**参考资料**  

* 网友高票Java解法：  
[https://leetcode.com/problems/kth-smallest-element-in-a-bst/discuss/63660/3-ways-implemented-in-JAVA-(Python)%3A-Binary-Search-in-order-iterative-and-recursive](https://leetcode.com/problems/kth-smallest-element-in-a-bst/discuss/63660/3-ways-implemented-in-JAVA-(Python)%3A-Binary-Search-in-order-iterative-and-recursive)  
