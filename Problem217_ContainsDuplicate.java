package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Srunkyo
 * @Date: 2018/8/13
 * @Description:
 * Easy
 *
 * Given an array of integers, find if the array contains any duplicates.
 *
 * Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
 *
 * Example 1:
 *
 * Input: [1,2,3,1]
 * Output: true
 * Example 2:
 *
 * Input: [1,2,3,4]
 * Output: false
 * Example 3:
 *
 * Input: [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 *
 * 一共有三种Naive解法：
 * 1. brute force O(n2)
 * 2. sort O(nlogn)
 * 3. HashTable O(n) 但是并不快？
 */
public class Problem217_ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
//                if (nums[i] > nums[j]) break;
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) {
                return true;
            }
            set.add(i);
        }
        return false;
    }
}
