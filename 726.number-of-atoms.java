/*
 * @lc app=leetcode id=726 lang=java
 *
 * [726] Number of Atoms
 */

// @lc code=start

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

class Solution {
    public String countOfAtoms(String formula) {
        StringBuilder res = new StringBuilder();
        TreeMap<String, Integer> mp = new TreeMap<>();
        Stack<Pair> st1 = new Stack<>();
        Stack<Pair> st2 = new Stack<>();
        StringBuilder atom = new StringBuilder();
        int num = 0;
        char[] c = formula.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] >= '0' && c[i] <= '9') {
                num = num * 10 + (c[i] - '0');
            } else if (c[i] >= 'a' && c[i] <= 'z') {
                atom.append(c[i]);
            } else {
                if (atom.length() > 0) {
                    String key = atom.toString();
                    atom.setLength(0);
                    int val = num > 0 ? num : 1;
                    num = 0;
                    st1.push(new Pair(key, val));
                }
                if (c[i] == '(') {
                    st1.push(new Pair("(", 0));
                } else if (c[i] == ')') {
                    while (i + 1 < c.length && c[i + 1] >= '0' && c[i + 1] <= '9') {
                        num = num * 10 + (c[i + 1] - '0');
                        i++;
                    }
                    num = num > 0 ? num : 1;
                    // Object[] arr = st1.toArray();
                    // for(Object obj : arr) {
                    // Pair p = (Pair) obj;
                    // System.out.println(p.first + ", " + p.second);
                    // }
                    while (!st1.isEmpty()) {
                        Pair p = st1.pop();
                        if (p.first.equals("(")) {
                            while (!st2.isEmpty()) {
                                st1.push(st2.pop());
                            }
                            break;
                        } else {
                            p.second *= num;
                            st2.push(p);
                        }
                    }

                    num = 0;
                } else {
                    atom.append(c[i]);
                }
            }
        }

        if (atom.length() > 0) {
            String key = atom.toString();
            atom.setLength(0);
            int val = num > 0 ? num : 1;
            num = 0;
            st1.push(new Pair(key, val));
        }

        while (!st1.isEmpty()) {
            Pair p = st1.pop();
            String key = p.first;
            int val = p.second;
            mp.compute(key, (k, preVal) -> preVal == null ? val : preVal + val);
        }

        for (Map.Entry<String, Integer> entry : mp.entrySet()) {
            res.append(entry.getKey());
            if (entry.getValue() > 1) {
                res.append(entry.getValue());
            }
        }

        return res.toString();
    }

    class Pair {
        public String first;
        public int second;

        public Pair(String first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
// @lc code=end
