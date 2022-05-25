package class07_RecursionToDP;

public class Code05_ConvertToLetterString {

    private static int number(String str){
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] s=str.toCharArray();
        return process1(s,0);
    }

    /**
     *
     * @param s:固定参数，参与的数字串
     * @param index:s数组0~index-1范围已经决定好。现在要返回从index~len-1范围上组织有多少方式
     * @return
     */
    private static int process1(char[] s, int index) {
        if (index == s.length) {//base case，从最底层的调用返回1给上层收集
            return 1;
        }
        if (s[index]==0){
            return 0;
        }
        int ways=process1(s,index+1);
        if (index+2<=s.length&&(s[index] - 'a') * 10 + s[index + 1] - 'a' <= 26) {
            ways+=process1(s,index+2);
        }
        return ways;
    }


    public static void main(String[] args) {
        System.out.println(number("123"));
    }

}
