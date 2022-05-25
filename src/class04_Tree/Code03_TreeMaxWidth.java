package class04_Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Code03_TreeMaxWidth {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * 使用hashmap来实现最大宽度的求解
	 * @param head
	 * @return
	 */
	public static int getMaxWidth(Node head) {
		if (head==null)
			return 0;
		int max=-1;
		int curLay=1;
		int cur=0;
		Queue<Node> queue=new LinkedList<>();
		queue.add(head);
		HashMap<Node,Integer> hashMap=new HashMap<>();
		hashMap.put(head,1);
		while(!queue.isEmpty()){
			head=queue.poll();
			if (head.left!=null){
				queue.add(head.left);
				hashMap.put(head.left,curLay+1);
			}
			if (head.right!=null){
				queue.add(head.right);
				hashMap.put(head.right,curLay+1);
			}
			if (hashMap.get(head)!=curLay){
				max=Math.max(max,cur);
				curLay++;
				cur=1;
			}else{
				cur++;
			}
		}
		return max;
	}

	/**
	 * 不使用哈希表实现最大宽度的求解，整体思路还是层序遍历
	 * @param head
	 * @return
	 */
	public static int getMaxWidth2(Node head){
		int max =-1;
		int curNum=0;
		Node cur=null;
		Node curLevel=head;//记录当前层的最后一个，一开始默认是头节点，因为头节点很自然的是第一层的最后一个结点
		Node nextLevel=null;//记录下一层的最后一个节点
		Queue<Node>queue=new LinkedList<>();
		queue.add(head);
		while(!queue.isEmpty()){
			cur=queue.poll();//每次弹出一个节点，把他的左右孩子入队（如果有的话）
			if (cur.left!=null){
				queue.add(cur.left);
				nextLevel= curLevel.left;//nextLevel的值就是最新入队的结点的值
			}
			if (cur.right!=null){
				queue.add(cur.right);
				nextLevel=curLevel.right;
			}
			curNum++;//cur的左右孩子都已经入队了之后把cur统计进当前层的结点总数
			if (cur==curLevel){//说明已经到大了当前层的最后一个结点了,结算max，并且迭代curlevel和nextlevel
				max=Math.max(max,curNum);
				curNum=0;//归零
				curLevel=nextLevel;
			}

		}
		return max;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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

		System.out.println(getMaxWidth(head));

		System.out.println(getMaxWidth2(head));

	}

}
