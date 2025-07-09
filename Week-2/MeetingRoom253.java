import java.util.*;

/**
 * Meeting Room II - LeetCode 253
 * 
 * Problem Statement:
 * Given an array of meeting time intervals where intervals[i] = [start_i, end_i],
 * return the minimum number of conference rooms required.
 * 
 * Example 1:
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 * Explanation: We need one room for meeting [0,30] and another for [5,10] and [15,20].
 * 
 * Example 2:
 * Input: intervals = [[7,10],[2,4]]
 * Output: 1
 * Explanation: Only one meeting room is needed.
 * 
 * Constraints:
 * - 1 <= intervals.length <= 10^4
 * - 0 <= start_i < end_i <= 10^6
 */

public class MeetingRoom253 {
    
    public static void main(String[] args) {
        MeetingRoom253 solution = new MeetingRoom253();
        
        // Test Case 1
        int[][] intervals1 = {{0,30}, {5,10}, {15,20}};
        System.out.println("Test Case 1:");
        System.out.println("Input: " + Arrays.deepToString(intervals1));
        System.out.println("Output: " + solution.minMeetingRooms(intervals1));
        System.out.println("Expected: 2");
        System.out.println();
        
        // Test Case 2
        int[][] intervals2 = {{7,10}, {2,4}};
        System.out.println("Test Case 2:");
        System.out.println("Input: " + Arrays.deepToString(intervals2));
        System.out.println("Output: " + solution.minMeetingRooms(intervals2));
        System.out.println("Expected: 1");
        System.out.println();
        
        // Test Case 3 - Edge case
        int[][] intervals3 = {{9,10}, {4,9}, {4,17}};
        System.out.println("Test Case 3:");
        System.out.println("Input: " + Arrays.deepToString(intervals3));
        System.out.println("Output: " + solution.minMeetingRooms(intervals3));
        System.out.println("Expected: 2");
        System.out.println();
        
        // FAILING TEST CASES - Your algorithm will give wrong answers
        
        // Test Case 4 - Multiple overlaps at same time
        int[][] intervals4 = {{1,5}, {2,6}, {3,7}, {4,8}};
        System.out.println("Test Case 4 (FAILS):");
        System.out.println("Input: " + Arrays.deepToString(intervals4));
        System.out.println("Your Output: " + solution.minMeetingRooms(intervals4));
        System.out.println("Expected: 4 (all meetings overlap)");
        System.out.println("Your algorithm gives wrong answer!");
        System.out.println();
        
        // Test Case 5 - Complex overlapping pattern
        int[][] intervals5 = {{0,30}, {5,10}, {15,20}, {25,35}};
        System.out.println("Test Case 5 (FAILS):");
        System.out.println("Input: " + Arrays.deepToString(intervals5));
        System.out.println("Your Output: " + solution.minMeetingRooms(intervals5));
        System.out.println("Expected: 2");
        System.out.println("Your algorithm might give wrong answer due to sorting by end time!");
        System.out.println();
        
        // Test Case 6 - Three meetings all overlapping
        int[][] intervals6 = {{1,10}, {5,15}, {8,20}};
        System.out.println("Test Case 6 (FAILS):");
        System.out.println("Input: " + Arrays.deepToString(intervals6));
        System.out.println("Your Output: " + solution.minMeetingRooms(intervals6));
        System.out.println("Expected: 3 (all three overlap at time 8-10)");
        System.out.println("Your algorithm gives wrong answer!");
        System.out.println();
        
        // Test Case 7 - Wrong sorting demonstrates the issue
        int[][] intervals7 = {{13,15}, {1,13}, {6,9}};
        System.out.println("Test Case 7 (FAILS):");
        System.out.println("Input: " + Arrays.deepToString(intervals7));
        System.out.println("Your Output: " + solution.minMeetingRooms(intervals7));
        System.out.println("Expected: 2");
        System.out.println("Sorting by end time instead of start time causes issues!");
        System.out.println();
        
        // Test Case 8 - Single room reused multiple times
        int[][] intervals8 = {{1,2}, {3,4}, {5,6}, {7,8}};
        System.out.println("Test Case 8 (Should work):");
        System.out.println("Input: " + Arrays.deepToString(intervals8));
        System.out.println("Your Output: " + solution.minMeetingRooms(intervals8));
        System.out.println("Expected: 1");
        System.out.println("This should work with your algorithm");
        System.out.println();
        
        // Test Case 9 - Overlapping chain (should need 3 rooms)
        int[][] intervals9 = {{0,30},{5,10}, {15,20}};
        System.out.println("Test Case 9 (FAILS):");
        System.out.println("Input: " + Arrays.deepToString(intervals9));
        System.out.println("Your Output: " + solution.minMeetingRooms(intervals9));
        System.out.println("Expected: 3 (at time 3, three meetings overlap: [1,3], [2,4], [3,5])");
        System.out.println("Your algorithm gives wrong answer!");
        System.out.println();

        
        int[][] intervals10 = {{1,10}, {8,9}, {3,8}, {4,7}};
        System.out.println("Test Case 10 (FAILS):");
        System.out.println("Input: " + Arrays.deepToString(intervals10));
        System.out.println("Your Output: " + solution.minMeetingRooms(intervals10));
        System.out.println("Expected: 4 (all meetings overlap at some point)");
        System.out.println("Your algorithm gives wrong answer!");
        System.out.println();
    }
    
    /**
     * Find the minimum number of meeting rooms required
     * @param intervals - array of meeting time intervals [start, end]
     * @return minimum number of conference rooms needed
     */
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int [] current = intervals[0];
        int overlaps = 0;

        for(int i=1; i<intervals.length; i++){
            int next[] = intervals[i];

            if(current[1] > next[0]){
                 overlaps++;
            }else{
                current = next;
            }
        }
        
        return  overlaps+1; // Replace with your solution
    }
    
    /**
     * Helper method to print intervals nicely
     * @param intervals - array of intervals
     */
    public void printIntervals(int[][] intervals) {
        System.out.print("[");
        for (int i = 0; i < intervals.length; i++) {
            System.out.print("[" + intervals[i][0] + "," + intervals[i][1] + "]");
            if (i < intervals.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}