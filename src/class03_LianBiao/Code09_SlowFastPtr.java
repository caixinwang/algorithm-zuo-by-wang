package class03_LianBiao;

public class Code09_SlowFastPtr {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 奇数返回中点，偶数返回左中。例如：1,2,3,4返回2  1,2,3,4,5返回3
     *
     * @param head
     * @return
     */
    public static Node MidOrLeftMid(Node head) {
        if (head == null) {
            return null;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 奇数返回中点，偶数返回右中。例如：1,2,3,4返回3  1,2,3,4,5返回3
     *
     * @param head
     * @return
     */
    public static Node MidOrRightMid(Node head) {
        if (head == null || head.next == null) {//保证至少有两个结点
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 奇数返回中点的前一个，偶数返回左中的前一个。例如：1,2,3,4返回1  1,2,3,4,5返回2
     *
     * @param head
     * @return
     */
    public static Node MidPreOrLeftMidPre(Node head) {
        if (head == null || head.next == null || head.next.next == null) {//保证了至少有三台结点。
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 奇数返回中点的前一个，偶数返回右中的前一个。例如：1,2,3,4返回2  1,2,3,4,5返回2
     *
     * @param head
     * @return
     */
    public static Node MidPreOrRightMidPre(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     *
     * @param num：要创建的结点个数
     * @return
     */
    public static Node generateList(int num) {
        Node head=new Node(1);
        Node cur = head;
        for (int i = 2; i <= num; i++) {
            cur.next = new Node(i);
            cur = cur.next;
        }
        return head;
    }


    public static void main(String[] args) {
        Node cur;

        for (int i=1;i<=10;i++){
            cur=MidOrLeftMid(generateList(i));
            System.out.print(cur==null?"null":cur.value+" ");
        }
        System.out.println();

        for (int i=1;i<=10;i++){
            cur=MidOrRightMid(generateList(i));
            System.out.print(cur==null?"null":cur.value+" ");
        }
        System.out.println();

        for (int i=1;i<=10;i++){
            cur=MidPreOrLeftMidPre(generateList(i));
            System.out.print((cur==null?"null":cur.value)+" ");
        }
        System.out.println();

        for (int i=1;i<=10;i++){
            cur=MidPreOrRightMidPre(generateList(i));
            System.out.print((cur==null?"null":cur.value)+" ");
        }
        System.out.println();

    }


}
