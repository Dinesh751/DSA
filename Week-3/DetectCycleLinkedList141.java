/**
 * LeetCode #141: Linked List Cycle
 * 
 * Problem Statement:
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 * 
 * There is a cycle in a linked list if there is some node in the list that can be reached again 
 * by continuously following the next pointer. Internally, pos is used to denote the index of the 
 * node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 * 
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 * 
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 * 
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
 * 
 * Example 3:
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 */

// Definition for singly-linked list
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class DetectCycleLinkedList141 {
    
    // Method 1: Floyd's Cycle Detection (Two Pointers) - Optimal
    // Time Complexity: O(n), Space Complexity: O(1)
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;    // Moves 1 step at a time
        ListNode fast = head;    // Moves 2 steps at a time
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            // If they meet, there's a cycle
            if (slow == fast) {
                return true;
            }
        }
        
        return false; // Fast pointer reached end, no cycle
    }
    
    // Method 2: HashSet approach
    // Time Complexity: O(n), Space Complexity: O(n)
    public boolean hasCycleHashSet(ListNode head) {
        if (head == null) return false;
        
        java.util.Set<ListNode> visited = new java.util.HashSet<>();
        ListNode current = head;
        
        while (current != null) {
            if (visited.contains(current)) {
                return true; // Found a node we've seen before
            }
            visited.add(current);
            current = current.next;
        }
        
        return false; // Reached end without cycle
    }
    
    // Method 3: Modifying node values (Destructive approach)
    // Time Complexity: O(n), Space Complexity: O(1)
    // Note: This modifies the original list
    public boolean hasCycleModify(ListNode head) {
        if (head == null) return false;
        
        final int VISITED_MARKER = Integer.MAX_VALUE;
        ListNode current = head;
        
        while (current != null) {
            if (current.val == VISITED_MARKER) {
                return true; // Found marked node
            }
            current.val = VISITED_MARKER; // Mark as visited
            current = current.next;
        }
        
        return false;
    }
    
    // Method 4: Limited iteration (Practical approach)
    // Time Complexity: O(n), Space Complexity: O(1)
    public boolean hasCycleLimited(ListNode head) {
        if (head == null) return false;
        
        ListNode current = head;
        int maxSteps = 10000; // Reasonable upper bound
        
        for (int i = 0; i < maxSteps && current != null; i++) {
            current = current.next;
        }
        
        // If we haven't reached end after many steps, likely a cycle
        return current != null;
    }
    
    // Method 5: Detailed Floyd's algorithm with explanation
    public boolean hasCycleDetailed(ListNode head) {
        System.out.println("=== Cycle Detection Analysis (Floyd's Algorithm) ===");
        
        if (head == null || head.next == null) {
            System.out.println("List is empty or has only one node - no cycle possible");
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        int step = 0;
        
        System.out.println("Starting with both pointers at head");
        System.out.println("Slow pointer moves 1 step, fast pointer moves 2 steps\n");
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            step++;
            
            System.out.printf("Step %d:%n", step);
            System.out.printf("  Slow pointer at node with value: %d%n", slow.val);
            System.out.printf("  Fast pointer at node with value: %d%n", fast.val);
            
            if (slow == fast) {
                System.out.printf("  *** CYCLE DETECTED! Both pointers meet at node with value %d ***%n", slow.val);
                System.out.printf("  Detection took %d steps%n", step);
                return true;
            } else {
                System.out.println("  Pointers are at different nodes, continuing...");
            }
            System.out.println();
        }
        
        System.out.println("Fast pointer reached end of list - no cycle detected");
        System.out.printf("Total steps taken: %d%n", step);
        return false;
    }
    
    // Method 6: Find cycle start position (bonus - not required by problem)
    public ListNode detectCycleStart(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        // Phase 1: Detect cycle using Floyd's algorithm
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                // Cycle detected, now find start
                break;
            }
        }
        
        // No cycle found
        if (fast == null || fast.next == null) {
            return null;
        }
        
        // Phase 2: Find cycle start
        ListNode start = head;
        while (start != slow) {
            start = start.next;
            slow = slow.next;
        }
        
        return start; // This is the start of the cycle
    }
    
    // Helper method to create a list with cycle for testing
    public ListNode createListWithCycle(int[] values, int cyclePos) {
        if (values.length == 0) return null;
        
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        ListNode cycleStart = null;
        
        if (cyclePos == 0) {
            cycleStart = head;
        }
        
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
            
            if (i == cyclePos) {
                cycleStart = current;
            }
        }
        
        // Create cycle if cyclePos is valid
        if (cyclePos >= 0 && cyclePos < values.length) {
            current.next = cycleStart;
        }
        
        return head;
    }
    
    // Helper method to create regular list without cycle
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
    
    // Helper method to print list (careful with cycles!)
    public void printListSafe(ListNode head, String message, int maxNodes) {
        System.out.print(message + ": ");
        
        if (head == null) {
            System.out.println("null");
            return;
        }
        
        ListNode current = head;
        java.util.Set<ListNode> visited = new java.util.HashSet<>();
        int count = 0;
        
        while (current != null && count < maxNodes) {
            if (visited.contains(current)) {
                System.out.printf("%d -> [CYCLE DETECTED - connects back to a previous node]", current.val);
                break;
            }
            
            visited.add(current);
            System.out.print(current.val);
            
            if (current.next != null && count < maxNodes - 1) {
                System.out.print(" -> ");
            }
            
            current = current.next;
            count++;
        }
        
        if (count == maxNodes && current != null) {
            System.out.print(" -> ...");
        }
        
        System.out.println();
    }
    
    // Helper method to get cycle length if cycle exists
    public int getCycleLength(ListNode head) {
        if (!hasCycle(head)) return 0;
        
        ListNode slow = head;
        ListNode fast = head;
        
        // Find meeting point
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        
        // Count cycle length
        int length = 1;
        ListNode current = slow.next;
        while (current != slow) {
            length++;
            current = current.next;
        }
        
        return length;
    }
    
    public static void main(String[] args) {
        DetectCycleLinkedList141 solution = new DetectCycleLinkedList141();
        
        // Test Case 1 - Cycle at position 1
        System.out.println("=== Test Case 1: Cycle at position 1 ===");
        ListNode list1 = solution.createListWithCycle(new int[]{3, 2, 0, -4}, 1);
        solution.printListSafe(list1, "List (showing first 10 nodes)", 10);
        boolean result1 = solution.hasCycle(list1);
        System.out.println("Has cycle: " + result1);
        System.out.println("Expected: true");
        if (result1) {
            System.out.println("Cycle length: " + solution.getCycleLength(list1));
        }
        System.out.println();
        
        // Test Case 2 - Cycle at position 0
        System.out.println("=== Test Case 2: Cycle at position 0 ===");
        ListNode list2 = solution.createListWithCycle(new int[]{1, 2}, 0);
        solution.printListSafe(list2, "List", 10);
        boolean result2 = solution.hasCycle(list2);
        System.out.println("Has cycle: " + result2);
        System.out.println("Expected: true");
        if (result2) {
            System.out.println("Cycle length: " + solution.getCycleLength(list2));
        }
        System.out.println();
        
        // Test Case 3 - No cycle
        System.out.println("=== Test Case 3: No cycle ===");
        ListNode list3 = solution.createList(new int[]{1, 2, 3, 4, 5});
        solution.printListSafe(list3, "List", 10);
        boolean result3 = solution.hasCycle(list3);
        System.out.println("Has cycle: " + result3);
        System.out.println("Expected: false");
        System.out.println();
        
        // Test Case 4 - Single node, no cycle
        System.out.println("=== Test Case 4: Single node, no cycle ===");
        ListNode list4 = solution.createList(new int[]{1});
        solution.printListSafe(list4, "List", 10);
        boolean result4 = solution.hasCycle(list4);
        System.out.println("Has cycle: " + result4);
        System.out.println("Expected: false");
        System.out.println();
        
        // Test Case 5 - Empty list
        System.out.println("=== Test Case 5: Empty list ===");
        ListNode list5 = null;
        solution.printListSafe(list5, "List", 10);
        boolean result5 = solution.hasCycle(list5);
        System.out.println("Has cycle: " + result5);
        System.out.println("Expected: false");
        System.out.println();
        
        // Detailed step-by-step analysis
        System.out.println("=".repeat(60));
        ListNode detailList = solution.createListWithCycle(new int[]{1, 2, 3, 4, 5}, 2);
        solution.hasCycleDetailed(detailList);
        
        // Test all approaches
        System.out.println("\n=== Testing All Approaches ===");
        ListNode testList = solution.createListWithCycle(new int[]{1, 2, 3, 4}, 1);
        
        System.out.println("Floyd's algorithm: " + solution.hasCycle(testList));
        
        // Create fresh lists for other methods since some are destructive
        ListNode testList2 = solution.createListWithCycle(new int[]{1, 2, 3, 4}, 1);
        System.out.println("HashSet approach: " + solution.hasCycleHashSet(testList2));
        
        ListNode testList3 = solution.createListWithCycle(new int[]{1, 2, 3, 4}, 1);
        System.out.println("Limited iteration: " + solution.hasCycleLimited(testList3));
        
        // Test cycle start detection
        System.out.println("\n=== Cycle Start Detection ===");
        ListNode cycleList = solution.createListWithCycle(new int[]{3, 2, 0, -4}, 1);
        ListNode cycleStart = solution.detectCycleStart(cycleList);
        if (cycleStart != null) {
            System.out.println("Cycle starts at node with value: " + cycleStart.val);
            System.out.println("Expected: 2 (0-indexed position 1)");
        }
        
        // Performance test
        System.out.println("\n=== Performance Test ===");
        int[] largeArray = new int[100000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i;
        }
        
        // Test with cycle
        ListNode largeCycleList = solution.createListWithCycle(largeArray, 50000);
        long start = System.nanoTime();
        boolean largeCycleResult = solution.hasCycle(largeCycleList);
        long end = System.nanoTime();
        
        System.out.printf("Large list with cycle (%d nodes): %b in %.2f μs%n", 
                         largeArray.length, largeCycleResult, (end - start) / 1000.0);
        
        // Test without cycle
        ListNode largeNoCycleList = solution.createList(largeArray);
        start = System.nanoTime();
        boolean largeNoCycleResult = solution.hasCycle(largeNoCycleList);
        end = System.nanoTime();
        
        System.out.printf("Large list without cycle (%d nodes): %b in %.2f μs%n", 
                         largeArray.length, largeNoCycleResult, (end - start) / 1000.0);
    }
}
