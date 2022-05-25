package class07_RecursionToDP;

import java.util.HashSet;
import java.util.LinkedList;

public class Code03_PrintAllSubsequences {

    private static LinkedList<String> subs(String str){
        char[] arr=str.toCharArray();
        String path="";
        LinkedList<String> list=new LinkedList<>();
        process(arr,0,list,path);
        return list;
    }

    /**
     * 在index位置分两种情况，一种是要了index位置，一种是不要index位置。
     * @param chars:这是一个固定参数
     * @param index:从index位置开始把后面的决定做完。0~index-1位置已经做好了决定
     * @param res:不需要返回值，我们把决定的结果放到res中
     * @param path:记录之前做决定的状态
     */
    private static void process(char[] chars, int index, LinkedList<String> res, String path) {
        if (index==chars.length){//base case,已经把决定做好了，直接加入到res中，返回
            res.add(path);
            return;
        }
        String yes=path+ chars[index];
        process(chars,index+1,res,yes);
        String no=path;
        process(chars,index+1,res,no);
    }

    private static LinkedList<String> subsNoRepeat(String str){
        char[] arr=str.toCharArray();
        LinkedList<String> list=new LinkedList<>();
        String path="";
        HashSet<String> set=new HashSet<>();//去重
        process2(arr,0,set,path);
        for(String s :set){
            list.add(s);
        }
        return list;
    }

    private static void process2(char[] chars, int index, HashSet<String>res, String path) {
        if (index==chars.length){//base case,已经把决定做好了，直接加入到res中，返回
            res.add(path);
            return;
        }
        String yes=path+ chars[index];
        process2(chars,index+1,res,yes);
        String no=path;
        process2(chars,index+1,res,no);
    }

    public static void main(String[] args) {
        String  str="aaa";
        for (String s:subsNoRepeat(str)){
            System.out.println(s);
        }
        System.out.println("===================");
        for (String s:subs(str)){
            System.out.println(s);
        }
        System.out.println("===================");

    }

}
