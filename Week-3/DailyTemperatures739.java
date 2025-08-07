import java.util.*;

/**
 * LeetCode #739: Daily Temperatures
 * 
 * Problem Statement:
 * Given an array of integers temperatures represents the daily temperatures, 
 * return an array answer such that answer[i] is the number of days you have to wait 
 * after the ith day to get a warmer temperature. If there is no future day for which 
 * this is possible, keep answer[i] == 0.
 * 
 * Example 1:
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * 
 * Example 2:
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * 
 * Example 3:
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 */
public class DailyTemperatures739 {
    
    // Method 1: Monotonic Stack (Optimal)
    // Time Complexity: O(n), Space Complexity: O(n)
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>(); // Store indices
        
        for (int i = 0; i < n; i++) {
            // While current temperature is warmer than temperatures at indices in stack
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                result[prevIndex] = i - prevIndex; // Days to wait
            }
            stack.push(i); // Push current index
        }
        
        return result;
    }
    
    // Method 2: Brute Force (For comparison)
    // Time Complexity: O(n²), Space Complexity: O(1)
    public int[] dailyTemperaturesBruteForce(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    result[i] = j - i;
                    break;
                }
            }
            // If no warmer day found, result[i] remains 0
        }
        
        return result;
    }
    
    // Method 3: Reverse iteration with stack
    // Time Complexity: O(n), Space Complexity: O(n)
    public int[] dailyTemperaturesReverse(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        // Process from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Remove indices with temperatures <= current temperature
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            
            // If stack not empty, top element is next warmer day
            if (!stack.isEmpty()) {
                result[i] = stack.peek() - i;
            }
            
            stack.push(i);
        }
        
        return result;
    }
    
    // Method 4: Array-based stack (Memory optimized)
    // Time Complexity: O(n), Space Complexity: O(n)
    public int[] dailyTemperaturesArray(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        int[] stack = new int[n]; // Array as stack
        int top = -1; // Stack pointer
        
        for (int i = 0; i < n; i++) {
            while (top >= 0 && temperatures[i] > temperatures[stack[top]]) {
                int prevIndex = stack[top--];
                result[prevIndex] = i - prevIndex;
            }
            stack[++top] = i;
        }
        
        return result;
    }
    
    // Method 5: Detailed step-by-step for learning
    public int[] dailyTemperaturesDetailed(int[] temperatures) {
        System.out.println("=== Daily Temperatures Analysis ===");
        System.out.println("Input temperatures: " + Arrays.toString(temperatures));
        
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        System.out.println("\nProcessing each day:");
        
        for (int i = 0; i < n; i++) {
            System.out.printf("\nDay %d: Temperature %d°F%n", i, temperatures[i]);
            System.out.println("Stack before: " + stack);
            
            // Process stack while current temp is warmer
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                int daysToWait = i - prevIndex;
                result[prevIndex] = daysToWait;
                
                System.out.printf("  Found warmer day for Day %d (%d°F): %d days to wait%n", 
                                prevIndex, temperatures[prevIndex], daysToWait);
            }
            
            stack.push(i);
            System.out.println("Stack after: " + stack);
            System.out.println("Current result: " + Arrays.toString(result));
        }
        
        System.out.println("\nFinal result: " + Arrays.toString(result));
        return result;
    }
    
    // Method 6: Next Greater Element pattern variation
    public int[] dailyTemperaturesNGE(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        // This follows the classic Next Greater Element pattern
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }
        
        return result;
    }
    

    
    // Helper method to format temperature array with indices
    private String formatTemperatures(int[] temperatures) {
        StringBuilder sb = new StringBuilder();
        sb.append("Index: ");
        for (int i = 0; i < temperatures.length; i++) {
            sb.append(String.format("%3d ", i));
        }
        sb.append("\nTemp:  ");
        for (int temp : temperatures) {
            sb.append(String.format("%3d ", temp));
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        DailyTemperatures739 solution = new DailyTemperatures739();
        
        // Test Case 1 - Standard example
        System.out.println("=== Test Case 1: Standard Example ===");
        int[] temps1 = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(solution.formatTemperatures(temps1));
        int[] result1 = solution.dailyTemperatures(temps1);
        System.out.println("Result: " + Arrays.toString(result1));
        System.out.println("Expected: [1, 1, 4, 2, 1, 1, 0, 0]\n");
        
        // Test Case 2 - Strictly increasing
        System.out.println("=== Test Case 2: Strictly Increasing ===");
        int[] temps2 = {30, 40, 50, 60};
        System.out.println(solution.formatTemperatures(temps2));
        int[] result2 = solution.dailyTemperatures(temps2);
        System.out.println("Result: " + Arrays.toString(result2));
        System.out.println("Expected: [1, 1, 1, 0]\n");
        
        // Test Case 3 - Strictly decreasing
        System.out.println("=== Test Case 3: Strictly Decreasing ===");
        int[] temps3 = {90, 80, 70, 60};
        int[] result3 = solution.dailyTemperatures(temps3);
        System.out.println("Input: " + Arrays.toString(temps3));
        System.out.println("Result: " + Arrays.toString(result3));
        System.out.println("Expected: [0, 0, 0, 0]\n");
        
        // Test Case 4 - Single element
        System.out.println("=== Test Case 4: Single Element ===");
        int[] temps4 = {89};
        int[] result4 = solution.dailyTemperatures(temps4);
        System.out.println("Input: " + Arrays.toString(temps4));
        System.out.println("Result: " + Arrays.toString(result4));
        System.out.println("Expected: [0]\n");
        
        // Test Case 5 - All same temperatures
        System.out.println("=== Test Case 5: All Same Temperatures ===");
        int[] temps5 = {75, 75, 75, 75};
        int[] result5 = solution.dailyTemperatures(temps5);
        System.out.println("Input: " + Arrays.toString(temps5));
        System.out.println("Result: " + Arrays.toString(result5));
        System.out.println("Expected: [0, 0, 0, 0]\n");
        
        // Detailed step-by-step analysis
        System.out.println("=".repeat(60));
        int[] detailedTest = {73, 74, 75, 71, 69, 72, 76, 73};
        solution.dailyTemperaturesDetailed(detailedTest);
        
        // Test different approaches
        System.out.println("\n=== Testing Different Approaches ===");
        int[] test = {73, 74, 75, 71, 69, 72, 76, 73};
        
        System.out.println("Monotonic stack: " + Arrays.toString(solution.dailyTemperatures(test)));
        System.out.println("Brute force: " + Arrays.toString(solution.dailyTemperaturesBruteForce(test)));
        System.out.println("Reverse iteration: " + Arrays.toString(solution.dailyTemperaturesReverse(test)));
        System.out.println("Array stack: " + Arrays.toString(solution.dailyTemperaturesArray(test)));
        System.out.println("NGE pattern: " + Arrays.toString(solution.dailyTemperaturesNGE(test)));
        
        // Performance comparison
        System.out.println("\n=== Performance Comparison ===");
        int[] largeTest = new int[10000];
        Random rand = new Random(42); // Fixed seed for reproducibility
        for (int i = 0; i < largeTest.length; i++) {
            largeTest[i] = rand.nextInt(100) + 30; // Temperatures 30-129
        }
        
        // Test stack approach
        long start = System.nanoTime();
        int[] stackResult = solution.dailyTemperatures(largeTest);
        long end = System.nanoTime();
        System.out.printf("Stack approach: %.2f ms%n", (end - start) / 1_000_000.0);
        
        // Test brute force approach (smaller array due to O(n²) complexity)
        int[] smallTest = Arrays.copyOf(largeTest, 1000);
        start = System.nanoTime();
        int[] bruteResult = solution.dailyTemperaturesBruteForce(smallTest);
        end = System.nanoTime();
        System.out.printf("Brute force (1000 elements): %.2f ms%n", (end - start) / 1_000_000.0);
        
        // Verify results are same for smaller array
        int[] stackSmall = solution.dailyTemperatures(smallTest);
        boolean same = Arrays.equals(stackSmall, bruteResult);
        System.out.println("Results match: " + same);
        
        // Memory usage demonstration
        System.out.println("\n=== Memory Usage Analysis ===");
        System.out.println("Input array size: " + largeTest.length + " integers");
        System.out.println("Result array size: " + stackResult.length + " integers");
        System.out.println("Stack max size: ~" + largeTest.length + " integers (worst case)");
        System.out.println("Total space complexity: O(n)");
    }
}
