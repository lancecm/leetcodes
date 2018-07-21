package LeetCode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Srunkyo
 * @Date: 2018/7/21
 * @Description:
 * 456
 * Medium
 * 
 * Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.
 *
 * Note: n will be less than 15,000.
 *
 * Example 1:
 * Input: [1, 2, 3, 4]
 *
 * Output: False
 *
 * Explanation: There is no 132 pattern in the sequence.
 * Example 2:
 * Input: [3, 1, 4, 2]
 *
 * Output: True
 *
 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 * Example 3:
 * Input: [-1, 3, 2, 0]
 *
 * Output: True
 *
 * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 *
 * TODO：本题有很多种解法
 */
public class Problem456_123Pattern {

    public boolean find132pattern(int[] nums) {
        if (nums.length <= 2) return false;
        int n = nums.length, i = 0, j = 0, k = 0;
        while (i < n) {
            while (i < n - 1 && nums[i] >= nums[i + 1]) ++i; // find 1st: smaller than second
            j = i + 1;
            while (j < n - 1 && nums[j] <= nums[j + 1]) ++j; // find 2nd: bigger than third
            k = j + 1;
            while (k < n) {
                if (nums[k] > nums[i] && nums[k] < nums[j]) return true; // find 3rd: bigger than second and smaller than third
                k++;
            }
            i = j + 1; // search begin from j + 1
        }
        return false;
    }

}
