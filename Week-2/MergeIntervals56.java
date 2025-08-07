import java.util.*;

/**
 * LeetCode #56: Merge Intervals
 * 
 * Problem Statement:
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, 
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * 
 * Example 1:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * 
 * Example 2:
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
public class MergeIntervals56 {
    
    // Method 1: Sorting + Linear Scan (Optimal)
    // Time Complexity: O(n log n), Space Complexity: O(1) or O(n) depending on output space
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        
        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        List<int[]> merged = new ArrayList<>();
        
        for (int[] interval : intervals) {
            // If merged list is empty or current interval doesn't overlap with last merged interval
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                // Merge the intervals by updating the end time
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }
        
        return merged.toArray(new int[merged.size()][]);
    }
    
    // Method 2: Using Stack Approach
    // Time Complexity: O(n log n), Space Complexity: O(n)
    public int[][] mergeStack(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        Stack<int[]> stack = new Stack<>();
        stack.push(intervals[0]);
        
        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            int[] top = stack.peek();
            
            if (top[1] < current[0]) {
                // No overlap
                stack.push(current);
            } else {
                // Overlap - merge
                top[1] = Math.max(top[1], current[1]);
            }
        }
        
        return stack.toArray(new int[stack.size()][]);
    }
    
    // Method 3: Two Pointer Approach (Alternative)
    // Time Complexity: O(n log n), Space Complexity: O(1)
    public int[][] mergeTwoPointer(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        int writeIndex = 0;
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[writeIndex][1] >= intervals[i][0]) {
                // Merge intervals
                intervals[writeIndex][1] = Math.max(intervals[writeIndex][1], intervals[i][1]);
            } else {
                // No overlap, move to next position
                writeIndex++;
                intervals[writeIndex] = intervals[i];
            }
        }
        
        return Arrays.copyOf(intervals, writeIndex + 1);
    }
    
    // Method 4: Detailed Step-by-Step for Learning
    public int[][] mergeDetailed(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        
        System.out.println("=== Merge Intervals Step-by-Step ===");
        System.out.println("Original intervals: " + Arrays.deepToString(intervals));
        
        // Step 1: Sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        System.out.println("After sorting: " + Arrays.deepToString(intervals));
        
        List<int[]> merged = new ArrayList<>();
        
        for (int i = 0; i < intervals.length; i++) {
            int[] current = intervals[i];
            System.out.println("\nProcessing interval: [" + current[0] + "," + current[1] + "]");
            
            if (merged.isEmpty()) {
                merged.add(current);
                System.out.println("First interval, added to merged list");
            } else {
                int[] last = merged.get(merged.size() - 1);
                System.out.println("Last merged interval: [" + last[0] + "," + last[1] + "]");
                
                if (last[1] < current[0]) {
                    // No overlap
                    merged.add(current);
                    System.out.println("No overlap (" + last[1] + " < " + current[0] + "), added new interval");
                } else {
                    // Overlap - merge
                    int newEnd = Math.max(last[1], current[1]);
                    last[1] = newEnd;
                    System.out.println("Overlap detected, merged to: [" + last[0] + "," + newEnd + "]");
                }
            }
            
            System.out.println("Current merged list: " + 
                Arrays.toString(merged.stream().map(arr -> Arrays.toString(arr)).toArray()));
        }
        
        int[][] result = merged.toArray(new int[merged.size()][]);
        System.out.println("\nFinal result: " + Arrays.deepToString(result));
        return result;
    }
    
    
    
    // Helper method to print intervals
    private void printIntervals(int[][] intervals, String title) {
        System.out.println(title + ": " + Arrays.deepToString(intervals));
    }
    
    public static void main(String[] args) {
        MergeIntervals56 solution = new MergeIntervals56();
        
        // Test Case 1
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println("=== Test Case 1 ===");
        solution.printIntervals(intervals1, "Input");
        int[][] result1 = solution.merge(intervals1.clone());
        solution.printIntervals(result1, "Output");
        System.out.println("Expected: [[1,6],[8,10],[15,18]]\n");
        
        // Test Case 2
        int[][] intervals2 = {{1, 4}, {4, 5}};
        System.out.println("=== Test Case 2 ===");
        solution.printIntervals(intervals2, "Input");
        int[][] result2 = solution.merge(intervals2.clone());
        solution.printIntervals(result2, "Output");
        System.out.println("Expected: [[1,5]]\n");
        
        // Test Case 3 - No overlaps
        int[][] intervals3 = {{1, 2}, {3, 4}, {5, 6}};
        System.out.println("=== Test Case 3 ===");
        solution.printIntervals(intervals3, "Input");
        int[][] result3 = solution.merge(intervals3.clone());
        solution.printIntervals(result3, "Output");
        System.out.println("Expected: [[1,2],[3,4],[5,6]]\n");
        
        // Test Case 4 - All overlap
        int[][] intervals4 = {{1, 4}, {2, 5}, {3, 6}};
        System.out.println("=== Test Case 4 ===");
        solution.printIntervals(intervals4, "Input");
        int[][] result4 = solution.merge(intervals4.clone());
        solution.printIntervals(result4, "Output");
        System.out.println("Expected: [[1,6]]\n");
        
        // Test Case 5 - Edge case
        int[][] intervals5 = {{1, 1}, {2, 2}, {3, 3}};
        System.out.println("=== Test Case 5 ===");
        solution.printIntervals(intervals5, "Input");
        int[][] result5 = solution.merge(intervals5.clone());
        solution.printIntervals(result5, "Output");
        System.out.println("Expected: [[1,1],[2,2],[3,3]]\n");
        
        // Detailed step-by-step example
        System.out.println("\n" + "=".repeat(50));
        int[][] intervals6 = {{1, 3}, {2, 6}, {8, 10}};
        solution.mergeDetailed(intervals6);
        
        // Test different approaches
        System.out.println("\n=== Testing Different Approaches ===");
        int[][] test = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        
        System.out.println("Regular approach: " + Arrays.deepToString(solution.merge(test.clone())));
        System.out.println("Stack approach: " + Arrays.deepToString(solution.mergeStack(test.clone())));
        System.out.println("Two pointer approach: " + Arrays.deepToString(solution.mergeTwoPointer(test.clone())));
    }
}
