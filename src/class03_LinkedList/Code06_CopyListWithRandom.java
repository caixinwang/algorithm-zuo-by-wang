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
        while(p!=null){//在每一个老结点的后面插上对应的新结点，插入完成以后链表的长度一定为偶数，2N
            next=p.next;//把p的后继存起来，后面要迭代
            p.next=new Node(p.value);
            p.next.next=next;//让新节点指向老结点的下一个
            p=next;
        }
        p=head;
        while(p!=null){//不涉及到破坏next链，一个p变量就可以搞定
            p.next.rand=p.rand==null?null:p.rand.next;//p.next代表新结点x，老结点的random指向另一个老结点y，y.next就是对应的新结点
            p=p.next.next;//下一个老结点。偶数个节点数，不会报错
        }
        p=head;
        Node p2,res;
        res=head.next;//保存新链头节点
        while(p!=null){//分离新老结点。奇偶结点分离
            next=p.next.next;//老结点。偶数个结点跳不出去
            p2=p.next;//新结点
            p.next=next;//老结点和老结点相连
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
