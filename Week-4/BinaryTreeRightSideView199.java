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

public class BinaryTreeRightSideView199 {
    /*
    Problem Statement:
    Given the root of a binary tree, imagine yourself standing on the right side of it, 
    return the values of the nodes you can see ordered from top to bottom.

    Example 1:
    Input: root = [1,2,3,null,5,null,4]
    Output: [1,3,4]
    Explanation: From the right side, you can see nodes with values 1, 3, and 4.

    Example 2:
    Input: root = [1,null,3]
    Output: [1,3]

    Example 3:
    Input: root = []
    Output: []

    Constraints:
    - The number of nodes in the tree is in the range [0, 100].
    - -100 <= Node.val <= 100
    */

    int lvl = 0;
    ArrayList<Integer> ans;

    public List<Integer> rightSideView(TreeNode root) {
        ans = new ArrayList<>();
        findRightView(root, 0);
        return ans;
    }

    public void findRightView(TreeNode root, int currLvl){
        if(root == null) return;

        if(currLvl == lvl){
            ans.add(root.val);
            lvl++;
        }
        else{
            ans.set(currLvl, root.val);
        }
        
        findRightView(root.left, currLvl+1);
        findRightView(root.right, currLvl+1);
        
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
        BinaryTreeRightSideView199 solution = new BinaryTreeRightSideView199();

        // Test case 1
        Integer[] arr1 = {1, 2, 3, null, 5, null, 4};
        TreeNode root1 = buildTree(arr1);
        List<Integer> expected1 = Arrays.asList(1, 3, 4);
        List<Integer> actual1 = solution.rightSideView(root1);
        System.out.println("Test Case 1: Expected: " + expected1 + ", Actual: " + actual1);

        // Test case 2
        solution.lvl = 0;
        Integer[] arr2 = {1, null, 3};
        TreeNode root2 = buildTree(arr2);
        List<Integer> expected2 = Arrays.asList(1, 3);
        List<Integer> actual2 = solution.rightSideView(root2);
        System.out.println("Test Case 2: Expected: " + expected2 + ", Actual: " + actual2);

        // Test case 3
        solution.lvl = 0;
        Integer[] arr3 = {};
        TreeNode root3 = buildTree(arr3);
        List<Integer> expected3 = new ArrayList<>();
        List<Integer> actual3 = solution.rightSideView(root3);
        System.out.println("Test Case 3: Expected: " + expected3 + ", Actual: " + actual3);

        // Test case 4
        solution.lvl = 0;
        Integer[] arr4 = {1, 2, 3, 4};
        TreeNode root4 = buildTree(arr4);
        List<Integer> expected4 = Arrays.asList(1, 3, 4);
        List<Integer> actual4 = solution.rightSideView(root4);
        System.out.println("Test Case 4: Expected: " + expected4 + ", Actual: " + actual4);
    }
}
