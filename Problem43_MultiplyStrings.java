package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/7/18
 * @Description:
 * 43
 * Medium
 *
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 *
 * Example 1:
 *
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 *
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * Note:
 *
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 * 思路1：加法&乘法
 * 思路2：
 * 仔细观察乘法运算，每两个数字相乘会产生两个数字。
 * 举例 123 * 456而言：
 * 6 * 2 与 5 * 3 在运算中的标记的位置是对齐的
 * 6 * 2为第二个数中末位乘以第一个数中倒二位；5 * 3为第一个数倒二位乘以第二个数中末位，在两个数中位置和相等
 * 在运算过程中设置一个【m+n】的数组，计算时只要把对应位置的和相加即可，即对应数组中的位置为[i+j](管进位)[i+j+1]（管求余结果）
 *          1  2  3
 *          4  5  6
 *          -------
 *             1  8     对应结果数组的位置【i + j】 【i + j + 1】
 *          1  2
 *       0  6
 *          1  5
 *       1  0
 *    0  5
 *       1  2
 *    0  8
 * 0  4
 * ----------------
 * 0  5  6  0  8  8   【长度为 m + n， 其中数值会随着运算的进行及时更新】
 * 0  1  2  3  4  5   【INDEX】
 *
 * 因此最终只需要把结果数组一个个转化成StringBuilder
 */
public class Problem43_MultiplyStrings {
    /*
        别人的写法！
     */
    public String multiply2(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        int[] pos = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                int sum = mul + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = sum % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int p : pos) if (!(sb.length() == 0 && p == 0)) sb.append(p); // 如果开头没有数字，则不能加0
        return sb.length() == 0 ? "0" : sb.toString();
    }

    /*
        自己写的极其笨重的写法。
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        if(num1.length() < num2.length()) return multiply(num2, num1); // num1 must be longer than num2
        char[] nc1 = num1.toCharArray();
        char[] nc2 = num2.toCharArray();
        String result = "0";
        int zeroNum = 0;
        for(int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0;
            StringBuilder tmp = new StringBuilder();
            for (int z = zeroNum; z > 0; z--) tmp.append(0);
            int number = nc2[i] - '0';
            for(int j = num1.length() - 1; j >= 0; j--) {
                int sum = carry + (number * (nc1[j] - '0'));
                tmp.append(sum % 10);
                carry = sum / 10;
            }
            while(carry != 0) {
                tmp.append(carry % 10);
                carry /= 10;
            }
            result = add(result, tmp.reverse().toString());
            zeroNum ++;
        }
        return result;
    }

    public String add(String num1, String num2) {
        if(num1.length() < num2.length()) return add(num2, num1); // num1 must be longer than num2
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        char[] nc1 = num1.toCharArray();
        char[] nc2 = num2.toCharArray();
        int len1 = num1.length();
        int len2 = num2.length();
        for(int i = 0; i <= len2 - 1; i++) {
            int sum = carry + nc1[len1 - 1 - i] - '0' + nc2[len2 - 1 - i] - '0';
            sb.append(sum % 10);
            carry = sum / 10;
        }
        for(int i = len1 - len2 - 1; i >= 0; i--) {
            int sum = carry + nc1[i] - '0';
            sb.append(sum % 10);
            carry = sum / 10;
        }
        while (carry != 0) {
            sb.append(carry % 10);
            carry /= 10;
        }
        return sb.reverse().toString();
    }

    @Test
    public void testAdd() {
        System.out.println(add("0","0"));
        System.out.println(add("99","99"));
    }

    @Test
    public void testMultiply() {
        System.out.println(multiply("123","456"));
        System.out.println(multiply("9133","0"));

    }
}
