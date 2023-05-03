package Leetcode.TopLikedQuestions;

public class Problem_0124_BinaryTreeMaximumPathSum {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	public static int maxPathSum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return process(root).maxSum;
	}

	public static class Info {
		public int maxSum;
		public int maxSumFromHead;

		public Info(int path, int head) {
			maxSum = path;
			maxSumFromHead = head;
		}
	}

	public static Info process(TreeNode x) {
		if (x == null) {
			return null;
		}
		Info leftInfo = process(x.left);
		Info rightInfo = process(x.right);
		int p1 = Integer.MIN_VALUE;
		if (leftInfo != null) {
			p1 = leftInfo.maxSum;
		}
		int p2 = Integer.MIN_VALUE;
		if (rightInfo != null) {
			p2 = rightInfo.maxSum;
		}
		int p3 = x.val;
		int p4 = Integer.MIN_VALUE;
		if (leftInfo != null) {
			p4 = x.val + leftInfo.maxSumFromHead;
		}
		int p5 = Integer.MIN_VALUE;
		if (rightInfo != null) {
			p5 = x.val + rightInfo.maxSumFromHead;
		}
		int p6 = Integer.MIN_VALUE;
		if (leftInfo != null && rightInfo != null) {
			p6 = x.val + leftInfo.maxSumFromHead + rightInfo.maxSumFromHead;
		}
		int maxSum = Math.max(Math.max(Math.max(p1, p2), Math.max(p3, p4)), Math.max(p5, p6));
		int maxSumFromHead = Math.max(p3, Math.max(p4, p5));
		return new Info(maxSum, maxSumFromHead);
	}

}
