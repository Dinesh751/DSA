import java.util.LinkedList;
import java.util.Queue;

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
}

public class BalancedBinaryTree110 {
    /*
    Problem Statement:
    Given a binary tree, determine if it is height-balanced.

    For this problem, a height-balanced binary tree is defined as:
    A binary tree in which the left and right subtrees of every node differ in height by no more than 1.

    Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: true

    Example 2:
    Input: root = [1,2,2,3,3,null,null,4,4]
    Output: false

    Example 3:
    Input: root = []
    Output: true

    Constraints:
    - The number of nodes in the tree is in the range [0, 5000].
    - -10^4 <= Node.val <= 10^4
    */
    
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
        
    }

    // Helper method to calculate height of a tree
    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        int left = getHeight(root.left);
        if(left == -1) return -1;
        int right = getHeight(root.right);
        if(right == -1) return -1;

        if(Math.abs(left - right) > 1){
            return -1;
        }
        return Math.max(left , right) + 1;
    }

    // Helper method to build a binary tree from an array
    public static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0) return null;

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 1;
        while (!queue.isEmpty() && i < arr.length) {
            TreeNode current = queue.poll();

            if (arr[i] != null) {
                current.left = new TreeNode(arr[i]);
                queue.add(current.left);
            }
            i++;

            if (i < arr.length && arr[i] != null) {
                current.right = new TreeNode(arr[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }

    public static void main(String[] args) {
        BalancedBinaryTree110 solution = new BalancedBinaryTree110();

        // Test case 1
        Integer[] arr1 = {3, 9, 20, null, null, 15, 7};
        TreeNode root1 = buildTree(arr1);
        boolean expected1 = true;
        boolean actual1 = solution.isBalanced(root1);
        System.out.println("Test Case 1: Expected: " + expected1 + ", Actual: " + actual1);

        // Test case 2
        Integer[] arr2 = {1, 2, 2, 3, 3, null, null, 4, 4};
        TreeNode root2 = buildTree(arr2);
        boolean expected2 = false;
        boolean actual2 = solution.isBalanced(root2);
        System.out.println("Test Case 2: Expected: " + expected2 + ", Actual: " + actual2);

        // Test case 3
        Integer[] arr3 = {};
        TreeNode root3 = buildTree(arr3);
        boolean expected3 = true;
        boolean actual3 = solution.isBalanced(root3);
        System.out.println("Test Case 3: Expected: " + expected3 + ", Actual: " + actual3);

        // Test case 4
        Integer[] arr4 = {1};
        TreeNode root4 = buildTree(arr4);
        boolean expected4 = true;
        boolean actual4 = solution.isBalanced(root4);
        System.out.println("Test Case 4: Expected: " + expected4 + ", Actual: " + actual4);
    }
}
