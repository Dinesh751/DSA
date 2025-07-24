import java.util.*;

public class PartitionEqualSubsetSum416 {
    /*
    Problem Statement:
    Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.

    Example 1:
    Input: nums = [1,5,11,5]
    Output: true
    Explanation: The array can be partitioned as [1, 5, 5] and [11].

    Example 2:
    Input: nums = [1,2,3,5]
    Output: false
    Explanation: The array cannot be partitioned into equal sum subsets.

    Constraints:
    - 1 <= nums.length <= 200
    - 1 <= nums[i] <= 100
    */

     public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for(int num : nums){
            totalSum += num;
        }

        if(totalSum % 2 == 1) return false; 

        int target = totalSum /2;
        Boolean memo[][] = new Boolean[nums.length][target+1]; 

        return helper(nums, 0, target, memo);
    }

    public boolean helper(int nums[], int ind, int target, Boolean memo[][]){
        if( target == 0) return true;

        if(target < 0 || ind == nums.length) return false;
        
        if(memo[ind][target] != null) return memo[ind][target];

        boolean take = helper(nums, ind+1, target - nums[ind], memo);
        boolean notTake = helper(nums, ind+1, target, memo);
         
         
        return memo[ind][target] = take || notTake;
    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum416 solution = new PartitionEqualSubsetSum416();

        // Test case 1
        int[] nums1 = {1, 5, 11, 5};
        boolean expected1 = true;
        boolean actual1 = solution.canPartition(nums1);
        System.out.println("Test Case 1: Expected: " + expected1 + ", Actual: " + actual1);

        // Test case 2
        int[] nums2 = {1, 2, 3, 5};
        boolean expected2 = false;
        boolean actual2 = solution.canPartition(nums2);
        System.out.println("Test Case 2: Expected: " + expected2 + ", Actual: " + actual2);
    }
}
