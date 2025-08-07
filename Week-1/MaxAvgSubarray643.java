/**
 * LeetCode #643: Maximum Average Subarray I
 * 
 * Problem Statement:
 * You are given an integer array nums consisting of n elements, and an integer k.
 * Find a contiguous subarray whose length is equal to k that has the maximum average value 
 * and return this value. Any answer with a calculation error less than 10^-5 will be accepted.
 * 
 * Example 1:
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 * 
 * Example 2:
 * Input: nums = [5], k = 1
 * Output: 5.00000
 */
public class MaxAvgSubarray643 {
    
    // Method 1: Brute Force Approach
    // Time Complexity: O(n * k), Space Complexity: O(1)
    public double findMaxAverageBruteForce(int[] nums, int k) {
        double maxAverage = Double.NEGATIVE_INFINITY;
        
        for (int i = 0; i <= nums.length - k; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += nums[j];
            }
            double average = (double) sum / k;
            maxAverage = Math.max(maxAverage, average);
        }
        
        return maxAverage;
    }
    
    // Method 2: Sliding Window Approach (Optimal)
    // Time Complexity: O(n), Space Complexity: O(1)
    public double findMaxAverage(int[] nums, int k) {
        // Calculate sum of first k elements
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        
        int maxSum = sum;
        
        // Use sliding window to find maximum sum
        for (int i = k; i < nums.length; i++) {
            sum = sum - nums[i - k] + nums[i]; // Remove left element, add right element
            maxSum = Math.max(maxSum, sum);
        }
        
        return (double) maxSum / k;
    }
    
    // Method 3: Sliding Window with Running Sum
    // Time Complexity: O(n), Space Complexity: O(1)
    public double findMaxAverageRunningSum(int[] nums, int k) {
        if (nums.length < k) {
            return 0.0;
        }
        
        // Initialize with first window
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        
        int maxSum = windowSum;
        
        // Slide the window
        for (int i = 1; i <= nums.length - k; i++) {
            windowSum = windowSum - nums[i - 1] + nums[i + k - 1];
            maxSum = Math.max(maxSum, windowSum);
        }
        
        return (double) maxSum / k;
    }
    
    // Method 4: Using Long to avoid overflow
    // Time Complexity: O(n), Space Complexity: O(1)
    public double findMaxAverageLong(int[] nums, int k) {
        long sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        
        long maxSum = sum;
        
        for (int i = k; i < nums.length; i++) {
            sum = sum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        
        return (double) maxSum / k;
    }
    
    public static void main(String[] args) {
        MaxAvgSubarray643 solution = new MaxAvgSubarray643();
        
        // Test Case 1
        int[] nums1 = {1, 12, -5, -6, 50, 3};
        int k1 = 4;
        double result1 = solution.findMaxAverage(nums1, k1);
        System.out.printf("Test Case 1: %.5f%n", result1); // Expected: 12.75000
        
        // Test Case 2
        int[] nums2 = {5};
        int k2 = 1;
        double result2 = solution.findMaxAverage(nums2, k2);
        System.out.printf("Test Case 2: %.5f%n", result2); // Expected: 5.00000
        
        // Test Case 3
        int[] nums3 = {0, 1, 1, 3, 3};
        int k3 = 4;
        double result3 = solution.findMaxAverage(nums3, k3);
        System.out.printf("Test Case 3: %.5f%n", result3); // Expected: 2.00000
        
        // Test Case 4 - Negative numbers
        int[] nums4 = {-1, -2, -3, -4, -5};
        int k4 = 2;
        double result4 = solution.findMaxAverage(nums4, k4);
        System.out.printf("Test Case 4: %.5f%n", result4); // Expected: -1.50000
        
        // Test different approaches
        System.out.printf("Brute Force: %.5f%n", solution.findMaxAverageBruteForce(nums1, k1));
        System.out.printf("Running Sum: %.5f%n", solution.findMaxAverageRunningSum(nums1, k1));
        System.out.printf("Long approach: %.5f%n", solution.findMaxAverageLong(nums1, k1));
    }
}
