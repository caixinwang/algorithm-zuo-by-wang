package class04_Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code01_PreInPosTraversal {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void preOrderRecur(Node head) {
        if (head==null)
            return;
        System.out.print(head.value+" ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public static void inOrderRecur(Node head) {
        if (head==null)
            return;
        inOrderRecur(head.left);
        System.out.print(head.value+" ");
        inOrderRecur(head.right);
    }

    public static void posOrderRecur(Node head) {
        if (head==null)
            return;
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value+" ");
    }

    public static void preOrderUnRecur(Node head){
        System.out.print("preorder unrecur:");
        if (head==null)
            return;
        Stack<Node> stack =new Stack<>();
        stack.push(head);
        while(!stack.isEmpty()){
            head=stack.pop();
            System.out.print(head.value+" ");
            if (head.right!=null){
                stack.push(head.right);
            }
            if (head.left!=null){
                stack.push(head.left);
            }
        }
        System.out.println();
    }

    public static void preOrderUnRecur2(Node head){//ZheJiang
        if (head==null)
            return;
        Stack<Node> stack =new Stack<>();
        while(!stack.isEmpty()||head!=null){
            while(head!=null){
                stack.push(head);
                System.out.print(head.value+" ");
                head= head.left;
            }
            head= stack.pop();
            head=head.right;
        }
        System.out.println();
    }

    public static void inOrderUnRecur(Node head) {
        if (head==null)
            return;
        System.out.print("inorder unrecur:");
        Stack<Node> stack=new Stack<>();
        while(head!=null||!stack.isEmpty()){
            while(head!=null){
                stack.push(head);
                head= head.left;
            }
            head= stack.pop();
            System.out.print(head.value+" ");
            head=head.right;
        }
        System.out.println();
    }

    public static void posOrderUnRecur1(Node head){
        System.out.print("postorder unrecur:");
        if (head==null)
            return;
        Stack<Node> stack1=new Stack<>();
        Stack<Node> stack2=new Stack<>();
        while(head!=null||!stack1.isEmpty()){
            while(head!=null){
                stack1.push(head);
                stack2.push(head);
                head=head.right;
            }
            head=stack1.pop();
            head=head.left;
        }
        while(!stack2.isEmpty()){
            System.out.print(stack2.pop().value+" ");
        }
        System.out.println();
    }

    /**
     * 这里利用head来跟踪刚才打印过的结点，这样就能知道当前栈顶的结点的左子树处理完了没，右子树处理完了没。如果处理完了那么
     * 会打印孩子，这时候head也就跟踪了孩子。如果p.left!=head说明p的左孩子没处理过（注意这里p.right!=head因为右树处理完了就
     * 代表左孩子肯定处理完了），左孩子没有处理过那么我就去处理左孩子，就把左孩子压栈。如果p.right!=head那么就说明右孩子没有处理
     * 右孩子进栈。如果发现左右孩子都处理完了，那么我就打印自己。
     *
     * 代码有另外一种写法，就是先让左边界一条线压栈，然后打印，然后设置head。我们这里就是让head处理不会干扰我们的位置，
     * 这里“干扰”的含义就是不会干扰我们走逻辑分支，我们就是首先一条线左边界压栈了。head一开始是没有意义的，只要不干扰
     * 走逻辑分支1就行了。
     *
     * 核心思想就是利用head来标记现在栈顶的结点的子过程有没有完毕。
     * @param head
     */
    public static void posOrderUnRecur2(Node head) {
        if (head==null)
            return;
        System.out.print("postorder unrecur :");
        Stack<Node> stack=new Stack<>();
        stack.push(head);
        Node p=null;
        while(!stack.isEmpty()){
            p= stack.peek();//瞥一眼栈顶，通过下面的逻辑分支来判断是三种情况的哪一种
            if (p.left!=null&&p.left!=head&&p.right!=head){//说明左子树还没有处理
                stack.push(p.left);
            }else if (p.right!=null&&p.right!=head){ //说明右子树没有处理
                stack.push(p.right);
            }else {//左右子树都处理完了，打印当前栈顶的结点。
                System.out.print(stack.pop().value+" ");
                head=p;//当第一次打印的时候，head才被赋予了它真正的含义
            }
        }
        System.out.println();
    }

    /**
     * 按照层序打印
     * @param head
     */
    private static void printByLay(Node head){
        if (head==null)
            return;
        System.out.print("by lay:");
        Queue<Node> queue=new LinkedList<>();
        queue.add(head);
        while(!queue.isEmpty()){
            head=queue.poll();
            System.out.print(head.value+" ");
            if (head.left!=null){
                queue.add(head.left);
            }
            if (head.right!=null){
                queue.add(head.right);
            }
        }
        System.out.println();
    }



    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        preOrderUnRecur(head);
        inOrderUnRecur(head);
        posOrderUnRecur1(head);
        posOrderUnRecur2(head);

        System.out.println("============print by lay=============");
        printByLay(head);

    }
}
