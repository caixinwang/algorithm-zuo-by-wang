package class02_QiTaPaiXu;

import java.util.Arrays;

public class Code03_HeapSort {
    private static void heapSort(int[] arr){
        if (arr==null||arr.length<2)
            return;
//        for (int i = 0; i < arr.length; i++) {//把数组调整成最大堆
//            insert(arr, i);
//        }
        for (int i=arr.length-1;i>=0;i--){//比上面注释的代码快一点，目的也是把数组调整成最大堆
            percDown(arr,i,arr.length);
        }
        for (int i=arr.length-1;i>=1;i--){
            swap(arr,0,i);//这边的i代表下标
            percDown(arr,0,i);//这边的i代表大小
        }
    }

    /**
     * 从i的地方开始向上调整成最大堆。不需要知道size因为向上的边界自动是0
     * @param arr
     * @param i
     */
    private static void insert(int[] arr, int i) {
        int child=i,temp=arr[i];
        for (;child>0&&arr[(child-1)/2]<temp;child=(child-1)/2)
            arr[child]=arr[(child-1)/2];
        arr[child]=temp;
    }

    /**
     * 从index的地方向下调整成最大堆，堆的大小为size。这里需要知道size，因为我们需要确定
     * 向下调整时候的边界。从树的角度看，index结点下面的子树都是最大堆。
     * @param arr
     * @param index
     * @param size
     */
    private static void percDown(int[] arr, int index, int size) {
        int child,parent=index,temp=arr[index];
        for (;parent*2+1<size;parent=child){//迭代如果不是直接利用本身迭代的，那么最好就用一个额外变量
            child=parent*2+2<size&&arr[parent*2+2]>arr[parent*2+1]?parent*2+2:parent*2+1;//只有存在右孩子并且右孩子比左孩子大的时候才等于右孩子
            if (temp<arr[child])
                arr[parent]=arr[child];
            else
                break;//如果temp>=arr.[child]说明找到了，退出
        }
        arr[parent]=temp;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp=arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
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
        int testTime = 10000;
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
