package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/8/7
 * @Description:
 * Medium
 *
 * Given a sorted array consisting of only integers where every element appears twice except for one element which appears once. Find this single element that appears only once.
 *
 * Example 1:
 * Input: [1,1,2,3,3,4,4,8,8]
 * Output: 2
 * Example 2:
 * Input: [3,3,7,7,10,11,11]
 * Output: 10
 * Note: Your solution should run in O(log n) time and O(1) space.
 *
 * 二分法，利用排序数对的性质
 * 注意和single number区别！
 */
public class Problem540_SingleElementInASortedArray {
    public int singleNonDuplicate(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            // let mid set at the position of first element of the middle pair;
            if (mid % 2 != 0) mid--;
            if (nums[mid] != nums[mid + 1]) hi = mid; // single number is on the left side
            else lo = mid + 2; // single number is on the right side
        }
        return nums[lo];
    }

    @Test
    public void test() {
        System.out.println(singleNonDuplicate(new int[] {3,3,7,7,10}));
    }
}
