package class18_InterviewCodings.InterviewCoding21;

import java.util.HashMap;

public class Code02_LongestConsecutive {

	/**
	 * 使用一个map即可，key为一个数，val为它所在区间的长度。原理像并查集，不区分开头的结尾。区分开头结尾的用两张map
	 * @param arr arr如果排序完之后最长的连续区间的长度
	 * @return -
	 */
	public static int longestConsecutive(int[] arr) {
		if (arr==null||arr.length==0) return 0;
		HashMap<Integer,Integer> map=new HashMap<>();
		int max=Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (!map.containsKey(arr[i])){
				map.put(arr[i],1);
				max=1;
				if (map.containsKey(arr[i]-1)){
					Integer time1=map.get(arr[i]-1);
					Integer time2 = map.get(arr[i]);
					map.put(arr[i]-1,time1+time2);
					map.put(arr[i],time1+time2);
					max = Math.max(max, time1+time2);
				}
				if (map.containsKey(arr[i]+1)){
					Integer time1=map.get(arr[i]+1);
					Integer time2 = map.get(arr[i]);
					map.put(arr[i]+1,time1+time2);
					map.put(arr[i],time1+time2);
					max = Math.max(max, time1+time2);
				}
			}
		}
		return max;
	}


	public static void main(String[] args) {
		int[] arr = { 100, 4, 200, 1, 3, 2,101,201,205,203,202,204 };
		System.out.println(longestConsecutive(arr));
	}

}
