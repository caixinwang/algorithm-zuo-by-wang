package Class11_KMPandManacher;

public class Code02_Manacher {

    /** 回文半径的大小.12321回文半径为2,4-2=2。
     * pArr[i] = i<=R ? Math.min(pArr[2 * C - i], R - i) : 0;这一句代表了4个分支Parr应该赋值的Parr
     * 1. 当i在R的外面的时候，parr[i]就初始化为0。因为自己到自己的距离为0.
     * 2. 当i在R里面，就可以利用parr数组进行加速，也就是直接把parr[i]设置为parr[i']的值，但是如果i+parr[i']大于R那么就设置为R-i。
     * @param s:返回s的最长回文子串的长度
     * @return :返回最长回文子串的长度，例如12321返回5
     */
    public static int manacher(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = manacherString(s);//垫上#
        int[] parr = new int[str.length];//回文半径数组，用于加速过程
        int C = -1;//和R对应的中心位置
        int R = -1;//最后一个成功的位置
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) {
            parr[i] = i<=R ? Math.min(parr[2 * C - i], R - i) : 0;
            while (i + parr[i]+1 < str.length && i - parr[i]-1 > -1) {
                if (str[i + parr[i]+1] == str[i - parr[i]-1])
                    parr[i]++;
                else {
                    break;//情况23一进while就会从这里出去。可以证明他们不可能再扩了
                }
            }
            if (i + parr[i] > R) {
                R = i + parr[i];
                C = i;
            }
            max = Math.max(max, parr[i]);
        }
        return max;
    }

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {//#永远都是在奇数位置
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
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
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != right(str)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
