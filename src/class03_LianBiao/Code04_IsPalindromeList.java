package class03_LianBiao;

import java.util.Stack;

public class Code04_IsPalindromeList {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    private static boolean isPalindrome1(Node head){
        Stack<Node> stack=new Stack<>();
        Node temp=head;
        while(temp!=null){
            stack.push(temp);
            temp= temp.next;
        }
        while(!stack.isEmpty()){
            if (stack.pop().value!=head.value)
                return false;
            head= head.next;
        }
        return true;
    }

    private static boolean isPalindrome2(Node head){
        if (head==null||head.next==null)// 确定至少有两个元素
            return true;
        Node n1=head.next;//慢指针设置在1位置可以保证快指针遍历结束后慢指针在对称轴的右边
        Node n2=head;//快指针在0位置,控制快指针不跳出链表，也就是要在[0-N]范围上
        while(n2.next!=null&&n2.next.next!=null){
            n2=n2.next.next;
            n1=n1.next;
        }
        //出循环，现在n1在对称轴右边
        Stack<Node>stack=new Stack<>();
        while(n1!=null){
            stack.push(n1);
            n1=n1.next;
        }
        while(!stack.isEmpty()){
            if (stack.pop().value!=head.value)
                return false;
            head=head.next;
        }
        return true;
    }

    private static boolean isPalindrome3(Node head){
        if (head==null||head.next==null)
            return true;
        Node n1=head;//快
        Node n2= head.next;//慢,快指针结束慢指针指在对称轴右边的位置
        while(n1.next!=null&&n1.next.next!=null){
            n1=n1.next.next;
            n2=n2.next;
        }
        n1=null;//作为pre
        Node n3;//作为next
        while(n2!=null){
            n3=n2.next;
            n2.next=n1;
            n1=n2;
            n2=n3;
        }
        //退出循环n1现在是尾结点，而head是头节点
        boolean res=true;
        n2=n1;//保存尾结点
        n3=head;//n3充当左边链表的头
        while(n1!=null){//从n1这里开始因为右边比较短
            if (n1.value!= n3.value){
                res= false;
                break;
            }
            n1=n1.next;
            n3=n3.next;
        }

        //恢复链表
        n1=null;//pre
        while(n2!=null){
            n3=n2.next;
            n2.next=n1;
            n1=n2;
            n2=n3;
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
