# Week 4: Binary Trees & Tree Traversals 🌳

## Overview
Week 4 focuses on binary tree data structures, fundamental tree traversals (DFS and BFS), and essential tree algorithms. This week builds recursive thinking and tree manipulation skills.

## Problems Solved (7/7)

| # | Problem | Difficulty | Pattern | Time | Space | Key Concepts |
|---|---------|------------|---------|------|-------|--------------|
| 1 | [Max Depth of Binary Tree #104](MaxDepthOfTree104.java) | Easy | DFS/BFS | O(n) | O(h) | Tree Height |
| 2 | [Level Order Traversal #102](LevelOrderTraversal102.java) | Medium | BFS | O(n) | O(w) | Queue Processing |
| 3 | [Binary Tree Right Side View #199](BinaryTreeRightSideView199.java) | Medium | BFS/DFS | O(n) | O(h) | Level Traversal |
| 4 | [Balanced Binary Tree #110](BalancedBinaryTree110.java) | Easy | DFS | O(n) | O(h) | Height Validation |
| 5 | [Diameter of Binary Tree #543](DiameterOfBinaryTree543.java) | Easy | DFS | O(n) | O(h) | Path Length |
| 6 | [Create Binary Tree](CreateBinaryTree.java) | - | Construction | O(n) | O(h) | Tree Building |

## Algorithm Templates 📚

### 1. TreeNode Structure
```java
public class TreeNode {
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
```

### 2. DFS Traversal Templates
```java
// Inorder (Left → Root → Right) - for BST sorted output
public void inorder(TreeNode root) {
    if (root == null) return;
    inorder(root.left);
    process(root.val);
    inorder(root.right);
}

// Preorder (Root → Left → Right) - for tree copying
public void preorder(TreeNode root) {
    if (root == null) return;
    process(root.val);
    preorder(root.left);
    preorder(root.right);
}

// Postorder (Left → Right → Root) - for tree cleanup
public void postorder(TreeNode root) {
    if (root == null) return;
    postorder(root.left);
    postorder(root.right);
    process(root.val);
}
```

### 3. BFS Level Order Template
```java
public List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null) return new ArrayList<>();
    
    List<List<Integer>> result = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> level = new ArrayList<>();
        
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            level.add(node.val);
            
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        result.add(level);
    }
    return result;
}
```

### 4. Tree Height/Depth Template
```java
// Recursive DFS approach
public int maxDepth(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}

// Iterative BFS approach
public int maxDepthBFS(TreeNode root) {
    if (root == null) return 0;
    
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int depth = 0;
    
    while (!queue.isEmpty()) {
        depth++;
        int levelSize = queue.size();
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }
    return depth;
}
```

### 5. Tree Validation Template
```java
// Check if balanced tree
public boolean isBalanced(TreeNode root) {
    return checkHeight(root) != -1;
}

private int checkHeight(TreeNode root) {
    if (root == null) return 0;
    
    int leftHeight = checkHeight(root.left);
    if (leftHeight == -1) return -1;
    
    int rightHeight = checkHeight(root.right);
    if (rightHeight == -1) return -1;
    
    if (Math.abs(leftHeight - rightHeight) > 1) return -1;
    
    return 1 + Math.max(leftHeight, rightHeight);
}
```

## Key Patterns & Techniques 🎯

### Tree Traversal Patterns
1. **DFS (Depth-First)**: Inorder, Preorder, Postorder
2. **BFS (Breadth-First)**: Level-by-level processing
3. **Recursive**: Natural for tree problems
4. **Iterative**: Using stacks/queues for traversal

### Tree Problem Types
1. **Height/Depth**: Calculate tree dimensions
2. **Path Problems**: Find paths with specific properties
3. **Validation**: Check tree properties (balanced, BST)
4. **Construction**: Build trees from traversals
5. **Modification**: Transform tree structure

### Tree Properties
- **Height**: Longest path from root to leaf
- **Depth**: Distance from root to specific node
- **Balanced**: Height difference between subtrees ≤ 1
- **Complete**: All levels filled except possibly last
- **Perfect**: All internal nodes have 2 children

## Complexity Analysis 📊

| Pattern | Time Complexity | Space Complexity | Use Cases |
|---------|----------------|------------------|-----------|
| DFS Traversal | O(n) | O(h) | Path problems, tree validation |
| BFS Traversal | O(n) | O(w) | Level processing, minimum depth |
| Height Calculation | O(n) | O(h) | Tree properties, validation |
| Tree Search (BST) | O(log n) avg, O(n) worst | O(h) | Searching in BST |

*where n = number of nodes, h = height of tree, w = maximum width*

## Common Pitfalls & Tips ⚠️

### Tree Traversal
- Always handle null nodes first (base case)
- Choose correct traversal for the problem
- Consider iterative solutions for very deep trees
- Use level-order for width-based problems

### Recursive Solutions
- Define clear base cases
- Think about what each recursive call returns
- Combine results from left and right subtrees
- Handle edge cases (empty tree, single node)

### Tree Properties
- Height vs depth terminology
- Balanced tree definition varies by context
- Complete vs perfect tree distinctions
- Binary search tree properties

## Practice Strategy 📈

### Beginner Level
1. Master basic tree traversals (inorder, preorder, postorder)
2. Calculate tree height and depth
3. Implement level-order traversal

### Intermediate Level
1. Solve tree validation problems (balanced, BST)
2. Find paths with specific sums
3. Transform tree structures

### Advanced Level
1. Build trees from traversal arrays
2. Find lowest common ancestors
3. Serialize/deserialize trees

## Quick Review Checklist ✅

- [ ] Can implement all DFS traversals (inorder, preorder, postorder)
- [ ] Master BFS level-order traversal with queue
- [ ] Calculate tree height both recursively and iteratively
- [ ] Validate if tree is balanced
- [ ] Find diameter of binary tree
- [ ] Handle edge cases (null trees, single nodes)
- [ ] Understand time/space complexity trade-offs

## Problem Solving Steps 🔧

1. **Identify Pattern**: Traversal, validation, or construction?
2. **Choose Approach**: DFS vs BFS, recursive vs iterative
3. **Define Base Case**: Handle null nodes appropriately
4. **Recursive Logic**: Combine results from subtrees
5. **Edge Cases**: Empty trees, single nodes, skewed trees
6. **Optimize**: Consider space complexity for deep trees

## Next Week Preview 🔮
Week 5 will focus on Backtracking and Graph algorithms, building upon the recursive thinking developed with trees.

---
**Total Problems**: 7 | **Completion**: 100% | **Focus**: Binary Trees & Traversals
