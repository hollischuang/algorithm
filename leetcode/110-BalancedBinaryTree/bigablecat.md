**110. 平衡二叉树**  
---
[https://leetcode-cn.com/problems/balanced-binary-tree/](https://leetcode-cn.com/problems/balanced-binary-tree/)  

* 网友高票Java解法：  

```java  

    /**
     * 网友高票Java解法
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    /**
     * 获取当前节点的树高
     * 如果是高度平衡的二叉树，返回树的真实高度
     * 如果不是高度平衡二叉树，返回-1
     *
     * @param node
     * @return
     */
    public int height(TreeNode node) {
        //检查当前节点是否为空
        if (node == null) {
            //空节点返回0
            return 0;
        }
        //获取左子节点的树高
        int lH = height(node.left);
        //如果返回-1，说明左子节点不符合题意
        if (lH == -1) {
            return -1;
        }
        //同理判断右子节点
        int rH = height(node.right);
        if (rH == -1) {
            return -1;
        }
        //检查左右子节点高度差的绝对值是否不超过1
        if (lH - rH < -1 || lH - rH > 1) {
            //绝对值超过1，不符合题意，返回-1
            return -1;
        }
        //Math.max(lH,rH)返回左右子节点中高度较大的一个
        //在子节点中较大的高度上+1，即当前节点自身的高度1
        //返回的最终结果就是当前节点的树高
        return Math.max(lH, rH) + 1;
    }

```  

**参考资料**  

* 网友高票答案：  
[https://leetcode.com/problems/balanced-binary-tree/discuss/35686/Java-solution-based-on-height-check-left-and-right-node-in-every-recursion-to-avoid-further-useless-search](https://leetcode.com/problems/balanced-binary-tree/discuss/35686/Java-solution-based-on-height-check-left-and-right-node-in-every-recursion-to-avoid-further-useless-search)  

