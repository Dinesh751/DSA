import java.util.*;

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
}

public class LevelOrderTraversal102 {
    /*
    Problem Statement:
    Given the root of a binary tree, return the level order traversal of its nodes' values. 
    (i.e., from left to right, level by level).

    Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: [[3],[9,20],[15,7]]

    Example 2:
    Input: root = [1]
    Output: [[1]]

    Example 3:
    Input: root = []
    Output: []

    Constraints:
    - The number of nodes in the tree is in the range [0, 2000].
    - -1000 <= Node.val <= 1000
    */

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        helper(root, 0, ans);
        return ans;
    }
     void helper(TreeNode root, int level, List<List<Integer>> ans){
        if (root == null) return;
    
        if (level == ans.size()){
            ans.add(new ArrayList<>());
        }
        ans.get(level).add(root.val);

        helper(root.left, level + 1 , ans);
        helper(root.right, level + 1 , ans);

    }

    /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

   


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
        LevelOrderTraversal102 solution = new LevelOrderTraversal102();

        // Test case 1
        Integer[] arr1 = {3, 9, 20, null, null, 15, 7};
        TreeNode root1 = buildTree(arr1);
        List<List<Integer>> expected1 = Arrays.asList(
            Arrays.asList(3),
            Arrays.asList(9, 20),
            Arrays.asList(15, 7)
        );
        List<List<Integer>> actual1 = solution.levelOrder(root1);
        System.out.println("Test Case 1: Expected: " + expected1 + ", Actual: " + actual1);

        // Test case 2
        Integer[] arr2 = {1};
        TreeNode root2 = buildTree(arr2);
        List<List<Integer>> expected2 = Arrays.asList(
            Arrays.asList(1)
        );
        List<List<Integer>> actual2 = solution.levelOrder(root2);
        System.out.println("Test Case 2: Expected: " + expected2 + ", Actual: " + actual2);

        // Test case 3
        Integer[] arr3 = {};
        TreeNode root3 = buildTree(arr3);
        List<List<Integer>> expected3 = new ArrayList<>();
        List<List<Integer>> actual3 = solution.levelOrder(root3);
        System.out.println("Test Case 3: Expected: " + expected3 + ", Actual: " + actual3);
    }
}
