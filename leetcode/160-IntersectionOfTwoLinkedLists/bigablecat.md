**160. 相交链表**  
---
[https://leetcode-cn.com/problems/intersection-of-two-linked-lists/](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/)  

* 网友高票java答案  

```java  

    /**
     * https://leetcode.com/articles/intersection-of-two-linked-lists/
     * 官方题解3：双指针法
     * https://leetcode.com/problems/intersection-of-two-linked-lists/discuss/49785/Java-solution-without-knowing-the-difference-in-len!
     * 网友高票答案
     *
     * 时间复杂度：O(m+n)，
     * while循环最差情况是完整遍历两个链表，
     * 时间复杂度为 m+n
     *
     * 空间复杂度：O(1)，
     * 方法没有占用额外空间，所以空间复杂度为O(1)
     *
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //检查边界条件
        if(headA == null || headB == null) return null;

        //定义指针a和b分别指向headA和headB
        ListNode a = headA;
        ListNode b = headB;

        //检查a和b是否有相同结点，如果没有则继续循环
        while( a != b){
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            //(a == null)检查headA是否到达了链表结尾
            //当a为null时，headA遍历结束，将a指向链表headB的头结点
            //如果a不为空，则a指向后继结点
            a = (a == null)? headB : a.next;
            //同理，对b和headB做相同的操作
            b = (b == null)? headA : b.next;

            //上述代码的逻辑简书如下
            //在while循环中，每个链表遍历结束时都指向另一个链表
            //假设 headA 的长度为 lenA，headB 的长度为 lenB，且lenB >= lenA
            //int x = lenB - lenA;
            //当指针a在headA上走完lenA步的时候，指针b离headB的尾结点还有x步
            //接着a指向headB，两个指针同时继续前进
            //当b走完x步来到headB的尾结点，a在headB上也从头结点前行了x步
            //此时b来到headA的头结点，距离headA的尾结点还有lenA步
            //而a在headB的第x个结点，距离headB的尾结点还有lenB-x = lenA步
            //所以当循环到较长的链表headB时，两个链表上的指针剩余的长度是一样的
            //此时继续遍历，当a==b，且都不为null
            //即指针a和指针b指向相同的结点时，这个结点就是两个链表的交点
            //当a和b同时为null，说明指针在第二轮同时走到终点也没有交点
        }

        return a;
    }

```  

时间复杂度：O(m+n)，
while循环最差情况是完整遍历两个链表，
时间复杂度为 m+n

空间复杂度：O(1)，
方法没有占用额外空间，所以空间复杂度为O(1)

**参考资料**  


* 官方题解3：双指针法  
[https://leetcode.com/articles/intersection-of-two-linked-lists/](https://leetcode.com/articles/intersection-of-two-linked-lists/)  

* 网友高票Java答案：  
[https://leetcode.com/problems/intersection-of-two-linked-lists/discuss/49785/Java-solution-without-knowing-the-difference-in-len!](https://leetcode.com/problems/intersection-of-two-linked-lists/discuss/49785/Java-solution-without-knowing-the-difference-in-len!)  
