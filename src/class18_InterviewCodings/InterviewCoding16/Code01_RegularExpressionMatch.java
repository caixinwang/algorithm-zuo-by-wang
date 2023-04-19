package class18_InterviewCodings.InterviewCoding16;

public class Code01_RegularExpressionMatch {

	public static boolean isValid(char[] s, char[] e) {
		// s中不能有'.'  or  '*'
		for (int i = 0; i < s.length; i++) {
			if (s[i] == '*' || s[i] == '.') {
				return false;
			}
		}
		// 开头的e[0]不能是'*'，没有相邻的'*'
		for (int i = 0; i < e.length; i++) {
			if (e[i] == '*' && (i == 0 || e[i - 1] == '*')) {
				return false;
			}
		}
		return true;
	}

	public static boolean isMatch(String str, String exp) {
		if (str == null || exp == null) {
			return false;
		}
		char[] s = str.toCharArray();
		char[] e = exp.toCharArray();
		return isValid(s, e) && process(s, e, 0, 0);
	}

	// e[ei....]  能否变成  s[si...]
	// 重要限制：e[ei]不能压中'*'
	public static boolean process(char[] s, char[] e, int si, int ei) {
		if (ei==e.length) return si==s.length;
		if (ei==e.length-1||e[ei+1]!='*'){//ei位置后面不是*，e的最后位置也认为是后面不为*的情况
			//si要有东西，并且要和ei匹配
			return si!=s.length&&(e[ei]==s[si]||e[ei]=='.')&&process(s,e,si+1,ei+1);
		}else {//ei后面是星
			boolean res=false;
			res|=process(s,e,si,ei+2);// x* 组合拳不解决任何东西
			while(si!=s.length&&(e[ei]==s[si]||e[ei]=='.')){
				res|=process(s,e,++si,ei+2);
			}
			return res;
		}
	}

	public static boolean isMatch2(String str, String exp) {
		if (str == null || exp == null) {
			return false;
		}
		char[] s = str.toCharArray();
		char[] e = exp.toCharArray();
		Boolean[][] booleans=new Boolean[s.length+1][e.length+1];
		return isValid(s, e) && process2(s, e, 0, 0,booleans);
	}

	// e[ei....]  能否变成  s[si...]
	// 重要限制：e[ei]不能压中'*'
	public static boolean process2(char[] s, char[] e, int si, int ei,Boolean[][] dp) {
		if (dp[si][ei]!=null) return dp[si][ei];
		if (ei==e.length) {
			dp[si][ei]=si == s.length;
			return dp[si][ei];
		}
		if (ei==e.length-1||e[ei+1]!='*'){//ei位置后面不是*，e的最后位置也认为是后面不为*的情况
			//si要有东西，并且要和ei匹配
			dp[si][ei]=si!=s.length&&(e[ei]==s[si]||e[ei]=='.')&&process2(s,e,si+1,ei+1,dp);
			return dp[si][ei];
		}else {//ei后面是星
			boolean res=false;
			res|=process2(s,e,si,ei+2,dp);// x* 组合拳不解决任何东西
			while(si!=s.length&&(e[ei]==s[si]||e[ei]=='.')){
				res|=process2(s,e,++si,ei+2,dp);
			}
			dp[si][ei]=res;
			return dp[si][ei];
		}
	}

	public static boolean isMatchDP(String str, String exp) {
		if (str == null || exp == null) {
			return false;
		}
		char[] s = str.toCharArray();
		char[] e = exp.toCharArray();
		if (!isValid(s, e)) {
			return false;
		}
		// initDPMap  做出一张表，而且填好倒数两列，和最后一行
		boolean[][] dp = initDPMap(s, e);
		for (int i = s.length - 1; i > -1; i--) {
			for (int j = e.length - 2; j > -1; j--) {
				if (e[j + 1] != '*') {
					dp[i][j] = (s[i] == e[j] || e[j] == '.') && dp[i + 1][j + 1];
				} else {
					int si = i;
					while (si != s.length && (s[si] == e[j] || e[j] == '.')) {
						if (dp[si][j + 2]) {
							dp[i][j] = true;
							break;
						}
						si++;
					}
					if (dp[i][j] != true) {
						dp[i][j] = dp[si][j + 2];
					}
				}
			}
		}
		return dp[0][0];
	}

	public static boolean[][] initDPMap(char[] s, char[] e) {
		int slen = s.length;
		int elen = e.length;
		boolean[][] dp = new boolean[slen + 1][elen + 1];
		dp[slen][elen]=true;//dp[i][elen]
		if (s[slen-1]==e[elen-1]||e[elen-1]=='.') dp[slen-1][elen-1]=true;//dp[i][elen-1]
		//dp[slen][i]
		for (int i = elen-1;i>=0; i-=2) {
			if (e[i]=='*') dp[slen][i]=true;
			else break;
		}
		return dp;
	}

	public static void main(String[] args) {
		String str = "abcccdefgee";
		String exp = "ab.*d.*e.*.";
		System.out.println(isMatch(str, exp));
		System.out.println(isMatchDP(str, exp));

	}

}
