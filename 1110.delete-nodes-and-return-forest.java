/*
 * @lc app=leetcode id=1110 lang=java
 *
 * [1110] Delete Nodes And Return Forest
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

    void solve(TreeNode u, TreeNode par, HashSet<TreeNode> set, HashSet<Integer> deleteSet) {
        if (u == null)
            return;

        boolean deleted = deleteSet.contains(u.val);
        if (deleted) {
            set.remove(u);
            if (u.left != null)
                set.add(u.left);
            if (u.right != null)
                set.add(u.right);
        }
        solve(u.left, u, set, deleteSet);
        solve(u.right, u, set, deleteSet);

        if (deleted && par != null) {
            if (u == par.left)
                par.left = null;
            else
                par.right = null;
        }

    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        HashSet<Integer> deleteSet = new HashSet<>(Arrays.stream(to_delete).boxed().collect(Collectors.toSet()));
        HashSet<TreeNode> set = new HashSet<>();
        set.add(root);
        solve(root, null, set, deleteSet);
        List<TreeNode> rs = new ArrayList<>(set);
        return rs;
    }
}
// @lc code=end
