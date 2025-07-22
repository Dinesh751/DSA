/*
Problem Statement:
Given an array representation of a binary tree in preorder traversal with `null` values indicating missing children, construct the binary tree.

Example:
Input: arr = {1, 2, 3, null, null, 3, null, null, 5, 6, null, null, 6, null, null}

The binary tree represented by this array is:

        1
       / \
      2   3
         / \
        3   5
           / \
          6   6

Output: Construct the binary tree and return the root node.

Constraints:
- The array represents a valid binary tree in preorder traversal.
- `null` values indicate missing children.
*/

// Definition for a binary tree node


import java.util.LinkedList;
import java.util.Queue;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
}

public class CreateBinaryTree {
    public static void main(String[] args) {
        // Example array to create a binary tree
        int[] arr = {1, 2, -1, -1, 3, 4,-1, -1, 5, -1, -1};
        index = -1;
        TreeNode root = buildTreeFromPreorderArray(arr);
        System.out.println("Binary tree created successfully.");
        System.out.println("Binary tree level order traversal");
        printTree(root);
        System.out.println("Binary tree preorder traversal");
        preOrder(root);
        System.out.println(); // Add newline
        System.out.println("Binary tree inorder traversal");
        inOrder(root);
        System.out.println(); // Add newline
        System.out.println("Binary tree postorder traversal");
        postOrder(root);
        System.out.println("Binary height of tree ");
        System.out.println("Binary tree height " + heightOfTree(root));
        
    }
   static int index = -1;
  
    // Method to build a binary tree from a preorder array with nulls
    public static TreeNode buildTreeFromPreorderArray(int[] arr) {
          index++;
        if( arr[index] == -1) return null;
        
        TreeNode root = new TreeNode(arr[index]);

        root.left = buildTreeFromPreorderArray(arr);
        root.right = buildTreeFromPreorderArray(arr);

        return root;
    
    }

    public static void preOrder(TreeNode root){
        if(root == null) return;
        
        System.out.print(root.val+"->");

        preOrder(root.left);
        preOrder(root.right);
    }

    public static void inOrder(TreeNode root){
        if(root == null) return;

        inOrder(root.left);
        System.out.print(root.val + "->");
        inOrder(root.right);

    }

    public static void postOrder(TreeNode root){
        if(root == null){
            return;
        }

        postOrder(root.right);
        postOrder(root.left);
        System.out.print(root.val + "->");
    }

    // Utility method to print the tree level by level
    public static void printTree(TreeNode root) {
       if(root == null ) return;

       Queue<TreeNode> q = new LinkedList<>();
       q.add(root);

       while(!q.isEmpty()){
        int size = q.size();
        while(size != 0){
            TreeNode current = q.poll();
            if(current != null){
                System.out.print(current.val + " ");
                q.add(current.left);
                q.add(current.right);
            }else{
                System.out.print("null");
            }
            size--;  
        }
        System.out.println("");
       }

    }

    public static int heightOfTree(TreeNode root){
        if(root == null) return 0;

        int left = heightOfTree(root.left);
        int right = heightOfTree(root.right);

        int height = Math.max(left, right) + 1;

        return height;
    }
}