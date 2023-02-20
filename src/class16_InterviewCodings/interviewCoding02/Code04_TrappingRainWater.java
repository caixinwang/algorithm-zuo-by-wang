package class16_InterviewCodings.interviewCoding02;

public class Code04_TrappingRainWater {

	/*
	 * 给定一个正整数数组arr，把arr想象成一个直方图。返回这个直方图如果装水，能装下几格水？
	 * 比如，r={3,1,2,5,2,4}根据值画出的直方图就是容器形状，该容器可以装下5格水
	 */

	public static int water1(int[] arr) {//不用辅助数组
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		int water = 0;
		for (int i = 1; i < N - 1; i++) {
			int leftMax = Integer.MIN_VALUE;
			for (int j = 0; j < i; j++) {
				leftMax = Math.max(leftMax, arr[j]);
			}
			int rightMax = Integer.MIN_VALUE;
			for (int j = i + 1; j < N; j++) {
				rightMax = Math.max(rightMax, arr[j]);
			}
			water += Math.max(Math.min(leftMax, rightMax) - arr[i], 0);
		}
		return water;
	}

	public static int water2(int[] arr) {//用两个辅助数组
		if (arr == null || arr.length <= 2) return 0;
		int res=0,N=arr.length,tl=Integer.MIN_VALUE,tr=Integer.MIN_VALUE;
		int[] lm=new int[N],rm=new int[N];
		for (int i = 0; i < N; i++) {
			tl = Math.max(tl, arr[i]);
			tr = Math.max(tr, arr[N-1-i]);
			lm[i]=tl;
			rm[N-1-i]=tr;
		}
		for (int i = 1; i < N-1; i++) {
			res+=Math.max(0,Math.min(lm[i-1],rm[i+1])-arr[i]);
		}
		return res;
	}

	public static int water3(int[] arr) {//用一个辅助数组
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		int[] rightMaxs = new int[N];
		rightMaxs[N - 1] = arr[N - 1];
		for (int i = N - 2; i >= 0; i--) {
			rightMaxs[i] = Math.max(rightMaxs[i + 1], arr[i]);
		}
		int water = 0;
		int leftMax = arr[0];
		for (int i = 1; i < N - 1; i++) {
			water += Math.max(Math.min(leftMax, rightMaxs[i + 1]) - arr[i], 0);
			leftMax = Math.max(leftMax, arr[i]);
		}
		return water;
	}

	public static int water4(int[] arr) {//不用辅助数组
		if (arr==null||arr.length<=2)return 0;
		int res=0,l=1,r=arr.length-2,lm=arr[0],rm=arr[arr.length-1];
		while(l<=r){
			if (lm<=rm){//更新L位置
				lm = Math.max(lm, arr[l]);
				res+= Math.max(lm-arr[l++], 0);
			}else {//更新R位置
				rm = Math.max(rm, arr[r]);
				res+= Math.max(rm-arr[r--], 0);
			}
		}
		return res;
	}

	// for test
	public static int[] generateRandomArray(int len, int value) {
		int[] ans = new int[(int) (Math.random() * len) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (int) (Math.random() * value) + 1;
		}
		return ans;
	}

	public static void main(String[] args) {
		int len = 100;
		int value = 200;
		int testTimes = 10;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(len, value);
			int ans1 = water1(arr);
			int ans2 = water2(arr);
			int ans3 = water3(arr);
			int ans4 = water4(arr);
			if (ans1 != ans2 || ans3 != ans4 || ans1 != ans3) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
