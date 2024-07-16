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
  boolean find(TreeNode u, int val, StringBuilder path) {
    if (u == null)
      return false;
    if (u.val == val)
      return true;

    path.append('L');
    if (find(u.left, val, path))
      return true;
    path.deleteCharAt(path.length() - 1);

    path.append('R');
    if (find(u.right, val, path))
      return true;
    path.deleteCharAt(path.length() - 1);

    return false;
  }

  public String getDirections(TreeNode root, int startValue, int destValue) {
    StringBuilder pathToStart = new StringBuilder();
    StringBuilder pathToDest = new StringBuilder();

    find(root, startValue, pathToStart);
    find(root, destValue, pathToDest);

    int len1 = pathToStart.length();
    int len2 = pathToDest.length();
    int m = Math.min(len1, len2);
    int i = 0;
    StringBuilder rs = new StringBuilder();
    while (i < m && pathToStart.charAt(i) == pathToDest.charAt(i)) {
      i++;
    }
    System.out.println(len1 + " " + len2 + " " + i);
    System.out.println(pathToStart.toString());
    System.out.println(pathToDest.toString());
    rs.append("U".repeat(len1 - i));
    rs.append(pathToDest.substring(i, len2));

    return rs.toString();
  }
}