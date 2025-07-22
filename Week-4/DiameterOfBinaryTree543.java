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

public class DiameterOfBinaryTree543 {
    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        heightOfTree(root);
        return maxDiameter;
    }

    private int heightOfTree(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = heightOfTree(root.left);
        int rightHeight = heightOfTree(root.right);

        // Update the maximum diameter
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

        // Return the height of the current node
        return Math.max(leftHeight, rightHeight) + 1;
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
        DiameterOfBinaryTree543 solution = new DiameterOfBinaryTree543();

        // Test case 1
        solution.maxDiameter = 0;
        Integer[] arr1 = {1, 2, 3, 4, 5};
        TreeNode root1 = buildTree(arr1);
        int expected1 = 3;
        int actual1 = solution.diameterOfBinaryTree(root1);
        System.out.println("Test Case 1: Expected: " + expected1 + ", Actual: " + actual1);

        // Test case 2
        solution.maxDiameter = 0;
        Integer[] arr2 = {1, 2};
        TreeNode root2 = buildTree(arr2);
        int expected2 = 1;
        int actual2 = solution.diameterOfBinaryTree(root2);
        System.out.println("Test Case 2: Expected: " + expected2 + ", Actual: " + actual2);

        // Test case 3
        solution.maxDiameter = 0;
        Integer[] arr3 = {1, null, 2, null, 3, null, 4};
        TreeNode root3 = buildTree(arr3);
        int expected3 = 3;
        int actual3 = solution.diameterOfBinaryTree(root3);
        System.out.println("Test Case 3: Expected: " + expected3 + ", Actual: " + actual3);
    }
}