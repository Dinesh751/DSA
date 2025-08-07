# Week 5: Backtracking & Graph Algorithms 🕸️

## Overview
Week 5 focuses on backtracking algorithms and graph fundamentals including representation, traversal algorithms (DFS/BFS), and classic graph problems. This week builds systematic exploration and recursive thinking skills.

## Problems Solved (6/6)

| # | Problem | Difficulty | Pattern | Time | Space | Key Concepts |
|---|---------|------------|---------|------|-------|--------------|
| 1 | [All Subsets #78](AllSubsets78.java) | Medium | Backtracking | O(2^n) | O(2^n) | Power Set Generation |
| 2 | [N-Queens #51](NQueens51.java) | Hard | Backtracking | O(N!) | O(N) | Constraint Satisfaction |
| 3 | [Partition Equal Subset Sum #416](PartitionEqualSubsetSum416.java) | Medium | DP/Backtracking | O(n*sum) | O(sum) | Subset Sum Problem |
| 4 | [Rat in a Maze](RatInAMage01GFG.java) | Medium | Backtracking | O(4^(n*m)) | O(n*m) | Path Finding |
| 5 | [Path Exists in Graph #1971](PathExistInGraph1972.java) | Easy | Graph DFS/BFS | O(V+E) | O(V) | Graph Traversal |
| 6 | [Create Graph](CreateGraph.java) | - | Graph Construction | O(V+E) | O(V+E) | Graph Representation |

## Algorithm Templates 📚

### 1. Backtracking Template
```java
public void backtrack(parameters) {
    // Base case - found solution
    if (isComplete(parameters)) {
        processSolution(parameters);
        return;
    }
    
    // Try all possible choices
    for (Choice choice : getAllChoices(parameters)) {
        if (isValid(choice, parameters)) {
            makeChoice(choice, parameters);
            backtrack(updatedParameters);
            undoChoice(choice, parameters); // Backtrack
        }
    }
}
```

### 2. Subset Generation (Power Set)
```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    generateSubsets(nums, 0, new ArrayList<>(), result);
    return result;
}

private void generateSubsets(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
    result.add(new ArrayList<>(current)); // Add current subset
    
    for (int i = index; i < nums.length; i++) {
        current.add(nums[i]);              // Include element
        generateSubsets(nums, i + 1, current, result);
        current.remove(current.size() - 1); // Backtrack
    }
}
```

### 3. N-Queens Problem
```java
public List<List<String>> solveNQueens(int n) {
    List<List<String>> result = new ArrayList<>();
    char[][] board = new char[n][n];
    for (int i = 0; i < n; i++) {
        Arrays.fill(board[i], '.');
    }
    solve(board, 0, result);
    return result;
}

private void solve(char[][] board, int row, List<List<String>> result) {
    if (row == board.length) {
        result.add(construct(board));
        return;
    }
    
    for (int col = 0; col < board.length; col++) {
        if (isValid(board, row, col)) {
            board[row][col] = 'Q';
            solve(board, row + 1, result);
            board[row][col] = '.'; // Backtrack
        }
    }
}
```

### 4. Graph Representation & Traversal
```java
// Adjacency List Representation
public class Graph {
    private int vertices;
    private ArrayList<Integer>[] adjList;
    
    public Graph(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new ArrayList<>();
        }
    }
    
    public void addEdge(int src, int dest) {
        adjList[src].add(dest);
        adjList[dest].add(src); // For undirected graph
    }
}
```

### 5. DFS & BFS Templates
```java
// DFS (Recursive)
public void dfs(ArrayList<Integer>[] graph, boolean[] visited, int node) {
    visited[node] = true;
    System.out.print(node + " ");
    
    for (int neighbor : graph[node]) {
        if (!visited[neighbor]) {
            dfs(graph, visited, neighbor);
        }
    }
}

// BFS (Iterative with Queue)
public void bfs(ArrayList<Integer>[] graph, int start) {
    boolean[] visited = new boolean[graph.length];
    Queue<Integer> queue = new LinkedList<>();
    
    visited[start] = true;
    queue.offer(start);
    
    while (!queue.isEmpty()) {
        int node = queue.poll();
        System.out.print(node + " ");
        
        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                queue.offer(neighbor);
            }
        }
    }
}
```

## Key Patterns & Techniques 🎯

### Backtracking Patterns
1. **Choice Exploration**: Try all possible choices systematically
2. **Constraint Checking**: Validate choices before proceeding
3. **State Management**: Track current state and undo changes
4. **Pruning**: Skip invalid branches early

### Graph Patterns
1. **DFS (Depth-First)**: Path finding, cycle detection, connectivity
2. **BFS (Breadth-First)**: Shortest path, level processing
3. **Connectivity**: Connected components, reachability
4. **Cycle Detection**: Validation, dependency checking

### Common Problem Types
1. **Subset Generation**: Power sets, combinations
2. **Permutation Problems**: Arrangements, sequences
3. **Constraint Satisfaction**: N-Queens, Sudoku
4. **Path Finding**: Maze solving, graph traversal

## Complexity Analysis 📊

| Pattern | Time Complexity | Space Complexity | Use Cases |
|---------|----------------|------------------|-----------|
| Subset Generation | O(2^n) | O(2^n) | Power set, combination problems |
| N-Queens | O(N!) | O(N) | Constraint satisfaction |
| Graph DFS | O(V + E) | O(V) | Path finding, connectivity |
| Graph BFS | O(V + E) | O(V) | Shortest path, level processing |
| Backtracking (general) | O(b^d) | O(d) | b=branching factor, d=depth |

*where V = vertices, E = edges, n = input size*

## Common Pitfalls & Tips ⚠️

### Backtracking
- Always undo changes after recursive calls (backtrack step)
- Use efficient data structures for state management
- Implement pruning to avoid unnecessary exploration
- Consider iterative approaches for very deep recursion

### Graph Algorithms
- Always initialize visited array properly
- Handle disconnected components by checking all vertices
- Choose appropriate representation (adjacency list vs matrix)
- Consider direction of edges (directed vs undirected)

### Optimization Techniques
- Use memoization for overlapping subproblems
- Implement early termination when solution found
- Use bit manipulation for subset problems when applicable
- Consider iterative deepening for space-constrained problems

## Practice Strategy 📈

### Beginner Level
1. Generate all subsets of an array
2. Solve simple maze problems
3. Implement basic DFS and BFS

### Intermediate Level
1. Solve N-Queens problem
2. Find all permutations with constraints
3. Detect cycles in graphs

### Advanced Level
1. Optimize backtracking with advanced pruning
2. Solve complex constraint satisfaction problems
3. Implement graph algorithms with weighted edges

## Quick Review Checklist ✅

- [ ] Can implement backtracking template correctly
- [ ] Generate all subsets using backtracking
- [ ] Solve N-Queens with proper constraint checking
- [ ] Implement both DFS and BFS for graphs
- [ ] Handle graph connectivity and cycle detection
- [ ] Understand when to use backtracking vs other approaches
- [ ] Can optimize backtracking with pruning techniques

## Problem Solving Steps 🔧

1. **Identify Pattern**: Backtracking, graph traversal, or combination?
2. **Define State Space**: What choices are available at each step?
3. **Check Constraints**: What makes a choice valid/invalid?
4. **Choose Algorithm**: DFS/BFS for graphs, backtracking for search
5. **Implement Base Case**: When to stop recursion?
6. **Optimize**: Add pruning, memoization, or iterative approaches

## Next Week Preview 🔮
Week 6 will focus on Advanced Topics including Dynamic Programming patterns, Trie data structures, and optimization techniques.

---
**Total Problems**: 6 | **Completion**: 100% | **Focus**: Backtracking & Graph Fundamentals
