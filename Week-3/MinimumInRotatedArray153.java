/*
LeetCode 153: Find Minimum in Rotated Sorted Array

Problem Statement:
Suppose an array of length n sorted in ascending order is rotated between 1 and n times. 
For example, the array nums = [0,1,2,4,5,6,7] might become:

- [4,5,6,7,0,1,2] if it was rotated 4 times.
- [0,1,2,4,5,6,7] if it was rotated 7 times.

Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array 
[a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time.

Example 1:
Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.

Example 2:
Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.

Example 3:
Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times. 
(This means no rotation occurred, so minimum is still the first element)

Example 4:
Input: nums = [2,1]
Output: 1

Example 5:
Input: nums = [1]
Output: 1

Constraints:
- n == nums.length
- 1 <= n <= 5000
- -5000 <= nums[i] <= 5000
- All the integers of nums are unique
- nums is sorted and rotated between 1 and n times

Hints:
1. Use binary search to achieve O(log n) time complexity
2. The minimum element is the only element that is smaller than its previous element
3. If the array is not rotated, the minimum is at index 0
4. Compare middle element with the rightmost element to decide which half to search
5. The minimum element will always be in the unsorted half

Key Insight:
- If nums[mid] > nums[right], then the minimum is in the right half
- If nums[mid] < nums[right], then the minimum is in the left half (including mid)
- If nums[mid] == nums[right], this won't happen since all elements are unique

Visual Example:
Original: [1,2,3,4,5,6,7]
Rotated:  [4,5,6,7,1,2,3]
           ^     ^
        left   minimum (pivot point)

Time Complexity Goal: O(log n)
Space Complexity Goal: O(1)
*/

public class MinimumInRotatedArray153 {
    
    // TODO: Implement your solution here
    public int findMin(int[] nums) {
        // Your solution goes here
        int start = 0, end = nums.length -1;

        while(start < end ){
            int mid = start + (end - start)/2;

            if(nums[end] < nums[mid]){
                start = mid +1;
            }else{
                end = mid;
            }
        }
        return nums[start];
    }
    
    // Test cases for verification
    public static void main(String[] args) {
        MinimumInRotatedArray153 solution = new MinimumInRotatedArray153();
        
        // Test case 1
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println("Test Case 1:");
        System.out.println("Input: nums = [3,4,5,1,2]");
        System.out.println("Expected: 1");
        System.out.println("Actual: " + solution.findMin(nums1));
        System.out.println();
        
        // Test case 2
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Test Case 2:");
        System.out.println("Input: nums = [4,5,6,7,0,1,2]");
        System.out.println("Expected: 0");
        System.out.println("Actual: " + solution.findMin(nums2));
        System.out.println();
        
        // Test case 3 - No rotation
        int[] nums3 = {11, 13, 15, 17};
        System.out.println("Test Case 3 (No rotation):");
        System.out.println("Input: nums = [11,13,15,17]");
        System.out.println("Expected: 11");
        System.out.println("Actual: " + solution.findMin(nums3));
        System.out.println();
        
        // Test case 4
        int[] nums4 = {2, 1};
        System.out.println("Test Case 4:");
        System.out.println("Input: nums = [2,1]");
        System.out.println("Expected: 1");
        System.out.println("Actual: " + solution.findMin(nums4));
        System.out.println();
        
        // Test case 5 - Single element
        int[] nums5 = {1};
        System.out.println("Test Case 5 (Single element):");
        System.out.println("Input: nums = [1]");
        System.out.println("Expected: 1");
        System.out.println("Actual: " + solution.findMin(nums5));
        System.out.println();
        
        // Test case 6 - Rotated once
        int[] nums6 = {2, 3, 4, 5, 1};
        System.out.println("Test Case 6:");
        System.out.println("Input: nums = [2,3,4,5,1]");
        System.out.println("Expected: 1");
        System.out.println("Actual: " + solution.findMin(nums6));
        System.out.println();
        
        // Test case 7 - All but one rotation (almost no rotation)
        int[] nums7 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 7 (Almost no rotation):");
        System.out.println("Input: nums = [1,2,3,4,5]");
        System.out.println("Expected: 1");
        System.out.println("Actual: " + solution.findMin(nums7));
    }
}
