package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/8/14
 * @Description:
 */
public class Problem459_RepeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String str) {
        int l = str.length();
        for (int i = l/2; i >= 1; i--) {
            if (l % i == 0) {
                int m = l/i;
                String subS = str.substring(0, i);
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < m; j++) {
                    sb.append(subS);
                }
                if (sb.toString().equals(str)) return true;
            }
        }
        return false;
    }

    // 改进ver: 不需要构造出整个字符串，每次一部分一部分地比较。
    public boolean repeatedSubstringPattern2(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        int length = s.length();
        for (int i = length/2; i > 0; i--) {
            if (length % i == 0 && fit(s, i, s.substring(0, i))) {
                return true;
            }
        }
        return false;
    }

    private boolean fit(String s, int index, String substring) {
        if (index == s.length()) return true;
        return s.startsWith(substring, index) && fit(s, index + substring.length(), substring);
    }

    @Test
    public void test() {
        System.out.println(repeatedSubstringPattern2("aba"));
    }
}
