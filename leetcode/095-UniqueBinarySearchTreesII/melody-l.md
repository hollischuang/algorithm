**095.UniqueBinarySearchTreesII**  
---
[https://leetcode-cn.com/problems/unique-binary-search-trees-ii/](https://leetcode-cn.com/problems/unique-binary-search-trees-ii/)  

方法一：动态规划+递推  
对于二叉搜索树，其左子树中的所有节点的值都小于根节点，右子树的所有节点的值都大于根节点。因此，该问题可以转为  
* 对于从1到n的序列A={1...n}，求以i为根节点的所有二叉搜索树集合（其中，i>=1 且 i<=n）。  

设以i为根节点的所有二叉树集合为F(i)，很显然，F(i)的结果为{1...i-1}的可能BTS集合和{i+1...n}的可能BTS集合的笛卡尔积。此时，这个问题又回归到了初始问题（求1到n的BTS集合）。这种再次回归到初始问题的就可以采用递归的办法解决。  

当然，该问题经过不断递归是有解的。因为不断的递归之后，问题域的size是逐渐减小的。当size==1的时候，问题是可解的（此时，集合只有一个节点，所有的BTS组合唯一）。  

综上，dp思路为：  
设`F(start, end)`表示从start到end序列的所有BTS可能性的集合。则 `F(start, end)={ F(start, i-1) × F（i+1, end） | i属于{1, ... ,n} }`

```java  
class Solution {
    public List<TreeNode> generateTrees(int n) {
        // 测试用例中存在n=0的情况
        if (n <= 0)
            return new ArrayList<TreeNode>();

        return getTrees(1, n);
    }

    /**
     * 获取从start到end的所有二叉搜索树集合
     * @param start 起始位置
     * @param end 终点位置
     * @return 从start到end之间的所有Tree组合的集合
     */
    private List<TreeNode> getTrees(int start, int end) {

        List<TreeNode> list = new LinkedList<TreeNode>();

        // 若上层递归root结点该方向无节点，则递归到此处
        if (start > end) {
            list.add(null);
            return list;
        }

        // 若上层递归root结点该方向存在一个结点，则递归到此处
        // 这一步是可以省略的，
        // 因为当start==end的时候，后面的循环递归的逻辑与该处逻辑等价
        // 这里提前返回，避免再次进入start>end的递归
        if (start == end) {
            list.add(new TreeNode(start));
            return list;
        }

        List<TreeNode> leftNodeList, rightNodeList;
        for (int i = start; i <= end; i++) {
            // 获取start到i-1的所有二叉搜索树
            leftNodeList = getTrees(start, i - 1);
            // 获取i+1到end的所有二叉搜索树
            rightNodeList = getTrees(i + 1, end);
            // 获取到当根节点为i的左二叉搜索树和右二叉搜索树的所有情况
            // 遍历左BST与右BST组合的所有情况，
            // 将所有情况都与root结合为一个二叉搜索树
            for (TreeNode leftNode : leftNodeList) {
                for (TreeNode rightNode : rightNodeList) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    list.add(root);
                }
            }
        }
        
        return list;
    }
}

```  

---


**参考资料**  

* 官方题解：  
[https://leetcode.com/articles/unique-binary-search-trees-ii/](https://leetcode.com/articles/unique-binary-search-trees-ii/)  
