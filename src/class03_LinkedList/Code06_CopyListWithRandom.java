package class03_LinkedList;

import java.util.HashMap;

public class Code06_CopyListWithRandom {
    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node copyListWithRand1(Node head){
        Node p=head;
        HashMap<Node,Node> map=new HashMap<>();
        while(p!=null){
            map.put(p,new Node(p.value));
            p=p.next;
        }
        p=head;
        Node res=map.get(head);
        while(p!=null){
            map.get(p).next=map.get(p.next);
            map.get(p).rand=map.get(p.rand);
            p=p.next;
        }
        return res;
    }

    public static Node copyListWithRand2(Node head){
        if (head==null)
            return null;
        Node p=head;
        Node next;
        while(p!=null){
            next=p.next;//把p的后继存起来，后面要迭代
            p.next=new Node(p.value);
            p.next.next=next;
            p=next;
        }
        p=head;
        while(p!=null){//不涉及到破坏next链，一个p变量就可以搞定
            p.next.rand=p.rand==null?null:p.rand.next;
            p=p.next.next;//偶数个节点数，不会报错
        }
        p=head;
        Node p2,res;
        res=head.next;//保存新链头节点
        while(p!=null){
            next=p.next.next;//偶数个结点跳不出去
            p2=p.next;
            p.next=next;
            p2.next=next==null?null:next.next;
            p=next;
        }
        return res;
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

    }
}
