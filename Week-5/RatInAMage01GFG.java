import java.util.*;

public class RatInAMage01GFG {

    public List<String> findPath(int[][] maze, int n) {
        List<String> res = new ArrayList<>();
        boolean[][] vis = new boolean[n][n];

        if (maze[0][0] == 0 || maze[n - 1][n - 1] == 0)
            return Arrays.asList("-1");

        dfs(0, 0, maze, n, "", res, vis);
        
        if (res.isEmpty()) return Arrays.asList("-1");

        Collections.sort(res);  // Lexicographical order
        return res;
    }

    private void dfs(int row, int col, int[][] maze, int n, String path, List<String> res, boolean[][] vis) {
        // Base case: reached destination
        if (row == n - 1 && col == n - 1) {
            res.add(path);
            return;
        }

        // Directions: Down, Left, Right, Up (Lexicographic order)
        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, -1, 1, 0};
        char[] dir = {'D', 'L', 'R', 'U'};

        vis[row][col] = true;

        for (int i = 0; i < 4; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];

            if (isSafe(newRow, newCol, maze, vis, n)) {
                dfs(newRow, newCol, maze, n, path + dir[i], res, vis);
            }
        }

        vis[row][col] = false;  // Backtrack
    }

    private boolean isSafe(int r, int c, int[][] maze, boolean[][] vis, int n) {
        return r >= 0 && r < n && c >= 0 && c < n && !vis[r][c] && maze[r][c] == 1;
    }
    public static void main(String[] args) {
        RatInAMage01GFG solution = new RatInAMage01GFG();

        // Test case 1
        int[][] maze1 = {
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {1, 1, 0, 0},
            {0, 1, 1, 1}
        };
        List<String> expected1 = Arrays.asList("DDRDRR", "DRDDRR");
        List<String> actual1 = solution.findPath(maze1, 4);
        System.out.println("Test Case 1: Expected: " + expected1 + ", Actual: " + actual1);

        // Test case 2
        int[][] maze2 = {
            {1, 0},
            {1, 0}
        };
        List<String> expected2 = Arrays.asList("-1");
        List<String> actual2 = solution.findPath(maze2, 2);
        System.out.println("Test Case 2: Expected: " + expected2 + ", Actual: " + actual2);

        // Test case 3
        int[][] maze3 = {
            {1, 1},
            {1, 1}
        };
        List<String> expected3 = Arrays.asList("DR", "RD");
        List<String> actual3 = solution.findPath(maze3, 2);
        System.out.println("Test Case 3: Expected: " + expected3 + ", Actual: " + actual3);
    }
}
