package class01_JianDanPaiXuErFen;

import java.util.Arrays;

import static class01_JianDanPaiXuErFen.Code01_SelectSort.generateArray;

public class Code05_BSNearLeft {
    private static int nearestIndex(int[] sortedArray,int num){//大于num的最小数字
        if (sortedArray==null&&sortedArray.length==0)
            return -1;
        int left=0,right=sortedArray.length-1,mid=0,index=-1;
         while(left<=right){
             mid=((right-left)>>1)+left;
             if (sortedArray[mid]>num){
                 index=mid;
                 right=mid-1;
             }
             else
                 left=mid+1;
         }
         return index;//如果没有找到那么index就是等于-1
    }

    private static int Test(int[] sortedArray,int checkNum){
        if (sortedArray==null&&sortedArray.length==0)
            return -1;
        int index;
        for (index=0;index<sortedArray.length&&sortedArray[index]<=checkNum;index++);
        if (index==sortedArray.length)
            return -1;
        else return index;
    }

    public static void main(String[] args) {
        int testTimes=1000;
        int maxSize=30;
        int maxValue=200;
        boolean isSuccess=true;

        for (int i=0;i<testTimes;i++){
            int [] arr=generateArray(maxSize,maxValue);
            Arrays.sort(arr);
            int checkNum=(int)((maxValue+1)*Math.random());
            if (Test(arr,checkNum)!=nearestIndex(arr,checkNum)){//只要有一次测试不成功就退出
                isSuccess=false;
                break;
            }
        }
        System.out.println(isSuccess?"Nice!":"Fucked!");
    }
}
