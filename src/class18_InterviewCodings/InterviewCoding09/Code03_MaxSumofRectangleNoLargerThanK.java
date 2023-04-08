package class18_InterviewCodings.InterviewCoding09;

import TestUtils.ArrayUtil;

import java.util.TreeSet;

public class Code03_MaxSumofRectangleNoLargerThanK {

	public static int maxSumSubmatrix(int[][] matrix, int k) {
		if (matrix == null||matrix.length==0 || matrix[0] == null||matrix[0].length==0)
			return Integer.MIN_VALUE;
		int row = matrix.length, col = matrix[0].length, res = Integer.MIN_VALUE;
		TreeSet<Integer> sumSet = new TreeSet<>();
		for (int s = 0; s < row; s++) { // s开始行
			int[] colSum = new int[col];
			for (int e = s; e < row; e++) { // e结束行
				// 子矩阵必须包含s~e行的数，且只包含s~e行的数
				sumSet.add(0);
				int rowSum = 0;
				for (int c = 0; c < col; c++) {
					colSum[c] += matrix[e][c];
					rowSum += colSum[c];
					Integer it = sumSet.ceiling(rowSum - k);
					if (it != null) {
						res = Math.max(res, rowSum - it);
					}
					sumSet.add(rowSum);
				}
				sumSet.clear();
			}
		}
		return res;
	}

	public static int maxSumSubmatrix2(int[][] matrix, int k) {
		if (matrix == null||matrix.length==0 || matrix[0] == null||matrix[0].length==0)
			return Integer.MIN_VALUE;
		int row = matrix.length, col = matrix[0].length, res = Integer.MIN_VALUE;
		for (int s = 0; s < row; s++) { // s开始行
			int[] colSum = new int[col];
			for (int e = s; e < row; e++) { // e结束行
				for (int i = 0; i < col; i++) {
					colSum[i]+=matrix[e][i];
				}
				res = Math.max(res, getMaxLessOrEqualK(colSum,k));
			}
		}
		return res;
	}


	// arr -> m
	// m  logM
	public static int getMaxLessOrEqualK(int[] arr, int K) {
		TreeSet<Integer> sums = new TreeSet<>();
		sums.add(0);
		int sum = 0;
		int ans = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			Integer tmp = sums.ceiling(sum - K);	
			if (tmp != null) {
				ans = Math.max(ans, sum - tmp);
			}
			sums.add(sum);
		}
		return ans;
	}


	public static void main(String[] args) {//参数为arr
		ArrayUtil arrayUtil = new ArrayUtil();
		int times = 100000;//测试次数
		boolean isok = true;
		int maxN = 10;//matrix大小在[0~maxN][0~maxM]随机
		int maxM = 10;//matrix大小在[0~maxN][0~maxM]随机
		int maxValue = 200;//matrix的值在[0,maxValue]随机
        int parameter1=0;//测试函数的参数
        int maxParameter1=1000;//参数1在[0,maxParameter1]随机
		int[][] t1 = null;
//        int[][] t2 = null;
		int res1 = 0, res2 = 0;
		for (int i = 0; i < times; i++) {
//            parameter1=arrayUtil.ran(maxParameter1);

			t1 = arrayUtil.generateRandomMatrix(arrayUtil.ran(maxN), arrayUtil.ran(maxM), maxValue);
//            t2 = arrayUtil.generateRandomMatrix(arrayUtil.ran(maxN), arrayUtil.ran(maxM), maxValue);

//            t1=arrayUtil.generateRandomArr(arrayUtil.ran(maxSize),1,maxValue);//正数数组[1,maxValue]
//            t2=arrayUtil.generateRandomArr(arrayUtil.ran(maxSize),1,maxValue);//正数数组[1,maxValue]
			res1 = maxSumSubmatrix(t1,parameter1);
			res2 = maxSumSubmatrix2(t1,parameter1);
			if (res1 != res2) {
				isok = false;
				break;
			}
		}
		arrayUtil.printMatrix(t1);//打印参数
        System.out.println(parameter1);//打印参数
		System.out.println(res1);//针对返回值的操作
		System.out.println(res2);//针对返回值的操作
		System.out.println(isok ? "success" : "fail");
	}

}
