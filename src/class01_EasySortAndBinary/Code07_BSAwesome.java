package class01_EasySortAndBinary;

public class Code07_BSAwesome {
    private static int getLessIndex(int[] arr) {//这边的数组不要求是有序数组
        if (arr.length < 2 || arr == null)
            return -1;
        if (arr[0] < arr[1])
            return 0;
        if (arr[arr.length - 1] < arr[arr.length - 2])
            return arr.length - 1;
        int left = 1, right = arr.length - 2, mid = 0;//left和right设为1和len-2是为了防止后面判断局部最小的时候防止越界
        while (left <= right) {
            mid = ((right - left) >> 1) + left;
            if (arr[mid] > arr[mid + 1])
                left = mid + 1;
            else if (arr[mid] > arr[mid - 1])
                right = mid - 1;
            else
                return mid;
        }
        return -1;
    }

    private static boolean isLessIndex(int[] arr, int lessIndex) {
        if (arr.length < 2 || arr == null)
            return true;
        if (lessIndex == 0)
            return arr[lessIndex] < arr[lessIndex + 1];
        if (lessIndex == arr.length - 1)
            return arr[lessIndex] < arr[lessIndex - 1];
        return arr[lessIndex] < arr[lessIndex + 1] && arr[lessIndex] < arr[lessIndex - 1];
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {//相邻不相等
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        arr[0] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
        for (int i = 1; i < arr.length; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
            } while (arr[i] == arr[i - 1]);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxSize = 30;
        int maxValue = 200;
        boolean isSuccess = true;

        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int[] arr2={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
//            selectSort(arr);//加了sort之后不行的原因是相邻不相等，sort之后不相邻的相等的数排在一起了
//            int checkNum=(int)((maxValue+1)*Math.random());
            int a=getLessIndex(arr2);
            if (!isLessIndex(arr2,a)) {//只要有一次测试不成功就退出
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "Nice!" : "Fucked!");
    }
}
