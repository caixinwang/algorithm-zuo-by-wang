package class07_RecursionToDP;

public class Code09_RobotWalk {

    private static int ways(int n, int start, int end, int k){
        if (n<=1||start>n-1||start<0||end>n-1||end<0||k<0){
            return 0;
        }
        return process1(n,start,end,k);
    }

    private static int process1(int n, int cur, int end, int rest) {
        if (rest==0){//base case，上层收集这些1
            return cur==end?1:0;
        }
        int goLeft=cur>0?process1(n, cur-1, end, rest-1):0;
        int goRight=cur<n-1?process1(n, cur+1, end, rest-1):0;
        return goLeft+goRight;
    }

    private static int ways2(int n, int start, int end, int k){
        if (n<=1||start>n-1||start<0||end>n-1||end<0||k<0){
            return 0;
        }
        int [][]dp=new int[n][k+1];//0~n-1  0~k
        for (int i=0;i<dp.length;i++){
            for (int j=0;j<dp[0].length;j++){
                dp[i][j]=-1;
            }
        }
        return process2(n,start,end,k,dp);


    }

    private static int process2(int n, int cur, int end, int rest, int[][] dp) {
        if (rest==0){//base case，上层收集这些1
            return cur==end?1:0;
        }
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int goLeft=cur>0?process1(n, cur-1, end, rest-1):0;
        int goRight=cur<n-1?process1(n, cur+1, end, rest-1):0;
        dp[cur][rest]=goLeft+goRight;
        return goLeft+goRight;
    }

    private static int ways3(int n, int start, int end, int k){
        if (n<=1||start>n-1||start<0||end>n-1||end<0||k<0){
            return 0;
        }
        int dp[][]=new int[n][k+1];//[cur][rest]
        dp[end][0]=1;//rest=0已经初始化
        for (int rest=1;rest<=k;rest++){
            for (int cur=0;cur<n;cur++){
                int goLeft=cur>0?dp[cur-1][rest-1]:0;
                int goRight=cur<n-1?dp[cur+1][rest-1]:0;
                dp[cur][rest]= goLeft+goRight;
            }
        }
        return dp[start][k];
    }

    public static void main(String[] args) {
        System.out.println(ways(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
    }

}
