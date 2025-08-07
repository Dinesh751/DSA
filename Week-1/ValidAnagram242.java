import java.util.*;

/**
 * LeetCode #242: Valid Anagram
 * 
 * Problem Statement:
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word 
 * or phrase, typically using all the original letters exactly once.
 * 
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * 
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 */
public class ValidAnagram242 {
    
    // Method 1: Sorting Approach
    // Time Complexity: O(n log n), Space Complexity: O(n)
    public boolean isAnagramSorting(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        
        Arrays.sort(sArray);
        Arrays.sort(tArray);
        
        return Arrays.equals(sArray, tArray);
    }
    
    // Method 2: HashMap Approach
    // Time Complexity: O(n), Space Complexity: O(n)
    public boolean isAnagramHashMap(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        Map<Character, Integer> map = new HashMap<>();
        
        // Count characters in string s
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        // Subtract characters from string t
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) - 1);
            if (map.get(c) < 0) {
                return false;
            }
        }
        
        // Check if all counts are zero
        for (int count : map.values()) {
            if (count != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    // Method 3: Array Counter Approach (Optimal for lowercase letters)
    // Time Complexity: O(n), Space Complexity: O(1)
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        int[] counter = new int[26]; // For lowercase English letters
        
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    // Method 4: Two Pointers Approach (after sorting)
    // Time Complexity: O(n log n), Space Complexity: O(n)
    public boolean isAnagramTwoPointers(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        
        Arrays.sort(sArray);
        Arrays.sort(tArray);
        
        int left = 0, right = 0;
        while (left < sArray.length && right < tArray.length) {
            if (sArray[left] != tArray[right]) {
                return false;
            }
            left++;
            right++;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        ValidAnagram242 solution = new ValidAnagram242();
        
        // Test Case 1
        String s1 = "anagram", t1 = "nagaram";
        boolean result1 = solution.isAnagram(s1, t1);
        System.out.println("Test Case 1: " + result1); // Expected: true
        
        // Test Case 2
        String s2 = "rat", t2 = "car";
        boolean result2 = solution.isAnagram(s2, t2);
        System.out.println("Test Case 2: " + result2); // Expected: false
        
        // Test Case 3
        String s3 = "listen", t3 = "silent";
        boolean result3 = solution.isAnagram(s3, t3);
        System.out.println("Test Case 3: " + result3); // Expected: true
        
        // Test different approaches
        System.out.println("Sorting approach: " + solution.isAnagramSorting(s1, t1));
        System.out.println("HashMap approach: " + solution.isAnagramHashMap(s1, t1));
        System.out.println("Two pointers approach: " + solution.isAnagramTwoPointers(s1, t1));
    }
}
