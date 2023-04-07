package TestUtils;

public class ArrayUtil {
    /**
     * @param arr 数组
     * @return 返回一个和arr一样的数组
     */
    public int[] copyArray(int[] arr) {
        if (arr == null) return null;
        int arr2[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) arr2[i] = arr[i];
        return arr2;
    }

    /**
     *
     * @param arr1 数组1
     * @param arr2 数组2
     * @return 返回数组1和数组2是否相等
     */
    public boolean isEqual(int[] arr1, int[] arr2) {
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

    public void printArr(int [] arr){
        if (arr==null)
            return;
        for (int a:arr){
            System.out.print(a+" ");
        }
        System.out.println();
    }

    /**
     * 赋值[1,n]产出来的就是正数数组了
     * @param size 生成的数组大小为size
     * @param l 数组里面值的范围在[l,r]，闭区间
     * @param r 如上
     * @return 返回一个大小为size，值在[l,r]上随机的数组
     */
    public int[] generateRandomArr(int size,int l,int r){
        int[] res=new int[size];
        for (int i = 0; i < res.length; i++) {
            res[i]=ran(l,r);
        }
        return res;
    }

    /**
     * @param size 生成的数组大小为size
     * @return 返回一个大小为size，值在[0,max]上随机的数组
     */
    public int[] generateRandomArr(int size,int max){
        int[] res=new int[size];
        for (int i = 0; i < res.length; i++) {
            res[i]=ran(max);
        }
        return res;
    }

    public int[][] generateRandomMatrix(int N,int M,int max){
        if (N<0||M<0||max<0) return null;
        int[][] res=new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                res[i][j]=ran(max);
            }
        }
        return res;
    }

    public int[][] generateRandomMatrix(int N,int M,int l,int r){
        if (N<0||M<0||l>r||l<0) return null;
        int[][] res=new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                res[i][j]=ran(l,r);
            }
        }
        return res;
    }

    public void printMatrix(int[][] matrix){
        if (matrix==null||matrix.length==0||matrix[0]==null||matrix[0].length==0) return;
        int N=matrix.length,M=matrix[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.printf("%4d ",matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int ran(int max){//[0,max]
        return (int) (Math.random()*(max+1));
    }


    public int ran(int l,int r){//[l,r]
        return l+ran(r-l);
    }

    public static void main(String[] args) {
        ArrayUtil au=new ArrayUtil();
        au.printMatrix(au.generateRandomMatrix(4,5,999));
    }

}
