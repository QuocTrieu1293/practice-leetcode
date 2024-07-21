/*
 * @lc app=leetcode id=2392 lang=java
 *
 * [2392] Build a Matrix With Conditions
 */

// @lc code=start
import java.util.*;

class Solution {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        List<Integer>[] graphRow = new List[k + 1];
        List<Integer>[] graphCol = new List[k + 1];
        for (int[] edge : rowConditions) {
            if (graphRow[edge[0]] == null)
                graphRow[edge[0]] = new LinkedList<>();
            graphRow[edge[0]].add(edge[1]);
        }
        for (int[] edge : colConditions) {
            if (graphCol[edge[0]] == null)
                graphCol[edge[0]] = new LinkedList<>();
            graphCol[edge[0]].add(edge[1]);
        }
        int[] visitedRow = new int[k + 1];
        int[] visitedCol = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            if (!(visitedCol[i] == 0 ? isDAG(i, graphCol, visitedCol) : true)
                    || !(visitedRow[i] == 0 ? isDAG(i, graphRow, visitedRow) : true)) {
                return new int[0][];
            }
        }

        // Exist valid result
        int[] posRow = new int[k + 1];
        int[] posCol = new int[k + 1];
        int row = k - 1;
        int col = k - 1;
        int[][] rs = new int[k][k];
        Arrays.fill(visitedCol, 0);
        Arrays.fill(visitedRow, 0);
        for (int i = 1; i <= k; i++) {
            if (visitedCol[i] == 0) {
                col = dfs(i, graphCol, visitedCol, col, posCol);
            }
            if (visitedRow[i] == 0) {
                row = dfs(i, graphRow, visitedRow, row, posRow);
            }
        }
        for (int i = 1; i <= k; i++) {
            rs[posRow[i]][posCol[i]] = i;
        }

        return rs;
    }

    boolean isDAG(int u, List<Integer>[] graph, int[] visited) {
        visited[u] = 1;
        if (graph[u] != null) {
            for (Integer v : graph[u]) {
                if (visited[v] == 1 || (visited[v] == 0 && !isDAG(v, graph, visited)))
                    return false;
            }
        }
        visited[u] = 2;
        return true;
    }

    int dfs(int u, List<Integer>[] graph, int[] visited, int idx, int[] pos) {
        visited[u] = 1;
        int curIdx = idx;
        if (graph[u] != null) {
            for (Integer v : graph[u]) {
                if (visited[v] == 0) {
                    curIdx = dfs(v, graph, visited, curIdx, pos);
                }
            }
        }
        pos[u] = curIdx;
        return curIdx - 1;
    }

}
// @lc code=end
