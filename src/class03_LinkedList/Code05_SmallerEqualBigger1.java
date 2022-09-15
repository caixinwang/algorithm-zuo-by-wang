package class03_LinkedList;

public class Code05_SmallerEqualBigger1 {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node listPartition1(Node head, int pivot){

        Node p=head;
        int i=0;
        while(p!=null){
            i++;
            p=p.next;
        }
        p=head;
        Node[] arr=new Node[i];
        i=0;
        while(p!=null){
            arr[i++]=p;
            p=p.next;
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
        int p=0;
        while(p<more){
            if (arr[p].value<pivot){
                swap(arr,++less,p++);
            }else if(arr[p].value>pivot){
                swap(arr,--more,p);
            }else{
                p++;
            }
        }
    }

    private static void swap(Node[] arr, int a, int b) {
        Node temp=arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
    }

    public static Node listPartition2(Node head, int pivot) {
        Node smallStart=null;
        Node smallEnd=null;
        Node equalStart=null;
        Node equalEnd=null;
        Node bigStart=null;
        Node bigEnd=null;
        while(head!=null){
            if (head.value<pivot){
                if (smallStart==null){
                    smallStart=head;
                    smallEnd=head;
                }else {
                    smallEnd.next=head;
                    smallEnd=head;
                }
            }else if (head.value>pivot){
                if (equalStart==null){
                    equalStart=head;
                    equalEnd=head;
                }else {
                    equalEnd.next=head;
                    equalEnd=head;
                }
            }else {
                if (bigStart==null){
                    bigStart=head;
                    bigEnd=head;
                }else {
                    bigEnd.next=head;
                    bigEnd=head;
                }
            }
            head= head.next;
        }
        if (smallStart!=null){
            smallEnd.next=equalStart;
            equalEnd=equalEnd==null?smallEnd:equalEnd;
        }
        if (equalEnd!=null){
            equalEnd.next=bigStart;
            bigEnd=bigEnd==null?equalEnd:bigEnd;
        }
        return smallStart==null?equalStart==null?bigStart:equalStart:smallStart;
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
         head1 = listPartition1(head1, 5);
//        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }
}
