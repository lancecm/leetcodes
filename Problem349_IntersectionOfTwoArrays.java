package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Srunkyo
 * @Date: 2018/8/18
 * @Description:
 * Easy
 *
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 * Note:
 *
 * Each element in the result must be unique.
 * The result can be in any order.
 */
public class Problem349_IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int n : nums1) set.add(n);
        for (int n : nums2) {
            if (set.contains(n)) {
                res.add(n);
            }
        }
        int[] result = new int[res.size()];
        int i = 0;
        for (int n : res) {
            result[i++] = n;
        }
        return result;
    }

    // 另外两种解法：
    // 1. 排序数组 + two pointers
    // 2. binary search  在一个list中查找另一个list中的值
}
