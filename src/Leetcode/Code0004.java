package Leetcode;

public class Code0004 {//寻找两个正序数组的中位数--有序

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1==null||nums2==null) return 0;
        if (nums1.length==0&&nums2.length==0) return 0;
        int all=nums1.length+nums2.length;
        if ((all&1)==1){
            return findKthMinNumber(nums1,nums2,1+all>>1);
        }else {
            int upmid=findKthMinNumber(nums1,nums2,1+all>>1);
            int downmid=findKthMinNumber(nums1,nums2,(1+all>>1)+1);
            return (upmid+downmid)/2f;
        }
    }

    public static int findKthMinNumber(int[] arr1,int[] arr2,int kth){
        if (arr1 == null || arr2 == null) {
            return -1;
        }
        if (kth < 1 || kth > arr1.length + arr2.length) {
            return -1;
        }
        if (arr1.length==0&&arr2.length==0) return -1;
        if (arr1.length==0) return arr2[kth-1];
        if (arr2.length==0) return arr1[kth-1];
        if (arr1.length>arr2.length){
            int[] tmp=arr1;
            arr1=arr2;
            arr2=tmp;
        }
        int s= arr1.length,l=arr2.length;
        if (kth<=s){
            return getMiddle(arr1,0,kth-1,arr2,0,kth-1);
        } else if (kth <= l) {
            if (arr2[kth-s-1]>=arr1[s-1]) return arr2[kth-s-1];
            return getMiddle(arr1,0,s-1,arr2,kth-s,kth-1);
        }
        if (arr1[kth-l-1]>=arr2[l-1])return arr1[kth-l-1];
        if (arr2[kth-s-1]>=arr1[s-1])return arr2[kth-s-1];
        return getMiddle(arr1,kth-l,s-1,arr2,kth-s,l-1);
    }

    public static int getMiddle(int[] A,int s1,int e1,int[] B,int s2,int e2){
        if (s1==e1) return Math.min(A[s1],B[s2]);
        int mid1=s1+e1>>1;
        int mid2=s2+e2>>1;
        if (A[mid1]==B[mid2]) return A[mid1];
        if ((e1-s1+1&1)==1){
            if (A[mid1]>B[mid2]) return getMiddle(A,s1,mid1,B,mid2,e2);
            else return getMiddle(A,mid1,e1,B,s2,mid2);
        }else {
            if (A[mid1]>B[mid2]) return getMiddle(A,s1,mid1,B,mid2+1,e2);
            else return getMiddle(A,mid1+1,e1,B,s2,mid2);
        }
    }

}
