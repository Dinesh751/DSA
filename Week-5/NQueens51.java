import java.util.*;

public class NQueens51 {
    /*
    Problem Statement:
    The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

    Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

    Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

    Example 1:
    Input: n = 4
    Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
    Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.

    Example 2:
    Input: n = 1
    Output: [["Q"]]

    Constraints:
    - 1 <= n <= 9
    */

    List<List<String>> res = new LinkedList<>();
    public List<List<String>> solveNQueens(int n) {
        boolean vis[][] = new boolean [n][n];
        helper(vis, 0);
        return res;
    }

  public  void helper(boolean vis[][], int row){
    if(row == vis.length){
       List<String> list = createList(vis);
       res.add(list);
       return;
    }

    for(int col=0; col<vis[row].length; col++){
        if(isSafe(row, col, vis)){
            vis[row][col] = true;
            helper(vis, row+1);
            vis[row][col] = false;
        }
    }
  }

  public  List<String> createList(boolean vis[][]){
    List<String> list = new LinkedList<>();

    for(int i=0; i<vis.length; i++){
        StringBuilder sb = new StringBuilder();
        for(int j=0; j<vis.length; j++){
            if(vis[i][j] == true){
                sb.append("Q");
            }else{
                sb.append(".");
            }
        }
        list.add(sb.toString());
    }
    return list;
  }

  public  boolean isSafe(int row, int col, boolean vis[][]){
    for(int i=0; i<row; i++){
        if(vis[i][col]){
            return false;
        }
    }

    for(int i=1; i<= Math.min(row, col); i++){
        if(vis[row-i][col-i]){
            return false;
        }
    }
    for(int i=1; i<=Math.min(row,vis.length-1-col); i++){
        if(vis[row-i][col+i]){
            return false;
        }
    }
    return true;
  }


    public static void main(String[] args) {
        NQueens51 solution = new NQueens51();

        // Test case 1
        int n1 = 4;
        List<List<String>> expected1 = Arrays.asList(
            Arrays.asList(".Q..", "...Q", "Q...", "..Q."),
            Arrays.asList("..Q.", "Q...", "...Q", ".Q..")
        );
        List<List<String>> actual1 = solution.solveNQueens(n1);
        System.out.println("Test Case 1: Expected: " + expected1 + ", Actual: " + actual1);
    }
}
