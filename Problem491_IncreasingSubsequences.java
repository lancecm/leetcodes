package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Srunkyo
 * @Date: 2018/8/15
 * @Description:
 * Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 .
 *
 * Example:
 * Input: [4, 6, 7, 7]
 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * Note:
 * The length of the given array will not exceed 15.
 * The range of integer in the given array is [-100,100].
 * The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
 *
 * 思路：
 * 回溯
 */
public class Problem491_IncreasingSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        List<Integer> item = new ArrayList<>();
        helper(res, set, item, 0, nums);
        return res;
    }

    public void helper(List<List<Integer>> res, Set<List<Integer>> set, List<Integer> item, int idx, int[] nums) {
        if (!set.contains(item) && item.size() >=2) {
            List<Integer> insert = new ArrayList<>(item);
            res.add(insert);
            set.add(insert);
        }
        if(idx == nums.length) {
            return;
        }
        for (int i = idx; i < nums.length; i++) {
            if (item.size() == 0 || item.get(item.size() - 1) <= nums[i]) {
                item.add(nums[i]);
                helper(res, set, item, i + 1, nums);
                item.remove(item.size() - 1);
            }
        }
    }

    // 优化版本：直接使用set判断重复，最后的时候将set转换为list即可
    public List<List<Integer>> findSubsequences2(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        List<Integer> item = new ArrayList<>();
        findSequence(res, item, 0, nums);
        return new ArrayList<>(res);
    }

    public void findSequence(Set<List<Integer>> res, List<Integer> item, int index, int[] nums) {
        if (item.size() >= 2) res.add(new ArrayList<>(item));
        for (int i = 0; i < nums.length; i++) {
            if (item.size() == 0 || item.get(item.size() - 1) <= nums[i]) {
                item.add(nums[i]);
                findSequence(res, item, i + 1, nums);
                item.remove(item.size() - 1);
            }
        }
    }
}
