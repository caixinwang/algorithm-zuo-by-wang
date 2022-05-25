package class01_JianDanPaiXuErFen;

import java.util.Arrays;

public class Code01_SelectSort1 {

    private static void selectSort(int[] arr){
        for (int i=0;i<arr.length-1;i++){
            int minIndex=i;
            for (int j=minIndex;j<arr.length;j++){
                minIndex=arr[minIndex]<arr[j]?minIndex:j;
            }
            swap(arr,i,minIndex);
        }
    }

    private static void swap(int [] arr,int i, int minIndex) {
        int temp=arr[i];
        arr[i]=arr[minIndex];
        arr[minIndex]=temp;
    }


    public static int[] generateArray(int maxSize, int maxValue) {
        //生成一个随机大小、值的大小也随机的数组。大小的范围最大位maxSize，值的大小最大为maxValue
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i=0;i< arr.length;i++) {
//            a=(int)((maxValue+1)*Math.random());[0~maxValue]
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue+1) * Math.random());//[-maxValue~+maxValue]
        }
        return arr;
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null)
            return null;
        int arr2[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr2[i] = arr[i];
        }
        return arr2;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {//判断arr1和arr2是否有其中一个为空
            if (arr1 == null & arr2 == null)
                return true;
            else
                return false;
        }
        if (arr1.length != arr2.length)//长度不相等就不需要比较
            return false;
        int i;
        for (i = 0; i < arr1.length && arr1[i] == arr2[i]; i++) ;
        if (i==arr1.length)//退出循环的条件有两个，一个是i=len，一个是数组1不等于数组2.
            return true;
        else
            return false;
    }

    public static void printArray(int [] arr){
        if (arr==null)
            return;
        for (int a:arr){
            System.out.print(a+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTimes=10000;
        int maxSize=100;
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
