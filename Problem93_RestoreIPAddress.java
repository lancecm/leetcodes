package LeetCode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Srunkyo
 * @Date: 2018/7/19
 * @Description:
 * 93
 * Medium
 *
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *
 * Example:
 *
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 *
 * 思路：
 * DFS+剪枝 尤其注意special case: 最后一轮、大于255的值、以0开头的值等
 *
 * TODO: 时间空间复杂度分析
 */
public class Problem93_RestoreIPAddress {

    // 宝宝的写法
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        helper(s, 4, 0, result, "");
        return result;
    }

    private void helper(String sr, int remain, int done, List<String> result, String s) {
        if(sr.length() > remain * 3 || sr.length() < remain) {
            return;
        }
        if(sr.equals("") && remain == 0 && done == 4) {
            result.add(s.substring(1));
            return;
        }
        if (remain == 1 && !(sr.length() > 1 && sr.substring(0, 1).equals("0"))) {
            int tmp = Integer.parseInt(sr);
            if (tmp < 256) helper("", 0, 4, result, s + "." + sr);
            return;
        }
        else {
            if (sr.length() >= 1) helper(sr.substring(1), remain - 1, done + 1, result, s +"." + sr.substring(0,1));
            if (!sr.substring(0, 1).equals("0")) {
                if (sr.length() >= 2) helper(sr.substring(2), remain - 1, done + 1, result, s +"." + sr.substring(0,2));
                if (sr.length() >= 3) {
                    int tmp = Integer.parseInt(sr.substring(0, 3));
                    if (tmp < 256) {
                        helper(sr.substring(3), remain - 1, done + 1, result, s +"." + sr.substring(0,3));
                    }
                }
            }
        }
    }

    // 别人的写法
    // 先划分，再统一验证
    public List<String> restoreIpAddresses2(String s) {
        List<String> res = new ArrayList<>();
        int len = s.length();
        for (int i = 0; i < 4 && i < len - 2; i++)
            for (int j = i + 1; j < i + 4 && j < len - 1; j++)
                for (int k = j + 1; k < j + 4 && k < len; k++) {
                    String s1 = s.substring(0, i);
                    String s2 = s.substring(i, j);
                    String s3 = s.substring(j, k);
                    String s4 = s.substring(k, len);
                    if (isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)) {
                        res.add(s1 + "." + s2 + "." + s3 + "." + s4);
                    }
                }
        return res;
    }

    private boolean isValid(String s) {
        if (s.length() > 3 || s.length() == 0 || (s.charAt(0)=='0' && s.length() > 1 ) || Integer.parseInt(s) > 255) {
            return false;
        }
        return true;
    }

    @Test
    public void test() {
        List<String> result = restoreIpAddresses("010010");
        for (String s: result) System.out.println(s);
    }

    @Test
    public void test2() {
        List<String> result = restoreIpAddresses2("010010");
        for (String s: result) System.out.println(s);
    }
}
