/**
 * LeetCode #283: Move Zeroes
 * 
 * Problem Statement:
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative 
 * order of the non-zero elements.
 * 
 * Note that you must do this in-place without making a copy of the array.
 * 
 * Example 1:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * 
 * Example 2:
 * Input: nums = [0]
 * Output: [0]
 */
import java.util.*;

public class MoveZeroes283 {
    
    // Method 1: Two Pointers Approach (Optimal)
    // Time Complexity: O(n), Space Complexity: O(1)
    public void moveZeroes(int[] nums) {
        int insertPos = 0; // Position to insert next non-zero element
        
        // Move all non-zero elements to the front
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[insertPos] = nums[i];
                insertPos++;
            }
        }
        
        // Fill remaining positions with zeros
        while (insertPos < nums.length) {
            nums[insertPos] = 0;
            insertPos++;
        }
    }
    
    // Method 2: Two Pointers with Swap (Alternative)
    // Time Complexity: O(n), Space Complexity: O(1)
    public void moveZeroesSwap(int[] nums) {
        int left = 0; // Points to the first zero
        
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                // Swap non-zero element with zero at left position
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
        }
    }
    
    // Method 3: Optimized Swap (Avoid unnecessary swaps)
    // Time Complexity: O(n), Space Complexity: O(1)
    public void moveZeroesOptimizedSwap(int[] nums) {
        int left = 0;
        
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                if (left != right) { // Only swap if necessary
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                }
                left++;
            }
        }
    }
    
    // Method 4: Using ArrayList (Not in-place, for comparison)
    // Time Complexity: O(n), Space Complexity: O(n)
    public void moveZeroesList(int[] nums) {
        List<Integer> nonZeros = new ArrayList<>();
        int zeroCount = 0;
        
        // Collect non-zero elements and count zeros
        for (int num : nums) {
            if (num != 0) {
                nonZeros.add(num);
            } else {
                zeroCount++;
            }
        }
        
        // Rebuild the array
        int index = 0;
        for (int num : nonZeros) {
            nums[index++] = num;
        }
        
        // Add zeros at the end
        for (int i = 0; i < zeroCount; i++) {
            nums[index++] = 0;
        }
    }
    
    // Method 5: Bubble Sort Style (Less efficient)
    // Time Complexity: O(n²), Space Complexity: O(1)
    public void moveZeroesBubble(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] == 0 && nums[j + 1] != 0) {
                    // Swap zero with non-zero
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }
    
    // Helper method to print array
    private void printArray(int[] nums) {
        System.out.println(Arrays.toString(nums));
    }
    
    public static void main(String[] args) {
        MoveZeroes283 solution = new MoveZeroes283();
        
        // Test Case 1
        int[] nums1 = {0, 1, 0, 3, 12};
        System.out.print("Test Case 1 - Before: ");
        solution.printArray(nums1);
        solution.moveZeroes(nums1);
        System.out.print("Test Case 1 - After: ");
        solution.printArray(nums1); // Expected: [1, 3, 12, 0, 0]
        
        // Test Case 2
        int[] nums2 = {0};
        System.out.print("Test Case 2 - Before: ");
        solution.printArray(nums2);
        solution.moveZeroes(nums2);
        System.out.print("Test Case 2 - After: ");
        solution.printArray(nums2); // Expected: [0]
        
        // Test Case 3
        int[] nums3 = {1, 2, 3, 0, 0, 4, 5};
        System.out.print("Test Case 3 - Before: ");
        solution.printArray(nums3);
        solution.moveZeroes(nums3);
        System.out.print("Test Case 3 - After: ");
        solution.printArray(nums3); // Expected: [1, 2, 3, 4, 5, 0, 0]
        
        // Test different approaches
        int[] nums4 = {0, 1, 0, 3, 12};
        System.out.print("Swap approach - Before: ");
        solution.printArray(nums4);
        solution.moveZeroesSwap(nums4);
        System.out.print("Swap approach - After: ");
        solution.printArray(nums4);
        
        int[] nums5 = {0, 1, 0, 3, 12};
        System.out.print("Optimized swap - Before: ");
        solution.printArray(nums5);
        solution.moveZeroesOptimizedSwap(nums5);
        System.out.print("Optimized swap - After: ");
        solution.printArray(nums5);
    }
}
