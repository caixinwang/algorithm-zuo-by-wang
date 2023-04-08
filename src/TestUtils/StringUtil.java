package TestUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StringUtil {


    /**
     *
     * @param listSize 生成一个规模为size的，装着随机String的List
     * @return 返回一个规模为size的，装着随机String的List,且里面的String不重复
     */
    public ArrayList<String> generateRandomStringListNoRepeat(int listSize,int stringSize){
        ArrayList<String> res=new ArrayList<>();
        HashSet<String> set=new HashSet<>();
        int i=0;
        while (i < listSize) {//每次进while，生成一个长度随机的String
            int realLen=(int) (Math.random()*(stringSize+1));//该随机字符串的长度
            String str = generateRandom_a_z_String(realLen);//字符串的字符在a~z随机
//            String str = generateRandom_all_String(realLen);//字符串的字符在a~z随机
            if (!set.contains(str)){
                set.add(str);
                res.add(str);
                i++;//只有生成的字符串不一样的时候才i才++,才把str添加到res
            }
        }
        return res;
    }

    /**
     *
     * @param listSize 生成一个规模为size的，装着随机String的List
     * @return 返回一个规模为size的，装着随机String的List,且里面的String不重复
     */
    public ArrayList<String> generateRandomStringList(int listSize,int stringSize){
        ArrayList<String> res=new ArrayList<>();
        int i=0;
        while (i < listSize) {//每次进while，生成一个长度随机的String
            int realLen=(int) (Math.random()*(stringSize+1));//该随机字符串的长度
            String str = generateRandom_a_z_String(realLen);//字符串的字符在a~z随机
//            String str = generateRandom_all_String(realLen);//字符串的字符在a~z随机
                res.add(str);
                i++;//只有生成的字符串不一样的时候才i才++,才把str添加到res
        }
        return res;
    }

    public String[] generateRandomStringArr(int arrSize,int stringSize){
        String[] res=new String[arrSize];
        for (int i = 0; i < res.length; i++) {
            res[i]=generateRandom_a_z_String(stringSize);
        }
        return res;
    }

    public String[] generateRandomStringArrNoRepeat(int arrSize,int stringSize){
        String[] res=new String[arrSize];
        ArrayList<String> strings = generateRandomStringListNoRepeat(arrSize,stringSize);
        int i=0;
        for (String string : strings) {
            res[i++]=string;
        }
        return res;
    }

    /**
     *
     * @param size 生成大小为size的随机字符串
     * @return 大小为size的随机字符串,字符在a~z随机，都是小写字母
     */
    public String generateRandom_a_z_String(int size){
        StringBuilder builder=new StringBuilder();
        for (int j = 0; j < size; j++) {//用随机字符拼出一个随机字符串
            int ran=(int) ran('z'-'a');//[0,25]随机，共26个字母
            char c=(char) (ran+'a');//[a,z]
//                int ran=(int) (Math.random()*(127+1));//[0,127]随机，共128个字符
//                char c=(char) ran;
            builder.append(c);
        }
        return builder.toString();
    }

    /**
     *
     * @param size 生成大小为size的随机字符串
     * @return 大小为size的随机字符串,字符在0~127的ASCII中随机
     */
    public String generateRandom_all_String(int size){
        StringBuilder builder=new StringBuilder();
        for (int j = 0; j < size; j++) {//用随机字符拼出一个随机字符串
                int ran=ran(127);//[0,127]随机，共128个字符
                char c=(char) ran;
            builder.append(c);
        }
        return builder.toString();
    }

    public void printList(List<String> list){
        if (list==null||list.size()==0) return;
        for (String s : list) {
            System.out.print(s+ " ");
        }
        System.out.println();
    }

    public void printArr(String[] list){
        if (list==null||list.length==0) return;
        for (String s : list) {
            System.out.print(s+ " ");
        }
        System.out.println();
    }

    public int ran(int max){//[0,max]
        return (int) (Math.random()*(max+1));
    }

    public int ran(int l,int r){//[l,r]
        return l+ran(r-l);
    }


}
