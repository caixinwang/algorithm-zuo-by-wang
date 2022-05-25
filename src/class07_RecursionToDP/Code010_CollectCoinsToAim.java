package class07_RecursionToDP;

public class Code010_CollectCoinsToAim {

    /**
     *
     * @param amount:硬币的面额
     * @param aim:目标
     * @return :返回的是最少的硬币数
     */
    private static int minCoins(int[] amount,int aim){
        if (amount==null||amount.length==0||aim<0){
            return 0;
        }
        return process1(amount,0,aim);
    }

    /**
     *
     * @param amount:固定参数面额
     * @param index:index之前的硬币已经决定使用多少
     * @param rest:需要用index及其往后的硬币凑成rest
     * @return :返回凑成rest需要的最少硬币
     */
    private static int process1(int[] amount, int index, int rest) {
        if (index== amount.length){//需要用返回值来区分一个调用是否合法
            return rest==0?0:Integer.MAX_VALUE;
        }
        int min=Integer.MAX_VALUE;
        for (int i=0;amount[index]*i<=rest;i++){//i代表amount[index]硬币选了几个
            int next=process1(amount, index+1, rest-amount[index]*i);
            min=Math.min(next!= Integer.MAX_VALUE?i+next: Integer.MAX_VALUE,min);
        }
        return min;
    }

    public static void main(String[] args) {
        int[] amount={1,2,3,4,5,6,7};
        int aim=50;
        System.out.println(minCoins(amount,aim));
    }

}
