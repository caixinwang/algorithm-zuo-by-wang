package OtherCoding.DividedAndConquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code01_FindMinDistance {
    static class Point{
        public double x;
        public double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    private static double distance(Point a,Point b){
        return Math.sqrt(Math.pow(a.x-b.x,2)+Math.pow(a.y-b.y,2));
    }

    public static double findMinDistance(Point[] arr){//蛮力
        if (arr==null||arr.length<2) return 0;
        double minDistance=distance(arr[0],arr[1]);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                minDistance = Math.min(minDistance, distance(arr[i],arr[j]));
            }
        }
        return minDistance;
    }

    public static double findMinDistance2(Point[] arr){//分治
        Point[] arrx=copyArr(arr);
        Point[] arry=copyArr(arr);
        Arrays.sort(arrx,(Point a, Point b)-> {//x升序
            return  (a.x - b.x)<0?-1:1;
        });
        Arrays.sort(arry,(Point a,Point b)-> {//y升序
            return  (a.y - b.y)<0?-1:1;
        });
        return findMinDistance2(arrx,arry);
    }

    private static double findMinDistance2(Point[] arrx, Point[] arry) {
        if (arrx.length<2) return Double.MAX_VALUE;
        if (arrx.length==2) return distance(arrx[0],arrx[1]);
        int mid=arrx.length/2;
        Point[] arrx1=copyArr(arrx,0,mid);
        Point[] arrx2=copyArr(arrx,mid+1,arrx.length-1);
        Point[] arry1=copyArr(arrx1);
        Point[] arry2=copyArr(arrx2);
        Arrays.sort(arry1,(Point a,Point b)-> {//y升序
            return  (a.y - b.y)<0?-1:1;
        });
        Arrays.sort(arry2,(Point a,Point b)-> {//y升序
            return  (a.y - b.y)<0?-1:1;
        });
        double d=Math.min(findMinDistance2(arrx1,arry1),findMinDistance2(arrx2,arry2));
        double m=arrx[arrx.length/2].x;
        Point[] ltd=copyArrByLessThanD(arry,m,d);
        double dminsq=Math.pow(d,2);
        for (int i = 0; i < ltd.length - 1; i++) {
            int k=i+1;
            while(k< ltd.length&&Math.pow(ltd[i].y-ltd[k].y,2)<dminsq){
                dminsq = Math.min(dminsq, Math.pow(ltd[i].x-ltd[k].x,2)+Math.pow(ltd[i].y-ltd[k].y,2));
                k++;
            }
        }
        return Math.sqrt(dminsq);
    }


    public static Point[] copyArr(Point[] arr){//复制arr数组
        if (arr==null) return null;
        Point[] res=new Point[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i]=new Point(arr[i].x,arr[i].y);
        }
        return res;
    }

    public static Point[] copyArr(Point[] arr,int a,int b){//从下标a到下标b复制arr数组
        if (arr==null) return null;
        Point[] res=new Point[b-a+1];
        for (int i = a; i <=b; i++) {
            res[i-a]=new Point(arr[i].x,arr[i].y);
        }
        return res;
    }

    public static Point[] copyArrByLessThanD(Point[] arr,double m,double d){
        List<Point> list=new ArrayList<>();
        for (Point point : arr) {
            if (Math.abs(point.x - m) < d) list.add(point);
        }
        Point[] points =new Point[list.size()];
        for (int i = 0; i < points.length; i++) {
            points[i]=list.get(i);
        }
        return points;
    }

    public static Point[] generateRandomPoints(int size,double valRange){//生成size大小的随机数组
        Point[] arr=new Point[size];
        for (int i = 0; i < size; i++) {
            double x=Math.random()*valRange-Math.random()*valRange;//(-valRange,valRange]
            double y=Math.random()*valRange-Math.random()*valRange;//(-valRange,valRange]
            arr[i]=new Point(x,y);
        }
        return arr;
    }

    public static void main(String[] args) {
        boolean isSuccess=true;
        int times=100;
        for (int i = 0; i < times; i++) {//一共测试times次
            Point[] arr=generateRandomPoints(100,1000);
            if (findMinDistance(arr)!=findMinDistance2(arr)){//利用蛮力法验证分治法的正确性
                isSuccess=false;
                System.out.println("fail!");
                System.out.println(findMinDistance(arr));
                System.out.println(findMinDistance2(arr));
                break;
            }
        }
        if (isSuccess) System.out.println("yes!");
    }
}