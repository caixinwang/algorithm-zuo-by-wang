package class07_RecursionToDP;

public class Code10_CollectCoinsToAim {

    /**
     * @param amount:硬币的面额
     * @param aim:目标
     * @return :返回的是最少的硬币数
     */
    private static int minCoins(int[] amount, int aim) {
        if (amount == null || amount.length == 0 || aim < 0) {
            return 0;
        }
        return process1(amount, 0, aim);
    }

    /**
     * @param amount:固定参数面额
     * @param index:index之前的硬币已经决定使用多少
     * @param rest:需要用index及其往后的硬币凑成rest
     * @return :返回凑成rest需要的最少硬币
     */
    private static int process1(int[] amount, int index, int rest) {
        if (index == amount.length) {//需要用返回值来区分一个调用是否合法
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; amount[index] * i <= rest; i++) {//i代表amount[index]硬币选了几个
            int next = process1(amount, index + 1, rest - amount[index] * i);
            min = Math.min(next != Integer.MAX_VALUE ? i + next : Integer.MAX_VALUE, min);
        }
        return min;
    }

    private static int minCoinsDp1(int[] amount, int aim) {
        if (amount == null || amount.length == 0 || aim < 0) {
            return 0;
        }
        int N = amount.length;
        int[][] dp = new int[N + 1][aim + 1];//[index][rest]
        for (int rest = 1; rest <= aim; rest++) {//初始化第N行
            dp[N][rest] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int min = Integer.MAX_VALUE;
                for (int i = 0; amount[index] * i <= rest; i++) {//i代表amount[index]硬币选了几个
                    int next = dp[index + 1][rest - amount[index] * i];
                    min = Math.min(next != Integer.MAX_VALUE ? i + next : Integer.MAX_VALUE, min);
                }
                dp[index][rest] = min;
            }
        }
        return dp[0][aim];
    }

    private static int minCoinsDp2(int[] amount, int aim) {
        if (amount == null || amount.length == 0 || aim < 0) {
            return 0;
        }
        int N = amount.length;
        int[][] dp = new int[N + 1][aim + 1];//[index][rest]
        for (int rest = 1; rest <= aim; rest++) {//初始化第N行
            dp[N][rest] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];//一张都不选
                if (rest - amount[index] >= 0 && dp[index][rest - amount[index]] != Integer.MAX_VALUE) {//至少有一张的话利用空间感可以优化
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - amount[index]] + 1);
                }
            }
        }
        return dp[0][aim];
    }


    /**
     *
     * @param amount:面额数组，每种硬币可以使用无数个
     * @param aim:要凑成的目标
     * @return :凑成aim一共有多少种方法
     */
    private static int ways(int[] amount, int aim) {
        if (amount == null || amount.length == 0 || aim < 0) {
            return 0;
        }
        return process2(amount, 0, aim);
    }

    /**
     * @param amount:
     * @param index:
     * @param rest:
     * @return :返回的是有多少种方法凑成rest
     */
    private static int process2(int[] amount, int index, int rest) {
        if (index == amount.length) {
            return rest == 0 ? 1 : 0;
        }
        int sum = 0;
        for (int i = 0; amount[index] * i <= rest; i++) {//i代表amount[index]硬币选了几个
            int next = process2(amount, index + 1, rest - amount[index] * i);
            sum += next;
        }
        return sum;
    }

    private static int waysDp1(int[] amount, int aim) {
        if (amount == null || amount.length == 0 || aim < 0) {
            return 0;
        }
        int N = amount.length;
        int dp[][] = new int[N + 1][aim + 1];
        dp[N][0] = 1;//第N行初始化完毕
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int sum = 0;
                for (int i = 0; amount[index] * i <= rest; i++) {//i代表amount[index]硬币选了几个
                    int next = dp[index + 1][rest - amount[index] * i];
                    sum += next;
                }
                dp[index][rest] = sum;
            }
        }
        return dp[0][aim];
    }

    /**
     * 去for循环的方法：如果至少可以用一个硬币就可以使用规律。如果一个都不用就保留dp[index+1][rest]
     * @param amount:
     * @param aim:
     * @return
     */
    private static int waysDp2(int[] amount, int aim) {
        if (amount == null || amount.length == 0 || aim < 0) {
            return 0;
        }
        int N = amount.length;
        int dp[][] = new int[N + 1][aim + 1];
        dp[N][0] = 1;//第N行初始化完毕
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest]=dp[index+1][rest];
                if (rest-amount[index]>=0){
                    dp[index][rest]+=dp[index][rest-amount[index]];
                }
            }
        }
        return dp[0][aim];
    }


    public static void main(String[] args) {
        int[] amount = {2, 3, 5, 8, 7, 6};
        int aim = 100;
        System.out.println(minCoins(amount, aim));
        System.out.println(minCoinsDp1(amount, aim));
        System.out.println(minCoinsDp2(amount, aim));
        System.out.println("====================");
        System.out.println(ways(amount,aim));
        System.out.println(waysDp1(amount,aim));
        System.out.println(waysDp2(amount,aim));
    }

}
