/**
 * LeetCode #206: Reverse Linked List
 * 
 * Problem Statement:
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * 
 * Example 1:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * 
 * Example 2:
 * Input: head = [1,2]
 * Output: [2,1]
 * 
 * Example 3:
 * Input: head = []
 * Output: []
 */

// Definition for singly-linked list
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class ReverseLinkedList206 {
    
    // Method 1: Iterative approach (Most common)
    // Time Complexity: O(n), Space Complexity: O(1)
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        
        while (current != null) {
            ListNode nextNode = current.next; // Store next node
            current.next = prev;              // Reverse the link
            prev = current;                   // Move prev forward
            current = nextNode;               // Move current forward
        }
        
        return prev; // prev is the new head
    }
    
    // Method 2: Recursive approach (Elegant)
    // Time Complexity: O(n), Space Complexity: O(n) due to call stack
    public ListNode reverseListRecursive(ListNode head) {
        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Recursively reverse the rest of the list
        ListNode reversedHead = reverseListRecursive(head.next);
        
        // Reverse the current connection
        head.next.next = head;
        head.next = null;
        
        return reversedHead;
    }
    
    // Method 3: Stack-based approach
    // Time Complexity: O(n), Space Complexity: O(n)
    public ListNode reverseListStack(ListNode head) {
        if (head == null) return null;
        
        java.util.Stack<ListNode> stack = new java.util.Stack<>();
        ListNode current = head;
        
        // Push all nodes to stack
        while (current != null) {
            stack.push(current);
            current = current.next;
        }
        
        // Pop nodes and create reversed list
        ListNode newHead = stack.pop();
        current = newHead;
        
        while (!stack.isEmpty()) {
            current.next = stack.pop();
            current = current.next;
        }
        current.next = null;
        
        return newHead;
    }
    
    // Method 4: Two-pass approach (Convert to array and back)
    // Time Complexity: O(n), Space Complexity: O(n)
    public ListNode reverseListTwoPass(ListNode head) {
        if (head == null) return null;
        
        // First pass: collect values
        java.util.List<Integer> values = new java.util.ArrayList<>();
        ListNode current = head;
        while (current != null) {
            values.add(current.val);
            current = current.next;
        }
        
        // Create new reversed list
        ListNode dummy = new ListNode(0);
        current = dummy;
        
        for (int i = values.size() - 1; i >= 0; i--) {
            current.next = new ListNode(values.get(i));
            current = current.next;
        }
        
        return dummy.next;
    }
    
    // Method 5: Detailed step-by-step for learning
    public ListNode reverseListDetailed(ListNode head) {
        System.out.println("=== Reverse Linked List Analysis ===");
        printList(head, "Original list");
        
        if (head == null) {
            System.out.println("Empty list - nothing to reverse");
            return null;
        }
        
        ListNode prev = null;
        ListNode current = head;
        int step = 1;
        
        System.out.println("\nReversing step by step:");
        
        while (current != null) {
            ListNode nextNode = current.next;
            
            System.out.printf("Step %d:%n", step++);
            System.out.printf("  Before: prev=%s, current=%d, next=%s%n", 
                            (prev == null ? "null" : String.valueOf(prev.val)), 
                            current.val,
                            (nextNode == null ? "null" : String.valueOf(nextNode.val)));
            
            // Reverse the link
            current.next = prev;
            
            System.out.printf("  After reversing link: current.next now points to %s%n", 
                            (prev == null ? "null" : String.valueOf(prev.val)));
            
            // Move pointers
            prev = current;
            current = nextNode;
            
            System.out.printf("  Updated pointers: prev=%s, current=%s%n", 
                            prev.val,
                            (current == null ? "null" : String.valueOf(current.val)));
            
            // Show partial result
            System.out.print("  Partial reversed list: ");
            printListFromNode(prev, "");
            System.out.println();
        }
        
        printList(prev, "Final reversed list");
        return prev;
    }
    
    // Method 6: Recursive with detailed explanation
    public ListNode reverseListRecursiveDetailed(ListNode head) {
        return reverseRecursiveHelper(head, 0);
    }
    
    private ListNode reverseRecursiveHelper(ListNode head, int depth) {
        String indent = "  ".repeat(depth);
        System.out.printf("%sCall depth %d: head = %s%n", 
                         indent, depth, (head == null ? "null" : String.valueOf(head.val)));
        
        if (head == null || head.next == null) {
            System.out.printf("%sBase case reached, returning %s%n", 
                             indent, (head == null ? "null" : String.valueOf(head.val)));
            return head;
        }
        
        System.out.printf("%sRecursively reversing from node %d%n", indent, head.next.val);
        ListNode reversedHead = reverseRecursiveHelper(head.next, depth + 1);
        
        System.out.printf("%sReversing connection: %d.next.next = %d%n", 
                         indent, head.val, head.val);
        head.next.next = head;
        head.next = null;
        
        System.out.printf("%sReturning reversed head: %d%n", indent, reversedHead.val);
        return reversedHead;
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
    
    // Helper method to get list length
    public int getLength(ListNode head) {
        int length = 0;
        ListNode current = head;
        
        while (current != null) {
            length++;
            current = current.next;
        }
        
        return length;
    }
    
    // Helper method to check if two lists are equal
    public boolean areListsEqual(ListNode list1, ListNode list2) {
        while (list1 != null && list2 != null) {
            if (list1.val != list2.val) {
                return false;
            }
            list1 = list1.next;
            list2 = list2.next;
        }
        
        return list1 == null && list2 == null;
    }
    
    public static void main(String[] args) {
        ReverseLinkedList206 solution = new ReverseLinkedList206();
        
        // Test Case 1 - Standard example
        System.out.println("=== Test Case 1: Standard Example ===");
        ListNode list1 = solution.createList(new int[]{1, 2, 3, 4, 5});
        solution.printList(list1, "Original");
        ListNode reversed1 = solution.reverseList(list1);
        solution.printList(reversed1, "Reversed");
        System.out.println("Expected: [5, 4, 3, 2, 1]\n");
        
        // Test Case 2 - Two nodes
        System.out.println("=== Test Case 2: Two Nodes ===");
        ListNode list2 = solution.createList(new int[]{1, 2});
        solution.printList(list2, "Original");
        ListNode reversed2 = solution.reverseList(list2);
        solution.printList(reversed2, "Reversed");
        System.out.println("Expected: [2, 1]\n");
        
        // Test Case 3 - Single node
        System.out.println("=== Test Case 3: Single Node ===");
        ListNode list3 = solution.createList(new int[]{1});
        solution.printList(list3, "Original");
        ListNode reversed3 = solution.reverseList(list3);
        solution.printList(reversed3, "Reversed");
        System.out.println("Expected: [1]\n");
        
        // Test Case 4 - Empty list
        System.out.println("=== Test Case 4: Empty List ===");
        ListNode list4 = null;
        solution.printList(list4, "Original");
        ListNode reversed4 = solution.reverseList(list4);
        solution.printList(reversed4, "Reversed");
        System.out.println("Expected: []\n");
        
        // Detailed step-by-step analysis
        System.out.println("=".repeat(50));
        ListNode detailList = solution.createList(new int[]{1, 2, 3});
        solution.reverseListDetailed(detailList);
        
        // Test recursive approach with explanation
        System.out.println("\n=== Recursive Approach Analysis ===");
        ListNode recursiveTest = solution.createList(new int[]{1, 2, 3});
        System.out.println("Original: [1, 2, 3]");
        ListNode recursiveResult = solution.reverseListRecursiveDetailed(recursiveTest);
        solution.printList(recursiveResult, "Final result");
        
        // Test all approaches
        System.out.println("\n=== Testing All Approaches ===");
        int[] testArray = {1, 2, 3, 4, 5};
        
        ListNode test1 = solution.createList(testArray);
        ListNode result1 = solution.reverseList(test1);
        System.out.println("Iterative: " + java.util.Arrays.toString(solution.listToArray(result1)));
        
        ListNode test2 = solution.createList(testArray);
        ListNode result2 = solution.reverseListRecursive(test2);
        System.out.println("Recursive: " + java.util.Arrays.toString(solution.listToArray(result2)));
        
        ListNode test3 = solution.createList(testArray);
        ListNode result3 = solution.reverseListStack(test3);
        System.out.println("Stack-based: " + java.util.Arrays.toString(solution.listToArray(result3)));
        
        ListNode test4 = solution.createList(testArray);
        ListNode result4 = solution.reverseListTwoPass(test4);
        System.out.println("Two-pass: " + java.util.Arrays.toString(solution.listToArray(result4)));
        
        // Performance test
        System.out.println("\n=== Performance Test ===");
        int[] largeArray = new int[10000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i + 1;
        }
        
        ListNode largeList = solution.createList(largeArray);
        
        long start = System.nanoTime();
        ListNode reversedLarge = solution.reverseList(largeList);
        long end = System.nanoTime();
        
        System.out.printf("Reversed %d nodes in %.2f μs%n", 
                         largeArray.length, (end - start) / 1000.0);
        System.out.printf("First few elements: [%d, %d, %d]%n", 
                         reversedLarge.val, reversedLarge.next.val, reversedLarge.next.next.val);
        
        // Verify correctness
        int[] reversedArray = solution.listToArray(reversedLarge);
        boolean correct = true;
        for (int i = 0; i < Math.min(10, reversedArray.length); i++) {
            if (reversedArray[i] != largeArray.length - i) {
                correct = false;
                break;
            }
        }
        System.out.println("Large test correctness: " + correct);
    }
}
