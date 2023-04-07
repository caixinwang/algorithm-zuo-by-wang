package TestUtils;

import java.util.Arrays;

public class TestTemplates {

    public static void testForArr(){//参数为arr
        ArrayUtil arrayUtil=new ArrayUtil();
        int times=1000;//测试次数
        boolean isok=true;
        int maxSize=10;//数组大小在[0~maxSize]随机
        int maxValue=10;//数组的值在[0,maxValue]随机
//        int parameter1=0;//测试函数的参数
//        int maxParameter1=100;//参数1在[0,maxParameter1]随机
        int r=0;//测试函数的返回值
        int[] f= arrayUtil.generateRandomArr(arrayUtil.ran(maxSize), maxValue);
//        int[] f= arrayUtil.generateRandomArr(arrayUtil.ran(maxSize),1, maxValue);//正数数组[1,maxValue]
        int res1=0,res2=0;
        for (int i = 0; i < times; i++) {
//            parameter1=arrayUtil.ran(maxParameter1);
            int[] t=arrayUtil.generateRandomArr(arrayUtil.ran(maxSize),maxValue);
//            int[] t=arrayUtil.generateRandomArr(arrayUtil.ran(maxSize),1,maxValue);//正数数组[1,maxValue]
            f=t;
            res1=m1(t);
            res2=m2(t);
            if (res1!=res2){
                isok=false;
                break;
            }
        }
        arrayUtil.printArr(f);//打印参数
//        System.out.println(parameter1);//打印参数
        System.out.println(res1);//针对返回值的操作
        System.out.println(res2);//针对返回值的操作
        System.out.println(isok?"success":"fail");
    }

    public static void testForMatrix(){//参数为arr
        ArrayUtil arrayUtil=new ArrayUtil();
        int times=1000;//测试次数
        boolean isok=true;
        int maxN=10;//matrix大小在[0~maxN][0~maxM]随机
        int maxM=10;//matrix大小在[0~maxN][0~maxM]随机
        int maxValue=10;//matrix的值在[0,maxValue]随机
//        int parameter1=0;//测试函数的参数
//        int maxParameter1=100;//参数1在[0,maxParameter1]随机
        int r=0;//测试函数的返回值
        int[][] f= arrayUtil.generateRandomMatrix(arrayUtil.ran(maxN), arrayUtil.ran(maxM), maxValue);
//        int[] f= arrayUtil.generateRandomArr(arrayUtil.ran(maxSize),1, maxValue);//正数数组[1,maxValue]
        int res1=0,res2=0;
        for (int i = 0; i < times; i++) {
//            parameter1=arrayUtil.ran(maxParameter1);
            int[][] t= arrayUtil.generateRandomMatrix(arrayUtil.ran(maxN), arrayUtil.ran(maxM), maxValue);
//            int[] t=arrayUtil.generateRandomArr(arrayUtil.ran(maxSize),1,maxValue);//正数数组[1,maxValue]
            f=t;
            res1=m1(t);
            res2=m2(t);
            if (res1!=res2){
                isok=false;
                break;
            }
        }
        arrayUtil.printMatrix(f);//打印参数
//        System.out.println(parameter1);//打印参数
        System.out.println(res1);//针对返回值的操作
        System.out.println(res2);//针对返回值的操作
        System.out.println(isok?"success":"fail");
    }

    public static int m1(int[] arr,int parameter1){
        return 0;
    }

    public static int m2(int[] arr,int parameter1){
        return 0;
    }

    public static int m1(int[] arr){
        return 0;
    }

    public static int m2(int[] arr){
        return 0;
    }

    public static int m1(int[][] matrix,int parameter1){
        return 0;
    }

    public static int m2(int[][] matrix,int parameter1){
        return 0;
    }

    public static int m1(int[][] matrix){
        return 0;
    }

    public static int m2(int[][] matrix){
        return 0;
    }


}
