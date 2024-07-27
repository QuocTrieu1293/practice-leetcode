/*
 * @lc app=leetcode id=912 lang=java
 *
 * [912] Sort an Array
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    void quickSort(int[] arr, int left, int right) {
        if (left >= right)
            return;
        int mid = (left + right) >> 1;
        int val = arr[mid] + arr[left] + arr[right]
                - Math.min(arr[mid], Math.min(arr[right], arr[left]))
                - Math.max(arr[mid], Math.max(arr[right], arr[left]));
        if (val == arr[left] && mid != left) {
            arr[mid] += arr[left];
            arr[left] = arr[mid] - arr[left];
            arr[mid] -= arr[left];
        } else if (val == arr[right] && mid != right) {
            arr[mid] += arr[right];
            arr[right] = arr[mid] - arr[right];
            arr[mid] -= arr[right];
        }

        int i = left, j = right;
        while (true) {
            while (i <= j && arr[i] <= arr[mid]) {
                i++;
            }
            while (j >= i && (arr[j] > arr[mid] || j == mid))
                j--;
            if (i < j) {
                arr[i] += arr[j];
                arr[j] = arr[i] - arr[j];
                arr[i] -= arr[j];
            } else {

                if (i < mid) {
                    if (arr[i] != arr[mid]) {
                        arr[mid] += arr[i];
                        arr[i] = arr[mid] - arr[i];
                        arr[mid] -= arr[i];
                    }
                    mid = i;
                } else {
                    if (arr[mid] != arr[j]) {
                        arr[mid] += arr[j];
                        arr[j] = arr[mid] - arr[j];
                        arr[mid] -= arr[j];
                    }
                    mid = j;
                }
                break;
            }
        }
        quickSort(arr, left, mid - 1);
        quickSort(arr, mid + 1, right);
    }

    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }
}
// @lc code=end
