/*
 * @lc app=leetcode id=2191 lang=java
 *
 * [2191] Sort the Jumbled Numbers
 */

// @lc code=start
class Solution {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][3];
        for (int i = 0; i < n; i++) {
            int k = 1;
            if (nums[i] == 0)
                arr[i][0] = mapping[0];
            else {
                while (nums[i] / k > 0) {
                    arr[i][0] = mapping[(nums[i] / k) % 10] * k + arr[i][0];
                    k *= 10;
                }
            }
            arr[i][1] = i;
            arr[i][2] = nums[i];
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        int[] rs = new int[n];
        for (int i = 0; i < n; i++) {
            rs[i] = arr[i][2];
        }
        return rs;
    }
}
// @lc code=end
