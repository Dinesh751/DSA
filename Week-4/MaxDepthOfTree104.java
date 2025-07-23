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

public class MaxDepthOfTree104 {
    /*
    Problem Statement:
    Given the root of a binary tree, return its maximum depth.

    A binary tree's maximum depth is the number of nodes along the longest path 
    from the root node down to the farthest leaf node.

    Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: 3

    Example 2:
    Input: root = [1,null,2]
    Output: 2

    Constraints:
    - The number of nodes in the tree is in the range [0, 10^4].
    - -100 <= Node.val <= 100
    */
// maxDepth -> max nodes from root last leaf.
    public int maxDepth(TreeNode root) {
         if(root == null) return 0;

         int left = maxDepth(root.left);
         int right = maxDepth(root.right);

         return Math.max(left, right) + 1;
        
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
        MaxDepthOfTree104 solution = new MaxDepthOfTree104();

        // Test case 1
        Integer[] arr1 = {3, 9, 20, null, null, 15, 7};
        TreeNode root1 = buildTree(arr1);
        int expected1 = 3;
        int actual1 = solution.maxDepth(root1);
        System.out.println("Test Case 1: Expected: " + expected1 + ", Actual: " + actual1);

        // Test case 2
        Integer[] arr2 = {1, null, 2};
        TreeNode root2 = buildTree(arr2);
        int expected2 = 2;
        int actual2 = solution.maxDepth(root2);
        System.out.println("Test Case 2: Expected: " + expected2 + ", Actual: " + actual2);

        // Test case 3
        Integer[] arr3 = {1};
        TreeNode root3 = buildTree(arr3);
        int expected3 = 1;
        int actual3 = solution.maxDepth(root3);
        System.out.println("Test Case 3: Expected: " + expected3 + ", Actual: " + actual3);
    }
}
