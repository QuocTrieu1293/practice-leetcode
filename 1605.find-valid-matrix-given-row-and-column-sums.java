/*
 * @lc app=leetcode id=1605 lang=java
 *
 * [1605] Find Valid Matrix Given Row and Column Sums
 */

// @lc code=start
class Solution {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m = rowSum.length, n = colSum.length;
        int[][] rs = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rs[i][j] = Math.min(rowSum[i], colSum[j]);
                rowSum[i] -= rs[i][j];
                colSum[j] -= rs[i][j];
            }
        }
        return rs;
    }
}
// @lc code=end
