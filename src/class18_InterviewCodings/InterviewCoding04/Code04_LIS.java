package class18_InterviewCodings.InterviewCoding04;

public class Code04_LIS {

	public static int[] lis1(int[] arr) {
		if (arr == null || arr.length == 0) return null;
		int[] dp = getdp1(arr);
		int[] res = generateLIS(arr,dp);
		return res;
	}

	public static int[] getdp1(int[] arr) {
		int N = arr.length;
		int[] dp = new int[N];//dp[i]：以i结尾的最长递增子序列的长度
		dp[0] = 1;//以0位置结尾的最长递增子序列显然长度为1
		for (int i = 1; i < N; i++) {//找到arr[k]小于arr[i]的下标k1,...kn,dp[i]=max{dp[k1]+1,...,dp[kn]+1}
			for (int k = i - 1; k >= 0; k--) {
				if (arr[k] < arr[i]) dp[i] = Math.max(dp[i], dp[k] + 1);
			}
		}
		return dp;
	}

	public static int[] generateLIS(int[] arr, int[] dp) {//利用dp进行加工得到LIS序列具体是什么
		//先找到dp中最大值max以及其下标index，然后顺着dp的index往前找max-1,max-2...直到1，假设dp[k]=max-1，那么res[i]=arr[k]
		int max = 0, index = 0;
		for (int i = 0; i < dp.length; i++) {
			if (dp[i] > max) {
				max = dp[i];
				index = i;
			}
		}
		int[] res = new int[max];
		for (int k = index,i=max-1; k >= 0; k--) {
			if (max == dp[k]) {
				res[i--] = arr[k];
				max--;
			}
		}
		return res;
	}

	public static int[] lis2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		int[] dp = getdp2(arr);
		return generateLIS(arr, dp);
	}

	public static int[] getdp2(int[] arr) {
		int[] dp = new int[arr.length];
		int[] ends = new int[arr.length];
		ends[0] = arr[0];
		dp[0] = 1;
		int right = 0; // 0....right   right往右无效
		int l = 0;
		int r = 0;
		int m = 0;
		for (int i = 1; i < arr.length; i++) {
			l = 0;
			r = right;
			while (l <= r) {
				m = (l + r) / 2;
				if (arr[i] > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			// l -> right+1
			right = Math.max(right, l);
			ends[l] = arr[i];
			dp[i] = l + 1;
		}
		return dp;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 2, 1, 5, 3, 6, 4, 8, 9, 7 };
		printArray(arr);
		printArray(lis1(arr));
		printArray(lis2(arr));

	}
}