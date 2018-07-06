package LeetCode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/5
 * @Description:
 * 47
 * 中
 *
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * Example:
 *
 * Input: [1,1,2]
 * Output:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * 思路：回溯法，同Permutation1是一个道理；不同的是使用数组表示是否访问，并跳过与前一级相同且访问过的节点。
 * Time： O（n!*n）
 *        共有n*(n-1)*(n-2)....1次recursion call
 *        每次recursion call进行了n次循环
 * Space：TODO
 */
public class Problem47_PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        int[] marker = new int[nums.length];
        // If the head is the same with its previous, then the permutation it produces is the same as its previous.
        // Here by sorting the array we can easily skip those same head in the backtracing process.
        Arrays.sort(nums);
        helper(nums, tmp, result, marker);
        return result;
    }

    public void helper(int[] nums, List<Integer> tmp, List<List<Integer>> result, int[] marker) {
        if (tmp.size() == nums.length) result.add(new ArrayList(tmp));
        else {
            for (int i = 0; i < nums.length; i++) {
                if (marker[i] != 0) continue; // visited
                if (i > 0 && nums[i] == nums[i-1] && marker[i - 1] == 1) continue; // skip same visited precious head
                tmp.add(nums[i]);
                marker[i] = 1;
                helper(nums, tmp, result, marker);
                tmp.remove(tmp.size() - 1);
                marker[i] = 0;
            }
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{1,1,2};
        List<List<Integer>> result = permuteUnique(nums);
        for (List i : result) {
            for (int j = 0; j < i.size(); j++) {
                System.out.print(i.get(j) + " ");
            }
            System.out.println();
        }
    }
}
