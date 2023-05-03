package Class18_InterviewCodings.InterviewCoding14;

import TestUtils.ArrayUtil;

import java.util.Arrays;

public class Code04_ShuffleProblem {

	public static void shuffle(int[] arr) {
		if (arr != null && arr.length != 0 && (arr.length & 1) == 0) {
			shuffle(arr, 0, arr.length - 1);
		}
	}

	private static void shuffle(int[] arr, int l, int r) {
		while(l<r){
			int len=r-l+1;
			int base=1;
			int k=0;//base=3^k
			while(3*base-1<=len){//找到最接近len的base-1,条件就是3*base-1，这样出while的时候base就是最近的状态，注意，这是<=
				base*=3;
				k++;
			}
			int mid=l+r>>1;
			int half=base-1>>1;//base-1这个长度是要去下标循环怼的,搞出一半来
			rotate(arr,l+half,mid+half,(mid)-(l+half)+1);//画图去观察
			cycle(arr,l,l+base-1-1,k);
			l=l+base-1;
		}
	}

	/**
	 *
	 * @param arr 在数组[l,r]范围上做下标循环怼，怼几轮呢，开始点从3^0到3^(k-1)---从1开始，注意转换关系
	 * @param l -
	 * @param r -
	 * @param k 数组的长度告诉你为(3^k)-1，直接传进来的，省得重复计算，shuffle函数里面会有计算的步骤的
	 */
	private static void cycle(int[] arr,int l,int r,int k){//长度为(3^k)-1
		for (int i = 0,base=1; i < k; i++,base*=3) {//循环怼k轮
			int start=l+(base-1);
			int preVal=arr[start];
			int p=getIndex(l,r,start);//从第二个位置开始
			while(p!=start){
				int t=arr[p];
				arr[p]=preVal;
				preVal=t;
				p=getIndex(l,r,p);
			}
			arr[p]=preVal;//开头位置处理一下
		}
	}

	//arr[l...r]范围上前k个和后面几个对换整体的顺序
	//[1,2,3,4,5,6]调用k=2,==> [3,4,5,6,1,2]
	private static void rotate(int[] arr,int l,int r,int k){
		reverse(arr,l,l+k-1);
		reverse(arr,l+k,r);
		reverse(arr,l,r);
	}

	private static void reverse(int[] arr,int l,int r){
		while(l<r){
			int t=arr[l];
			arr[l]=arr[r];
			arr[r]=t;
			l++;
			r--;
		}
	}


	/**
	 * @param s|e  数组范围假设为[s,e],长度必须为偶数
	 * @param i 计算出第i位置的数应该跳转到哪,i∈[s,e]
	 * @return 返回如果在arr[s...e]做完美洗牌，下标在i位置的数应该跳转到哪
	 */
	public static int getIndex(int s,int e,int i){
		int N=e-s+1>>1;
		if (i>s+e>>1){//在右半区
			return s+((i-s-N)<<1);
		}else {//在左半区
			return s+((i-s<<1)+1);
		}
	}


	static ArrayUtil arrayUtil=new ArrayUtil();
	public static void test1(){
		int[] arr=new int[]{1,2,3,4,5,6,7,8};
//		reverse(arr,1,5);
		rotate(arr,1,7,3);
		arrayUtil.printArr(arr);
	}
	public static void test2(){
		int[] arr=new int[]{1,2,3,4,5,6,7,8};
		cycle(arr,0,7,2);
		arrayUtil.printArr(arr);
	}
	public static void test3(){
		for (int i = 0; i < 50; i++) {
			int[] arr = generateArray();
			Arrays.sort(arr);
			arrayUtil.printArr(arr);
			wiggleSort(arr);
			arrayUtil.printArr(arr);
			System.out.println("==============================");
			if (!isValidWiggle(arr)) {
				System.out.println("ooops!");
				printArray(arr);
				break;
			}
		}
	}

	public static void wiggleSort(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		// 假设这个排序是额外空间复杂度O(1)的，当然系统提供的排序并不是，你可以自己实现一个堆排序
		Arrays.sort(arr);
		if ((arr.length & 1) == 1) {
			shuffle(arr, 1, arr.length - 1);
		} else {
			shuffle(arr, 0, arr.length - 1);
			for (int i = 0; i < arr.length; i += 2) {
				int tmp = arr[i];
				arr[i] = arr[i + 1];
				arr[i + 1] = tmp;
			}
		}
	}

	// for test
	public static boolean isValidWiggle(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			if ((i & 1) == 1 && arr[i] < arr[i - 1]) {
				return false;
			}
			if ((i & 1) == 0 && arr[i] > arr[i - 1]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static int[] generateArray() {
		int len = (int) (Math.random() * 10) * 2;
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * 100);
		}
		return arr;
	}
	public static void main(String[] args) {
//		test1();
//		test2();
		test3();

	}
}
