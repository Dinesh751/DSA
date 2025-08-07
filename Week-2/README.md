# Week 2: Interval Problems & Greedy Algorithms 📊

## Overview
Week 2 focuses on interval manipulation problems using greedy algorithms and sorting techniques. This week emphasizes pattern recognition in scheduling, resource allocation, and optimization problems involving time intervals.

## Problems Solved (4/4)

| # | Problem | Difficulty | Pattern | Time | Space | Key Concepts |
|---|---------|------------|---------|------|-------|--------------|
| 1 | [Interval List Intersection #986](IntervalListIntersection986.java) | Medium | Two Pointers | O(m+n) | O(1) | Interval Intersection |
| 2 | [Meeting Rooms II #253](MeetingRoom253.java) | Medium | Min Heap/Sorting | O(n log n) | O(n) | Event Processing |
| 3 | [Minimum Arrows #452](MinimumNoOfArrows452.java) | Medium | Greedy Algorithm | O(n log n) | O(1) | Activity Selection |
| 4 | [Remove Overlapping Intervals #435](RemoveOverlapsIntervals435.java) | Medium | Greedy Algorithm | O(n log n) | O(1) | Greedy Choice |

## Algorithm Templates 📚

### 1. Interval Representation & Sorting
```java
// Standard interval representation
int[][] intervals = {{1,3}, {2,6}, {8,10}};

// Sort by start time
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

// Sort by end time (for greedy problems)
Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
```

### 2. Overlap Detection
```java
// Two intervals [a,b] and [c,d] overlap if:
boolean overlaps = Math.max(interval1[0], interval2[0]) <= Math.min(interval1[1], interval2[1]);

// No overlap conditions:
// b < c || d < a
```

### 3. Interval List Intersection (Two Pointers)
```java
// Template for finding intersections
while (i < list1.length && j < list2.length) {
    int start = Math.max(list1[i][0], list2[j][0]);
    int end = Math.min(list1[i][1], list2[j][1]);
    
    if (start <= end) {
        result.add(new int[]{start, end});
    }
    
    // Move pointer with earlier end time
    if (list1[i][1] < list2[j][1]) i++;
    else j++;
}
```

### 4. Meeting Rooms II (Event Processing)
```java
// Method 1: Separate Events
int[] starts = new int[n], ends = new int[n];
Arrays.sort(starts); Arrays.sort(ends);

// Method 2: Priority Queue
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
```

### 5. Greedy Algorithm Template (Activity Selection)
```java
// Sort by end time, choose earliest ending
Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
int count = 1, lastEnd = intervals[0][1];

for (int i = 1; i < intervals.length; i++) {
    if (intervals[i][0] >= lastEnd) {
        count++;
        lastEnd = intervals[i][1];
    }
}
```

## Key Patterns & Techniques 🎯

### Interval Patterns
1. **Two Pointers**: For intersection and comparison problems
2. **Greedy Choice**: Select optimal intervals based on criteria
3. **Event Processing**: Track start/end events separately
4. **Sorting Strategy**: Choose start time vs end time based on problem

### Greedy Algorithm Principles
1. **Activity Selection**: Choose earliest ending activity
2. **Resource Optimization**: Minimize resource usage
3. **Conflict Resolution**: Maximize non-conflicting selections
4. **Local Optimal Choice**: Make best choice at each step

## Complexity Analysis 📊

| Pattern | Time Complexity | Space Complexity | Use Cases |
|---------|----------------|------------------|-----------|
| Two Pointers (Intersection) | O(m + n) | O(1) | Finding overlaps between sorted lists |
| Event Processing | O(n log n) | O(n) | Meeting rooms, scheduling |
| Greedy (Activity Selection) | O(n log n) | O(1) | Optimization problems |
| Priority Queue | O(n log n) | O(n) | Dynamic resource allocation |

## Common Pitfalls & Tips ⚠️

### Interval Problems
- Always clarify if intervals are inclusive or exclusive
- Draw timeline diagrams to visualize overlaps
- Consider edge cases: empty arrays, single intervals
- Choose correct sorting criteria (start vs end time)

### Greedy Algorithms
- Verify that greedy choice leads to optimal solution
- Understand why local optimal = global optimal
- Sort by the right criteria for greedy choice
- Handle tie-breaking cases properly

## Practice Strategy 📈

### Beginner Level
1. Start with basic interval operations (merge, intersection)
2. Understand different sorting strategies
3. Practice identifying overlap conditions

### Intermediate Level
1. Master greedy algorithm patterns
2. Learn event processing techniques
3. Practice with meeting room problems

### Advanced Level
1. Combine multiple patterns in complex problems
2. Optimize space complexity where possible
3. Handle multiple constraint problems

## Quick Review Checklist ✅

- [ ] Can identify when to sort by start vs end time
- [ ] Understand overlap detection conditions
- [ ] Know two-pointer technique for intersections
- [ ] Can apply greedy algorithm for optimization
- [ ] Master event processing with priority queues
- [ ] Handle edge cases (empty, single intervals)
- [ ] Can prove why greedy choice is optimal

## Problem Solving Steps 🔧

1. **Understand Intervals**: What do start/end times represent?
2. **Identify Pattern**: Intersection, optimization, or scheduling?
3. **Choose Sorting**: Start time for merging, end time for greedy
4. **Apply Technique**: Two pointers, greedy, or event processing
5. **Handle Edge Cases**: Empty arrays, single intervals, ties
6. **Optimize**: Consider space/time tradeoffs

## Next Week Preview 🔮
Week 3 will focus on Stacks, Linked Lists, and Binary Search, building upon sorting and two-pointer techniques learned this week.

---
**Total Problems**: 4 | **Completion**: 100% | **Focus**: Intervals & Greedy Algorithms
