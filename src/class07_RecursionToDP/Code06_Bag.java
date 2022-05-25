package class07_RecursionToDP;

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

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp1(weights, values, bag));
    }

}
