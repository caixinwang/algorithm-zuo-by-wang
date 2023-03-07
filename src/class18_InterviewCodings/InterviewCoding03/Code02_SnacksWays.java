package class18_InterviewCodings.InterviewCoding03;

public class Code02_SnacksWays {

	public static int ways1(int[] arr, int w) {
		// arr[0...]
		return process2(arr, 0, w);
//		return process(arr,0.w);
	}

	/** 递归前判断合法性
	 * 如果(rest-arr[index])<0不放在p2的话，那么就需要多写一个base case返回-1，代表背包容量溢出
	 * @param arr 零食
	 * @param index 0~index-1 已经选择过了，现在是index~len-1来选择
	 * @param rest 还剩下rest的空间
	 * @return 方法数
	 */
	public static int process(int[] arr, int index, int rest) {
		if (index==arr.length) return 1;
		int p1=process(arr,index+1,rest);
		int p2=(rest-arr[index])<0?0:process(arr,index+1,rest-arr[index]);
		return p1+p2;
	}

	//process的另一种写法，递归后才判断合不合法
	public static int process2(int[] arr, int index, int rest) {
		if (rest<0) return -1;//不合法
		if (index==arr.length) return 1;
		int p1=process(arr,index+1,rest);
		int p2=process(arr,index+1,rest-arr[index]);
		return p1+(p2==-1?0:p2);//要记得加括号
	}


	public static int ways2(int[] arr, int w) {//将上面改成动态规划
		int N = arr.length;
		int[][] dp = new int[N + 1][w + 1];
		for (int j = 0; j <= w; j++) {
			dp[N][j] = 1;
		}
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j <= w; j++) {
				dp[i][j] = dp[i + 1][j] + (j - arr[i] >= 0? dp[i + 1][j - arr[i]] : 0);//要把三元运算符括起来
			}
		}
		return dp[0][w];
	}

	/**
	 * dp [ i ] [ j ] 代表0~i自由选择，凑成 j 的的方法数。最终结果就是sum{dp [n-1] [0...j]}
	 * @param arr 零食
	 * @param w 背包大小
	 * @return 方法总数
	 */
	public static int ways3(int[] arr, int w) {
		int N = arr.length;
		int[][] dp = new int[N][w + 1];
		for (int i = 0; i < N; i++) {
			dp[i][0] = 1;
		}
		if (arr[0] <= w) {
			dp[0][arr[0]] = 1;
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <= w; j++) {
				dp[i][j] = dp[i - 1][j] + ((j - arr[i]) >= 0 ? dp[i - 1][j - arr[i]] : 0);
			}
		}
		int ans = 0;
		for (int j = 0; j <= w; j++) {
			ans += dp[N - 1][j];
		}
		return ans;
	}

	public static void main(String[] args) {
		int[] arr = { 4, 3, 2, 9 };
		int w = 8;
		System.out.println(ways1(arr, w));
		System.out.println(ways2(arr, w));
		System.out.println(ways3(arr, w));

	}

}
