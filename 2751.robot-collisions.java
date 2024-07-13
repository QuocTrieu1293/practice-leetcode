/*
 * @lc app=leetcode id=2751 lang=java
 *
 * [2751] Robot Collisions
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        int[][] pos_idx = new int[n][2];
        for (int i = 0; i < n; i++) {
            pos_idx[i][0] = positions[i];
            pos_idx[i][1] = i;
        }

        // Arrays.sort(pos_idx, new Comparator<int[]>() {
        // @Override
        // public int compare(int[] r1, int[] r2) {
        // return Integer.compare(r1[0], r2[0]);
        // }
        // });
        Arrays.sort(pos_idx, (r1, r2) -> Integer.compare(r1[0], r2[0]));

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            int j = pos_idx[i][1];
            if (directions.charAt(j) == 'L') {
                while (!st.isEmpty()) {
                    int k = st.peek();
                    if (healths[k] > healths[j]) {
                        healths[k]--;
                        healths[j] = 0;
                        break;
                    } else if (healths[k] < healths[j]) {
                        healths[j]--;
                        healths[k] = 0;
                        st.pop();
                    } else {
                        st.pop();
                        healths[k] = 0;
                        healths[j] = 0;
                        break;
                    }
                }
            } else {
                st.push(j);
            }
        }

        List<Integer> rs = new LinkedList<>();
        for (int i : healths) {
            if (i > 0)
                rs.add(i);
        }

        return rs;
    }
}
// @lc code=end
