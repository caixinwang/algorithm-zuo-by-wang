package class03_LinkedList;

public class Code10_AddTwoNumbers {//给定两个链表的头节点head1和head2,认为从左到右是某个数字从低位到高位，返回相加之后的链表。
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        int len1 = listLength(head1);
        int len2 = listLength(head2);
        ListNode longList = len1 >= len2 ? head1 : head2;//longList为head1和head2中比较长的那个
        ListNode shortList = longList == head1 ? head2 : head1;//shortList为另外一个比较短的
        ListNode head = longList;//返回它
        ListNode longPre = longList;//指向长链表指针的前一个位置，最后可能因为进位需要创建一个新的结点

        int carry = 0;
        int sum = 0;
        while (shortList != null) {
            sum = shortList.val + longList.val + carry;
            longList.val = sum % 10;
            carry = sum / 10;
            longPre = longList;
            longList = longList.next;
            shortList = shortList.next;
        }

        while (longList != null) {
            sum = longList.val + carry;
            longList.val = sum % 10;
            carry = sum / 10;
            longPre = longList;
            longList = longList.next;
        }

        if (carry != 0) {
            longPre.next = new ListNode(1);
        }

        return head;

    }

    // 求链表长度
    public static int listLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
}
