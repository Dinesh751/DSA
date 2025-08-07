/**
 * LeetCode #125: Valid Palindrome
 * 
 * Problem Statement:
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters 
 * and removing all non-alphanumeric characters, it reads the same forward and backward. 
 * Alphanumeric characters include letters and numbers.
 * 
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * 
 * Example 1:
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * 
 * Example 2:
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 * 
 * Example 3:
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric characters.
 * Since an empty string reads the same forward and backward, it is a palindrome.
 */
public class ValidPalindrome125 {
    
    // Method 1: StringBuilder Approach
    // Time Complexity: O(n), Space Complexity: O(n)
    public boolean isPalindromeStringBuilder(String s) {
        StringBuilder cleaned = new StringBuilder();
        
        // Clean the string: keep only alphanumeric characters and convert to lowercase
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }
        
        // Check if cleaned string is palindrome
        String cleanedStr = cleaned.toString();
        String reversed = cleaned.reverse().toString();
        
        return cleanedStr.equals(reversed);
    }
    
    // Method 2: Two Pointers Approach (Optimal)
    // Time Complexity: O(n), Space Complexity: O(1)
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            // Skip non-alphanumeric characters from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            
            // Skip non-alphanumeric characters from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            
            // Compare characters (case-insensitive)
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
    
    // Method 3: Using Regular Expression
    // Time Complexity: O(n), Space Complexity: O(n)
    public boolean isPalindromeRegex(String s) {
        // Remove non-alphanumeric characters and convert to lowercase
        String cleaned = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        
        // Check if cleaned string equals its reverse
        StringBuilder sb = new StringBuilder(cleaned);
        return cleaned.equals(sb.reverse().toString());
    }
    
    // Method 4: Recursive Approach
    // Time Complexity: O(n), Space Complexity: O(n) due to recursion stack
    public boolean isPalindromeRecursive(String s) {
        return isPalindromeHelper(s, 0, s.length() - 1);
    }
    
    private boolean isPalindromeHelper(String s, int left, int right) {
        // Base case
        if (left >= right) {
            return true;
        }
        
        // Skip non-alphanumeric characters from left
        if (!Character.isLetterOrDigit(s.charAt(left))) {
            return isPalindromeHelper(s, left + 1, right);
        }
        
        // Skip non-alphanumeric characters from right
        if (!Character.isLetterOrDigit(s.charAt(right))) {
            return isPalindromeHelper(s, left, right - 1);
        }
        
        // Compare characters
        if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
            return false;
        }
        
        return isPalindromeHelper(s, left + 1, right - 1);
    }
    
    public static void main(String[] args) {
        ValidPalindrome125 solution = new ValidPalindrome125();
        
        // Test Case 1
        String s1 = "A man, a plan, a canal: Panama";
        boolean result1 = solution.isPalindrome(s1);
        System.out.println("Test Case 1: " + result1); // Expected: true
        
        // Test Case 2
        String s2 = "race a car";
        boolean result2 = solution.isPalindrome(s2);
        System.out.println("Test Case 2: " + result2); // Expected: false
        
        // Test Case 3
        String s3 = " ";
        boolean result3 = solution.isPalindrome(s3);
        System.out.println("Test Case 3: " + result3); // Expected: true
        
        // Test Case 4
        String s4 = "Madam";
        boolean result4 = solution.isPalindrome(s4);
        System.out.println("Test Case 4: " + result4); // Expected: true
        
        // Test different approaches
        System.out.println("Regex approach: " + solution.isPalindromeRegex(s1));
        System.out.println("Recursive approach: " + solution.isPalindromeRecursive(s1));
    }
}
