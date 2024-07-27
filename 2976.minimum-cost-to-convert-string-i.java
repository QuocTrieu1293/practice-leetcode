/*
 * @lc app=leetcode id=2976 lang=java
 *
 * [2976] Minimum Cost to Convert String I
 */

// @lc code=start
import java.util.*;

class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int[][] dp = new int[27][27];
        final int inf = 2000000005;
        for (int i = 0; i < 27; i++) {
            Arrays.fill(dp[i], inf);
            dp[i][i] = 0;
        }
        int n = cost.length;
        for (int i = 0; i < n; i++) {
            dp[original[i] - 'a'][changed[i] - 'a'] = Math.min(dp[original[i] - 'a'][changed[i] - 'a'], cost[i]);
        }
        for (int k = 0; k < 27; k++) {
            for (int i = 0; i < 27; i++) {
                for (int j = 0; j < 27; j++) {
                    if (dp[i][k] != inf && dp[k][j] != inf)
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);

                }
            }
        }
        long res = 0;
        int m = source.length();
        for (int i = 0; i < m; i++) {
            int c = dp[source.charAt(i) - 'a'][target.charAt(i) - 'a'];
            if (c == inf)
                return -1;
            res += c;
        }
        return res;
    }
}
// @lc code=end
