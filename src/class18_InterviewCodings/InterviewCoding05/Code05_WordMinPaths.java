package class18_InterviewCodings.InterviewCoding05;

import java.util.*;

public class Code05_WordMinPaths {
	/**
	 *
	 * 思路：首先把给出的list，变成一张图，里面的每个结点代表string，边代表两个字符串之间差一个字符可以变到
	 * 这个图其实就是一个list的list。图做出来之后，通过一个宽度优先遍历把start到其它结点的最短距离都求出来
	 * 然后用深度优先遍历收集start到end的答案。
	 */

	/**
	 * 1.生成图---List的List
	 * 2.宽度优先遍历把start到图中任何一个结点的距离记到一个HashMap中
	 * 3.广度优先遍历，把所有符合要求的答案收集起来
	 * @param start 开始的字符串
	 * @param end 结束的字符串
	 * @param list 所有的字符串都在list里面，start和end也包括在内。start到end途经的string也在list中
	 * @return 返回start到end的所有最短的路径，是一个List的List
	 */
	public static List<List<String>> getPaths(String start,String end,List<String> list){
		List<List<String>> res=new LinkedList<>();

		return res;
	}

	/**
	 * 生成每个string对应的边的List的方法：先把list中所有的string放到一个set中。假设现在要生成s对应的边的list
	 * 那么就把s[0...N-1]位置上的字符依次改成'a~z'，然后和set里面的比对，如果有那么就加入list中。可以把这个过程
	 * 写在一个方法里面getlist(String s,Set<String> set).
	 * @param list 字符串集
	 * @return 把list中的字符串当成一个一个的结点，只相差一个字符的字符串之间有边。我们把一个字符串能到达的所有的字符串
	 * 	也就是有之间有边的，都放到一个List中，例如abc这个字符串，就对应[abd,abe]等等
	 */
	public static List<List<String>> getMap(List<String> list){
		List<List<String>>	res=new LinkedList<>();//
		Set<String> set=new HashSet<>();//
		for (String s : list) {//生成s对应的边的list，放入res
			char[] str = s.toCharArray();

		}
		return res;
	}

	public static List<String> getList(String s,Set<String> set){

	}
}
