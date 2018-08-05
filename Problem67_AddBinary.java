package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/8/5
 * @Description:
 */
public class Problem67_AddBinary {
    public String addBinary(String a, String b) {
        int pa = a.length() - 1;
        int pb = b.length() - 1;
        String result = "";
        int carry = 0;
        while (pa >= 0 || pb >= 0 || carry > 0) {
            int sum = (pa < 0 ? 0 : (a.charAt(pa) - '0')) + (pb < 0 ? 0 : (b.charAt(pb) - '0')) + carry;
            if (sum == 0) result = "0" + result;
            else if (sum == 1) {
                result = "1" + result;
                carry = 0;
            }
            else if (sum == 2) {
                result = "0" + result;
                carry = 1;
            }
            else if (sum == 3) {
                result = "1" + result;
                carry = 1;
            }
            pa--;
            pb--;
        }
        return result;
    }

    @Test
    public void test() {
        System.out.println(addBinary("0","0"));
    }
}
