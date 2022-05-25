package class02_QiTaPaiXu;

import java.util.Arrays;

public class Code03_HeapSort {
    private static void heapSort(int[] arr){
        if (arr==null||arr.length<2)
            return;
//        for (int i=0;i<arr.length;i++){//把数组调整成最大堆
//            insert(arr,i);
//        }
        for (int index=arr.length-1;index>=0;index--){//比上面注释的代码快一点
            percDown(arr,index,arr.length);
        }
        for (int size=arr.length;size>=1;){
            swap(arr,0,size-1);
            size--;//注意要把最后一个排除在堆的外面
            percDown(arr,0,size);
        }
    }

    private static void percDown(int[] arr, int index, int size) {
        int temp=arr[index];
        int parent,child;
        for (parent=index;parent*2+1<size;parent=child){
            child=parent*2+1;
            child=child+1<size&&arr[child+1]>arr[child]?child+1:child;//找到了大孩子
            if (temp<arr[child]){
                arr[parent]=arr[child];
            }else {
                break;
            }
        }

        arr[parent]=temp;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp=arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
    }

    private static void insert(int[] arr, int index) {
        int parent,child;
        int temp=arr[index];
        for (child=index;(child-1)/2>0;child=parent){//注意：这边的条件是出不去的，要自己判断
            parent=(child-1)/2;
//            if (child==0)  break;//说明到顶了。
            if (temp>arr[parent]){
                arr[child]=arr[parent];
            }else{
                break;
            }
        }
        arr[child]=temp;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 5000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        printArray(arr);

    }
}
