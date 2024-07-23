/*
 * @lc app=leetcode id=1636 lang=java
 *
 * [1636] Sort Array by Increasing Frequency
 */

// @lc code=start
class Solution {
    public int[] frequencySort(int[] nums) {
        public int[] frequencySort(int[] nums) {
            int n = nums.length;
            Map<Integer, Integer> mp = new HashMap<>();
            for(int i : nums) {
                mp.compute(i, (k, v) -> v == null ? 1 : v+1);
            }
            Pair<Integer, Integer>[] arr = new Pair[mp.keySet().size()];
            int j = 0;
            for(Map.Entry<Integer, Integer> entry : mp.entrySet()) {
                arr[j++] = new Pair(entry.getKey(), entry.getValue());
            }
            Arrays.sort(arr, (p1, p2) -> {
                int val1 = p1.getValue(), val2 = p2.getValue();
                if(val1 == val2)
                    return -Integer.compare(p1.getKey(), p2.getKey());
                return Integer.compare(val1, val2);
            });
            int[] rs = new int[n];
            j = 0;
            int cnt = arr[0].getValue();
            for(int i=0; i<n; i++) {
                if(cnt == 0) {
                    j++;
                    cnt = arr[j].getValue();
                }
                rs[i] = arr[j].getKey();
                cnt--;
            }
            return rs;
        }
    }
}
// @lc code=end
