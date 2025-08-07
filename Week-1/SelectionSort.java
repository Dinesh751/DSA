import java.util.*;

/**
 * Selection Sort Algorithm Implementation
 * 
 * Algorithm Description:
 * Selection sort is a simple sorting algorithm that divides the input list into two parts:
 * 1. A sorted portion at the left end
 * 2. An unsorted portion at the right end
 * 
 * The algorithm repeatedly selects the smallest (or largest) element from the unsorted portion
 * and moves it to the end of the sorted portion.
 * 
 * Time Complexity: O(n²) in all cases (best, average, worst)
 * Space Complexity: O(1) - in-place sorting algorithm
 * 
 * Advantages:
 * - Simple implementation
 * - In-place sorting (O(1) space)
 * - Performs well on small datasets
 * - Number of swaps is minimal O(n)
 * 
 * Disadvantages:
 * - O(n²) time complexity even in best case
 * - Not stable (doesn't preserve relative order of equal elements)
 * - Not adaptive (doesn't perform better on partially sorted data)
 */
public class SelectionSort {
    
    // Method 1: Standard Selection Sort (Ascending Order)
    public void selectionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int minIndex = i;
            
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            // Swap the found minimum element with the first element
            if (minIndex != i) {
                swap(arr, i, minIndex);
            }
            
            // Print array state after each iteration
            System.out.println("After iteration " + (i + 1) + ": " + Arrays.toString(arr));
        }
    }
    
    // Method 2: Selection Sort (Descending Order)
    public void selectionSortDescending(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            // Find the maximum element in unsorted array
            int maxIndex = i;
            
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            
            // Swap the found maximum element with the first element
            if (maxIndex != i) {
                swap(arr, i, maxIndex);
            }
        }
    }
    
    // Method 3: Selection Sort with Comparator (Generic)
    public void selectionSortGeneric(Integer[] arr, Comparator<Integer> comparator) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            int targetIndex = i;
            
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(arr[j], arr[targetIndex]) < 0) {
                    targetIndex = j;
                }
            }
            
            if (targetIndex != i) {
                Integer temp = arr[i];
                arr[i] = arr[targetIndex];
                arr[targetIndex] = temp;
            }
        }
    }
    
    // Method 4: Selection Sort with Statistics
    public SortStats selectionSortWithStats(int[] arr) {
        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;
        
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            if (minIndex != i) {
                swap(arr, i, minIndex);
                swaps++;
            }
        }
        
        return new SortStats(comparisons, swaps);
    }
    
    // Helper method to swap elements
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    // Method 5: Recursive Selection Sort
    public void selectionSortRecursive(int[] arr, int start) {
        if (start >= arr.length - 1) {
            return; // Base case
        }
        
        // Find minimum element in the remaining array
        int minIndex = start;
        for (int i = start + 1; i < arr.length; i++) {
            if (arr[i] < arr[minIndex]) {
                minIndex = i;
            }
        }
        
        // Swap if needed
        if (minIndex != start) {
            swap(arr, start, minIndex);
        }
        
        // Recursive call for the rest of the array
        selectionSortRecursive(arr, start + 1);
    }
    
    // Class to store sorting statistics
    static class SortStats {
        int comparisons;
        int swaps;
        
        SortStats(int comparisons, int swaps) {
            this.comparisons = comparisons;
            this.swaps = swaps;
        }
        
        @Override
        public String toString() {
            return "Comparisons: " + comparisons + ", Swaps: " + swaps;
        }
    }
    
    // Dry run demonstration
    public void dryRun(int[] arr) {
        System.out.println("=== Selection Sort Dry Run ===");
        System.out.println("Initial array: " + Arrays.toString(arr));
        System.out.println();
        
        int n = arr.length;
        
        for (int i = 0; i < n - 1; i++) {
            System.out.println("Iteration " + (i + 1) + ":");
            System.out.println("Finding minimum in subarray from index " + i + " to " + (n - 1));
            
            int minIndex = i;
            int minValue = arr[i];
            
            for (int j = i + 1; j < n; j++) {
                System.out.println("  Comparing arr[" + j + "] = " + arr[j] + " with current min = " + minValue);
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                    minValue = arr[j];
                    System.out.println("  New minimum found: " + minValue + " at index " + minIndex);
                }
            }
            
            if (minIndex != i) {
                System.out.println("Swapping arr[" + i + "] = " + arr[i] + " with arr[" + minIndex + "] = " + arr[minIndex]);
                swap(arr, i, minIndex);
            } else {
                System.out.println("No swap needed, minimum is already at position " + i);
            }
            
            System.out.println("Array after iteration " + (i + 1) + ": " + Arrays.toString(arr));
            System.out.println();
        }
        
        System.out.println("Final sorted array: " + Arrays.toString(arr));
    }
    
    public static void main(String[] args) {
        SelectionSort sorter = new SelectionSort();
        
        // Test Case 1: Basic sorting
        System.out.println("=== Test Case 1: Basic Sorting ===");
        int[] arr1 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original: " + Arrays.toString(arr1));
        sorter.selectionSort(arr1.clone());
        
        // Test Case 2: Dry run demonstration
        System.out.println("\n=== Test Case 2: Dry Run ===");
        int[] arr2 = {5, 2, 8, 1, 9};
        sorter.dryRun(arr2.clone());
        
        // Test Case 3: Descending order
        System.out.println("\n=== Test Case 3: Descending Order ===");
        int[] arr3 = {3, 7, 1, 9, 4};
        System.out.println("Original: " + Arrays.toString(arr3));
        sorter.selectionSortDescending(arr3);
        System.out.println("Descending: " + Arrays.toString(arr3));
        
        // Test Case 4: With statistics
        System.out.println("\n=== Test Case 4: With Statistics ===");
        int[] arr4 = {4, 3, 2, 1};
        System.out.println("Original: " + Arrays.toString(arr4));
        SortStats stats = sorter.selectionSortWithStats(arr4);
        System.out.println("Sorted: " + Arrays.toString(arr4));
        System.out.println("Statistics: " + stats);
        
        // Test Case 5: Recursive approach
        System.out.println("\n=== Test Case 5: Recursive Approach ===");
        int[] arr5 = {6, 3, 8, 2, 7};
        System.out.println("Original: " + Arrays.toString(arr5));
        sorter.selectionSortRecursive(arr5, 0);
        System.out.println("Recursively sorted: " + Arrays.toString(arr5));
        
        // Test Case 6: Edge cases
        System.out.println("\n=== Test Case 6: Edge Cases ===");
        
        // Empty array
        int[] empty = {};
        sorter.selectionSort(empty);
        System.out.println("Empty array: " + Arrays.toString(empty));
        
        // Single element
        int[] single = {42};
        sorter.selectionSort(single);
        System.out.println("Single element: " + Arrays.toString(single));
        
        // Already sorted
        int[] sorted = {1, 2, 3, 4, 5};
        System.out.println("Already sorted - Original: " + Arrays.toString(sorted));
        sorter.selectionSort(sorted);
        
        // Reverse sorted
        int[] reverse = {5, 4, 3, 2, 1};
        System.out.println("Reverse sorted - Original: " + Arrays.toString(reverse));
        sorter.selectionSort(reverse);
    }
}
