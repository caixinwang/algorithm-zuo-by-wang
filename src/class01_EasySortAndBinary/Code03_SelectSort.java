package class01_EasySortAndBinary;

import static class01_EasySortAndBinary.Code01_SelectSort.*;
import static class01_EasySortAndBinary.Code01_SelectSort.printArray;

public class Code03_SelectSort {

    public static void selectSort(int[] arr){
        int i,j;
        for (i=1;i<arr.length;i++){
            int temp=arr[i];//拿牌
            for (j=i;j>0&&temp<arr[j-1];j--)//j是放入的位置
                arr[j]=arr[j-1];
            arr[j]=temp;
        }
    }

    public static void main(String[] args) {
        int testTimes=10000;
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
