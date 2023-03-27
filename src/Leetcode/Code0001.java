package Leetcode;

import java.util.HashMap;

public class Code0001 {//两数之和
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map=new HashMap<>();//存int和下标
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])) return new int[]{map.get(target-nums[i]),i};
            else map.put(nums[i],i );
        }
        return null;
    }
}
