/**
 * LeetCode #53: Maximum Subarray (Kadane's Algorithm)
 * 
 * Problem Statement:
 * Given an integer array nums, find the contiguous subarray (containing at least one number) 
 * which has the largest sum and return its sum.
 * 
 * A subarray is a contiguous part of an array.
 * 
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * 
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 * 
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 */
import java.util.*;

public class KadaneAlgorithm53 {
    
    // Method 1: Kadane's Algorithm (Optimal)
    // Time Complexity: O(n), Space Complexity: O(1)
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // Either extend the existing subarray or start a new one
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            
            // Update the maximum sum found so far
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
    
    // Method 2: Kadane's Algorithm with Subarray Indices
    // Time Complexity: O(n), Space Complexity: O(1)
    public SubarrayResult maxSubArrayWithIndices(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        int start = 0, end = 0, tempStart = 0;
        
        for (int i = 1; i < nums.length; i++) {
            if (currentSum < 0) {
                currentSum = nums[i];
                tempStart = i;
            } else {
                currentSum += nums[i];
            }
            
            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }
        }
        
        return new SubarrayResult(maxSum, start, end);
    }
    
    // Method 3: Brute Force Approach (For comparison)
    // Time Complexity: O(n²), Space Complexity: O(1)
    public int maxSubArrayBruteForce(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            int currentSum = 0;
            for (int j = i; j < nums.length; j++) {
                currentSum += nums[j];
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        
        return maxSum;
    }
    
    // Method 4: Divide and Conquer Approach
    // Time Complexity: O(n log n), Space Complexity: O(log n)
    public int maxSubArrayDivideConquer(int[] nums) {
        return maxSubArrayHelper(nums, 0, nums.length - 1);
    }
    
    private int maxSubArrayHelper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        
        int mid = left + (right - left) / 2;
        
        // Maximum subarray sum in left half
        int leftMax = maxSubArrayHelper(nums, left, mid);
        
        // Maximum subarray sum in right half
        int rightMax = maxSubArrayHelper(nums, mid + 1, right);
        
        // Maximum subarray sum that crosses the midpoint
        int crossMax = maxCrossingSum(nums, left, mid, right);
        
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }
    
    private int maxCrossingSum(int[] nums, int left, int mid, int right) {
        // Find maximum sum for left side (including mid)
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftSum = Math.max(leftSum, sum);
        }
        
        // Find maximum sum for right side (excluding mid)
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightSum = Math.max(rightSum, sum);
        }
        
        return leftSum + rightSum;
    }
    
    // Method 5: Dynamic Programming Approach
    // Time Complexity: O(n), Space Complexity: O(n)
    public int maxSubArrayDP(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int maxSum = dp[0];
        
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        
        return maxSum;
    }
    
    // Method 6: Kadane's Algorithm with Step-by-Step Explanation
    public int maxSubArrayExplained(int[] nums) {
        System.out.println("=== Kadane's Algorithm Step-by-Step ===");
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println();
        
        int maxSum = nums[0];
        int currentSum = nums[0];
        
        System.out.printf("Initial: maxSum = %d, currentSum = %d%n", maxSum, currentSum);
        
        for (int i = 1; i < nums.length; i++) {
            int prevCurrentSum = currentSum;
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            
            System.out.printf("i=%d, nums[%d]=%d: ", i, i, nums[i]);
            System.out.printf("currentSum = max(%d, %d + %d) = %d", 
                            nums[i], prevCurrentSum, nums[i], currentSum);
            
            if (currentSum > maxSum) {
                maxSum = currentSum;
                System.out.printf(" -> New maxSum = %d", maxSum);
            }
            System.out.println();
        }
        
        System.out.println("Final maxSum: " + maxSum);
        return maxSum;
    }
    
    // Helper class to store subarray result with indices
    static class SubarrayResult {
        int maxSum;
        int startIndex;
        int endIndex;
        
        SubarrayResult(int maxSum, int startIndex, int endIndex) {
            this.maxSum = maxSum;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
        
        @Override
        public String toString() {
            return String.format("MaxSum: %d, Start: %d, End: %d", maxSum, startIndex, endIndex);
        }
    }
    
    public static void main(String[] args) {
        KadaneAlgorithm53 solution = new KadaneAlgorithm53();
        
        // Test Case 1
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int result1 = solution.maxSubArray(nums1);
        System.out.println("Test Case 1: " + result1); // Expected: 6
        
        // Test Case 2
        int[] nums2 = {1};
        int result2 = solution.maxSubArray(nums2);
        System.out.println("Test Case 2: " + result2); // Expected: 1
        
        // Test Case 3
        int[] nums3 = {5, 4, -1, 7, 8};
        int result3 = solution.maxSubArray(nums3);
        System.out.println("Test Case 3: " + result3); // Expected: 23
        
        // Test Case 4: All negative numbers
        int[] nums4 = {-3, -2, -5, -1};
        int result4 = solution.maxSubArray(nums4);
        System.out.println("Test Case 4: " + result4); // Expected: -1
        
        // Test with indices
        System.out.println("\n=== Test with Subarray Indices ===");
        SubarrayResult result = solution.maxSubArrayWithIndices(nums1);
        System.out.println("Result: " + result);
        System.out.print("Subarray: [");
        for (int i = result.startIndex; i <= result.endIndex; i++) {
            System.out.print(nums1[i]);
            if (i < result.endIndex) System.out.print(", ");
        }
        System.out.println("]");
        
        // Step-by-step explanation
        System.out.println("\n=== Step-by-Step Explanation ===");
        solution.maxSubArrayExplained(new int[]{-2, 1, -3, 4, -1, 2});
        
        // Compare different approaches
        System.out.println("\n=== Comparison of Different Approaches ===");
        int[] testArray = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Kadane's Algorithm: " + solution.maxSubArray(testArray));
        System.out.println("Brute Force: " + solution.maxSubArrayBruteForce(testArray));
        System.out.println("Divide & Conquer: " + solution.maxSubArrayDivideConquer(testArray));
        System.out.println("Dynamic Programming: " + solution.maxSubArrayDP(testArray));
    }
}
