package class02_QiTaPaiXu;

import java.util.PriorityQueue;

public class Code04_SortArrayDistanceLessK {

    private static void sort(int[]arr ,int k){

        PriorityQueue<Integer>heap=new PriorityQueue<>();
        int index=0;//用来放进堆
        for (;index<Math.min(arr.length,k+1);index++){
            heap.add(arr[index]);
        }
        int i=0;//用来指示排序的进度
        for (;index<arr.length;i++,index++){
            arr[i]=heap.poll();
            heap.add(arr[index]);
        }
        while(i<arr.length){
            arr[i++]=heap.poll();
        }

    }
}
