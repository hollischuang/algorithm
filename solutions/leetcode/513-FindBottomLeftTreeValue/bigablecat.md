**513. 找树左下角的值**  
---
[https://leetcode-cn.com/problems/find-bottom-left-tree-value/](https://leetcode-cn.com/problems/find-bottom-left-tree-value/)  

* 网友高票Java解法：  

```java  
    /**
     * 网友高票Java解法
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        //定义一个队列
        Queue<TreeNode> queue = new LinkedList<>();
        //将当前节点加入队列
        queue.add(root);
        //如果队列非空，继续循环
        while (!queue.isEmpty()) {
            //从队列中取出第一个元素，赋值给root
            root = queue.poll();
            //如果root右子节点非空
            //将右子节点加入队列
            if (root.right != null)
                queue.add(root.right);
            //同理将左子节点加入队列
            if (root.left != null)
                queue.add(root.left);

            //队列先进先出
            //右子节点先被放进Queue，会在下一轮循环中先出
            //这个解法的思路是不断搜索树的节点
            //将节点按照右-左的顺序存入队列
            //再在下一轮循环中弹出队列顶端的节点
            //最终队列只留下树最后一行的左节点
        }
        //返回最后一行左节点的值
        return root.val;
    }


```  

**参考资料**  

* 网友高票Java解法：  
[https://leetcode.com/problems/find-bottom-left-tree-value/discuss/98779/Right-to-Left-BFS-(Python-%2B-Java)](https://leetcode.com/problems/find-bottom-left-tree-value/discuss/98779/Right-to-Left-BFS-(Python-%2B-Java))  
