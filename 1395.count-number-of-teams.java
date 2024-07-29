/*
 * @lc app=leetcode id=1395 lang=java
 *
 * [1395] Count Number of Teams
 */

// @lc code=start
class Solution {
    public int numTeams(int[] rating) {
        int n = rating.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int rs = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (rating[j] < rating[i]) {
                    if (j < i)
                        left[i]++;
                    else
                        right[i]++;
                }
            }
            rs += left[i] * (n - 1 - i - right[i]) + right[i] * (i - left[i]);
        }
        return rs;
    }
}
// @lc code=end
