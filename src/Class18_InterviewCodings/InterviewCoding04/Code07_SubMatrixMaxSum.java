package Class18_InterviewCodings.InterviewCoding04;

public class Code07_SubMatrixMaxSum {

	/**
	 * 转化为在0~0 0~1 ... 0~n求最大子数组累加和,1~1,...,1~n最大自数组累加和。
	 * 求0~n-1就是把0~n-1行的数据全部加到一个大小为n的数组arr里面例如。[1,2,3][4,5,6][7,8,9] -->[1+4+7,2+5+8,3+6+9]
	 * 然后0~n加出来的arr，可以被0~n-1的利用，只需要多加一行上去即可
	 * @param m 求m的最大子矩阵累加和，
	 */
	public static int maxSum(int[][] m) {
		if (m==null||m.length==0||m[0]==null||m[0].length==0) return Integer.MIN_VALUE;
		int max=Integer.MIN_VALUE;
		for (int r1 = 0; r1 < m.length; r1++) {
			int[] arr=new int[m[0].length];//有几列，arr就有多大
			for (int r2 = 0; r2 < m.length; r2++) {
				for (int i = 0; i < arr.length; i++) {
					arr[i]+=m[r2][i];//每多一行(r2+1)，就累加到arr上
				}
				max = Math.max(max, f(arr));
			}
		}
		return max;
	}

	/**
	 * @param arr 求arr数组的最大子数组的累加和
	 */
	public static int f(int[] arr){
		if (arr==null||arr.length==0) return Integer.MIN_VALUE;
		int max=0,sum=0;
		for (int i = 0; i < arr.length; i++) {
			sum+=arr[i];
			max = Math.max(max, sum);
			if (sum<0) sum=0;
		}
		return max;
	}

	public static void main(String[] args) {
		int[][] matrix = { { -90, 48, 78 }, { 64, -40, 64 }, { -81, -7, 66 } };
		System.out.println(maxSum(matrix));

	}

}
