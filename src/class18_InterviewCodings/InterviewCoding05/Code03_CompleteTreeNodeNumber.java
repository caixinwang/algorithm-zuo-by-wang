package class18_InterviewCodings.InterviewCoding05;

public class Code03_CompleteTreeNodeNumber {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	// 请保证head为头的树，是完全二叉树
	public static int nodeNum(Node head) {
		if (head == null) {
			return 0;
		}
		return bs(head, 1, mostLeftLevel(head, 1));
	}

	// node在第level层，h是总的深度（h永远不变，全局变量
	// 以node为头的完全二叉树，节点个数是多少
	public static int bs(Node node, int level, int h) {
		if (level==h) return 1;//在最后一层，说明是叶子结点
		int p = mostLeftLevel(node.right, level+1);
		if (p==h){//右子树如果到了h，说明左子树一定满了，左子树是h-level层的满二叉树
			return (1<<h-level)+bs(node.right,level+1,h);
		}else {//右子树如果是h-1，那么说明右子树满了
			return (1<<h-level-1)+bs(node.left,level+1,h);
		}
	}

	// 如果node在第level层，
	// 求以node为头的子树，最大深度是多少
	// node为头的子树，一定是完全二叉树
	public static int mostLeftLevel(Node node, int level) {
//		while(node!=null&&node.left!=null){ //这种不行，因为node为空的时候需要返回level-1，因为不能给空节点算高度
//			node=node.left;
//			level++;
//		}
//		return level;
		while (node != null) {
			level++;
			node = node.left;
		}
		return level - 1;
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		System.out.println(nodeNum(head));

	}

}
