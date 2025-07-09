import java.util.*;

public class RemoveOverlapsIntervals {
    public static void main(String[] args) {
        // Print hello message
        System.out.println("Hello Java!");
        
        // Test the eraseOverlapIntervals method
        int[][] intervals = {{1,2}, {2,3}, {3,4}, {1,3}};
        int result = eraseOverlapIntervals(intervals);
        System.out.println("Number of intervals to remove: " + result);
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        
        int removals = 0;
        int[] current = intervals[0];
        for(int i=1; i<intervals.length; i++){
            int[] next = intervals[i];
            if(current[1] > next[0]){
                removals++;
            }else{
                current = next;
            }
        }
        return removals;
    }
}