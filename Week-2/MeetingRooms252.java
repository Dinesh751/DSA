import java.util.*;

/**
 * LeetCode #252: Meeting Rooms
 * 
 * Problem Statement:
 * Given an array of meeting time intervals where intervals[i] = [starti, endi], 
 * determine if a person could attend all meetings.
 * 
 * Example 1:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 * Explanation: The meeting [5,10] overlaps with [0,30].
 * 
 * Example 2:
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 * Explanation: No overlapping meetings.
 */
public class MeetingRooms252 {
    
    // Method 1: Sorting Approach (Optimal)
    // Time Complexity: O(n log n), Space Complexity: O(1)
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return true;
        }
        
        // Sort meetings by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Check for overlaps
        for (int i = 1; i < intervals.length; i++) {
            // If current meeting starts before previous meeting ends
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false; // Overlap detected
            }
        }
        
        return true; // No overlaps
    }
    
    // Method 2: Brute Force Approach (For comparison)
    // Time Complexity: O(n²), Space Complexity: O(1)
    public boolean canAttendMeetingsBruteForce(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return true;
        }
        
        // Check every pair of meetings for overlap
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (isOverlapping(intervals[i], intervals[j])) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    // Method 3: Using Comparator (Clean approach)
    // Time Complexity: O(n log n), Space Complexity: O(1)
    public boolean canAttendMeetingsComparator(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return true;
        }
        
        // Sort by start time using Comparator
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        
        for (int i = 1; i < intervals.length; i++) {
            if (hasConflict(intervals[i - 1], intervals[i])) {
                return false;
            }
        }
        
        return true;
    }
    
    // Method 4: Event-based Approach (Alternative perspective)
    // Time Complexity: O(n log n), Space Complexity: O(n)
    public boolean canAttendMeetingsEvents(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return true;
        }
        
        List<int[]> events = new ArrayList<>();
        
        // Create start and end events
        for (int[] interval : intervals) {
            events.add(new int[]{interval[0], 1}); // Start event
            events.add(new int[]{interval[1], -1}); // End event
        }
        
        // Sort events by time, end events before start events for same time
        events.sort((a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]); // End (-1) before start (1)
            }
            return Integer.compare(a[0], b[0]);
        });
        
        int activeCount = 0;
        for (int[] event : events) {
            activeCount += event[1];
            if (activeCount > 1) {
                return false; // More than one meeting active
            }
        }
        
        return true;
    }
    
    // Method 5: Step-by-Step Analysis for Learning
    public boolean canAttendMeetingsDetailed(int[][] intervals) {
        System.out.println("=== Meeting Rooms Analysis ===");
        System.out.println("Input intervals: " + Arrays.deepToString(intervals));
        
        if (intervals == null || intervals.length <= 1) {
            System.out.println("Less than 2 meetings, can attend all: true");
            return true;
        }
        
        System.out.println("Original order: " + Arrays.deepToString(intervals));
        
        // Sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        System.out.println("After sorting by start time: " + Arrays.deepToString(intervals));
        
        System.out.println("\nChecking for overlaps:");
        for (int i = 1; i < intervals.length; i++) {
            int[] prev = intervals[i - 1];
            int[] curr = intervals[i];
            
            System.out.printf("Comparing [%d,%d] and [%d,%d]: ", 
                            prev[0], prev[1], curr[0], curr[1]);
            
            if (curr[0] < prev[1]) {
                System.out.printf("OVERLAP! (%d < %d)\n", curr[0], prev[1]);
                System.out.println("Cannot attend all meetings: false");
                return false;
            } else {
                System.out.printf("No overlap (%d >= %d)\n", curr[0], prev[1]);
            }
        }
        
        System.out.println("No overlaps found, can attend all meetings: true");
        return true;
    }
    
    // Helper method to check if two intervals overlap
    private boolean isOverlapping(int[] interval1, int[] interval2) {
        // Two intervals overlap if one starts before the other ends
        return Math.max(interval1[0], interval2[0]) < Math.min(interval1[1], interval2[1]);
    }
    
    // Helper method to check if two consecutive intervals have conflict
    private boolean hasConflict(int[] meeting1, int[] meeting2) {
        return meeting2[0] < meeting1[1]; // meeting2 starts before meeting1 ends
    }
    
    // Helper method to print intervals with timing
    private void printMeetingSchedule(int[][] intervals) {
        System.out.println("Meeting Schedule:");
        for (int i = 0; i < intervals.length; i++) {
            System.out.printf("Meeting %d: %02d:00 - %02d:00%n", 
                            i + 1, intervals[i][0], intervals[i][1]);
        }
    }
    
    public static void main(String[] args) {
        MeetingRooms252 solution = new MeetingRooms252();
        
        // Test Case 1 - Overlapping meetings
        System.out.println("=== Test Case 1: Overlapping Meetings ===");
        int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
        solution.printMeetingSchedule(intervals1);
        boolean result1 = solution.canAttendMeetings(intervals1);
        System.out.println("Can attend all meetings: " + result1);
        System.out.println("Expected: false\n");
        
        // Test Case 2 - Non-overlapping meetings
        System.out.println("=== Test Case 2: Non-overlapping Meetings ===");
        int[][] intervals2 = {{7, 10}, {2, 4}};
        solution.printMeetingSchedule(intervals2);
        boolean result2 = solution.canAttendMeetings(intervals2);
        System.out.println("Can attend all meetings: " + result2);
        System.out.println("Expected: true\n");
        
        // Test Case 3 - Adjacent meetings (touching)
        System.out.println("=== Test Case 3: Adjacent Meetings ===");
        int[][] intervals3 = {{1, 5}, {5, 8}, {8, 10}};
        solution.printMeetingSchedule(intervals3);
        boolean result3 = solution.canAttendMeetings(intervals3);
        System.out.println("Can attend all meetings: " + result3);
        System.out.println("Expected: true (meetings can be back-to-back)\n");
        
        // Test Case 4 - Single meeting
        System.out.println("=== Test Case 4: Single Meeting ===");
        int[][] intervals4 = {{1, 3}};
        boolean result4 = solution.canAttendMeetings(intervals4);
        System.out.println("Can attend all meetings: " + result4);
        System.out.println("Expected: true\n");
        
        // Test Case 5 - Empty schedule
        System.out.println("=== Test Case 5: Empty Schedule ===");
        int[][] intervals5 = {};
        boolean result5 = solution.canAttendMeetings(intervals5);
        System.out.println("Can attend all meetings: " + result5);
        System.out.println("Expected: true\n");
        
        // Detailed analysis
        System.out.println("=".repeat(50));
        int[][] intervals6 = {{9, 10}, {4, 9}, {4, 17}};
        solution.canAttendMeetingsDetailed(intervals6);
        
        // Test different approaches
        System.out.println("\n=== Testing Different Approaches ===");
        int[][] test = {{0, 30}, {5, 10}, {15, 20}};
        
        System.out.println("Sorting approach: " + solution.canAttendMeetings(test));
        System.out.println("Brute force approach: " + solution.canAttendMeetingsBruteForce(test));
        System.out.println("Comparator approach: " + solution.canAttendMeetingsComparator(test));
        System.out.println("Event-based approach: " + solution.canAttendMeetingsEvents(test));
        
        // Performance comparison for large input
        System.out.println("\n=== Performance Test ===");
        int[][] largeTest = {{1, 10}, {11, 20}, {21, 30}, {31, 40}, {41, 50}};
        
        long start = System.nanoTime();
        boolean result = solution.canAttendMeetings(largeTest);
        long end = System.nanoTime();
        
        System.out.printf("Sorted approach: %b (Time: %.2f μs)%n", 
                         result, (end - start) / 1000.0);
    }
}
