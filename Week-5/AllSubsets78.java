import java.util.*;

public class AllSubsets78 {
    /*
    Problem Statement:
    Given an integer array nums of unique elements, return all possible subsets (the power set).

    The solution set must not contain duplicate subsets. Return the solution in any order.

    Example 1:
    Input: nums = [1,2,3]
    Output: [[],[1],[2],[3],[1,2],[1,3],[2,3],[1,2,3]]

    Example 2:
    Input: nums = [0]
    Output: [[],[0]]

    Constraints:
    - 1 <= nums.length <= 10
    - -10 <= nums[i] <= 10
    - All the numbers of nums are unique.
    */

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        helper(nums, 0, result, list);
        return result;
    }

    public void helper(int nums[], int ind, List<List<Integer>> result, List<Integer> list){
        if(ind == nums.length){
            result.add(new ArrayList<>(list));
            
            return;
        }
        
        list.add(nums[ind]);
        helper(nums, ind+1, result, list);
        list.remove(list.size()-1);
        helper(nums, ind+1, result, list);
    }

    public static void main(String[] args) {
        AllSubsets78 solution = new AllSubsets78();

        // Test case 1
        int[] nums1 = {1, 2, 3};
        List<List<Integer>> expected1 = Arrays.asList(
            Arrays.asList(),
            Arrays.asList(1),
            Arrays.asList(2),
            Arrays.asList(3),
            Arrays.asList(1, 2),
            Arrays.asList(1, 3),
            Arrays.asList(2, 3),
            Arrays.asList(1, 2, 3)
        );
        List<List<Integer>> actual1 = solution.subsets(nums1);
        System.out.println("Test Case 1: Expected: " + expected1 + ", Actual: " + actual1);

        // Test case 2
        int[] nums2 = {0};
        List<List<Integer>> expected2 = Arrays.asList(
            Arrays.asList(),
            Arrays.asList(0)
        );
        List<List<Integer>> actual2 = solution.subsets(nums2);
        System.out.println("Test Case 2: Expected: " + expected2 + ", Actual: " + actual2);
    }
}
