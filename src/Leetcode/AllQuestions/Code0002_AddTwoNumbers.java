package Leetcode.AllQuestions;

public class Code0002_AddTwoNumbers {//两数相加--链表
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {
        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1==null||l2==null) return null;
        ListNode head1=l1,head2=l2;
        while(head1!=null&&head2!=null){
            head1=head1.next;
            head2=head2.next;
        }
        boolean l1Longer=head1!=null;
        head1=l1;
        head2=l2;
        if (!l1Longer){//head1为长的，head2为短的
            ListNode tmp=head1;
            head1=head2;
            head2=tmp;
        }
        int carry=0;
        ListNode p1=head1,p2=head2;
        ListNode pre=null;
        while(p1!=null&&p2!=null){
            int sum=p1.val+p2.val+carry;
            carry=sum/10;
            p1.val=sum%10;
            pre=p1;
            p1=p1.next;
            p2=p2.next;
        }
        while(p1!=null){
            int sum=p1.val+carry;
            carry=sum/10;
            p1.val=sum%10;
            pre=p1;
            p1=p1.next;
        }
        if (carry!=0){
            pre.next=new ListNode(carry);
        }
        return head1;//返回长的
    }
}
