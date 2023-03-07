package class18_InterviewCodings.InterviewCoding02;

public class Code01_FindNumInSortedMatrix {
	/**
	 * 从右上角或者左下角开始。因为这两个地方无论是小于还是大于k都只有一个方向可以选择。但是如果是左上角选择就不确定
	 * @param matrix 从左到右，从上到下的有序的矩阵
	 * @param K 要查找的数
	 * @return 如果matrix中有K，返回true
	 */
	public static boolean isContains(int[][] matrix, int K) {
		int row = 0,col = matrix[0].length - 1;//从右上角开始走
		while (row < matrix.length && col > -1) {
			if (matrix[row][col] == K) {
				return true;
			} else if (matrix[row][col] > K) {
				col--;
			} else {
				row++;
			}
		}
		return false;
	}

	public static boolean isContains2(int[][] matrix, int K) {
		int l=1,r= matrix.length*matrix[0].length,mid;
		while(l<=r){
			mid=(r+l)>>1;
			int value=get(matrix,mid);
			if (value==K) return true;
			if (value>K) r=mid-1;
			else l=mid+1;
		}
		return false;
	}

	private static int get(int[][] matrix , int index){//这里的index从1开始记
		int a= matrix.length;//行
		int b=matrix[0].length;//列
		if (index>=a*b) throw new RuntimeException();
		int row=index/b;
		int col=index%b;
		if (index%b==0){
			row--;
			col=matrix[0].length;
		}
		return matrix[row][col-1];
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 0, 1, 2, 3, 4, 5, 6 },// 0
				{ 10, 12, 13, 15, 16, 17, 18 },// 1
				{ 23, 24, 25, 26, 27, 28, 29 },// 2
				{ 44, 45, 46, 47, 48, 49, 50 },// 3
				{ 65, 66, 67, 68, 69, 70, 71 },// 4
				{ 96, 97, 98, 99, 100, 111, 122 },// 5
				{ 166, 176, 186, 187, 190, 195, 200 },// 6
				{ 233, 243, 321, 341, 356, 370, 380 } // 7
		};
		int K = 233;
		System.out.println(isContains(matrix, K));
		System.out.println(isContains2(matrix, K));
	}

}
