package LeetCode;

/**
 * @Author: Srunkyo
 * @Date: 2018/6/21
 * @Description:
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * Kadane's Algorithms.
 */
public class Problem53_MaximumSubArray {

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int maxLocal = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxLocal = Math.max(maxLocal + nums[i], nums[i]);
            if (maxLocal > max) max = maxLocal;
        }
        return max;
    }

    // 跟上面一样，但更精简的写法
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int maxCur = 0, maxSoFar = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++){
            maxCur = Math.max(nums[i], maxCur += nums[i]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }

    //8.19
    public int maxSubArray3(int[] nums) {
        int cur = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(nums[i], cur + nums[i]);
            max = Math.max(cur, max);
        }
        return max;
    }

    public static void main(String[] args) {
        Problem53_MaximumSubArray p = new Problem53_MaximumSubArray();
        System.out.println(p.maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
    }
}
