package class03_LianBiao;
import java.util.Stack;

public class Code04_IsPalindromeList1 {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    private static boolean isPalindrome1(Node head){
        Node p=head;
        Stack<Node> stack=new Stack<>();
        while(p!=null){
            stack.push(p);
            p= p.next;
        }
        p=head;
        while(!stack.isEmpty()){
            if (stack.pop().value!=p.value)
                return false;
            p=p.next;
        }
        return true;

    }

    private static boolean isPalindrome2(Node head){
        if (head==null||head.next==null)
            return true;
        Node p1=head;//快
        Node p2=head.next;//慢
        while(p1.next!=null&&p1.next.next!=null){
            p1=p1.next.next;
            p2=p2.next;
        }
        //现在慢指针指在了对称轴右边
        Stack<Node>stack=new Stack<>();
        while(p2!=null){
            stack.push(p2);
            p2=p2.next;
        }
        while(!stack.isEmpty()){
            if (stack.pop().value!=head.value){
                return false;
            }
            head= head.next;
        }
        return true;
    }

    private static boolean isPalindrome3(Node head){
        if (head==null||head.next==null)
            return true;
        Node p1,p2,p3;
        p1=head;//fast
        p2= head.next;//slow
        while(p1.next!=null&&p1.next.next!=null){
            p1=p1.next.next;
            p2=p2.next;
        }
        p1=null;//pre
        while(p2!=null){
            p3=p2.next;//next
            p2.next=p1;
            p1=p2;
            p2=p3;
        }
        //now p1 is the last node
        boolean res=true;//if not then quit
        p3=head;
        p2=p1;//save the last node
        while(p1!=null){
            if (p1.value!=p3.value){
                res=false;
                break;
            }
            p1=p1.next;
            p3=p3.next;
        }
        p1=null;//pre
        while(p2!=null){
            p3=p2.next;//next
            p2.next=p1;
            p1=p2;
            p2=p3;
        }
        return res;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }
}
