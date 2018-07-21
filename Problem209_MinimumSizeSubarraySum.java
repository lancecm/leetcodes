package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/7/21
 * @Description:
 * 209
 * medium
 *
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 *
 * Example:
 *
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 *
 */
public class Problem209_MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        // use two pointers (like sliding window)
        int i = 0, j = 0, sum = 0, min = Integer.MAX_VALUE;
        while(j < nums.length) {
            while (j < nums.length && sum < s) {
                sum += nums[j++];
            }
            while (sum >= s) {
                min = Math.min(j - i, min);
                sum -= nums[i++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    // Brute force with memoization
    public int minSubArrayLen2(int s, int[] nums) {
        int[] sums = new int[nums.length];
        if (nums == null || nums.length == 0) return 0;
        sums[0] = nums[0];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++)
            sums[i] += sums[i - 1] + nums[i];
        for (int i = 0; i < nums.length; i++) { // start point
            for (int j = i; j < nums.length; j++) { // end point
                int sum = sums[j] - sums[i] + nums[i];
                if (sum >= s) {
                    min = Math.min(min, j - i + 1);
                    break; // no need to add the remains
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    // Brute force with memoization + binarySearch（to find end）
    // o(nlogn)
    public int minSubArrayLen3(int s, int[] nums) {
        int[] sums = new int[nums.length];
        if (nums == null || nums.length == 0) return 0;
        sums[0] = nums[0];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++)
            sums[i] += sums[i - 1] + nums[i];
        for (int i = 0; i < nums.length; i++) {
            int j = findWindowEnd(i, sums, s); // find minimum j that satisfy sums[mid] - diff >= s
            if (j == nums.length) break;
            min = Math.min(j - i + 1, min);
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    private int findWindowEnd(int start, int[] sums, int s) {
        int i = start, j = sums.length - 1, diff = start == 0 ? 0 : sums[start - 1];
        while (i <= j) {
            int mid = (i + j) / 2;
            int sum = sums[mid] - diff;
            if (sum >= s) j = mid - 1;
            else i = mid + 1;
        }
        return i;
    }

    @Test
    public void test() {
        int s = 213;
        int[] nums = new int[] {12,28,83,4,25,26,25,2,25,25,25,12};
        System.out.println(minSubArrayLen3(s, nums));
    }
}
