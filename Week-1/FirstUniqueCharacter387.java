import java.util.*;

/**
 * LeetCode #387: First Unique Character in a String
 * 
 * Problem Statement:
 * Given a string s, find the first non-repeating character in it and return its index. 
 * If it does not exist, return -1.
 * 
 * Example 1:
 * Input: s = "leetcode"
 * Output: 0
 * 
 * Example 2:
 * Input: s = "loveleetcode"
 * Output: 2
 * 
 * Example 3:
 * Input: s = "aabb"
 * Output: -1
 */
public class FirstUniqueCharacter387 {
    
    // Method 1: HashMap Approach
    // Time Complexity: O(n), Space Complexity: O(1) - at most 26 lowercase letters
    public int firstUniqChar(String s) {
        Map<Character, Integer> charCount = new HashMap<>();
        
        // Count frequency of each character
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // Find first character with frequency 1
        for (int i = 0; i < s.length(); i++) {
            if (charCount.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        
        return -1;
    }
    
    // Method 2: Array Counter Approach (Optimal for lowercase letters)
    // Time Complexity: O(n), Space Complexity: O(1)
    public int firstUniqCharArray(String s) {
        int[] counter = new int[26]; // For lowercase English letters
        
        // Count frequency of each character
        for (char c : s.toCharArray()) {
            counter[c - 'a']++;
        }
        
        // Find first character with frequency 1
        for (int i = 0; i < s.length(); i++) {
            if (counter[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        
        return -1;
    }
    
    // Method 3: LinkedHashMap Approach (maintains insertion order)
    // Time Complexity: O(n), Space Complexity: O(1)
    public int firstUniqCharLinkedHashMap(String s) {
        Map<Character, Integer> charCount = new LinkedHashMap<>();
        
        // Count frequency of each character
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // Find first character with frequency 1
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            if (entry.getValue() == 1) {
                return s.indexOf(entry.getKey());
            }
        }
        
        return -1;
    }
    
    // Method 4: Two Pass with indexOf and lastIndexOf
    // Time Complexity: O(n), Space Complexity: O(1)
    public int firstUniqCharIndexOf(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (s.indexOf(c) == s.lastIndexOf(c)) {
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        FirstUniqueCharacter387 solution = new FirstUniqueCharacter387();
        
        // Test Case 1
        String s1 = "leetcode";
        int result1 = solution.firstUniqChar(s1);
        System.out.println("Test Case 1: " + result1); // Expected: 0
        
        // Test Case 2
        String s2 = "loveleetcode";
        int result2 = solution.firstUniqChar(s2);
        System.out.println("Test Case 2: " + result2); // Expected: 2
        
        // Test Case 3
        String s3 = "aabb";
        int result3 = solution.firstUniqChar(s3);
        System.out.println("Test Case 3: " + result3); // Expected: -1
        
        // Test Case 4
        String s4 = "abccba";
        int result4 = solution.firstUniqChar(s4);
        System.out.println("Test Case 4: " + result4); // Expected: -1
        
        // Test different approaches
        System.out.println("Array approach: " + solution.firstUniqCharArray(s1));
        System.out.println("LinkedHashMap approach: " + solution.firstUniqCharLinkedHashMap(s1));
        System.out.println("IndexOf approach: " + solution.firstUniqCharIndexOf(s1));
    }
}
