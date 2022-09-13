package Class11_KMPandManacher;

public class Code01_KMP {
    private static int kmp(String str, String match) {
        if (str==null||match==null||match.length()<1||str.length()<match.length()) return -1;
        char[] s1 = str.toCharArray();
        char[] s2 = match.toCharArray();
        int[] next=getNext(match);
        int p1=0;
        int p2=0;
        while(p1!=str.length()&&p2!=match.length()){//不越界
            if (s1[p1]==s2[p2]){
                p1++;
                p2++;
            }else if (p2!=0){//p2还可以回退
                p2=next[p2];
            }else{//p2==0,回退不了了，说明p1现在这个位置不对
                p1++;
            }
        }
        return p2==match.length()?p1-p2:-1;
    }

    private static int[] getNext(String match) {
        if (match.length() == 1) return new int[]{-1};
        char[] chars = match.toCharArray();
        int[] next = new int[match.length()];
        next[0] = -1;
        next[1] = 0;
        int i=2;
        int p=next[1];//当前要从2位置开始填，所以p要等于next[2-1]
        while(i!=match.length()){
            if (chars[i-1]==chars[p]){//i-1位置和p位置相等,
                next[i++]=++p;//长度是i-1位置长度+1，p即代表要比较的位置，也代表前缀子串的长度
            }else if (p==0){//p前面无路可跳
                next[i++]=0;
            }else {//p前面还有路可以跳
                p=next[p];
            }
        }
        return next;
    }

    private static int baoli(String str,String match){
        if (str==null||match==null||match.length()<1||str.length()<match.length()) return -1;
        char[] s1 = str.toCharArray();
        char[] s2 = match.toCharArray();
        int p1=0;
        int p2=0;
        for (int i = 0; i < str.length(); i++) {
            p1=i;
            p2=0;
            while(p1!=str.length()&&p2!=match.length()){
                if (s1[p1]==s2[p2]){
                    p1++;
                    p2++;
                }else
                    break;
            }
            if (p2==match.length())return p1-p2;
        }
        return -1;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (kmp(str, match) != baoli(str,match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }


}
