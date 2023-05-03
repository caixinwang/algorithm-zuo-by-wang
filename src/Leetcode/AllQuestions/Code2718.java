package Leetcode.AllQuestions;

public class Code2718 {
    public static int lengthOfLongestSubstring(String s) {//滑动窗口、动态规划也能解
        char[] chars = s.toCharArray();
        int l=0;
        int r=-1;//[l,r]
        int max=0;
        boolean[] isExist=new boolean[128];
        while(r<s.length()-1){
            if (!isExist[chars[r+1]]){//不是子串的一部分
                isExist[chars[r+1]]=true;
                r++;
                max=Math.max(max,r-l+1);
            }else {//是子串的一部分,窗口左边界移动，窗口缩小
                isExist[chars[l]]=false;
                l++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
