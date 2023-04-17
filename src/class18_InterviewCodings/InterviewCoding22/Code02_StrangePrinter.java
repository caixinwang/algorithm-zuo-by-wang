package class18_InterviewCodings.InterviewCoding22;

public class Code02_StrangePrinter {

	public static int strangePrinter(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int[][] dp = new int[N][N];
		dp[N - 1][N - 1] = 1;
		for (int i = 0; i < N - 1; i++) {
			dp[i][i] = 1;
			dp[i][i + 1] = str[i] == str[i + 1] ? 1 : 2;
		}
		for (int L = N - 3; L >= 0; L--) {
			for (int R = L + 2; R < N; R++) {

				// L....R

				dp[L][R] = R - L + 1;

				// L...k-1 k...R
				for (int k = L + 1; k <= R; k++) {
					dp[L][R] = Math.min(dp[L][R], dp[L][k - 1] + dp[k][R] - (str[L] == str[k] ? 1 : 0));
				}
			}
		}
		return dp[0][N - 1];
	}

	public static int strangePrinte2(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] chars = s.toCharArray();
		return process(chars,0,s.length()-1);
	}
	
	private static int process(char[] str,int L,int R) {
		if (L==R) return 1;
		int ans=Integer.MAX_VALUE;
		for (int i=L;i<R;i++){
			ans = Math.min(ans, process(str,L,i)+process(str,i+1,R)-(str[i+1]==str[L]?1:0));
		}
		return ans;
	}

	public static void main(String[] args) {
		String s="aadasds";
		System.out.println(strangePrinter(s));
		System.out.println(strangePrinte2(s));
	}

}
