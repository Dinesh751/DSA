/**
 * LeetCode #209: Minimum Size Subarray Sum
 * 
 * Problem Statement:
 * Given an array of positive integers nums and a positive integer target, return the minimal 
 * length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is 
 * greater than or equal to target. If there is no such subarray, return 0 instead.
 * 
 * Example 1:
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * 
 * Example 2:
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * 
 * Example 3:
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 */
public class MinimumSizeSubarraySum209 {
    
    // Method 1: Brute Force Approach
    // Time Complexity: O(n²), Space Complexity: O(1)
    public int minSubArrayLenBruteForce(int target, int[] nums) {
        int minLength = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    minLength = Math.min(minLength, j - i + 1);
                    break; // No need to extend further from this starting point
                }
            }
        }
        
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
    
    // Method 2: Sliding Window Approach (Optimal)
    // Time Complexity: O(n), Space Complexity: O(1)
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            
            // Shrink window while sum is >= target
            while (sum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
    
    // Method 3: Two Pointers with Early Termination
    // Time Complexity: O(n), Space Complexity: O(1)
    public int minSubArrayLenOptimized(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            
            // Try to shrink the window as much as possible
            while (left <= right && sum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                
                // If we found length 1, we can't do better
                if (minLength == 1) {
                    return 1;
                }
                
                sum -= nums[left];
                left++;
            }
        }
        
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
    
    // Method 4: Binary Search Approach (Alternative)
    // Time Complexity: O(n log n), Space Complexity: O(n)
    public int minSubArrayLenBinarySearch(int target, int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        
        // Build prefix sum array
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        
        int minLength = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            int targetSum = target + prefixSum[i];
            int bound = binarySearch(prefixSum, targetSum);
            
            if (bound != n + 1) {
                minLength = Math.min(minLength, bound - i);
            }
        }
        
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
    
    private int binarySearch(int[] prefixSum, int target) {
        int left = 0, right = prefixSum.length - 1;
        int result = prefixSum.length;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (prefixSum[mid] >= target) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        MinimumSizeSubarraySum209 solution = new MinimumSizeSubarraySum209();
        
        // Test Case 1
        int target1 = 7;
        int[] nums1 = {2, 3, 1, 2, 4, 3};
        int result1 = solution.minSubArrayLen(target1, nums1);
        System.out.println("Test Case 1: " + result1); // Expected: 2
        
        // Test Case 2
        int target2 = 4;
        int[] nums2 = {1, 4, 4};
        int result2 = solution.minSubArrayLen(target2, nums2);
        System.out.println("Test Case 2: " + result2); // Expected: 1
        
        // Test Case 3
        int target3 = 11;
        int[] nums3 = {1, 1, 1, 1, 1, 1, 1, 1};
        int result3 = solution.minSubArrayLen(target3, nums3);
        System.out.println("Test Case 3: " + result3); // Expected: 0
        
        // Test Case 4
        int target4 = 15;
        int[] nums4 = {1, 2, 3, 4, 5};
        int result4 = solution.minSubArrayLen(target4, nums4);
        System.out.println("Test Case 4: " + result4); // Expected: 5
        
        // Test different approaches
        System.out.println("Brute Force: " + solution.minSubArrayLenBruteForce(target1, nums1));
        System.out.println("Optimized: " + solution.minSubArrayLenOptimized(target1, nums1));
        System.out.println("Binary Search: " + solution.minSubArrayLenBinarySearch(target1, nums1));
    }
}
