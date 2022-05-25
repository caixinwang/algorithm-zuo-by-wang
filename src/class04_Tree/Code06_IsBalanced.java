package class04_Tree;

public class Code06_IsBalanced {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 利用后序遍历的递归序，在第三次返回结点的时候演算左右两边的高度是否差别小于等于1来判断是否为平衡二叉树。
     * 在这一整个过程中需要一个全局变量，这里我们使用一个数组来达到C语音中传进一个指针的效果，在上述的递归过程如果
     * 不是平衡二叉树就修改了指针的值。
     * @param head
     * @return
     */
    public static boolean isBalanced1(Node head) {
        boolean[] ans=new boolean[1];
        ans[0]=true;//如果调用process过程中检测出不是平衡的就修改为false
        process1(head,ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (head==null)
            return 0;//空树默认高度为0
        int left=process1(head.left,ans);
        int right=process1(head.right,ans);
        if ((Math.abs(left-right))>1){
            ans[0]=false;
        }
        return Math.max(left,right)+1;
    }

    public static boolean isBalanced2(Node head) {
        return process(head).isBalance;
    }

    public static class Info{
        boolean isBalance;
        int height;

        public Info(boolean isBalance,int height){
            this.height=height;
            this.isBalance=isBalance;
        }
    }

    public static Info process(Node x) {
        if (x==null){//base case
            return new Info(true,0);
        }

        boolean isBalance=true;
        int height=0;

        Info left=process(x.left);
        Info right=process(x.right);

        if (!left.isBalance|| !right.isBalance){
            isBalance=false;
        }

        if (Math.abs(left.height- right.height)>1){
            isBalance=false;
        }

        height=Math.max(left.height, right.height)+1;

        return new Info(isBalance,height);

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
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
