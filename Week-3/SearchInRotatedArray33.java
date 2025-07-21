/*
LeetCode 33: Search in Rotated Sorted Array

Problem Statement:
There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k 
(1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] 
(0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, 
or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Explanation: 0 is found at index 4.

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Explanation: 3 is not in the array.

Example 3:
Input: nums = [1], target = 0
Output: -1

Example 4:
Input: nums = [1], target = 1
Output: 0

Example 5:
Input: nums = [4,5,6,7,0,1,2], target = 5
Output: 1

Example 6:
Input: nums = [5,1,3], target = 3
Output: 2

Constraints:
- 1 <= nums.length <= 5000
- -10^4 <= nums[i] <= 10^4
- All values of nums are unique
- nums is an ascending array that is possibly rotated
- -10^4 <= target <= 10^4

Hints:
1. Think about using binary search since we need O(log n) complexity
2. At any point, at least one half of the array will be properly sorted
3. Determine which half is sorted, then decide which half to search
4. Consider the relationship between nums[left], nums[mid], nums[right], and target

Key Insight:
In a rotated sorted array, when you pick a middle element, one of the two halves 
will always be properly sorted. Use this fact to determine where to search next.

Time Complexity Goal: O(log n)
Space Complexity Goal: O(1)
*/

public class SearchInRotatedArray33 {
    
    // TODO: Implement your solution here
    public int search(int[] nums, int target) {
        // Your solution goes here
       int result = -1;
       int start =0, end = nums.length - 1;

       while(start <= end){
          int mid = start + (end - start)/2;

          if(nums[mid] == target) return mid;

          else if (nums[mid] > nums[start]){
            if(nums[mid] > target){
                end = mid -1;
            }else{
                start = mid +1;
            }
          }else{
            if(nums[mid] < target){
                start = mid +1;
            }else{
                end = mid -1;
            }
          }
       }
       return result;
    }



    
    // Test cases for verification
    public static void main(String[] args) {
        SearchInRotatedArray33 solution = new SearchInRotatedArray33();
        
        // Test case 1
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        System.out.println("Test Case 1:");
        System.out.println("Input: nums = [4,5,6,7,0,1,2], target = " + target1);
        System.out.println("Expected: 4");
        System.out.println("Actual: " + solution.search(nums1, target1));
        System.out.println();
        
        // Test case 2
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int target2 = 3;
        System.out.println("Test Case 2:");
        System.out.println("Input: nums = [4,5,6,7,0,1,2], target = " + target2);
        System.out.println("Expected: -1");
        System.out.println("Actual: " + solution.search(nums2, target2));
        System.out.println();
        
        // Test case 3
        int[] nums3 = {1};
        int target3 = 0;
        System.out.println("Test Case 3:");
        System.out.println("Input: nums = [1], target = " + target3);
        System.out.println("Expected: -1");
        System.out.println("Actual: " + solution.search(nums3, target3));
        System.out.println();
        
        // Test case 4
        int[] nums4 = {1};
        int target4 = 1;
        System.out.println("Test Case 4:");
        System.out.println("Input: nums = [1], target = " + target4);
        System.out.println("Expected: 0");
        System.out.println("Actual: " + solution.search(nums4, target4));
        System.out.println();
        
        // Test case 5
        int[] nums5 = {4, 5, 6, 7, 0, 1, 2};
        int target5 = 5;
        System.out.println("Test Case 5:");
        System.out.println("Input: nums = [4,5,6,7,0,1,2], target = " + target5);
        System.out.println("Expected: 1");
        System.out.println("Actual: " + solution.search(nums5, target5));
        System.out.println();
        
        // Test case 6
        int[] nums6 = {5, 1, 3};
        int target6 = 3;
        System.out.println("Test Case 6:");
        System.out.println("Input: nums = [5,1,3], target = " + target6);
        System.out.println("Expected: 2");
        System.out.println("Actual: " + solution.search(nums6, target6));
        System.out.println();
        
        // Test case 7 - No rotation
        int[] nums7 = {1, 2, 3, 4, 5, 6, 7};
        int target7 = 4;
        System.out.println("Test Case 7 (No rotation):");
        System.out.println("Input: nums = [1,2,3,4,5,6,7], target = " + target7);
        System.out.println("Expected: 3");
        System.out.println("Actual: " + solution.search(nums7, target7));
    }
}
