import java.util.*;

/**
 * LeetCode #3: Longest Substring Without Repeating Characters
 * 
 * Problem Statement:
 * Given a string s, find the length of the longest substring without repeating characters.
 * 
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * 
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * 
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeating3 {
    
    // Method 1: Brute Force Approach
    // Time Complexity: O(n³), Space Complexity: O(min(m,n))
    public int lengthOfLongestSubstringBruteForce(String s) {
        int maxLength = 0;
        
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (allUnique(s, i, j)) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        
        return maxLength;
    }
    
    private boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i <= end; i++) {
            if (set.contains(s.charAt(i))) {
                return false;
            }
            set.add(s.charAt(i));
        }
        return true;
    }
    
    // Method 2: Sliding Window with HashSet (Optimal)
    // Time Complexity: O(n), Space Complexity: O(min(m,n))
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0;
        int maxLength = 0;
        
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            
            // If character is already in set, shrink window from left
            while (set.contains(rightChar)) {
                set.remove(s.charAt(left));
                left++;
            }
            
            // Add current character to set
            set.add(rightChar);
            
            // Update max length
            maxLength = Math.max(maxLength, right - left + 1);
            
            right++;
        }
        
        return maxLength;
    }
    
    // Method 3: Sliding Window with HashMap (Optimized)
    // Time Complexity: O(n), Space Complexity: O(min(m,n))
    public int lengthOfLongestSubstringOptimized(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            
            if (map.containsKey(rightChar)) {
                left = Math.max(left, map.get(rightChar) + 1);
            }
            
            map.put(rightChar, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    public static void main(String[] args) {
        LongestSubstringWithoutRepeating3 solution = new LongestSubstringWithoutRepeating3();
        
        // Test Case 1
        String s1 = "abcabcbb";
        int result1 = solution.lengthOfLongestSubstring(s1);
        System.out.println("Test Case 1: " + result1); // Expected: 3
        
        // Test Case 2
        String s2 = "bbbbb";
        int result2 = solution.lengthOfLongestSubstring(s2);
        System.out.println("Test Case 2: " + result2); // Expected: 1
        
        // Test Case 3
        String s3 = "pwwkew";
        int result3 = solution.lengthOfLongestSubstring(s3);
        System.out.println("Test Case 3: " + result3); // Expected: 3
        
        // Test Case 4
        String s4 = "";
        int result4 = solution.lengthOfLongestSubstring(s4);
        System.out.println("Test Case 4: " + result4); // Expected: 0
        
        // Compare different approaches
        System.out.println("Brute Force: " + solution.lengthOfLongestSubstringBruteForce(s1));
        System.out.println("Optimized: " + solution.lengthOfLongestSubstringOptimized(s1));
    }
}
