package LeetCode;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/6
 * @Description:
 * 35
 * Easy
 *
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 *
 * Example 1:
 *
 * Input: [1,3,5,6], 5
 * Output: 2
 * Example 2:
 *
 * Input: [1,3,5,6], 2
 * Output: 1
 * Example 3:
 *
 * Input: [1,3,5,6], 7
 * Output: 4
 * Example 4:
 *
 * Input: [1,3,5,6], 0
 * Output: 0
 */
public class Problem35_SearchInsertPosition {

    // 1. 遍历法
    public int searchInsert1(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            if(target<=nums[i])return i;
        }
        return nums.length;
    }

    // 2. 二分查找
    // [1,3,5,6] 2
    public int searchInsert2(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) hi = mid - 1;
            else lo = mid + 1;
        }
        return lo;
    }
}
