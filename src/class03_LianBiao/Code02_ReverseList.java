package class03_LianBiao;

public class Code02_ReverseList {

    private static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            value = data;
        }
    }

    private static Node reverseList(Node head){
        Node pre=null,next=null;
        while(head!=null){
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;
    }

    private static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            value = data;
        }
    }

    private static DoubleNode reverseList(DoubleNode head){
        DoubleNode pre=null,next=null;
        while(head!=null){
            next=head.next;
            head.next=pre;
            head.last=next;
            pre=head;
            head=next;
        }
        return pre;
    }

    private static void printLinkedList(Node head){
        System.out.print("LinkedList: ");
        while(head!=null){
            System.out.print(head.value+" ");
            head=head.next;
        }
        System.out.println();
    }

    private  static void printDoubleLinkedList(DoubleNode head){
        System.out.print("Double LinkedList: ");
        DoubleNode end=null;
        while(head!=null){//退出这个while的时候head为空，end为尾结点
            System.out.print(head.value+" ");
            end=head;
            head=head.next;
        }
        System.out.print("| ");
        while(end!=null){
            System.out.print(end.value+" ");
            end=end.last;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        printLinkedList(head1);
        head1 = reverseList(head1);
        printLinkedList(head1);

        DoubleNode head2 = new DoubleNode(1);
        head2.next = new DoubleNode(2);
        head2.next.last = head2;
        head2.next.next = new DoubleNode(3);
        head2.next.next.last = head2.next;
        head2.next.next.next = new DoubleNode(4);
        head2.next.next.next.last = head2.next.next;
        printDoubleLinkedList(head2);
        printDoubleLinkedList(reverseList(head2));

    }
}
