# Week 6: Dynamic Programming

This week focuses on dynamic programming concepts, patterns, and optimization techniques.

## 📚 Planned Topics

### 1D Dynamic Programming
| Problem | LeetCode # | Difficulty | Key Pattern |
|---------|------------|------------|-------------|
| Climbing Stairs | [#70](https://leetcode.com/problems/climbing-stairs/) | Easy | Basic DP |
| House Robber | [#198](https://leetcode.com/problems/house-robber/) | Medium | Linear DP |
| Coin Change | [#322](https://leetcode.com/problems/coin-change/) | Medium | Unbounded Knapsack |
| Longest Increasing Subsequence | [#300](https://leetcode.com/problems/longest-increasing-subsequence/) | Medium | LIS Pattern |
| Word Break | [#139](https://leetcode.com/problems/word-break/) | Medium | String DP |

### 2D Dynamic Programming
| Problem | LeetCode # | Difficulty | Key Pattern |
|---------|------------|------------|-------------|
| Unique Paths | [#62](https://leetcode.com/problems/unique-paths/) | Medium | Grid DP |
| Minimum Path Sum | [#64](https://leetcode.com/problems/minimum-path-sum/) | Medium | Grid DP |
| Edit Distance | [#72](https://leetcode.com/problems/edit-distance/) | Hard | String Matching |
| Longest Common Subsequence | [#1143](https://leetcode.com/problems/longest-common-subsequence/) | Medium | LCS Pattern |

## 🎯 Key Concepts to Master

### 1. DP Fundamentals
- **Overlapping Subproblems**: Same subproblems appear multiple times
- **Optimal Substructure**: Optimal solution contains optimal solutions to subproblems
- **Memoization**: Top-down approach with caching
- **Tabulation**: Bottom-up approach with table filling

### 2. Common DP Patterns

#### Basic 1D DP Template
```java
public int solve(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];
    
    // Base cases
    dp[0] = baseCase;
    
    // Fill the table
    for (int i = 1; i < n; i++) {
        dp[i] = /* recurrence relation */;
    }
    
    return dp[n-1];
}
```

#### 2D Grid DP Template
```java
public int solve(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[][] dp = new int[m][n];
    
    // Initialize base cases
    dp[0][0] = grid[0][0];
    
    // Fill first row and column
    for (int i = 1; i < m; i++) dp[i][0] = /* base case */;
    for (int j = 1; j < n; j++) dp[0][j] = /* base case */;
    
    // Fill the rest
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] = /* recurrence relation */;
        }
    }
    
    return dp[m-1][n-1];
}
```

### 3. DP Optimization Techniques
- **Space Optimization**: Using O(1) or O(n) instead of O(n²)
- **Rolling Array**: When only previous row/column needed
- **State Compression**: Bit manipulation for subset problems

## 🔧 Classic DP Problems

### Fibonacci (Basic DP)
```java
// Memoization (Top-down)
public int fibMemo(int n, int[] memo) {
    if (n <= 1) return n;
    if (memo[n] != 0) return memo[n];
    
    memo[n] = fibMemo(n-1, memo) + fibMemo(n-2, memo);
    return memo[n];
}

// Tabulation (Bottom-up)
public int fibTab(int n) {
    if (n <= 1) return n;
    
    int[] dp = new int[n+1];
    dp[0] = 0;
    dp[1] = 1;
    
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i-1] + dp[i-2];
    }
    
    return dp[n];
}

// Space Optimized
public int fibOptimized(int n) {
    if (n <= 1) return n;
    
    int prev2 = 0, prev1 = 1;
    
    for (int i = 2; i <= n; i++) {
        int current = prev1 + prev2;
        prev2 = prev1;
        prev1 = current;
    }
    
    return prev1;
}
```

### Coin Change (Unbounded Knapsack)
```java
public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1);
    dp[0] = 0;
    
    for (int i = 1; i <= amount; i++) {
        for (int coin : coins) {
            if (coin <= i) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
    }
    
    return dp[amount] > amount ? -1 : dp[amount];
}
```

### Longest Common Subsequence
```java
public int longestCommonSubsequence(String text1, String text2) {
    int m = text1.length(), n = text2.length();
    int[][] dp = new int[m + 1][n + 1];
    
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (text1.charAt(i-1) == text2.charAt(j-1)) {
                dp[i][j] = dp[i-1][j-1] + 1;
            } else {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
    }
    
    return dp[m][n];
}
```

## 📊 DP Pattern Categories

| Pattern | Description | Example Problems |
|---------|-------------|------------------|
| Linear DP | 1D problems with sequential dependencies | House Robber, Climbing Stairs |
| Grid DP | 2D grid traversal problems | Unique Paths, Min Path Sum |
| Interval DP | Problems on intervals/ranges | Matrix Chain Multiplication |
| Tree DP | DP on tree structures | Tree diameter, Max path sum |
| Bitmask DP | State represented by bitmasks | Traveling Salesman |
| Digit DP | Problems involving digit constraints | Count numbers with property |

## 🧠 Problem-Solving Strategy

### Step 1: Identify DP Problem
- Can be broken into subproblems?
- Overlapping subproblems exist?
- Optimal substructure present?

### Step 2: Define State
- What does dp[i] represent?
- What are the dimensions needed?
- What's the base case?

### Step 3: Find Recurrence Relation
- How to compute dp[i] from previous states?
- What are the transitions?

### Step 4: Implement and Optimize
- Start with memoization
- Convert to tabulation
- Optimize space if possible

## 🎓 Common DP Optimizations

### Space Optimization Examples
```java
// From O(n²) to O(n)
// Only need previous row
int[] prev = new int[n];
int[] curr = new int[n];

// From O(n) to O(1)  
// Only need last few elements
int prev2 = 0, prev1 = 1;
```

### Rolling Array Technique
```java
// 2D DP with only 2 rows needed
int[][] dp = new int[2][n];
for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
        dp[i % 2][j] = /* recurrence using (i-1) % 2 */;
    }
}
```

## 📝 Quick Reference

### When to Use DP
- Problem has optimal substructure
- Overlapping subproblems exist
- Can define recurrence relation
- Need optimization (min/max/count)

### DP vs Other Approaches
| Problem Type | DP | Greedy | Backtracking |
|--------------|----| -------|--------------|
| Optimization | ✅ | ✅ | ❌ |
| All Solutions | ❌ | ❌ | ✅ |
| Exponential Reduction | ✅ | ✅ | ❌ |
| Local Optimum | ❌ | ✅ | ❌ |

### Time Complexity Patterns
- **1D DP**: Usually O(n) or O(n²)
- **2D DP**: Usually O(mn) where m,n are dimensions
- **With optimization**: Often reduces space complexity

---

**Status**: 📅 **Planned for Future Implementation**
