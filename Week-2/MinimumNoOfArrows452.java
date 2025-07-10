import java.util.*;

/**
 * Minimum Number of Arrows to Burst Balloons - LeetCode 452
 * 
 * Problem Statement:
 * There are some spherical balloons taped onto a flat wall that represents the XY-plane.
 * The balloons are represented as a 2D integer array points where points[i] = [xstart, xend]
 * denotes a balloon whose horizontal diameter stretches from xstart to xend.
 * You do not know the exact y-coordinates of the balloons.
 * 
 * Arrows can be shot up directly vertically (in the positive y-direction) from different
 * points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x
 * if xstart <= x <= xend. There is no limit to the number of arrows that can be shot.
 * A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
 * 
 * Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
 * 
 * Example 1:
 * Input: points = [[10,16],[2,8],[1,6],[7,12]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 * - Shoot an arrow at x = 6, bursting balloons [2,8] and [1,6].
 * - Shoot an arrow at x = 11, bursting balloons [10,16] and [7,12].
 * 
 * Example 2:
 * Input: points = [[1,2],[3,4],[5,6],[7,8]]
 * Output: 4
 * Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
 * 
 * Example 3:
 * Input: points = [[1,2],[2,3],[3,4],[4,5]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 * - Shoot an arrow at x = 2, bursting balloons [1,2] and [2,3].
 * - Shoot an arrow at x = 4, bursting balloons [3,4] and [4,5].
 * 
 * Constraints:
 * - 1 <= points.length <= 10^5
 * - points[i].length == 2
 * - -2^31 <= xstart < xend <= 2^31 - 1
 */

public class MinimumNoOfArrows452 {
    
    public static void main(String[] args) {
        MinimumNoOfArrows452 solution = new MinimumNoOfArrows452();
        
        // Test Case 1
        int[][] points1 = {{10,16}, {2,8}, {1,6}, {7,12}};
        System.out.println("Test Case 1:");
        System.out.println("Input: " + Arrays.deepToString(points1));
        System.out.println("Output: " + solution.findMinArrowShots(points1));
        System.out.println("Expected: 2");
        System.out.println();
        
        // Test Case 2
        int[][] points2 = {{1,2}, {3,4}, {5,6}, {7,8}};
        System.out.println("Test Case 2:");
        System.out.println("Input: " + Arrays.deepToString(points2));
        System.out.println("Output: " + solution.findMinArrowShots(points2));
        System.out.println("Expected: 4");
        System.out.println();
        
        // Test Case 3
        int[][] points3 = {{1,2}, {2,3}, {3,4}, {4,5}};
        System.out.println("Test Case 3:");
        System.out.println("Input: " + Arrays.deepToString(points3));
        System.out.println("Output: " + solution.findMinArrowShots(points3));
        System.out.println("Expected: 2");
        System.out.println();
        
        // Test Case 4 - Edge case
        int[][] points4 = {{1,10}};
        System.out.println("Test Case 4:");
        System.out.println("Input: " + Arrays.deepToString(points4));
        System.out.println("Output: " + solution.findMinArrowShots(points4));
        System.out.println("Expected: 1");
        System.out.println();
    }
    
    /**
     * Find the minimum number of arrows needed to burst all balloons
     * @param points - array of balloon positions [start, end]
     * @return minimum number of arrows needed
     */
    public int findMinArrowShots(int[][] points) {
        int n = points.length;
        Arrays.sort(points, (a,b) -> Integer.compare(a[1],b[1]));

        int arrow = 1;
         
        int end = points[0][1];

        for(int i=1; i<n; i++){
            if(end < points[i][0]){
                arrow++;
                end = points[i][1];
            }
        }

        return arrow;
    }
    
    /**
     * Helper method to print balloon points nicely
     * @param points - array of balloon points
     */
    public void printPoints(int[][] points) {
        System.out.print("[");
        for (int i = 0; i < points.length; i++) {
            System.out.print("[" + points[i][0] + "," + points[i][1] + "]");
            if (i < points.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
