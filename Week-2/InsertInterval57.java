import java.util.*;

/**
 * LeetCode #57: Insert Interval
 * 
 * Problem Statement:
 * You are given an array of non-overlapping intervals where intervals[i] = [starti, endi] 
 * represent the start and the end of the ith interval and intervals is sorted in ascending order by starti.
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 * 
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and 
 * intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 * 
 * Return intervals after the insertion.
 * 
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * 
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 */
public class InsertInterval57 {
    
    // Method 1: Three-step approach (Most intuitive)
    // Time Complexity: O(n), Space Complexity: O(n)
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;
        
        // Step 1: Add all intervals that end before newInterval starts
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        
        // Step 2: Merge all overlapping intervals with newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);
        
        // Step 3: Add all remaining intervals
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }
        
        return result.toArray(new int[result.size()][]);
    }
    
    // Method 2: Single pass with conditions
    // Time Complexity: O(n), Space Complexity: O(n)
    public int[][] insertSinglePass(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        boolean inserted = false;
        
        for (int[] interval : intervals) {
            if (interval[1] < newInterval[0]) {
                // Current interval ends before newInterval starts
                result.add(interval);
            } else if (interval[0] > newInterval[1]) {
                // Current interval starts after newInterval ends
                if (!inserted) {
                    result.add(newInterval);
                    inserted = true;
                }
                result.add(interval);
            } else {
                // Overlap detected, merge intervals
                newInterval[0] = Math.min(newInterval[0], interval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
        }
        
        // If newInterval hasn't been inserted yet, it should be at the end
        if (!inserted) {
            result.add(newInterval);
        }
        
        return result.toArray(new int[result.size()][]);
    }
    
    // Method 3: Binary search optimization for finding insertion point
    // Time Complexity: O(n), Space Complexity: O(n)
    public int[][] insertBinarySearch(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        
        // Find the position to insert using binary search
        int insertPos = findInsertPosition(intervals, newInterval[0]);
        
        // Add intervals before merge position
        for (int i = 0; i < insertPos; i++) {
            if (intervals[i][1] < newInterval[0]) {
                result.add(intervals[i]);
            } else {
                break;
            }
        }
        
        // Merge overlapping intervals
        int[] mergedInterval = newInterval.clone();
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] <= mergedInterval[1] && intervals[i][1] >= mergedInterval[0]) {
                mergedInterval[0] = Math.min(mergedInterval[0], intervals[i][0]);
                mergedInterval[1] = Math.max(mergedInterval[1], intervals[i][1]);
            } else if (intervals[i][0] > mergedInterval[1]) {
                // Add merged interval and remaining intervals
                result.add(mergedInterval);
                for (int j = i; j < intervals.length; j++) {
                    result.add(intervals[j]);
                }
                return result.toArray(new int[result.size()][]);
            } else if (intervals[i][1] < mergedInterval[0]) {
                result.add(intervals[i]);
            }
        }
        
        result.add(mergedInterval);
        return result.toArray(new int[result.size()][]);
    }
    
    // Method 4: In-place modification approach (when possible)
    // Time Complexity: O(n), Space Complexity: O(1) additional space
    public int[][] insertInPlace(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }
        
        List<int[]> result = new ArrayList<>();
        int start = newInterval[0];
        int end = newInterval[1];
        boolean merged = false;
        
        for (int[] interval : intervals) {
            if (interval[1] < start) {
                // No overlap, interval comes before newInterval
                result.add(interval);
            } else if (interval[0] > end) {
                // No overlap, interval comes after newInterval
                if (!merged) {
                    result.add(new int[]{start, end});
                    merged = true;
                }
                result.add(interval);
            } else {
                // Overlap, merge intervals
                start = Math.min(start, interval[0]);
                end = Math.max(end, interval[1]);
            }
        }
        
        // Add the merged interval if not added yet
        if (!merged) {
            result.add(new int[]{start, end});
        }
        
        return result.toArray(new int[result.size()][]);
    }
    
    // Method 5: Step-by-step detailed approach for learning
    public int[][] insertDetailed(int[][] intervals, int[] newInterval) {
        System.out.println("=== Insert Interval Analysis ===");
        System.out.println("Original intervals: " + Arrays.deepToString(intervals));
        System.out.println("New interval to insert: " + Arrays.toString(newInterval));
        
        List<int[]> result = new ArrayList<>();
        int i = 0;
        
        System.out.println("\nStep 1: Adding intervals that end before newInterval starts");
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            System.out.printf("  Adding [%d,%d] (ends at %d < %d)%n", 
                            intervals[i][0], intervals[i][1], intervals[i][1], newInterval[0]);
            result.add(intervals[i]);
            i++;
        }
        
        System.out.println("\nStep 2: Merging overlapping intervals");
        int[] mergedInterval = newInterval.clone();
        System.out.printf("  Starting with newInterval: [%d,%d]%n", mergedInterval[0], mergedInterval[1]);
        
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            System.out.printf("  Merging with [%d,%d]", intervals[i][0], intervals[i][1]);
            mergedInterval[0] = Math.min(mergedInterval[0], intervals[i][0]);
            mergedInterval[1] = Math.max(mergedInterval[1], intervals[i][1]);
            System.out.printf(" -> [%d,%d]%n", mergedInterval[0], mergedInterval[1]);
            i++;
        }
        
        result.add(mergedInterval);
        System.out.printf("  Final merged interval: [%d,%d]%n", mergedInterval[0], mergedInterval[1]);
        
        System.out.println("\nStep 3: Adding remaining intervals");
        while (i < intervals.length) {
            System.out.printf("  Adding remaining [%d,%d]%n", intervals[i][0], intervals[i][1]);
            result.add(intervals[i]);
            i++;
        }
        
        int[][] resultArray = result.toArray(new int[result.size()][]);
        System.out.println("\nFinal result: " + Arrays.deepToString(resultArray));
        return resultArray;
    }
    
    // Helper method for binary search
    private int findInsertPosition(int[][] intervals, int target) {
        int left = 0, right = intervals.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (intervals[mid][0] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    

    
    // Helper method to print intervals nicely
    private void printIntervals(int[][] intervals, String title) {
        System.out.println(title + ": " + Arrays.deepToString(intervals));
    }
    
    public static void main(String[] args) {
        InsertInterval57 solution = new InsertInterval57();
        
        // Test Case 1 - Insert in middle with merge
        System.out.println("=== Test Case 1: Insert with merge ===");
        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[] newInterval1 = {2, 5};
        solution.printIntervals(intervals1, "Original");
        System.out.println("New interval: " + Arrays.toString(newInterval1));
        int[][] result1 = solution.insert(intervals1, newInterval1);
        solution.printIntervals(result1, "Result");
        System.out.println("Expected: [[1,5],[6,9]]\n");
        
        // Test Case 2 - Insert with multiple merges
        System.out.println("=== Test Case 2: Multiple merges ===");
        int[][] intervals2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval2 = {4, 8};
        solution.printIntervals(intervals2, "Original");
        System.out.println("New interval: " + Arrays.toString(newInterval2));
        int[][] result2 = solution.insert(intervals2, newInterval2);
        solution.printIntervals(result2, "Result");
        System.out.println("Expected: [[1,2],[3,10],[12,16]]\n");
        
        // Test Case 3 - Insert at beginning
        System.out.println("=== Test Case 3: Insert at beginning ===");
        int[][] intervals3 = {{3, 5}, {12, 15}};
        int[] newInterval3 = {1, 2};
        solution.printIntervals(intervals3, "Original");
        System.out.println("New interval: " + Arrays.toString(newInterval3));
        int[][] result3 = solution.insert(intervals3, newInterval3);
        solution.printIntervals(result3, "Result");
        System.out.println("Expected: [[1,2],[3,5],[12,15]]\n");
        
        // Test Case 4 - Insert at end
        System.out.println("=== Test Case 4: Insert at end ===");
        int[][] intervals4 = {{1, 3}, {6, 9}};
        int[] newInterval4 = {10, 12};
        solution.printIntervals(intervals4, "Original");
        System.out.println("New interval: " + Arrays.toString(newInterval4));
        int[][] result4 = solution.insert(intervals4, newInterval4);
        solution.printIntervals(result4, "Result");
        System.out.println("Expected: [[1,3],[6,9],[10,12]]\n");
        
        // Test Case 5 - Empty intervals
        System.out.println("=== Test Case 5: Empty intervals ===");
        int[][] intervals5 = {};
        int[] newInterval5 = {5, 7};
        solution.printIntervals(intervals5, "Original");
        System.out.println("New interval: " + Arrays.toString(newInterval5));
        int[][] result5 = solution.insert(intervals5, newInterval5);
        solution.printIntervals(result5, "Result");
        System.out.println("Expected: [[5,7]]\n");
        
        // Test Case 6 - Merge all intervals
        System.out.println("=== Test Case 6: Merge all intervals ===");
        int[][] intervals6 = {{1, 2}, {3, 4}, {5, 6}};
        int[] newInterval6 = {0, 7};
        solution.printIntervals(intervals6, "Original");
        System.out.println("New interval: " + Arrays.toString(newInterval6));
        int[][] result6 = solution.insert(intervals6, newInterval6);
        solution.printIntervals(result6, "Result");
        System.out.println("Expected: [[0,7]]\n");
        
        // Detailed step-by-step analysis
        System.out.println("=".repeat(60));
        System.out.println("DETAILED ANALYSIS:");
        int[][] intervals7 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval7 = {4, 8};
        solution.insertDetailed(intervals7, newInterval7);
        
        // Test all approaches
        System.out.println("\n=== Testing Different Approaches ===");
        int[][] test = {{1, 3}, {6, 9}};
        int[] newTest = {2, 5};
        
        System.out.println("Three-step approach: " + 
                         Arrays.deepToString(solution.insert(test, newTest)));
        System.out.println("Single pass approach: " + 
                         Arrays.deepToString(solution.insertSinglePass(test, newTest)));
        System.out.println("In-place approach: " + 
                         Arrays.deepToString(solution.insertInPlace(test, newTest)));
        
        // Performance test
        System.out.println("\n=== Performance Test ===");
        int[][] largeTest = new int[1000][2];
        for (int i = 0; i < 1000; i++) {
            largeTest[i] = new int[]{i * 3, i * 3 + 1};
        }
        int[] newLarge = {1500, 1600};
        
        long start = System.nanoTime();
        int[][] perfResult = solution.insert(largeTest, newLarge);
        long end = System.nanoTime();
        
        System.out.printf("Processed %d intervals in %.2f μs%n", 
                         largeTest.length, (end - start) / 1000.0);
        System.out.printf("Result has %d intervals%n", perfResult.length);
    }
}
