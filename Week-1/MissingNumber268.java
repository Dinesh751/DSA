import java.util.*;

/**
 * LeetCode #268: Missing Number
 * 
 * Problem Statement:
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number 
 * in the range that is missing from the array.
 * 
 * Example 1:
 * Input: nums = [3,0,1]
 * Output: 2
 * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 
 * 2 is the missing number in the range since it does not appear in nums.
 * 
 * Example 2:
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 
 * 2 is the missing number in the range since it does not appear in nums.
 * 
 * Example 3:
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 
 * 8 is the missing number in the range since it does not appear in nums.
 */
public class MissingNumber268 {
    
    // Method 1: HashSet Approach
    // Time Complexity: O(n), Space Complexity: O(n)
    public int missingNumberHashSet(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        
        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            if (!numSet.contains(i)) {
                return i;
            }
        }
        
        return -1; // Should never reach here
    }
    
    // Method 2: Math Formula Approach (Optimal)
    // Time Complexity: O(n), Space Complexity: O(1)
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2; // Sum of numbers from 0 to n
        
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        
        return expectedSum - actualSum;
    }
    
    // Method 3: XOR Approach (Bit Manipulation)
    // Time Complexity: O(n), Space Complexity: O(1)
    public int missingNumberXOR(int[] nums) {
        int xor = nums.length; // Start with n
        
        for (int i = 0; i < nums.length; i++) {
            xor ^= i ^ nums[i];
        }
        
        return xor;
    }
    
    // Method 4: Sorting Approach
    // Time Complexity: O(n log n), Space Complexity: O(1)
    public int missingNumberSorting(int[] nums) {
        Arrays.sort(nums);
        
        // Check if 0 is missing
        if (nums[0] != 0) {
            return 0;
        }
        
        // Check if any number in between is missing
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                return nums[i - 1] + 1;
            }
        }
        
        // The missing number is at the end
        return nums.length;
    }
    
    // Method 5: Cyclic Sort Approach
    // Time Complexity: O(n), Space Complexity: O(1)
    public int missingNumberCyclicSort(int[] nums) {
        int n = nums.length;
        
        // Place each number at its correct position
        int i = 0;
        while (i < n) {
            if (nums[i] < n && nums[i] != nums[nums[i]]) {
                // Swap nums[i] with nums[nums[i]]
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            } else {
                i++;
            }
        }
        
        // Find the first position where number doesn't match index
        for (i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        
        return n; // Missing number is n
    }
    
    public static void main(String[] args) {
        MissingNumber268 solution = new MissingNumber268();
        
        // Test Case 1
        int[] nums1 = {3, 0, 1};
        int result1 = solution.missingNumber(nums1);
        System.out.println("Test Case 1: " + result1); // Expected: 2
        
        // Test Case 2
        int[] nums2 = {0, 1};
        int result2 = solution.missingNumber(nums2);
        System.out.println("Test Case 2: " + result2); // Expected: 2
        
        // Test Case 3
        int[] nums3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        int result3 = solution.missingNumber(nums3);
        System.out.println("Test Case 3: " + result3); // Expected: 8
        
        // Test Case 4
        int[] nums4 = {1};
        int result4 = solution.missingNumber(nums4);
        System.out.println("Test Case 4: " + result4); // Expected: 0
        
        // Test different approaches
        System.out.println("HashSet approach: " + solution.missingNumberHashSet(nums1));
        System.out.println("XOR approach: " + solution.missingNumberXOR(nums1));
        System.out.println("Sorting approach: " + solution.missingNumberSorting(nums1.clone()));
        System.out.println("Cyclic sort approach: " + solution.missingNumberCyclicSort(nums1.clone()));
    }
}
