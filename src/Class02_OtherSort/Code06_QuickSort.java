package Class02_OtherSort;

import java.util.Arrays;

public class Code06_QuickSort {
    /**
     * 使用了三向切分的快速排序。对于重复元素比较多的数组，效果比一般的快速排序好。
     * 对于包含大量重复元素的数组，它将排序时间从线性对数级降低到了线性级。
     * @param arr 对arr数组进行排序
     */
    private static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 使用了随机选取一个切分元素的方法来分摊最差情况的开销。
     * @param arr:
     * @param l:
     * @param r:
     */
    private static void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;
        swap(arr, l, l + (int) ((r - l + 1) * Math.random()));
        int[] a = partition(arr, l, r);
        quickSort(arr, l, a[0] - 1);
        quickSort(arr, a[1] + 1, r);
    }

    /**
     * 荷兰国旗问题---三向切分
     * @param arr:将arr数组三项切分，切分的元素是arr[r]
     * @param l:下标
     * @param r:下标
     * @return :返回两个数a,b。arr[a]~arr[b]相等。
     */
    private static int[] partition(int[] arr, int l, int r) {
        int less = l ;//arr[0]~arr[less]是小于切分元素的，闭区间
        int more = r+1;//设置为r而不是r+1是因为r位置为我们的切分元素。arr[more]~arr[N-1]是大于切分元素的
        int p=l+1,num=arr[l];
        while (p < more) {
            if (arr[p] < num) {
                swap(arr, ++less, p++);
            } else if (arr[p] > num) {
                swap(arr, --more, p);
            } else {
                p++;
            }
        }
        //[less+1~more-1]是等于切分元素的。r与more交换之后，变为[less+1~more]是等于切分元素的
        swap(arr, less, l);
        return new int[]{less, more-1};
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * 标准快排
     * @param arr 对arr数组进行排序
     */
    private static void quickSort2(int[] arr){
        quickSort2(arr,0,arr.length-1);
    }

    private static void quickSort2(int[] arr, int a, int b) {
        if (a>=b||arr==null) return;
        swap(arr,a,a+(int)(Math.random()*(b-a+1)));
        int i = partition2(arr, a, b);
        quickSort2(arr,a,i-1);
        quickSort2(arr,i+1,b);
    }

    /**
     * 标准快排的切分函数。切分元素默认是arr[l]
     * @param arr：将数组切分成a[l...i-1] a[i] a[i+1...r],这里的a[i]的值实际上是切分前arr[l]的值
     * @param l：进行切分的数组范围的左边界
     * @param r：进行切分的数组范围的右边界
     * @return ：返回切分元素在切分之后排在数组的哪一个位置index
     * 事实上，p1和p2交叉的时候一定满足p1-1=p2，所以p2是<=num区间的最后一个数，直接与arr[l]交换即可
     */
    private static int partition2(int[] arr,int l,int r){//由于是在快排中调用，所以r>l，即至少两个元素
        int num=arr[l];//切分元素
        int p1=l;//从while出来的时候都是小于等于num的，同时p1指向的是>=num的元素。
        int p2=r+1;//从while出来的时候p2+1~r都是大于等于num的，同时p2指向的是<=num的元素。
//        while(true){
//            while(arr[++p1]<num) if (p1==r) break;//防止越界。如果if条件成立，说明数组中所有的数都比num小
//            while(arr[--p2]>num) if (p2==l) break;//这里的if冗余，因为arr[l]!>arr[l]
//            if (p1>=p2){//此时arr[p2+1...r]>=num,arr[l,p2-1]<=num，因为[l,p2-1]范围比[l,p1-1]小，后者满足<=num
//                swap(arr,l,p2);//进行了尾部的定制，使用while(true),当p1>=p2的时候是p1和p2交换了。
//                break;
//            }else {
//                swap(arr,p1,p2);
//            }
//        }
        while(p1<p2){//上面那段代码和下面这段代码都ok
            while(p1<r&&arr[++p1]<num);
            while(p2>l&&arr[--p2]>num);
            if (p1<p2)swap(arr,p1,p2);
            else swap(arr,l,p2);//在while里面的最后一次循环进这个分支
        }
        return p2;
    }

    /**
     * partition2的低配版本，只从一侧开始扫描数组，交换次数比较多。
     * 思想是把数组切分成一侧全部<=num，另一侧全部>num
     * @param arr:将数组切分成a[l...i-1] a[i] a[i+1...r],这里的a[i]的值实际上是切分前arr[l]的值
     * @param l：进行切分的数组范围的左边界,默认选择arr[l]作为划分元素
     * @param r：进行切分的数组范围的右边界
     * @return ：返回切分元素在切分之后排在数组的哪一个位置（index）
     */
    private static int partition3(int[] arr,int l,int r){
        int num=arr[l];//划分
        int i=l+1;
        int less=l;//小于等于的边界,闭区间--包住的都是小于等于num的
        while(i<=r){//出while时，less为小于等于num的最右边界
            if (arr[i]<=num) swap(arr,i,++less);//左边界外扩，然后和i位置交换。
            i++;
        }
        swap(arr,less,l);
        return less;
    }

    private static void quickSort3(int[] arr){
        quickSort3(arr,0,arr.length-1);
    }

    private static void quickSort3(int[] arr, int a, int b) {
        if (a>=b||arr==null) return;
        swap(arr,a,a+(int)(Math.random()*(b-a+1)));
        int i = partition3(arr, a, b);
        quickSort3(arr,a,i-1);
        quickSort3(arr,i+1,b);
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
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1==null||arr2==null) {
            return false;
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
        int testTime = 1000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            quickSort2(arr1);
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
        quickSort(arr);
        printArray(arr);

    }
}
