package Class07_RecursionToDP;

public class Code06_Bag {

    private static int maxValue(int[] weight, int[] value, int bag) {
        if (weight == null || value == null || value.length == 0 || weight.length == 0 || bag <= 0) {
            return 0;
        }
        return process1(weight, value, 0, bag);
    }

    /**
     * @param weight:固定参数，代表货物的重量，数组的长度代表货物的种类，数组的长度一定和value数组的长度一样
     * @param value:固定参数，代表货物的价值
     * @param index:代表index之前的货物已经做过选择了。
     * @param rest:还剩下多少空间可以装载货物
     * @return :返回的是rest背包种类，对从index下标开始货物做选择能得到的最大价值
     */
    private static int process1(int[] weight, int[] value, int index, int rest) {
        if (index == weight.length) {//base case 已经没有货物可以选了
            return 0;
        }
        int p1 = process1(weight, value, index + 1, rest);//不选index位置的货物所能得到的最大值
        int p2 = rest - weight[index] >= 0 ?
                process1(weight, value, index + 1, rest - weight[index]) + value[index] : 0;
        return Math.max(p1, p2);
    }

    private static int dp1(int[] weight, int[] value,int bag){
        if (weight == null || value == null || value.length == 0 || weight.length == 0 || bag <= 0) {
            return 0;
        }
        int[][] dp=new int[weight.length+1][bag+1];
        //weight.len行默认就是0，已经初始化完毕
        for (int index=weight.length-1;index>=0;index--){//index
            for (int rest=0;rest<=bag;rest++){//bag
                dp[index][rest]=Math.max(dp[index+1][rest],
                        rest - weight[index] >= 0 ? dp[index + 1][ rest - weight[index]] + value[index] : 0);
            }
        }
        return dp[0][bag];
    }

    /** 直接憋动态规划
     * dp[i][j]代表0~i自由选择，背包大小为j所能达到的最大价值。---背包不一定装满j
     * @param weight:固定参数，代表货物的重量，数组的长度代表货物的种类，数组的长度一定和value数组的长度一样
     * @param value:固定参数，代表货物的价值
     * @return :返回bag大小的背包所能装到的最大价值
     */
    public static int dp2(int[] weight, int[] value,int bag){
        if (weight == null || value == null || value.length == 0 || weight.length == 0 || bag <= 0) {
            return 0;
        }
        int N=weight.length;
        int[][] dp=new int[N][bag+1];//dp[0][0]=0
        for (int i = 0; i <= bag; i++) {//第一行dp[0][i]
            if (i>=weight[0]) dp[0][i]=value[0];
        }
        //第一列都是0
        for (int i=1;i<N;i++){
            for (int j=1;j<=bag;j++){
                dp[i][j]=dp[i-1][j];
                if (j-weight[i]>=0) dp[i][j] = Math.max(dp[i][j], value[i]+dp[i-1][j-weight[i]]);
            }
        }
        return dp[N-1][bag];
    }

    /** 直接憋动态规划
     * 改一改可以解决种数的问题
     * dp[i][j]代表0~i自由选择，刚好装满了j的背包大小，所能达到的最大价值。---背包一定装满j
     * @param weight:固定参数，代表货物的重量，数组的长度代表货物的种类，数组的长度一定和value数组的长度一样
     * @param value:固定参数，代表货物的价值
     * @return :返回bag大小的背包所能装到的最大价值
     */
    public static int dp3(int[] weight, int[] value,int bag){
        if (weight == null || value == null || value.length == 0 || weight.length == 0 || bag <= 0) {
            return 0;
        }
        int N=weight.length,res=Integer.MIN_VALUE;
        int[][] dp=new int[N][bag+1];
        for (int i = 0; i < N; i++) {
            for (int j=0;j<=bag;j++){
                dp[i][j]=-1;//-1代表没有办法刚好装满bag
            }
        }
        dp[0][0]=0;
        if (weight[0]<=bag) dp[0][weight[0]]=value[0];//第一行
        for (int i = 1; i < N; i++) {//第一列
            dp[i][0]=0;
        }
        for (int i=1;i<N;i++){
            for (int j=1;j<=bag;j++){
                if (dp[i-1][j]!=-1)dp[i][j]=dp[i-1][j];//i不选就取决于上面的，上面行你就行，不行你就不行
                if (j-weight[i]>=0&&dp[i-1][j-weight[i]]!=-1) //-1不影响取最大值，不用管
                    dp[i][j] = Math.max(dp[i][j], value[i]+dp[i-1][j-weight[i]]);
            }
        }
        for (int i = 0; i <=bag; i++) {
            if (dp[N-1][i]!=-1)res = Math.max(res, dp[N-1][i]);
        }
        return res;
    }

    /** 直接憋动态规划
     * dp[i][j]代表0~i自由选择，背包里面物品价值刚好到达了j，最少使用多少背包空间。---背包物品价值一定刚好为j
     * @param weight:固定参数，代表货物的重量，数组的长度代表货物的种类，数组的长度一定和value数组的长度一样
     * @param value:固定参数，代表货物的价值
     * @return :返回bag大小的背包所能装到的最大价值
     */
    public static int dp4(int[] weight, int[] value,int bag){
        if (weight == null || value == null || value.length == 0 || weight.length == 0 || bag <= 0) {
            return 0;
        }
        int N=weight.length,res=Integer.MIN_VALUE,valSum=0;
        for (int i = 0; i < value.length; i++) valSum+=value[i];
        int[][] dp=new int[N][valSum+1];
        for (int i = 0; i < N; i++) {
            for (int j=0;j<=valSum;j++){
                dp[i][j]=-1;//代表凑不成j元
            }
        }
        dp[0][0]=0;
        dp[0][value[0]]=weight[0];//第一行
        for (int i = 1; i < N; i++) {//第一列
            dp[i][0]=0;
        }
        for (int i=1;i<N;i++){
            for (int j=1;j<=valSum;j++){
                if (dp[i-1][j]!=-1) dp[i][j] = dp[i - 1][j];
                if (j-value[i]>=0&&dp[i-1][j-value[i]]!=-1){//-1会影响到这个min，需要变成一个不影响的
                    dp[i][j] = Math.min(dp[i][j]==-1?Integer.MAX_VALUE:dp[i][j],weight[i]+dp[i-1][j-value[i]]);
                }
            }
        }
        for (int i = 0; i <=valSum; i++) {
            if (dp[N-1][i]!=-1&&bag>=dp[N-1][i]) res=i;
        }
        return res;
    }

    public static void printArr(int[][] arr){
        if (arr==null||arr.length==0)return;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j]==Integer.MAX_VALUE){
                    System.out.print("x ");
                }else {
                    System.out.print(arr[i][j]+" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp1(weights, values, bag));
        System.out.println(dp2(weights, values, bag));
        System.out.println(dp3(weights, values, bag));
        System.out.println(dp4(weights, values, bag));
    }

}
