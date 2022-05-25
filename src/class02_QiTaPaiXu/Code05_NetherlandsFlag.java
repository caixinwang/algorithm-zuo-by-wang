package class02_QiTaPaiXu;

import static class01_JianDanPaiXuErFen.Code01_SelectSort.swap;

public class Code05_NetherlandsFlag {

    private static int [] flag(int []arr,int l,int r,int num){
        int less=l-1;
        int more=r+1;
        while(l<more){
            if (arr[l]<num){
                swap(arr,++less,l++);
            }else if (arr[l]>num){
                swap(arr,--more,l);
            }else {
                l++;
            }
        }
        return new int[]{less+1,more-1};
    }

    private static void twoArea(int []arr,int num){
        int less=-1;
        for (int i=0;i<arr.length;i++){
            if (arr[i]<=num){
                swap(arr,less+1,i);
                less++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr={4,9,8,3,7,6,9,10};
        twoArea(arr,7);
        for (int a:arr){
            System.out.print(a+" ");
        }
    }

}
