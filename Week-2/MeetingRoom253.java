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
        

    }
    
    /**
     * Find the minimum number of meeting rooms required
     * @param intervals - array of meeting time intervals [start, end]
     * @return minimum number of conference rooms needed
     */
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> q = new PriorityQueue<>();

        q.add(intervals[0][1]);
        for(int i=1; i<intervals.length; i++){
            int current[] = intervals[i];
              if(current[0] >= q.peek()){
                q.poll();
              }
                q.add(current[1]);
              
             
        }   
        return q.size();   
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