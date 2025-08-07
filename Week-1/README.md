# Week 1: Arrays, Hashing & Two Pointers 🎯

## Overview
Week 1 focuses on fundamental array operations, hashing techniques, and essential algorithmic patterns like two pointers and sliding window. This foundational week establishes core problem-solving patterns used throughout the course.

## Problems Solved (14/14)

| # | Problem | Difficulty | Pattern | Time | Space | Key Concepts |
|---|---------|------------|---------|------|-------|--------------|
| 1 | [Two Sum #1](TwoSum1.java) | Easy | HashMap | O(n) | O(n) | Hash Table Lookup |
| 2 | [Best Time to Buy/Sell Stock #121](BestTimeToBuyStock121.java) | Easy | One Pass | O(n) | O(1) | Min Tracking |
| 3 | [Longest Substring Without Repeat #3](LongestSubstringWithoutRepeating3.java) | Medium | Sliding Window | O(n) | O(min(m,n)) | Variable Window |
| 4 | [Valid Anagram #242](ValidAnagram242.java) | Easy | Character Count | O(n) | O(1) | Frequency Array |
| 5 | [First Unique Character #387](FirstUniqueCharacter387.java) | Easy | HashMap | O(n) | O(1) | Character Frequency |
| 6 | [Group Anagrams #49](GroupAnagrams49.java) | Medium | HashMap + Sorting | O(n*k*log k) | O(n*k) | Key Generation |
| 7 | [Valid Palindrome #125](ValidPalindrome125.java) | Easy | Two Pointers | O(n) | O(1) | Character Processing |
| 8 | [Missing Number #268](MissingNumber268.java) | Easy | Math/XOR | O(n) | O(1) | Sum Formula |
| 9 | [Max Avg Subarray #643](MaxAvgSubarray643.java) | Easy | Sliding Window | O(n) | O(1) | Fixed Window |
| 10 | [Min Size Subarray Sum #209](MinimumSizeSubarraySum209.java) | Medium | Sliding Window | O(n) | O(1) | Variable Window |
| 11 | [Move Zeroes #283](MoveZeroes283.java) | Easy | Two Pointers | O(n) | O(1) | In-place Manipulation |
| 12 | [Selection Sort](SelectionSort.java) | - | Sorting | O(n²) | O(1) | Selection Algorithm |
| 13 | [Kadane's Algorithm #53](KadaneAlgorithm53.java) | Medium | Dynamic Programming | O(n) | O(1) | Optimal Substructure |

## Algorithm Templates 📚

### 1. HashMap for O(1) Lookups
```java
// Two Sum Pattern
Map<Integer, Integer> map = new HashMap<>();
for (int i = 0; i < nums.length; i++) {
    int complement = target - nums[i];
    if (map.containsKey(complement)) {
        return new int[]{map.get(complement), i};
    }
    map.put(nums[i], i);
}
```

### 2. Two Pointers Template
```java
// Opposite Direction
int left = 0, right = array.length - 1;
while (left < right) {
    if (condition_met) {
        // Process and move pointers
    } else if (need_larger_sum) {
        left++;
    } else {
        right--;
    }
}
```

### 3. Sliding Window (Fixed Size)
```java
// Fixed Window Template
int windowSum = 0;
for (int i = 0; i < k; i++) {
    windowSum += nums[i];
}
int maxSum = windowSum;

for (int i = k; i < nums.length; i++) {
    windowSum = windowSum - nums[i - k] + nums[i];
    maxSum = Math.max(maxSum, windowSum);
}
```

### 4. Sliding Window (Variable Size)
```java
// Variable Window Template
int left = 0, right = 0, minLength = Integer.MAX_VALUE;
while (right < array.length) {
    // Expand window
    windowSum += nums[right];
    
    while (windowSum >= target) {
        minLength = Math.min(minLength, right - left + 1);
        windowSum -= nums[left++]; // Shrink window
    }
    right++;
}
```

### 5. Kadane's Algorithm (Max Subarray)
```java
// Dynamic Programming Approach
int maxSum = nums[0], currentSum = nums[0];
for (int i = 1; i < nums.length; i++) {
    currentSum = Math.max(nums[i], currentSum + nums[i]);
    maxSum = Math.max(maxSum, currentSum);
}
```

## Key Patterns & Techniques 🎯

### Hashing Patterns
1. **O(1) Lookups**: Two Sum, frequency counting
2. **Character Mapping**: Anagram detection, unique characters
3. **Key Generation**: Grouping by sorted strings
4. **Frequency Arrays**: For limited character sets (a-z)

### Two Pointers Patterns
1. **Opposite Direction**: Palindrome checking, target sum
2. **Same Direction**: Array partitioning, in-place operations
3. **Fast/Slow**: Cycle detection (future weeks)
4. **Multiple Pointers**: Complex array manipulations

### Sliding Window Patterns
1. **Fixed Window**: Maximum/minimum in fixed size
2. **Variable Window**: Optimization problems (min/max length)
3. **Expanding Window**: Include elements until condition met
4. **Shrinking Window**: Exclude elements to maintain condition

## Complexity Analysis 📊

| Pattern | Time Complexity | Space Complexity | Use Cases |
|---------|----------------|------------------|-----------|
| HashMap Lookup | O(n) | O(n) | Two Sum, frequency problems |
| Two Pointers | O(n) | O(1) | Palindromes, sorted arrays |
| Fixed Sliding Window | O(n) | O(1) | Subarray with fixed size |
| Variable Sliding Window | O(n) | O(1) | Optimization problems |
| Character Frequency | O(n) | O(1) | String problems with limited charset |

## Common Pitfalls & Tips ⚠️

### HashMap Usage
- Always check `containsKey()` before `get()`
- Use `getOrDefault()` for cleaner frequency counting
- Consider `LinkedHashMap` when order matters
- Watch out for null keys/values

### Two Pointers
- Carefully handle boundary conditions (`left < right`)
- Consider what happens when pointers meet
- Ensure sorted array when required
- Handle edge cases (empty, single element)

### Sliding Window
- Clearly define expansion and shrinking conditions
- Track window state efficiently (sum, count, etc.)
- Consider edge cases (window larger than array)
- Optimize by avoiding recalculations

## Practice Strategy 📈

### Beginner Level
1. Master basic HashMap operations (Two Sum)
2. Practice simple two-pointer problems (Valid Palindrome)
3. Implement fixed-size sliding window (Max Average)

### Intermediate Level
1. Tackle variable sliding window problems
2. Combine patterns (Two Sum with sorted arrays)
3. Master character frequency problems

### Advanced Level
1. Optimize space complexity where possible
2. Handle multiple constraints simultaneously
3. Design custom data structures when needed

## Quick Review Checklist ✅

- [ ] Can implement Two Sum with HashMap in O(n)
- [ ] Understand when to use two pointers vs other approaches
- [ ] Master both fixed and variable sliding window
- [ ] Can handle character frequency problems efficiently
- [ ] Know Kadane's algorithm for maximum subarray
- [ ] Can identify optimal time/space complexity
- [ ] Handle edge cases consistently

## Problem Solving Steps 🔧

1. **Identify Pattern**: Array manipulation, frequency, optimization?
2. **Choose Technique**: HashMap, two pointers, or sliding window?
3. **Consider Constraints**: Time/space requirements, input size
4. **Handle Edge Cases**: Empty arrays, single elements, duplicates
5. **Optimize**: Can we reduce space? Improve readability?

## Next Week Preview 🔮
Week 2 will focus on Interval Problems and Greedy Algorithms, building upon sorting and optimization skills learned this week.

---
**Total Problems**: 14 | **Completion**: 100% | **Focus**: Arrays, Hashing & Two Pointers
