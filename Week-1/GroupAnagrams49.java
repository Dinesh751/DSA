import java.util.*;

/**
 * LeetCode #49: Group Anagrams
 * 
 * Problem Statement:
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, 
 * typically using all the original letters exactly once.
 * 
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 * 
 * Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 */
public class GroupAnagrams49 {
    
    // Method 1: Sorting Approach
    // Time Complexity: O(n * k log k), Space Complexity: O(n * k)
    // where n = number of strings, k = maximum length of a string
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            // Sort characters to create a key
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            
            // Add string to the corresponding group
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
    
    // Method 2: Character Count Approach (Optimal)
    // Time Complexity: O(n * k), Space Complexity: O(n * k)
    public List<List<String>> groupAnagramsOptimal(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            // Create character count array as key
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            
            // Convert count array to string key
            StringBuilder keyBuilder = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                keyBuilder.append('#');
                keyBuilder.append(count[i]);
            }
            String key = keyBuilder.toString();
            
            // Add string to the corresponding group
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
    
    // Method 3: Prime Number Approach (Alternative)
    // Time Complexity: O(n * k), Space Complexity: O(n * k)
    public List<List<String>> groupAnagramsPrime(String[] strs) {
        Map<Long, List<String>> map = new HashMap<>();
        
        // Prime numbers for each letter
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 
                       53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        
        for (String str : strs) {
            long key = 1;
            for (char c : str.toCharArray()) {
                key *= primes[c - 'a'];
            }
            
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }
        
        return new ArrayList<>(map.values());
    }
    
    // Helper method to print results
    private void printResult(List<List<String>> result) {
        System.out.print("[");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i < result.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    public static void main(String[] args) {
        GroupAnagrams49 solution = new GroupAnagrams49();
        
        // Test Case 1
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result1 = solution.groupAnagrams(strs1);
        System.out.print("Test Case 1: ");
        solution.printResult(result1);
        
        // Test Case 2
        String[] strs2 = {""};
        List<List<String>> result2 = solution.groupAnagrams(strs2);
        System.out.print("Test Case 2: ");
        solution.printResult(result2);
        
        // Test Case 3
        String[] strs3 = {"a"};
        List<List<String>> result3 = solution.groupAnagrams(strs3);
        System.out.print("Test Case 3: ");
        solution.printResult(result3);
        
        // Test different approaches
        System.out.print("Optimal approach: ");
        solution.printResult(solution.groupAnagramsOptimal(strs1));
        
        System.out.print("Prime approach: ");
        solution.printResult(solution.groupAnagramsPrime(strs1));
    }
}
