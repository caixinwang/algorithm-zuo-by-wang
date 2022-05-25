package class07_RecursionToDP;

public class Code01_Hanoi {

    private static void Hanoi1(int n){
        LeftToRight(n);
    }

    private static void LeftToRight(int n){
        if (n==1){
            System.out.println("left to right");
            return;
        }
        LeftToMid(n-1);
        System.out.println("left to right");
        MidToRight(n-1);
    }

    private static void MidToRight(int n) {
        if (n==1){
            System.out.println("mid to right");
            return;
        }
        MidToLeft(n-1);
        System.out.println("mid to right");
        LeftToRight(n-1);
    }

    private static void MidToLeft(int n) {
        if (n==1){
            System.out.println("mid to left");
            return;
        }
        MidToRight(n-1);
        System.out.println("mid to left");
        RightToLeft(n-1);
    }

    private static void LeftToMid(int n) {
        if (n==1){
            System.out.println("left to mid");
            return;
        }
        LeftToRight(n-1);
        System.out.println("left to mid");
        RightToMid(n-1);
    }

    private static void RightToMid(int n) {
        if (n==1){
            System.out.println("right to mid");
            return;
        }
        RightToLeft(n-1);
        System.out.println("right to mid");
        LeftToMid(n-1);
    }

    private static void RightToLeft(int n) {
        if (n==1){
            System.out.println("right to left");
            return;
        }
        RightToMid(n-1);
        System.out.println("right to mid");
        MidToLeft(n-1);
    }
    //==========================================================================

    private static void Hanoi2(int  n){
        process(n,"left","right","mid");
    }

    private static void  process(int n,String from,String to,String other){
        if (n==1){
            System.out.println(from+" to "+to);
            return;
        }
        process(n-1,from,other,to);
        System.out.println(from+" to "+to);
        process(n-1,other,to,from);
    }


    public static void main(String[] args) {
        Hanoi1(4);
        System.out.println("==================");
        Hanoi2(4);
    }
}
