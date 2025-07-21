

/*
LeetCode 104: Maximum Depth of Binary Tree

Problem Statement:
Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 3
Explanation: The maximum depth is 3.

Example 2:
Input: root = [1,null,2]
Output: 2

Constraints:
- The number of nodes in the tree is in the range [0, 10^4].
- -100 <= Node.val <= 100

Time Complexity: O(n) where n is the number of nodes in the tree
Space Complexity: O(h) where h is the height of the tree (due to recursion stack)
*/

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class MaxDepthTree104 {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }
    
    // Recursive method to build tree from array (level-order representation)
    // null values in array represent missing nodes
    public TreeNode buildTreeFromArray(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }
        return buildTreeRecursive(arr, 0);
    }
    
    private TreeNode buildTreeRecursive(Integer[] arr, int index) {
        // Base case: if index is out of bounds or value is null
        if (index >= arr.length || arr[index] == null) {
            return null;
        }
        
        // Create current node
        TreeNode node = new TreeNode(arr[index]);
        
        // Recursively build left and right subtrees
        // Left child is at index 2*i + 1, right child is at index 2*i + 2
        node.left = buildTreeRecursive(arr, 2 * index + 1);
        node.right = buildTreeRecursive(arr, 2 * index + 2);
        
        return node;
    }
    
    // Alternative: Build tree from preorder and inorder arrays
    public TreeNode buildTreeFromPreorderInorder(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        return buildPreorderInorderRecursive(preorder, inorder, 0, 0, inorder.length - 1);
    }
    
    private TreeNode buildPreorderInorderRecursive(int[] preorder, int[] inorder, 
                                                   int preStart, int inStart, int inEnd) {
        if (preStart >= preorder.length || inStart > inEnd) {
            return null;
        }
        
        // Root is the first element in preorder
        TreeNode root = new TreeNode(preorder[preStart]);
        
        // Find root in inorder array
        int rootIndex = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == preorder[preStart]) {
                rootIndex = i;
                break;
            }
        }
        
        // Recursively build left and right subtrees
        root.left = buildPreorderInorderRecursive(preorder, inorder, preStart + 1, 
                                                  inStart, rootIndex - 1);
        root.right = buildPreorderInorderRecursive(preorder, inorder, 
                                                   preStart + (rootIndex - inStart) + 1, 
                                                   rootIndex + 1, inEnd);
        
        return root;
    }
    
    // Utility method to print tree (for verification)
    public void printInorder(TreeNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(root.val + " ");
            printInorder(root.right);
        }
    }
    
    // Test cases
    public static void main(String[] args) {
        MaxDepthTree104 solution = new MaxDepthTree104();
        
        // Test case 1: [3,9,20,null,null,15,7] - Using recursive array builder
        Integer[] arr1 = {3, 9, 20, null, null, 15, 7};
        TreeNode root1 = solution.buildTreeFromArray(arr1);
        
        System.out.println("Test Case 1 (Built recursively from array):");
        System.out.println("Input: [3,9,20,null,null,15,7]");
        System.out.print("Inorder traversal: ");
        solution.printInorder(root1);
        System.out.println();
        System.out.println("Expected depth: 3");
        System.out.println("Actual depth: " + solution.maxDepth(root1));
        System.out.println();
        
        // Test case 2: [1,null,2] - Using recursive array builder
        Integer[] arr2 = {1, null, 2};
        TreeNode root2 = solution.buildTreeFromArray(arr2);
        
        System.out.println("Test Case 2 (Built recursively from array):");
        System.out.println("Input: [1,null,2]");
        System.out.print("Inorder traversal: ");
        solution.printInorder(root2);
        System.out.println();
        System.out.println("Expected depth: 2");
        System.out.println("Actual depth: " + solution.maxDepth(root2));
        System.out.println();
        
        // Test case 3: Empty tree
        Integer[] arr3 = {};
        TreeNode root3 = solution.buildTreeFromArray(arr3);
        
        System.out.println("Test Case 3 (Empty array):");
        System.out.println("Input: []");
        System.out.println("Expected depth: 0");
        System.out.println("Actual depth: " + solution.maxDepth(root3));
        System.out.println();
        
        // Test case 4: Single node - Using recursive array builder
        Integer[] arr4 = {1};
        TreeNode root4 = solution.buildTreeFromArray(arr4);
        
        System.out.println("Test Case 4 (Single node from array):");
        System.out.println("Input: [1]");
        System.out.print("Inorder traversal: ");
        solution.printInorder(root4);
        System.out.println();
        System.out.println("Expected depth: 1");
        System.out.println("Actual depth: " + solution.maxDepth(root4));
        System.out.println();
        
        // Test case 5: Deeper tree - Using recursive array builder
        Integer[] arr5 = {1, 2, null, 3, null, null, null, 4};
        TreeNode root5 = solution.buildTreeFromArray(arr5);
        
        System.out.println("Test Case 5 (Deeper tree from array):");
        System.out.println("Input: [1,2,null,3,null,null,null,4]");
        System.out.print("Inorder traversal: ");
        solution.printInorder(root5);
        System.out.println();
        System.out.println("Expected depth: 4");
        System.out.println("Actual depth: " + solution.maxDepth(root5));
        System.out.println();
        
        // Test case 6: Build tree from preorder and inorder arrays
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root6 = solution.buildTreeFromPreorderInorder(preorder, inorder);
        
        System.out.println("Test Case 6 (Built from preorder and inorder):");
        System.out.println("Preorder: [3,9,20,15,7]");
        System.out.println("Inorder: [9,3,15,20,7]");
        System.out.print("Result inorder traversal: ");
        solution.printInorder(root6);
        System.out.println();
        System.out.println("Expected depth: 3");
        System.out.println("Actual depth: " + solution.maxDepth(root6));
        System.out.println();
        
        // Test case 7: Balanced tree from array
        Integer[] arr7 = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root7 = solution.buildTreeFromArray(arr7);
        
        System.out.println("Test Case 7 (Balanced tree from array):");
        System.out.println("Input: [1,2,3,4,5,6,7]");
        System.out.print("Inorder traversal: ");
        solution.printInorder(root7);
        System.out.println();
        System.out.println("Expected depth: 3");
        System.out.println("Actual depth: " + solution.maxDepth(root7));
    }
}
