package class03_LinkedList;
//测试链接：https://leetcode.com/problems/reverse-nodes-in-k-group/

/**
 * 题目：链表分组，每组k个节点，组内逆序，不够k个的不管。
 * 也就是1->2->3->4->5->6->7->8->9->10，如果k为3，结果为3->2->1->6->5->4->9->8->7->10。
 * 可以看到，这个函数一定是一个换头函数。因为头变了，变成了3。
 */
public class Code02_ReverseNodesInKGroup {
    // 不要提交这个类
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int value){
            this.val=value;
            this.next=null;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start=head;
        ListNode end=countK(head,k);
        head=end;//最终返回的是这个结点
        ListNode last=null;//记录上一组的最后一个结点
        if (end==null){//不够k个，直接返回
            return head;
        }
        reverse(start,end);//先翻转第一组，第一组前面没有结点，不需要多进行处理
        last=start;//翻转之后start变成了最后一个
        while(start.next!=null){//存在下一组
            start=start.next;
            end=countK(start,k);
            if (end==null){//判断下一组是否满足k个
                break;
            }
            reverse(start,end);
            last.next=end;
            last=start;
        }
        return head;
    }

    /**
     * @param start：开始结点
     * @param k：第k个节点
     * @return ：这个函数的功能就是从start结点开始（第一个结点），返回第k个节点
     */
    public static ListNode countK(ListNode start, int k) {
        while(start!=null&&k!=1){//走了k-1步
            start= start.next;
            k--;
        }
        return start;
    }

    public static void reverse(ListNode start, ListNode end) {
        ListNode pre=end.next;//让翻转之后的最后一个结点start指向原本end结点的下一个接地那
        end=end.next;//作为终止条件
        ListNode next=null;//作为中间变量，方便迭代
        while(start!=end){
            next=start.next;
            start.next=pre;
            pre=start;
            start=next;
        }
    }
}
