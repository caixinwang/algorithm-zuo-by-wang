package Leetcode;

import java.util.HashMap;

public class Code0003 {//无重复字符的最长子串

    /**
     * dp[i]为必须以i结尾不含重复字符的最长子串
     * 分两种情况，第一种i这个字符出现在dp[i-1]的子串内部--比不过i不参与。第二种出现在子串外部，dp[i]=dp[i-1]+1
     */
    public int lengthOfLongestSubstring(String s) {//动态规划
        if (s==null||s.length()==0) return 0;
        int res=0;
        char[] str = s.toCharArray();
        int N=str.length;
        int[]dp=new int[N];
        HashMap<Character,Integer> map=new HashMap<>();//记录一个字符最早出现的位置
        dp[0]=1;
        map.put(str[0],0);
        for (int i=1;i<N;i++){
            if (!map.containsKey(str[i])){
                dp[i]=dp[i-1]+1;
            }else {
                int recent=map.get(str[i]);
                if (recent<=i-1-dp[i-1]){//最近出现的位置在dp[i-1]外面
                    dp[i] = dp[i-1]+1;
                }else {//出现在dp[i-1]那个子串的内部
                    dp[i]=i-recent;//recent是我不要的
                }
            }
            map.put(str[i],i);
        }
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public int lengthOfLongestSubstring2(String s) {//滑动窗口
        if (s==null||s.length()==0) return 0;
        int res=0;
        char[] str = s.toCharArray();
        int N=str.length;
        boolean[] isExist=new boolean[128];
        int l=0;
        int r=0;
        while(r<N){
            if (!isExist[str[r]]){//子串还没有包含r位置的字符，扩大窗口
                isExist[str[r++]]=true;
                res = Math.max(res, r-l);
            }else {//子串已经包含了r位置的字符，缩小窗口
                isExist[str[l++]]=false;
            }
        }
        return res;
    }


}
