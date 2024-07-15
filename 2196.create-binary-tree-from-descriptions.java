/*
 * @lc app=leetcode id=2196 lang=java
 *
 * [2196] Create Binary Tree From Descriptions
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        HashMap<Integer, TreeNode> mp = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        TreeNode res = null;

        for (int[] arr : descriptions) {
            TreeNode par;
            if (mp.containsKey(arr[0])) {
                par = mp.get(arr[0]);
            } else {
                par = new TreeNode(arr[0]);
                res = par;
                mp.put(arr[0], par);
                set.add(arr[0]);
            }

            TreeNode child;
            if (mp.containsKey(arr[1])) {
                child = mp.get(arr[1]);
                set.remove(arr[1]);
            } else {
                child = new TreeNode(arr[1]);
                mp.put(arr[1], child);
            }
            if (arr[2] == 1) {
                par.left = child;
            } else {
                par.right = child;
            }
        }

        res = mp.get(set.iterator().next());
        return res;
    }
}
// @lc code=end
