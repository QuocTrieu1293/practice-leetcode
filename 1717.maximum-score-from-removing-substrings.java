/*
 * @lc app=leetcode id=1717 lang=java
 *
 * [1717] Maximum Score From Removing Substrings
 */

// @lc code=start

import java.util.*;

class Solution {
    public int maximumGain(String s, int x, int y) {
        String str = x > y ? "ab" : "ba";
        int max = Math.max(x, y), min = x + y - max;
        Stack<Character> st1 = new Stack<>();
        Stack<Character> st2 = new Stack<>();
        st1.push('?');
        int res = 0;
        for (char c : s.toCharArray()) {
            if (str.equals(st1.peek() + "" + c)) {
                st1.pop();
                res += max;
            } else {
                st1.push(c);
            }
        }

        st2.push('?');
        while (!st1.isEmpty()) {
            char c = st1.pop();
            if (str.equals(st2.peek() + "" + c)) {
                res += min;
                st2.pop();
            } else {
                st2.push(c);
            }
        }

        return res;
    }
}
// @lc code=end
