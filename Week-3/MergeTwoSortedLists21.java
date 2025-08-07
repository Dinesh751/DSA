/**
 * LeetCode #21: Merge Two Sorted Lists
 * 
 * Problem Statement:
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists in a sorted manner and return the head of the new sorted linked list.
 * The list should be made by splicing together the nodes of the first two lists.
 * 
 * Example 1:
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * 
 * Example 2:
 * Input: list1 = [], list2 = []
 * Output: []
 * 
 * Example 3:
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 */

// Definition for singly-linked list (reusing from previous problem)
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class MergeTwoSortedLists21 {
    
    // Method 1: Iterative with dummy node (Most common)
    // Time Complexity: O(m + n), Space Complexity: O(1)
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0); // Dummy node to simplify logic
        ListNode current = dummy;
        
        // Compare and merge while both lists have nodes
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        
        // Append remaining nodes (if any)
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }
        
        return dummy.next;
    }
    
    // Method 2: Recursive approach (Elegant)
    // Time Complexity: O(m + n), Space Complexity: O(m + n) due to call stack
    public ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
        // Base cases
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        
        // Recursive case
        if (list1.val <= list2.val) {
            list1.next = mergeTwoListsRecursive(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoListsRecursive(list1, list2.next);
            return list2;
        }
    }
    
    // Method 3: Without dummy node (Direct approach)
    // Time Complexity: O(m + n), Space Complexity: O(1)
    public ListNode mergeTwoListsNoDummy(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        
        ListNode head, current;
        
        // Determine the head of merged list
        if (list1.val <= list2.val) {
            head = current = list1;
            list1 = list1.next;
        } else {
            head = current = list2;
            list2 = list2.next;
        }
        
        // Merge the rest
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        
        // Append remaining nodes
        current.next = (list1 != null) ? list1 : list2;
        
        return head;
    }
    
    // Method 4: Using priority queue (Overkill for 2 lists but good for k lists)
    // Time Complexity: O((m + n) log 2) = O(m + n), Space Complexity: O(1)
    public ListNode mergeTwoListsPriorityQueue(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        
        java.util.PriorityQueue<ListNode> pq = new java.util.PriorityQueue<>(
            (a, b) -> Integer.compare(a.val, b.val)
        );
        
        pq.offer(list1);
        pq.offer(list2);
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            current.next = node;
            current = current.next;
            
            if (node.next != null) {
                pq.offer(node.next);
            }
        }
        
        return dummy.next;
    }
    
    // Method 5: Detailed step-by-step for learning
    public ListNode mergeTwoListsDetailed(ListNode list1, ListNode list2) {
        System.out.println("=== Merge Two Sorted Lists Analysis ===");
        printList(list1, "List 1");
        printList(list2, "List 2");
        
        if (list1 == null && list2 == null) {
            System.out.println("Both lists are empty");
            return null;
        }
        if (list1 == null) {
            System.out.println("List 1 is empty, returning List 2");
            return list2;
        }
        if (list2 == null) {
            System.out.println("List 2 is empty, returning List 1");
            return list1;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int step = 1;
        
        System.out.println("\nMerging step by step:");
        
        while (list1 != null && list2 != null) {
            System.out.printf("Step %d: Comparing %d (list1) vs %d (list2)%n", 
                            step++, list1.val, list2.val);
            
            if (list1.val <= list2.val) {
                System.out.printf("  Choosing %d from list1%n", list1.val);
                current.next = list1;
                list1 = list1.next;
            } else {
                System.out.printf("  Choosing %d from list2%n", list2.val);
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
            
            // Show current merged result
            System.out.print("  Current merged: ");
            printListFromNode(dummy.next, "");
            System.out.println();
            
            System.out.print("  Remaining list1: ");
            printListFromNode(list1, "");
            System.out.println();
            
            System.out.print("  Remaining list2: ");
            printListFromNode(list2, "");
            System.out.println();
            System.out.println();
        }
        
        // Handle remaining nodes
        if (list1 != null) {
            System.out.println("Appending remaining nodes from list1:");
            printListFromNode(list1, "  ");
            System.out.println();
            current.next = list1;
        } else if (list2 != null) {
            System.out.println("Appending remaining nodes from list2:");
            printListFromNode(list2, "  ");
            System.out.println();
            current.next = list2;
        }
        
        printList(dummy.next, "Final merged list");
        return dummy.next;
    }
    
    // Method 6: Recursive with detailed explanation
    public ListNode mergeTwoListsRecursiveDetailed(ListNode list1, ListNode list2) {
        return mergeRecursiveHelper(list1, list2, 0);
    }
    
    private ListNode mergeRecursiveHelper(ListNode list1, ListNode list2, int depth) {
        String indent = "  ".repeat(depth);
        System.out.printf("%sCall depth %d: list1=%s, list2=%s%n", 
                         indent, depth, 
                         (list1 == null ? "null" : String.valueOf(list1.val)),
                         (list2 == null ? "null" : String.valueOf(list2.val)));
        
        if (list1 == null) {
            System.out.printf("%slist1 is null, returning list2%n", indent);
            return list2;
        }
        if (list2 == null) {
            System.out.printf("%slist2 is null, returning list1%n", indent);
            return list1;
        }
        
        if (list1.val <= list2.val) {
            System.out.printf("%s%d <= %d, choosing list1 node%n", indent, list1.val, list2.val);
            list1.next = mergeRecursiveHelper(list1.next, list2, depth + 1);
            System.out.printf("%sReturning node %d%n", indent, list1.val);
            return list1;
        } else {
            System.out.printf("%s%d > %d, choosing list2 node%n", indent, list1.val, list2.val);
            list2.next = mergeRecursiveHelper(list1, list2.next, depth + 1);
            System.out.printf("%sReturning node %d%n", indent, list2.val);
            return list2;
        }
    }
    
    // Helper method to create linked list from array
    public ListNode createList(int[] values) {
        if (values.length == 0) return null;
        
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        
        return head;
    }
    
    // Helper method to print linked list
    public void printList(ListNode head, String message) {
        System.out.print(message + ": ");
        printListFromNode(head, "");
        System.out.println();
    }
    
    private void printListFromNode(ListNode node, String prefix) {
        if (node == null) {
            System.out.print("null");
            return;
        }
        
        System.out.print(prefix);
        while (node != null) {
            System.out.print(node.val);
            if (node.next != null) {
                System.out.print(" -> ");
            }
            node = node.next;
        }
    }
    
    // Helper method to convert list to array for verification
    public int[] listToArray(ListNode head) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        ListNode current = head;
        
        while (current != null) {
            result.add(current.val);
            current = current.next;
        }
        
        return result.stream().mapToInt(i -> i).toArray();
    }
    
    // Helper method to verify if list is sorted
    public boolean isSorted(ListNode head) {
        if (head == null || head.next == null) return true;
        
        ListNode current = head;
        while (current.next != null) {
            if (current.val > current.next.val) {
                return false;
            }
            current = current.next;
        }
        return true;
    }
    
    public static void main(String[] args) {
        MergeTwoSortedLists21 solution = new MergeTwoSortedLists21();
        
        // Test Case 1 - Standard example
        System.out.println("=== Test Case 1: Standard Example ===");
        ListNode list1 = solution.createList(new int[]{1, 2, 4});
        ListNode list2 = solution.createList(new int[]{1, 3, 4});
        solution.printList(list1, "List 1");
        solution.printList(list2, "List 2");
        ListNode merged1 = solution.mergeTwoLists(list1, list2);
        solution.printList(merged1, "Merged");
        System.out.println("Expected: [1, 1, 2, 3, 4, 4]");
        System.out.println("Is sorted: " + solution.isSorted(merged1));
        System.out.println();
        
        // Test Case 2 - Both empty
        System.out.println("=== Test Case 2: Both Empty ===");
        ListNode empty1 = null;
        ListNode empty2 = null;
        ListNode merged2 = solution.mergeTwoLists(empty1, empty2);
        solution.printList(merged2, "Merged");
        System.out.println("Expected: []");
        System.out.println();
        
        // Test Case 3 - One empty
        System.out.println("=== Test Case 3: One Empty ===");
        ListNode empty = null;
        ListNode nonEmpty = solution.createList(new int[]{0});
        solution.printList(empty, "Empty list");
        solution.printList(nonEmpty, "Non-empty list");
        ListNode merged3 = solution.mergeTwoLists(empty, nonEmpty);
        solution.printList(merged3, "Merged");
        System.out.println("Expected: [0]");
        System.out.println();
        
        // Test Case 4 - Different lengths
        System.out.println("=== Test Case 4: Different Lengths ===");
        ListNode short1 = solution.createList(new int[]{1, 3});
        ListNode long1 = solution.createList(new int[]{2, 4, 5, 6, 7});
        solution.printList(short1, "Short list");
        solution.printList(long1, "Long list");
        ListNode merged4 = solution.mergeTwoLists(short1, long1);
        solution.printList(merged4, "Merged");
        System.out.println("Expected: [1, 2, 3, 4, 5, 6, 7]");
        System.out.println("Is sorted: " + solution.isSorted(merged4));
        System.out.println();
        
        // Test Case 5 - No overlap
        System.out.println("=== Test Case 5: No Overlap ===");
        ListNode lower = solution.createList(new int[]{1, 2, 3});
        ListNode higher = solution.createList(new int[]{4, 5, 6});
        solution.printList(lower, "Lower values");
        solution.printList(higher, "Higher values");
        ListNode merged5 = solution.mergeTwoLists(lower, higher);
        solution.printList(merged5, "Merged");
        System.out.println("Expected: [1, 2, 3, 4, 5, 6]");
        System.out.println();
        
        // Detailed step-by-step analysis
        System.out.println("=".repeat(60));
        ListNode detail1 = solution.createList(new int[]{1, 3, 5});
        ListNode detail2 = solution.createList(new int[]{2, 4, 6});
        solution.mergeTwoListsDetailed(detail1, detail2);
        
        // Test recursive approach with explanation
        System.out.println("\n=== Recursive Approach Analysis ===");
        ListNode rec1 = solution.createList(new int[]{1, 3});
        ListNode rec2 = solution.createList(new int[]{2, 4});
        System.out.println("Merging [1, 3] and [2, 4] recursively:");
        ListNode recResult = solution.mergeTwoListsRecursiveDetailed(rec1, rec2);
        solution.printList(recResult, "Final result");
        
        // Test all approaches
        System.out.println("\n=== Testing All Approaches ===");
        int[] array1 = {1, 3, 5, 7};
        int[] array2 = {2, 4, 6, 8};
        
        ListNode test1a = solution.createList(array1);
        ListNode test1b = solution.createList(array2);
        ListNode result1 = solution.mergeTwoLists(test1a, test1b);
        System.out.println("Iterative with dummy: " + java.util.Arrays.toString(solution.listToArray(result1)));
        
        ListNode test2a = solution.createList(array1);
        ListNode test2b = solution.createList(array2);
        ListNode result2 = solution.mergeTwoListsRecursive(test2a, test2b);
        System.out.println("Recursive: " + java.util.Arrays.toString(solution.listToArray(result2)));
        
        ListNode test3a = solution.createList(array1);
        ListNode test3b = solution.createList(array2);
        ListNode result3 = solution.mergeTwoListsNoDummy(test3a, test3b);
        System.out.println("No dummy node: " + java.util.Arrays.toString(solution.listToArray(result3)));
        
        ListNode test4a = solution.createList(array1);
        ListNode test4b = solution.createList(array2);
        ListNode result4 = solution.mergeTwoListsPriorityQueue(test4a, test4b);
        System.out.println("Priority queue: " + java.util.Arrays.toString(solution.listToArray(result4)));
        
        // Performance test
        System.out.println("\n=== Performance Test ===");
        int[] large1 = new int[5000];
        int[] large2 = new int[5000];
        
        for (int i = 0; i < 5000; i++) {
            large1[i] = i * 2;     // Even numbers
            large2[i] = i * 2 + 1; // Odd numbers
        }
        
        ListNode largeList1 = solution.createList(large1);
        ListNode largeList2 = solution.createList(large2);
        
        long start = System.nanoTime();
        ListNode largeMerged = solution.mergeTwoLists(largeList1, largeList2);
        long end = System.nanoTime();
        
        System.out.printf("Merged %d + %d nodes in %.2f μs%n", 
                         large1.length, large2.length, (end - start) / 1000.0);
        
        // Verify large result
        int[] mergedArray = solution.listToArray(largeMerged);
        boolean sorted = solution.isSorted(largeMerged);
        System.out.println("Result length: " + mergedArray.length);
        System.out.println("Is sorted: " + sorted);
        System.out.printf("First few: [%d, %d, %d, %d, %d]%n", 
                         mergedArray[0], mergedArray[1], mergedArray[2], mergedArray[3], mergedArray[4]);
    }
}
