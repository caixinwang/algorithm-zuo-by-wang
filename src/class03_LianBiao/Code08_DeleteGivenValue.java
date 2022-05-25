package class03_LianBiao;

public class Code08_DeleteGivenValue {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node removeValue(Node head, int num) {
        while(head!=null){
            if (head.value!=num){
                break;
            }
            head= head.next;
        }
        //现在来到了第一个不用删除的非空结点
        Node pre=head;//记录前一个不用删除的结点
        Node cur=head;
        while(cur!=null){
            if (cur.value==num){
                pre.next=cur.next;
            }else {
                pre=cur;
            }
            cur=cur.next;
        }
        return head;
    }

}
