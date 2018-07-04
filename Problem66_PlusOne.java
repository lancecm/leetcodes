package LeetCode;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/4
 * @Description:
 * 66
 * Easy
 *
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 *
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Example 2:
 *
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 *
 * 思路：
 * 加法运算规律
 */
public class Problem66_PlusOne {

    // try 1:
    public int[] plusOne(int[] digits) {
        int carry = 0;
        if (digits == null || digits.length == 0) return null;
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = carry + digits[i];
            if (i == digits.length - 1) sum ++;
            digits[i] = sum % 10;
            carry = sum / 10;
        }
        if (carry > 0) {
            int[] result = new int[digits.length + 1];
            result[0] = carry;
            for (int i = 1; i < result.length; i++) {
                result[i] = digits[i-1];
            }
            return result;
        }
        return digits;
    }

    // 优化：
    public int[] plusOne2(int[] digits) {
        int l = digits.length - 1;
        if (digits[l] < 9) {
            digits[l]++;
            return digits;
        }
        while (l >=0 && digits[l] == 9) {
            digits[l] = 0;
            l--;
        }
        if (l >= 0) {
            digits[l] += 1;
            return digits;
        }
        else {
            int[] result = new int[digits.length + 1];
            for (int i = 1; i < digits.length + 1; i++) {
                result[i] = digits[i-1];
            }
            result[0] = 1;
            return result;
        }
    }
}
