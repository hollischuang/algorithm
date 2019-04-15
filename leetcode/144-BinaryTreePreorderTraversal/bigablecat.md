**144. 二叉树的前序遍历**  
---
[https://leetcode-cn.com/problems/binary-tree-preorder-traversal/](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)  

* 网友高票Java解法：    

```java  
	
    /**
     * 前序遍历（DLR），是二叉树遍历的一种，首先访问根结点然后遍历左子树，最后遍历右子树
     *
     * 网友高票Java解法
     *
     * @param node
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode node) {
        //创建一个链表
        List<Integer> list = new LinkedList<Integer>();
        //创建一个栈对象，用于存储右节点
        Stack<TreeNode> rights = new Stack<TreeNode>();
        //当前节点非空时进行遍历
        while(node != null) {
            //将当前节点的值存入链表
            list.add(node.val);
            //如果当前节点存在右子节点
            if (node.right != null) {
                //将右子节点存入栈
                rights.push(node.right);
            }
            //获取当前节点的左子节点并赋值给node
            node = node.left;
            //node == null表示刚才获取的左子节点为空
            //rights.isEmpty()表示存放右子节点的栈非空
            if (node == null && !rights.isEmpty()) {
                //弹出栈最顶端的元素并赋值给node
                node = rights.pop();
            }
            //最终赋值的node将在下一次循环把val存入链表list
        }
        //返回最终结果
        return list;
    }
	
```  

---

**参考资料**  

* 网友高票Java解法：  
[https://leetcode.com/problems/binary-tree-preorder-traversal/discuss/45266/Accepted-iterative-solution-in-Java-using-stack.](https://leetcode.com/problems/binary-tree-preorder-traversal/discuss/45266/Accepted-iterative-solution-in-Java-using-stack.)  
