package class04_Tree;

import java.util.LinkedList;

public class Code05_IsCBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 对层序遍历进行修改，如果发现某一个入队结点不是左右孩子健全的，那么接下来入队的结点都是叶子结点。
     * 入队的结点不能有左没右，否则就不是完全二叉树
     * @param head
     * @return
     */
    public static boolean isCBT1(Node head) {
        if (head==null)
            return true;

        LinkedList<Node>list=new LinkedList<>();//队列
        list.add(head);
        boolean thing=false;//标记是否出现过左右儿子不双全的情况
        while(!list.isEmpty()){
            head= list.poll();
            if ((thing&&(head.left!=null||head.right!=null)) ||
                    (head.right!=null && head.left==null)){
                return false;
            }
            if (head.left!=null){
                list.add(head.left);
            }
            if (head.right!=null){
                list.add(head.right);
            }
            if (head.left==null|| head.right==null){
                thing=true;
            }
        }
        return true;
    }

    public static boolean isCBT2(Node head) {
        return  process(head).isCom;
    }

    // 对每一棵子树，是否是满二叉树、是否是完全二叉树、高度
    public static class Info {
        public boolean isCom;
        public boolean isFull;
        public int height;

        public Info(boolean isCom,boolean isFull,int height){
            this.height=height;
            this.isCom=isCom;
            this.isFull=isFull;
        }
    }

    public static Info process(Node head) {
        if (head==null){
            return new Info(true,true,0);
        }

        Info left=process(head.left);
        Info right=process(head.right);

        boolean isCom=false;
        boolean isFull=false;
        int height=0;

        if (left.isFull&& right.isFull&& left.height== right.height){
            isFull=true;
        }

        height=Math.max(left.height, right.height)+1;

        if (isFull){
            isCom=true;
        }else{
            if (left.isFull&& right.isFull&& left.height==right.height+1){
                isCom=true;
            }
            if (left.isFull&& right.isCom&& left.height== right.height){
                isCom=true;
            }
            if (left.isCom&& right.isFull&& left.height== right.height+1){
                isCom=true;
            }
        }
        return new Info(isCom,isFull,height);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
