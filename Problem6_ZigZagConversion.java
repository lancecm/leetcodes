package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/7/29
 * @Description:
 * Medium
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 *
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 * 思路：构建stringbuilder数组，按照顺序添加字符，最终把sb数组重新链接成字符串
 */
public class Problem6_ZigZagConversion {
    public String convert(String s, int numRows) {
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < sbs.length; i++) {
            sbs[i] = new StringBuilder();
        }
        char[] sch = s.toCharArray();
        int i = 0, len = sch.length;
        while (i < len) {
            for (int k = 0; k < numRows && i < len; i++, k++) {
                sbs[k].append(sch[i]);
            }
            for (int k = numRows - 2; k >= 1 && i < len; i++, k--) {
                sbs[k].append(sch[i]);
            }
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : sbs) {
            result.append(sb);
        }
        return result.toString();
    }

    @Test
    public void test() {
        System.out.println(convert("PAYPALISHIRING", 3));
    }
}
