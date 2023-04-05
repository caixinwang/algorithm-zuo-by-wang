package class18_InterviewCodings.InterviewCoding08;

public class Code04_MoneyProblem {

	// int[] d d[i]：i号怪兽的武力
	// int[] p p[i]：i号怪兽要求的钱
	// ability 当前你所具有的能力
	// index 来到了第index个怪兽的面前
	// 目前，你的能力是ability，你来到了index号怪兽的面前，如果要通过后续所有的怪兽，
	// 请返回需要花的最少钱数
	public static long process(int[] d, int[] p, int ability, int index) {
		if (index == d.length) {
			return 0;
		}
		if (ability < d[index]) {
			return p[index] + process(d, p, ability + d[index], index + 1);
		} else { // 可以贿赂，也可以不贿赂
			return
					Math.min(
							p[index] + process(d, p, ability + d[index], index + 1),
						    process(d, p, ability, index + 1)
							);
		}
	}

	public static long func1(int[] d, int[] p) {
		return process(d, p, 0, 0);
	}

	public static long func2(int[] d, int[] p) {//暴力递归直接改动态规划
		int sum = 0;
		for (int num : d) {
			sum += num;
		}
		long[][] dp = new long[d.length + 1][sum + 1];
		for (int cur = d.length - 1; cur >= 0; cur--) {
			for (int hp = 0; hp <= sum; hp++) {
				// 如果这种情况发生，那么这个hp必然是递归过程中不会出现的状态
				// 既然动态规划是尝试过程的优化，尝试过程碰不到的状态，不必计算
				if (hp + d[cur] > sum) {
					continue;
				}
				if (hp < d[cur]) {
					dp[cur][hp] = p[cur] + dp[cur + 1][hp + d[cur]];
				} else {
					dp[cur][hp] = Math.min(p[cur] + dp[cur + 1][hp + d[cur]], dp[cur + 1][hp]);
				}
			}
		}
		return dp[0][0];
	}


	/**
	 * dp[i][j]代表从0一路通关到i，刚好花够j元，所能达到的最大武力值
	 * @param d 怪兽武力值，>0
	 * @param p 怪兽的贿赂价格,>0
	 * @return 返回最少花多少钱能通关
	 */
	public static int dp1(int[] d,int[] p){
		if (d==null||p==null||d.length==0||p.length==0) return -1;
		int N=d.length,M=0,res=Integer.MAX_VALUE;
		for (int i=0;i<p.length;i++) M+=p[i];
		int[][] dp=new int[N][M+1];
		for (int i=0;i<N;i++){
			for (int j=0;j<=M;j++){
				dp[i][j]=-1;
			}
		}
		//dp[0][0]=-1，不花钱想通关？没门
		dp[0][p[0]]=d[0];//第一行，通过第一个怪兽必须得花钱，假设了怪兽能力值大于0，且你的能力值初始值为0
		//第一列，不花钱也别想通关！
		for (int i=1;i<N;i++){
			for (int j=1;j<=M;j++){
				if (dp[i-1][j]!=-1&&dp[i-1][j]>=d[i]) {//不花钱通关,需要武力值大于等于怪兽
					dp[i][j] = dp[i - 1][j];
				}
				if (j-p[i]>=0&&dp[i-1][j-p[i]]!=-1) {//花钱通关不需要大于怪兽的武力值，dp[i][j]为-1不影响max函数，不处理
					dp[i][j] = Math.max(dp[i][j], d[i] + dp[i - 1][j - p[i]]);//两种情况取最好的
				}
			}
		}
		for (int i=0;i<=M;i++) {
			if (dp[N-1][i]!=-1){//找到第一个能通关的地方
				res=i;
				break;
			}
		}
		return res;
	}

	/**
	 * dp[i][j]代表从0一路通关到i，刚好凑够j的能力值，所花的最少钱数
	 * @param d 怪兽武力值，>0
	 * @param p 怪兽的贿赂价格,>0
	 * @return 返回最少花多少钱能通关。dp数组最后一行中不等于-1最小的值就是答案
	 */
	public static int dp2(int[] d,int[] p){
		if (d==null||p==null||d.length==0||p.length==0) return -1;
		int N=d.length,M=0,res=Integer.MAX_VALUE;
		for (int i=0;i<d.length;i++) M+=d[i];
		int[][] dp=new int[N][M+1];
		for (int i=0;i<N;i++){
			for (int j=0;j<=M;j++){
				dp[i][j]=-1;
			}
		}
		//dp[0][0]=-1，0能力想通关？没门
		dp[0][d[0]]=p[0];//第一行，只能刚好凑够第1个怪兽的能力
		//第一列，0能力别想通关
		for (int i=1;i<N;i++){
			for (int j=1;j<=M;j++){
				if (dp[i-1][j]!=-1&&j>=d[i]) {//不花钱通关,首先0~i-1要通关,需要武力值大于等于怪兽
					dp[i][j] = dp[i - 1][j];
				}
				if (j-d[i]>=0&&dp[i-1][j-d[i]]!=-1) {//花钱通关不需要大于怪兽的武力值，dp[i][j]为-1影响min函数，需要处理
					dp[i][j] = Math.min(dp[i][j]==-1?Integer.MAX_VALUE:dp[i][j], p[i] + dp[i - 1][j - d[i]]);//两种情况取最好的
				}
			}
		}
		for (int i=0;i<=M;i++) {
			if (dp[N-1][i]!=-1){//找到第一个能通关的地方
				res = Math.min(res, dp[N-1][i]);
			}
		}
		return res;
	}

	public static int[][] generateTwoRandomArray(int len, int value) {
		int size = (int) (Math.random() * len) + 1;
		int[][] arrs = new int[2][size];
		for (int i = 0; i < size; i++) {
			arrs[0][i] = (int) (Math.random() * value) + 1;
			arrs[1][i] = (int) (Math.random() * value) + 1;
		}
		return arrs;
	}

	public static void main(String[] args) {
		int len = 10;
		int value = 20;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			int[][] arrs = generateTwoRandomArray(len, value);
			int[] d = arrs[0];
			int[] p = arrs[1];
			long ans1 = func1(d, p);
//			long ans2 = func2(d, p);
			long ans2 = dp2(d, p);
//			long ans3 = func3(d, p);
			long ans3 = dp1(d, p);
			if (ans1 != ans2 || ans2 != ans3) {
				System.out.println("oops!");
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println(ans3);
				break;
			}
		}



	}

}
