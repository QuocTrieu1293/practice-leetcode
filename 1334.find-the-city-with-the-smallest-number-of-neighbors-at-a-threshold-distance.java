/*
 * @lc app=leetcode id=1334 lang=java
 *
 * [1334] Find the City With the Smallest Number of Neighbors at a Threshold Distance
 */

// @lc code=start
import java.util.*;

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(arr[i], 100000005);
            arr[i][i] = 0;
        }
        for (int[] a : edges) {
            arr[a[0]][a[1]] = a[2];
            arr[a[1]][a[0]] = a[2];
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] > arr[i][k] + arr[k][j])
                        arr[i][j] = arr[i][k] + arr[k][j];
                }
            }
        }

        int city = -1, min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (arr[i][j] > 0 && arr[i][j] <= distanceThreshold)
                    cnt++;
            }
            if (cnt <= min) {
                min = cnt;
                city = i;
            }
        }

        return city;
    }
}
// @lc code=end
