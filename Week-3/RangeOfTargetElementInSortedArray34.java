/*
LeetCode 34: Find First and Last Position of Element in Sorted Array

Problem Statement:
Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Explanation: Target 8 is found at indices 3 and 4.

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Explanation: Target 6 is not found in the array.

Example 3:
Input: nums = [], target = 0
Output: [-1,-1]
Explanation: Empty array.

Example 4:
Input: nums = [1], target = 1
Output: [0,0]
Explanation: Single element found.

Example 5:
Input: nums = [2,2], target = 2
Output: [0,1]
Explanation: Both elements are the target.

Example 6:
Input: nums = [1,2,3], target = 2
Output: [1,1]
Explanation: Single occurrence of target.

Constraints:
- 0 <= nums.length <= 10^5
- -10^9 <= nums[i] <= 10^9
- nums is a non-decreasing array
- -10^9 <= target <= 10^9

Hints:
1. Use binary search to achieve O(log n) time complexity
2. You need to find two positions: leftmost and rightmost occurrence of target
3. Use two separate binary searches: one for finding the first occurrence, one for the last
4. For finding first occurrence: when nums[mid] == target, continue searching left
5. For finding last occurrence: when nums[mid] == target, continue searching right
6. Alternative: Find any occurrence first, then expand left and right (but this might be O(n) in worst case)

Key Insight:
Think of this as finding two boundaries:
- Left boundary: The first index where nums[i] >= target
- Right boundary: The first index where nums[i] > target, then subtract 1

Approach 1 - Two Binary Searches:
1. Binary search to find the leftmost position of target
2. Binary search to find the rightmost position of target

Approach 2 - Find Lower and Upper Bounds:
1. Find lower bound (first position >= target)
2. Find upper bound (first position > target)
3. Check if target actually exists

Time Complexity Goal: O(log n)
Space Complexity Goal: O(1)
*/

public class RangeOfTargetElementInSortedArray34 {
    
    // TODO: Implement your solution here
  
 public int[] searchRange(int[] nums, int target) {
        // Your solution goes here
        int ans1 = leftSearch(nums, target);
        int ans2 = rightSearch(nums, target);

        return new int[] {ans1, ans2};
    }

    public static int leftSearch(int nums[], int target){
        int start = 0;
        int end = nums.length -1;
        int result = -1;
        while(start <= end){
            int mid = start + (end - start) /2;

            if( nums[mid] == target){
                result = mid;
                end = mid -1;
            }else if(target > nums[mid]){
                start = mid +1;
            }else{
                end = mid -1;
            }
        }
        return result;
    }

        public static int rightSearch(int nums[], int target){
        int start = 0;
        int end = nums.length -1;
        int result = -1;
        while(start <= end){
            int mid = start + (end - start) /2;

            if( nums[mid] == target){
                result = mid;
                start = mid +1;
            }else if(target > nums[mid]){
                start = mid +1;
            }else{
                end = mid -1;
            }
        }
        return result;
    }

    
    // Test cases for verification
    public static void main(String[] args) {
        RangeOfTargetElementInSortedArray34 solution = new RangeOfTargetElementInSortedArray34();
        
        // Test case 1
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int target1 = 8;
        System.out.println("Test Case 1:");
        System.out.println("Input: nums = [5,7,7,8,8,10], target = " + target1);
        System.out.println("Expected: [3, 4]");
        System.out.println("Actual: " + java.util.Arrays.toString(solution.searchRange(nums1, target1)));
        System.out.println();
        
        // Test case 2
        int[] nums2 = {5, 7, 7, 8, 8, 10};
        int target2 = 6;
        System.out.println("Test Case 2:");
        System.out.println("Input: nums = [5,7,7,8,8,10], target = " + target2);
        System.out.println("Expected: [-1, -1]");
        System.out.println("Actual: " + java.util.Arrays.toString(solution.searchRange(nums2, target2)));
        System.out.println();
        
        // Test case 3
        int[] nums3 = {};
        int target3 = 0;
        System.out.println("Test Case 3:");
        System.out.println("Input: nums = [], target = " + target3);
        System.out.println("Expected: [-1, -1]");
        System.out.println("Actual: " + java.util.Arrays.toString(solution.searchRange(nums3, target3)));
        System.out.println();
        
        // Test case 4
        int[] nums4 = {1};
        int target4 = 1;
        System.out.println("Test Case 4:");
        System.out.println("Input: nums = [1], target = " + target4);
        System.out.println("Expected: [0, 0]");
        System.out.println("Actual: " + java.util.Arrays.toString(solution.searchRange(nums4, target4)));
        System.out.println();
        
        // Test case 5
        int[] nums5 = {2, 2};
        int target5 = 2;
        System.out.println("Test Case 5:");
        System.out.println("Input: nums = [2,2], target = " + target5);
        System.out.println("Expected: [0, 1]");
        System.out.println("Actual: " + java.util.Arrays.toString(solution.searchRange(nums5, target5)));
        System.out.println();
        
        // Test case 6
        int[] nums6 = {1, 2, 3};
        int target6 = 2;
        System.out.println("Test Case 6:");
        System.out.println("Input: nums = [1,2,3], target = " + target6);
        System.out.println("Expected: [1, 1]");
        System.out.println("Actual: " + java.util.Arrays.toString(solution.searchRange(nums6, target6)));
        System.out.println();
        
        // Test case 7 - Multiple occurrences at the beginning
        int[] nums7 = {1, 1, 1, 2, 3, 4};
        int target7 = 1;
        System.out.println("Test Case 7:");
        System.out.println("Input: nums = [1,1,1,2,3,4], target = " + target7);
        System.out.println("Expected: [0, 2]");
        System.out.println("Actual: " + java.util.Arrays.toString(solution.searchRange(nums7, target7)));
        System.out.println();
        
        // Test case 8 - Multiple occurrences at the end
        int[] nums8 = {1, 2, 3, 4, 4, 4};
        int target8 = 4;
        System.out.println("Test Case 8:");
        System.out.println("Input: nums = [1,2,3,4,4,4], target = " + target8);
        System.out.println("Expected: [3, 5]");
        System.out.println("Actual: " + java.util.Arrays.toString(solution.searchRange(nums8, target8)));
    }
}
