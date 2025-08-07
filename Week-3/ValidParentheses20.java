import java.util.*;

/**
 * LeetCode #20: Valid Parentheses
 * 
 * Problem Statement:
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', 
 * determine if the input string is valid.
 * 
 * An input string is valid if:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 * 
 * Example 1:
 * Input: s = "()"
 * Output: true
 * 
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 * 
 * Example 3:
 * Input: s = "(]"
 * Output: false
 */
public class ValidParentheses20 {
    
    // Method 1: Stack with HashMap (Most readable)
    // Time Complexity: O(n), Space Complexity: O(n)
    public boolean isValid(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false; // Odd length strings can't be valid
        }
        
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> closeToOpen = new HashMap<>();
        closeToOpen.put(')', '(');
        closeToOpen.put('}', '{');
        closeToOpen.put(']', '[');
        
        for (char c : s.toCharArray()) {
            if (closeToOpen.containsKey(c)) {
                // Closing bracket
                if (stack.isEmpty() || stack.pop() != closeToOpen.get(c)) {
                    return false;
                }
            } else {
                // Opening bracket
                stack.push(c);
            }
        }
        
        return stack.isEmpty();
    }
    
    // Method 2: Stack with switch statement (Faster)
    // Time Complexity: O(n), Space Complexity: O(n)
    public boolean isValidSwitch(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false;
        }
        
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                case '{':
                case '[':
                    stack.push(c);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                    break;
                default:
                    return false; // Invalid character
            }
        }
        
        return stack.isEmpty();
    }
    
    // Method 3: Array as Stack (Memory efficient)
    // Time Complexity: O(n), Space Complexity: O(n)
    public boolean isValidArray(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false;
        }
        
        char[] stack = new char[s.length()];
        int top = -1;
        
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack[++top] = c;
            } else {
                if (top == -1) return false; // No matching opening bracket
                
                char openBracket = stack[top--];
                if ((c == ')' && openBracket != '(') ||
                    (c == '}' && openBracket != '{') ||
                    (c == ']' && openBracket != '[')) {
                    return false;
                }
            }
        }
        
        return top == -1;
    }
    
    // Method 4: Replacement approach (Creative but inefficient)
    // Time Complexity: O(n²), Space Complexity: O(n)
    public boolean isValidReplacement(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false;
        }
        
        // Keep removing valid pairs until no more can be removed
        while (s.contains("()") || s.contains("{}") || s.contains("[]")) {
            s = s.replace("()", "").replace("{}", "").replace("[]", "");
        }
        
        return s.isEmpty();
    }
    
    // Method 5: Detailed step-by-step for learning
    public boolean isValidDetailed(String s) {
        System.out.println("=== Valid Parentheses Analysis ===");
        System.out.println("Input string: \"" + s + "\"");
        
        if (s == null || s.length() % 2 != 0) {
            System.out.println("Early termination: null or odd length");
            return false;
        }
        
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put('}', '{');
        pairs.put(']', '[');
        
        System.out.println("\nProcessing each character:");
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.printf("Step %d: Processing '%c'", i + 1, c);
            
            if (pairs.containsKey(c)) {
                // Closing bracket
                System.out.print(" (closing bracket)");
                if (stack.isEmpty()) {
                    System.out.println(" -> FAIL: No matching opening bracket");
                    return false;
                }
                
                char popped = stack.pop();
                if (popped != pairs.get(c)) {
                    System.out.printf(" -> FAIL: Expected '%c' but found '%c'%n", pairs.get(c), popped);
                    return false;
                }
                System.out.printf(" -> Match found with '%c'%n", popped);
            } else {
                // Opening bracket
                System.out.print(" (opening bracket) -> Push to stack");
                stack.push(c);
                System.out.println();
            }
            
            System.out.println("   Stack: " + stack);
        }
        
        boolean result = stack.isEmpty();
        System.out.println("\nFinal result: " + (result ? "VALID" : "INVALID"));
        if (!result) {
            System.out.println("Reason: Unmatched opening brackets remain: " + stack);
        }
        
        return result;
    }
    

    
    public static void main(String[] args) {
        ValidParentheses20 solution = new ValidParentheses20();
        
        // Test Case 1 - Simple valid parentheses
        System.out.println("=== Test Case 1: Simple Valid ===");
        String test1 = "()";
        boolean result1 = solution.isValid(test1);
        System.out.println("Input: \"" + test1 + "\"");
        System.out.println("Result: " + result1);
        System.out.println("Expected: true\n");
        
        // Test Case 2 - Multiple types valid
        System.out.println("=== Test Case 2: Multiple Types Valid ===");
        String test2 = "()[]{}" ;
        boolean result2 = solution.isValid(test2);
        System.out.println("Input: \"" + test2 + "\"");
        System.out.println("Result: " + result2);
        System.out.println("Expected: true\n");
        
        // Test Case 3 - Invalid mismatch
        System.out.println("=== Test Case 3: Invalid Mismatch ===");
        String test3 = "(]";
        boolean result3 = solution.isValid(test3);
        System.out.println("Input: \"" + test3 + "\"");
        System.out.println("Result: " + result3);
        System.out.println("Expected: false\n");
        
        // Test Case 4 - Nested valid
        System.out.println("=== Test Case 4: Nested Valid ===");
        String test4 = "{[()]}";
        boolean result4 = solution.isValid(test4);
        System.out.println("Input: \"" + test4 + "\"");
        System.out.println("Result: " + result4);
        System.out.println("Expected: true\n");
        
        // Test Case 5 - Unmatched opening
        System.out.println("=== Test Case 5: Unmatched Opening ===");
        String test5 = "((";
        boolean result5 = solution.isValid(test5);
        System.out.println("Input: \"" + test5 + "\"");
        System.out.println("Result: " + result5);
        System.out.println("Expected: false\n");
        
        // Test Case 6 - Complex nested
        System.out.println("=== Test Case 6: Complex Nested ===");
        String test6 = "{[()()]}()";
        boolean result6 = solution.isValid(test6);
        System.out.println("Input: \"" + test6 + "\"");
        System.out.println("Result: " + result6);
        System.out.println("Expected: true\n");
        
        // Detailed analysis
        System.out.println("=".repeat(50));
        solution.isValidDetailed("([)]");
        
        // Test all approaches
        System.out.println("\n=== Testing Different Approaches ===");
        String test = "()[]{}";
        System.out.println("Input: \"" + test + "\"");
        System.out.println("HashMap approach: " + solution.isValid(test));
        System.out.println("Switch approach: " + solution.isValidSwitch(test));
        System.out.println("Array approach: " + solution.isValidArray(test));
        System.out.println("Replacement approach: " + solution.isValidReplacement(test));
        
        // Edge cases
        System.out.println("\n=== Edge Cases ===");
        System.out.println("Empty string: " + solution.isValid(""));
        System.out.println("Single char: " + solution.isValid("("));
        System.out.println("Only closing: " + solution.isValid("))"));
        System.out.println("Only opening: " + solution.isValid("(("));
        
        // Performance test
        System.out.println("\n=== Performance Test ===");
        StringBuilder largeTest = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            largeTest.append("()");
        }
        
        long start = System.nanoTime();
        boolean perfResult = solution.isValid(largeTest.toString());
        long end = System.nanoTime();
        
        System.out.printf("Processed %d characters in %.2f μs%n", 
                         largeTest.length(), (end - start) / 1000.0);
        System.out.println("Result: " + perfResult);
    }
}
