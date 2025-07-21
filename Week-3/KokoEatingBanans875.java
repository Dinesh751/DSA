/*
LeetCode 875: Koko Eating Bananas

Problem Statement:
Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. 
The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas 
and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them 
instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards come back.

Return the minimum integer k such that she can eat all the bananas within h hours.

Example 1:
Input: piles = [3,6,7,11], h = 8
Output: 4
Explanation: If Koko eats at speed 4 bananas/hour:
- Hour 1: Eat 3 bananas from pile 0 (pile becomes [0,6,7,11])
- Hour 2: Eat 4 bananas from pile 1 (pile becomes [0,2,7,11])
- Hour 3: Eat 2 bananas from pile 1 (pile becomes [0,0,7,11])
- Hour 4: Eat 4 bananas from pile 2 (pile becomes [0,0,3,11])
- Hour 5: Eat 3 bananas from pile 2 (pile becomes [0,0,0,11])
- Hour 6: Eat 4 bananas from pile 3 (pile becomes [0,0,0,7])
- Hour 7: Eat 4 bananas from pile 3 (pile becomes [0,0,0,3])
- Hour 8: Eat 3 bananas from pile 3 (pile becomes [0,0,0,0])

Example 2:
Input: piles = [30,11,23,4,20], h = 5
Output: 30
Explanation: Koko needs to eat at speed 30 to finish in exactly 5 hours.

Example 3:
Input: piles = [30,11,23,4,20], h = 6
Output: 23
Explanation: Koko can eat at speed 23 and finish in 6 hours.

Constraints:
- 1 <= piles.length <= 10^4
- piles[i] <= 10^9
- 1 <= h <= 10^9
- h >= piles.length (there's always a solution)

Hints:
1. Think about this as a binary search problem
2. The minimum speed is 1, maximum speed could be the largest pile
3. For a given speed k, you can calculate if Koko can finish within h hours
4. Use binary search to find the minimum valid speed

Time Complexity Goal: O(n * log(max(piles)))
Space Complexity Goal: O(1)
*/
import java.util.Arrays;
public class KokoEatingBanans875 {
    
    // TODO: Implement your solution here
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = Arrays.stream(piles).max().getAsInt();
        int result = high;

        while (low <= high){
            int mid = low + (high - low)/2;
            long hours = 0; // Use long to avoid overflow

            for(int pile: piles){
                // Fix: Use proper ceiling division
                hours += (pile + mid - 1) / mid; // This is equivalent to Math.ceil(pile/mid)
                // Alternative: hours += (long)Math.ceil((double)pile / mid);
            }

            if(hours <= h){
                high = mid - 1;
                result = mid;
            }else{
                low = mid + 1;
            }
        }
        return result;
    }
    
    // Test cases for verification
    public static void main(String[] args) {
        KokoEatingBanans875 solution = new KokoEatingBanans875();
        
        // Test case 1
        int[] piles1 = {3, 6, 7, 11};
        int h1 = 8;
        System.out.println("Test Case 1:");
        System.out.println("Input: piles = [3,6,7,11], h = " + h1);
        System.out.println("Expected: 4");
        System.out.println("Actual: " + solution.minEatingSpeed(piles1, h1));
        System.out.println();
        
        // Test case 2
        int[] piles2 = {30, 11, 23, 4, 20};
        int h2 = 5;
        System.out.println("Test Case 2:");
        System.out.println("Input: piles = [30,11,23,4,20], h = " + h2);
        System.out.println("Expected: 30");
        System.out.println("Actual: " + solution.minEatingSpeed(piles2, h2));
        System.out.println();
        
        // Test case 3
        int[] piles3 = {30, 11, 23, 4, 20};
        int h3 = 6;
        System.out.println("Test Case 3:");
        System.out.println("Input: piles = [30,11,23,4,20], h = " + h3);
        System.out.println("Expected: 23");
        System.out.println("Actual: " + solution.minEatingSpeed(piles3, h3));
        System.out.println();
        
        // Additional test case 4
        int[] piles4 = {312884470};
        int h4 = 968709470;
        System.out.println("Test Case 4 (Edge case):");
        System.out.println("Input: piles = [312884470], h = " + h4);
        System.out.println("Expected: 1");
        System.out.println("Actual: " + solution.minEatingSpeed(piles4, h4));
    }
}
