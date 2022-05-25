package class03_LianBiao;

public class Code05_SmallerEqualBigger {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node listPartition1(Node head, int pivot){
        Node temp=head;
        int num=0;
        while (temp!=null){//遍历统计链表的大小
            num++;
            temp=temp.next;
        }
        Node[] arr=new Node[num];
        temp=head;
        int i=0;
        while(temp!=null){
            arr[i++]=temp;
            temp=temp.next;
        }
        partition(arr,pivot);
        for (i=0;i<arr.length-1;i++){
            arr[i].next=arr[i+1];
        }
        arr[i].next=null;
        return arr[0];
    }

    private static void partition(Node[] arr, int pivot) {
        int less=-1;
        int more=arr.length;
        int index=0;
        while(index<more){
            if (arr[index].value<pivot){
                swap(arr,++less,index++);
            }else if (arr[index].value>pivot){
                swap(arr,--more,index);
            }else {
                index++;
            }
        }
    }

    private static void swap(Node[] arr, int a, int b) {
        Node temp=arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
    }

    public static Node listPartition2(Node head, int pivot) {
        Node ss=null;
        Node se=null;
        Node es=null;
        Node ee=null;
        Node bs=null;
        Node be=null;
        while(head!=null){
            Node next=head.next;
            head.next=null;//如果不置空，可能会导大于链的最后一个结点和某一个其它结点连接形成闭环，导致无限循环
            if (head.value<pivot){
                if (ss==null){
                    ss=head;
                    se=head;
                }else{
                    se.next=head;
                    se=head;
                }
            }else if (head.value==pivot){
                if (es==null){
                    es=head;
                    ee=head;
                }else{
                    ee.next=head;
                    ee=head;
                }
            }else {
                if (bs==null){
                    bs=head;
                    be=head;
                }else {
                    be.next=head;
                    be=head;
                }
            }
            head=next;
        }
        //开始连接这三个链
        if (se!=null){//要对尾巴进行判断
            se.next=es;
            ee=ee==null?se:ee;
        }
        if (ee!=null){//都是对尾巴进行判断，因为是尾巴的next
            ee.next=bs;
        }
        return ss!=null?ss:es!=null?es:bs;
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
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
         head1 = listPartition1(head1, 4);
//        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }
}
