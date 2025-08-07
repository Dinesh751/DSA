import java.util.*;

/**
 * LeetCode #704: Binary Search
 * 
 * Problem Statement:
 * Given an array of integers nums which is sorted in ascending order, and an integer target, 
 * write a function to search target in nums. If target exists, then return its index. 
 * Otherwise, return -1.
 * 
 * You must write an algorithm with O(log n) runtime complexity.
 * 
 * Example 1:
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * 
 * Example 2:
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 */
public class BinarySearch704 {
    
    // Method 1: Classic Binary Search (Iterative)
    // Time Complexity: O(log n), Space Complexity: O(1)
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2; // Avoid overflow
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }
        
        return -1; // Target not found
    }
    
    // Method 2: Recursive Binary Search
    // Time Complexity: O(log n), Space Complexity: O(log n) due to call stack
    public int searchRecursive(int[] nums, int target) {
        return binarySearchHelper(nums, target, 0, nums.length - 1);
    }
    
    private int binarySearchHelper(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1; // Base case: target not found
        }
        
        int mid = left + (right - left) / 2;
        
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            return binarySearchHelper(nums, target, mid + 1, right);
        } else {
            return binarySearchHelper(nums, target, left, mid - 1);
        }
    }
    
    // Method 3: Binary Search with different mid calculation
    // Time Complexity: O(log n), Space Complexity: O(1)
    public int searchAlternative(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        
        while (left < right) {
            int mid = (left + right) / 2;
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return -1;
    }
    
    // Method 4: Lower bound binary search (finds first occurrence)
    // Time Complexity: O(log n), Space Complexity: O(1)
    public int searchLowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        // Check if target was found
        if (left < nums.length && nums[left] == target) {
            return left;
        }
        
        return -1;
    }
    
    // Method 5: Upper bound binary search (finds last occurrence)
    // Time Complexity: O(log n), Space Complexity: O(1)
    public int searchUpperBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        // Check if target was found
        if (left > 0 && nums[left - 1] == target) {
            return left - 1;
        }
        
        return -1;
    }
    
    // Method 6: Detailed step-by-step for learning
    public int searchDetailed(int[] nums, int target) {
        System.out.println("=== Binary Search Analysis ===");
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Target: " + target);
        System.out.println("Array length: " + nums.length);
        
        if (nums.length == 0) {
            System.out.println("Empty array - target not found");
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        int step = 1;
        
        System.out.println("\nSearching step by step:");
        System.out.printf("Initial: left=%d, right=%d%n", left, right);
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            System.out.printf("\nStep %d:%n", step++);
            System.out.printf("  Search range: [%d, %d]%n", left, right);
            System.out.printf("  Mid index: %d%n", mid);
            System.out.printf("  Mid value: %d%n", nums[mid]);
            System.out.printf("  Comparing: %d vs %d%n", nums[mid], target);
            
            if (nums[mid] == target) {
                System.out.printf("  *** FOUND! Target %d found at index %d ***%n", target, mid);
                return mid;
            } else if (nums[mid] < target) {
                System.out.printf("  %d < %d, search RIGHT half%n", nums[mid], target);
                left = mid + 1;
                System.out.printf("  New left boundary: %d%n", left);
            } else {
                System.out.printf("  %d > %d, search LEFT half%n", nums[mid], target);
                right = mid - 1;
                System.out.printf("  New right boundary: %d%n", right);
            }
            
            if (left <= right) {
                System.out.printf("  Remaining elements to search: %d%n", right - left + 1);
                System.out.print("  Current search range: [");
                for (int i = left; i <= right; i++) {
                    System.out.print(nums[i]);
                    if (i < right) System.out.print(", ");
                }
                System.out.println("]");
            }
        }
        
        System.out.printf("\nSearch completed. Target %d not found in array.%n", target);
        System.out.printf("Total steps taken: %d%n", step - 1);
        return -1;
    }
    
    // Method 7: Recursive with detailed explanation
    public int searchRecursiveDetailed(int[] nums, int target) {
        System.out.println("=== Recursive Binary Search Analysis ===");
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Target: " + target);
        return recursiveHelper(nums, target, 0, nums.length - 1, 0);
    }
    
    private int recursiveHelper(int[] nums, int target, int left, int right, int depth) {
        String indent = "  ".repeat(depth);
        System.out.printf("%sCall depth %d: search range [%d, %d]%n", indent, depth, left, right);
        
        if (left > right) {
            System.out.printf("%sBase case: left > right, target not found%n", indent);
            return -1;
        }
        
        int mid = left + (right - left) / 2;
        System.out.printf("%sMid index: %d, Mid value: %d%n", indent, mid, nums[mid]);
        
        if (nums[mid] == target) {
            System.out.printf("%sFOUND! Target %d at index %d%n", indent, target, mid);
            return mid;
        } else if (nums[mid] < target) {
            System.out.printf("%s%d < %d, searching right half [%d, %d]%n", 
                             indent, nums[mid], target, mid + 1, right);
            return recursiveHelper(nums, target, mid + 1, right, depth + 1);
        } else {
            System.out.printf("%s%d > %d, searching left half [%d, %d]%n", 
                             indent, nums[mid], target, left, mid - 1);
            return recursiveHelper(nums, target, left, mid - 1, depth + 1);
        }
    }
    
    // Helper method to find first and last position of target
    public int[] searchRange(int[] nums, int target) {
        int first = searchLowerBound(nums, target);
        int last = searchUpperBound(nums, target);
        
        if (first == -1) {
            return new int[]{-1, -1};
        }
        
        return new int[]{first, last};
    }
    
    // Helper method to count occurrences of target
    public int countOccurrences(int[] nums, int target) {
        int[] range = searchRange(nums, target);
        if (range[0] == -1) {
            return 0;
        }
        return range[1] - range[0] + 1;
    }
    
    // Helper method to find insertion position
    public int searchInsertPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    

    
    public static void main(String[] args) {
        BinarySearch704 solution = new BinarySearch704();
        
        // Test Case 1 - Target found
        System.out.println("=== Test Case 1: Target Found ===");
        int[] nums1 = {-1, 0, 3, 5, 9, 12};
        int target1 = 9;
        int result1 = solution.search(nums1, target1);
        System.out.println("Array: " + Arrays.toString(nums1));
        System.out.println("Target: " + target1);
        System.out.println("Result: " + result1);
        System.out.println("Expected: 4");
        System.out.println();
        
        // Test Case 2 - Target not found
        System.out.println("=== Test Case 2: Target Not Found ===");
        int[] nums2 = {-1, 0, 3, 5, 9, 12};
        int target2 = 2;
        int result2 = solution.search(nums2, target2);
        System.out.println("Array: " + Arrays.toString(nums2));
        System.out.println("Target: " + target2);
        System.out.println("Result: " + result2);
        System.out.println("Expected: -1");
        System.out.println();
        
        // Test Case 3 - Single element found
        System.out.println("=== Test Case 3: Single Element Found ===");
        int[] nums3 = {5};
        int target3 = 5;
        int result3 = solution.search(nums3, target3);
        System.out.println("Array: " + Arrays.toString(nums3));
        System.out.println("Target: " + target3);
        System.out.println("Result: " + result3);
        System.out.println("Expected: 0");
        System.out.println();
        
        // Test Case 4 - Single element not found
        System.out.println("=== Test Case 4: Single Element Not Found ===");
        int[] nums4 = {5};
        int target4 = 3;
        int result4 = solution.search(nums4, target4);
        System.out.println("Array: " + Arrays.toString(nums4));
        System.out.println("Target: " + target4);
        System.out.println("Result: " + result4);
        System.out.println("Expected: -1");
        System.out.println();
        
        // Test Case 5 - Target at boundaries
        System.out.println("=== Test Case 5: Boundary Tests ===");
        int[] nums5 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        System.out.println("Array: " + Arrays.toString(nums5));
        System.out.println("First element (1): " + solution.search(nums5, 1));
        System.out.println("Last element (10): " + solution.search(nums5, 10));
        System.out.println("Middle element (5): " + solution.search(nums5, 5));
        System.out.println("Expected: 0, 9, 4");
        System.out.println();
        
        // Detailed step-by-step analysis
        System.out.println("=".repeat(60));
        int[] detailArray = {1, 3, 5, 7, 9, 11, 13, 15};
        solution.searchDetailed(detailArray, 7);
        
        // Recursive analysis
        System.out.println("\n" + "=".repeat(60));
        solution.searchRecursiveDetailed(detailArray, 11);
        
        // Test all approaches
        System.out.println("\n=== Testing All Approaches ===");
        int[] testArray = {-1, 0, 3, 5, 9, 12};
        int testTarget = 5;
        
        System.out.println("Array: " + Arrays.toString(testArray));
        System.out.println("Target: " + testTarget);
        System.out.println("Classic iterative: " + solution.search(testArray, testTarget));
        System.out.println("Recursive: " + solution.searchRecursive(testArray, testTarget));
        System.out.println("Alternative: " + solution.searchAlternative(testArray, testTarget));
        System.out.println("Lower bound: " + solution.searchLowerBound(testArray, testTarget));
        System.out.println("Upper bound: " + solution.searchUpperBound(testArray, testTarget));
        
        // Test with duplicates
        System.out.println("\n=== Testing with Duplicates ===");
        int[] duplicates = {1, 2, 2, 2, 3, 4, 4, 5};
        int dupTarget = 2;
        
        System.out.println("Array: " + Arrays.toString(duplicates));
        System.out.println("Target: " + dupTarget);
        System.out.println("Any occurrence: " + solution.search(duplicates, dupTarget));
        System.out.println("First occurrence: " + solution.searchLowerBound(duplicates, dupTarget));
        System.out.println("Last occurrence: " + solution.searchUpperBound(duplicates, dupTarget));
        System.out.println("Count occurrences: " + solution.countOccurrences(duplicates, dupTarget));
        
        // Test insertion position
        System.out.println("\n=== Testing Insertion Position ===");
        int[] insertArray = {1, 3, 5, 6};
        int[] insertTargets = {2, 4, 7, 0};
        
        System.out.println("Array: " + Arrays.toString(insertArray));
        for (int target : insertTargets) {
            int pos = solution.searchInsertPosition(insertArray, target);
            System.out.printf("Insert position for %d: %d%n", target, pos);
        }
        
        // Performance test
        System.out.println("\n=== Performance Test ===");
        int[] largeArray = new int[1000000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i * 2; // Even numbers
        }
        
        int largeTarget = 999998; // Last element
        
        long start = System.nanoTime();
        int largeResult = solution.search(largeArray, largeTarget);
        long end = System.nanoTime();
        
        System.out.printf("Searched %d elements for target %d%n", largeArray.length, largeTarget);
        System.out.printf("Found at index: %d in %.2f μs%n", largeResult, (end - start) / 1000.0);
        
        // Compare with linear search
        start = System.nanoTime();
        for (int i = 0; i < largeArray.length; i++) {
            if (largeArray[i] == largeTarget) {
                break;
            }
        }
        end = System.nanoTime();
        
        System.out.printf("Linear search took: %.2f μs%n", (end - start) / 1000.0);
        System.out.println("Binary search is much faster for large arrays!");
        
        // Edge cases
        System.out.println("\n=== Edge Cases ===");
        System.out.println("Empty array: " + solution.search(new int[]{}, 1));
        System.out.println("Target smaller than all: " + solution.search(new int[]{5, 6, 7}, 1));
        System.out.println("Target larger than all: " + solution.search(new int[]{1, 2, 3}, 10));
    }
}
