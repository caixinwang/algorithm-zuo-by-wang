package class03_LinkedList;

public class Code10_FastAndSlowPointer {//定制快慢指针
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 奇数返回中点，偶数返回左中。例如：1,2,3,4返回2  1,2,3,4,5返回3
     * slow放在2位置（从1开始计），fast放在3位置。通过if来手动返回节点数小于3的情况
     * @param head
     * @return
     */
    public static Node MidOrLeftMid(Node head) {//（2,3）-->(1,1)
        Node slow=head;
        Node fast=head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 奇数返回中点，偶数返回右中。例如：1,2,3,4返回3  1,2,3,4,5返回3
     * @param head
     * @return
     */
    public static Node MidOrRightMid(Node head) {//(2,2)-->(2,2)
        if (head==null||head.next==null) return head;//0~1个结点自己判断返回，有1个结点是有意义的
        Node slow=head.next;
        Node fast=head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 奇数返回中点的前一个，偶数返回左中的前一个。例如：1,2,3,4返回1  1,2,3,4,5返回2
     * 至少要有3个结点，因为2结点的时候没有左中前
     * @param head
     * @return
     */
    public static Node MidPreOrLeftMidPre(Node head) {//(1,3)-->(1,3)
        if (head == null || head.next == null || head.next.next == null) {//保证了至少有三台结点,少于3个都是没有意义的。
            return null;
        }
        Node slow = head;//1位置
        Node fast = head.next.next;//slow最后要在左中前，fast不能影响slow，放在3位置
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 奇数返回中点的前一个，偶数返回右中的前一个。例如：1,2,3,4返回2  1,2,3,4,5返回2
     * @param head
     * @return
     */
    public static Node MidPreOrRightMidPre(Node head) {//(1,2)-->(1,2)
        if (head == null || head.next == null) {//至少要有两个结点,少于两个结点都是没有意义的
            return null;
        }
        Node slow = head;//1位置
        Node fast = head.next;//slow要动，fast要在2位置
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
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
        Node node1 = generateList(21);//1~21
        Node node2 = generateList(20);//1~20
        System.out.print("中和左中：奇数"+MidOrLeftMid(node1).value+"  偶数");
        System.out.println(MidOrLeftMid(node2).value);
        System.out.print("中和右中：奇数"+MidOrRightMid(node1).value+"  偶数");
        System.out.println(MidOrRightMid(node2).value);
        System.out.print("中前和左中前：奇数"+MidPreOrLeftMidPre(node1).value+"  偶数");
        System.out.println(MidPreOrLeftMidPre(node2).value);
        System.out.print("中前和右中前：奇数"+MidPreOrRightMidPre(node1).value+"  偶数");
        System.out.println(MidPreOrRightMidPre(node2).value);


    }


}
