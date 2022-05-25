package class01_JianDanPaiXuErFen;

import static class01_JianDanPaiXuErFen.Code01_SelectSort.*;
import static class01_JianDanPaiXuErFen.Code01_SelectSort.printArray;

public class Code03_SelectSort1 {
    private static void selectSort(int[] arr){
        for (int p=1;p<arr.length;p++){
            int temp=arr[p];
            int i;//应该放的位置
            for (i=p;i>0&&arr[i-1]>temp;i--){
                arr[i]=arr[i-1];
            }
            arr[i]=temp;
        }
    }
    public static void main(String[] args) {
        int testTimes=100;
        int maxSize=30;
        int maxValue=200;
        boolean isSuccess=true;

        for (int i=0;i<testTimes;i++){
            int [] arr1=generateArray(maxSize,maxValue);
            int [] arr2=copyArray(arr1);
            selectSort(arr1);
            comparator(arr2);
            if(!isEqual(arr1,arr2)){
                isSuccess=false;
                //失败了！打印一个正确的和一个错误的给你看
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(isSuccess?"Nice!":"Fucked!");
        int [] arr =generateArray(maxSize,maxValue);
        printArray(arr);
        selectSort(arr);
        printArray(arr);
    }
}
