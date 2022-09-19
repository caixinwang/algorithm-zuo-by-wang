package class03_LinkedList;

public class Code07_FindFirstIntersectNode {//找到两个链表第一个相交的结点

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 传进来两个链表要判断他们两个是否相交，由于这两个链表可能是循环链表也可能不是。所以我们要写一个函数来判断
     * 他们的类型。由于无环链表只能和无环链表相交，循环链表只能和循环链表相交，其中无环链表和循环链表不可能相交。
     * 所以我们这里就分成两种情况就行。
     * @param head1:链表1的头节点
     * @param head2:链表2的头节点
     * @return :
     */
    public static Node getIntersectNode(Node head1, Node head2) {
        Node in1=getLoopNode(head1);//入环结点1
        Node in2=getLoopNode(head2);//入环结点2
        if (in1==null&&in2==null){
            return noLoop(head1,head2);
        }else if (in1!=null&&in2!=null){
            return bothLoop(head1,in1,head2,in2);
        }else {
            return null;
        }
    }

    public static Node getLoopNode(Node head) {
        if (head==null||head.next==null){
            return null;
        }
        Node p1=head.next.next;//快
        Node p2=head.next;//慢，都从下标为0的位置开始
        while(p1.next!=null&&p1.next.next!=null){
            if (p1==p2) {
                break;//说明是循环链表
            }
            p1=p1.next.next;
            p2=p2.next;
        }
        //判断是从哪一个分支出来
        if (p1==p2){
            p1=head;
            while(p1!=p2){
                p1=p1.next;
                p2=p2.next;
            }
            return p1;
        }else{//不是循环链表
            return null;
        }
    }

    public static Node noLoop(Node head1, Node head2) {
        if (head1==null||head2==null){
            return null;
        }
        Node p1=head1;
        Node p2=head2;
        int len=0;
        while(p1.next!=null){
            len++;
            p1=p1.next;
        }
        while(p2.next!=null){
            len--;
            p2=p2.next;
        }
        if (p1==p2){//说明有相交
            p1=head1;
            p2=head2;
            if(len>0){
                while(len>0){
                    p1=p1.next;
                    len--;
                }
            }else if (len<0){
                while(len<0){
                    p2=p2.next;
                    len++;
                }
            }
            while(p1!=p2){
                p1=p1.next;
                p2=p2.next;
            }
            return p1;
        }else{
            return null;
        }

    }

    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node p1=loop1;
        Node p2=loop2;
        if (p1==p2){//转化成两个单链的情况
            //把后面循环的部分拆掉最后再复原
            p1=loop1.next;
            p2=loop2.next;
            loop1.next=null;
            loop2.next=null;
            //调用之前写的函数算出res的值
            Node res=null;
            res=noLoop(head1,head2);
            //恢复后面的循环部分
            loop1.next=p1;
            loop2.next=p2;
            return res;
        }else{
            p1=loop1.next;
            while(p1!=loop1){
                if (p1==loop2){
                    break;
                }
                p1=p1.next;
            }
            if (p1==loop2){//说明相交了
                return loop1;
            }else{//说明没有相交
                return null;
            }
        }

    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }
}
