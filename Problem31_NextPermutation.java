package LeetCode;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/11
 * @Description:
 * 31
 * Medium
 *
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 *
 * The replacement must be in-place and use only constant extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 *
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * 给定序列，生成一个字母顺序比它更大的下一个重排序列；如果给定序列已经按照字母顺序排列，则进行逆序排列。
 * 要求不使用额外空间。必须在原数组上面操作。
 *
 * 思路：
 * 感觉这是一道找规律题。
 * 1. 从后向前遍历，找到第一个不满足降序得到元素，若全为降序，则将序列翻转
 * 2. 将元素同后面比它大的第一个元素交换
 * 3. 将该元素后的所有元素排列，使之成为最小的排列
 * 121543321
 *    12    1    5433     2     1
 *    12    2    5433     1     1
 *    12    2    113354
 */
public class Problem31_NextPermutation {

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int s = -1; // find the first smaller int
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                s = i - 1;
                break;
            }
        }
        if(s == -1) {
            reverse(nums, 0, nums.length - 1);
            print(nums);
            return;
        }
        int b = nums.length - 1; // Find first int bigger than s
        while (nums[b] <= nums[s]) b--;
        // swap nums[s] nums[b]
        swap(nums, b, s);
        // sort array behind s; Since it's decreasing, just sort by swap.
        reverse(nums, s + 1, nums.length - 1);  // Arrays.sort(nums, s + 1, nums.length);
        print(nums);
    }

    private void reverse(int[] nums, int start, int end) {
        if (start > end) return;
        for (int i = start; i <= (end + start) / 2; i++)
            swap(nums, i, start + end - i);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    /* ———————————————————————————————————————————— */
    public void print(int[] res) {
        for (int i : res) System.out.print(i + " ");
        System.out.println();
    }

    @Test
    public void test() {
        nextPermutation(new int[] {1,1});
        nextPermutation(new int[] {1,2,3});
        nextPermutation(new int[] {5,4,3,2,1});
        nextPermutation(new int[] {1,2,1,5,4,3,3,2,1});
//        reverse(new int[] {5,4,3,2,1}, 0, 4);
    }
}
