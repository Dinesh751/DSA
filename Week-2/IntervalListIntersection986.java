import java.util.*;

/**
 * Interval List Intersections - LeetCode 986
 * 
 * Problem Statement:
 * You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [start_i, end_i] 
 * and secondList[j] = [start_j, end_j]. Each list of intervals is pairwise disjoint and in sorted order.
 * Return the intersection of these two interval lists.
 * 
 * Example 1:
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * 
 * Example 2:
 * Input: firstList = [[1,3],[5,9]], secondList = []
 * Output: []
 * 
 * Constraints:
 * - 0 <= firstList.length, secondList.length <= 1000
 * - firstList.length + secondList.length >= 1
 * - 0 <= start_i < end_i <= 10^9
 * - end_i < start_i+1 for all i
 */

public class IntervalListIntersection986 {
    
    public static void main(String[] args) {
        IntervalListIntersection986 solution = new IntervalListIntersection986();
        
        // Test Case 1
        int[][] firstList1 = {{0,2}, {5,10}, {13,23}, {24,25}};
        int[][] secondList1 = {{1,5}, {8,12}, {15,24}, {25,26}};
        System.out.println("Test Case 1:");
        System.out.println("FirstList: " + Arrays.deepToString(firstList1));
        System.out.println("SecondList: " + Arrays.deepToString(secondList1));
        System.out.println("Output: " + Arrays.deepToString(solution.intervalIntersection(firstList1, secondList1)));
        System.out.println("Expected: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]");
        System.out.println();
        
        // Test Case 2
        int[][] firstList2 = {{1,3}, {5,9}};
        int[][] secondList2 = {};
        System.out.println("Test Case 2:");
        System.out.println("FirstList: " + Arrays.deepToString(firstList2));
        System.out.println("SecondList: " + Arrays.deepToString(secondList2));
        System.out.println("Output: " + Arrays.deepToString(solution.intervalIntersection(firstList2, secondList2)));
        System.out.println("Expected: []");
        System.out.println();
        
        // Test Case 3 - Edge case
        int[][] firstList3 = {{0,2}, {5,10}};
        int[][] secondList3 = {{1,6}, {8,10}};
        System.out.println("Test Case 3:");
        System.out.println("FirstList: " + Arrays.deepToString(firstList3));
        System.out.println("SecondList: " + Arrays.deepToString(secondList3));
        System.out.println("Output: " + Arrays.deepToString(solution.intervalIntersection(firstList3, secondList3)));
        System.out.println("Expected: [[1,2],[5,6],[8,10]]");
        System.out.println();
    }
    
    /**
     * Helper method to get intersection of two intervals
     * @param interval1 - first interval
     * @param interval2 - second interval
     * @return intersection interval
     */
    public static int[] getIntersection(int[] interval1, int[] interval2) {
        int res[] = new int[2];
        res[0] = Math.max(interval1[0], interval2[0]);
        res[1] = Math.min(interval1[1], interval2[1]);
        return res;
    }

    /**
     * Helper method to check if two intervals overlap
     * @param interval1 - first interval
     * @param interval2 - second interval
     * @return true if intervals overlap, false otherwise
     */
    public static boolean isOverlapping(int[] interval1, int[] interval2) {
        return Math.max(interval1[0], interval2[0]) <= Math.min(interval1[1], interval2[1]);
    }

    /**
     * Find the intersection of two interval lists
     * @param firstList - first list of intervals
     * @param secondList - second list of intervals
     * @return array of intersection intervals
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> list = new ArrayList<>();

        if(firstList.length == 0 || secondList.length == 0) {
            return list.toArray(new int[list.size()][]);
        }

        int i=0, j=0;
        while(i < firstList.length && j < secondList.length) {
            int[] interval1 = firstList[i];
            int[] interval2 = secondList[j];
            
            if(isOverlapping(interval1, interval2)) {
                int[] intersection = getIntersection(interval1, interval2);
                list.add(intersection);
            }

            if(interval1[1] >= interval2[1]) {
                j++;
            } else {
                i++;
            }
        }

        return list.toArray(new int[list.size()][]);
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
