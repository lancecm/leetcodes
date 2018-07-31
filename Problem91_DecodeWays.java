package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/7/31
 * @Description:
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * Example 1:
 *
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 *
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 * 思路：
 * 动态规划
 * 226
 *    1
 *   1
 *  2
 * 3
 */
public class Problem91_DecodeWays {
    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0) return 0;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        if (s.charAt(n-1) != '0') {
            dp[n - 1] = 1;
        }
        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') continue;
            if (Integer.parseInt(s.substring(i, i + 2)) <= 26) {
                dp[i] = dp[i + 1] + dp[i + 2];
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }

    //   2 2 6 0 1
    // 1
    //   1
    //     2
    //       3
    //
    //
    public int numDecodings2(String s) {
        int n = s.length();
        if (n == 0) return 0;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = s.charAt(i - 1) == '0' ? 0 : dp[i - 1];
            if (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) >= '0' && s.charAt(i - 1) <= '6'))
                    dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    @Test
    public void test() {
        System.out.println(numDecodings("22623219"));
        System.out.println(numDecodings2("22623219"));
    }

}
