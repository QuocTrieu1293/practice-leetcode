/*
 * @lc app=leetcode id=1530 lang=java
 *
 * [1530] Number of Good Leaf Nodes Pairs
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
    public int countPairs(TreeNode root, int distance) {
        int res = 0;
        Queue<Pair<TreeNode, Long>> qu = new LinkedList<>();
        List<Long> arr = new ArrayList<>();
        qu.offer(new Pair<>(root, 0l));
        while (!qu.isEmpty()) {
            Pair<TreeNode, Long> p = qu.poll();
            TreeNode u = p.first;
            long pos = p.second;
            if (u.left == null && u.right == null)
                arr.add(pos);
            else {
                if (u.left != null)
                    qu.offer(new Pair<>(u.left, 2l * pos + 1));
                if (u.right != null)
                    qu.offer(new Pair<>(u.right, 2l * pos + 2));
            }
        }
        int n = arr.size();
        for (int j = 0; j < n - 1; j++) {
            for (int k = j + 1; k < n; k++) {
                long a = arr.get(j), b = arr.get(k);
                int dist = 0;
                while (a != b && dist < distance) {
                    if (a > b) {
                        a = (a - ((a % 2) == 0 ? 2 : 1)) / 2;
                    } else {
                        b = (b - ((b % 2) == 0 ? 2 : 1)) / 2;
                    }
                    dist++;
                }
                if (a == b && dist <= distance)
                    res++;
            }
        }
        return res;
    }

    class Pair<K, V> {
        K first;
        V second;

        Pair(K u, V pos) {
            this.first = u;
            this.second = pos;
        }
    }
}
// @lc code=end
