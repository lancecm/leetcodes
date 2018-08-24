package LeetCode;

/**
 * @author Srunkyo
 * @Date: 2018/8/24
 * @Description:
 * Hard
 *
 * Validate if a given string is numeric.
 *
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 *
 * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
 *
 * Update (2015-02-10):
 * The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button to reset your code definition.
 */
public class Problem65_ValidNumber {
    public boolean isNumber(String s) {
        s = s.trim();
        boolean point = false;
        boolean e = false;
        boolean num = false;
        boolean numAfterE = false;
        for (int i = 0; i < s.length(); i++) {
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                num = true;
                numAfterE = true;
            }
            else if (s.charAt(i) == '.') {
                if (e || point) return false;
                point = true; // 点的前面不能存在点或者E
            }
            else if (s.charAt(i) == 'e') {
                if (e || !num) return false; // e的前面必须存在数字且前面不能存在e
                numAfterE = false;
                e = true;
            }
            else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (i != 0 && s.charAt(i - 1) != 'e') { // -1.  正负号可以放在首位充当正负，或者在10e+19里面充当符号，共两种存在情况
                    return false;
                }
            }
            else return false;
        }
        return num && numAfterE; // 0e->false  9->true 最后要检查如果是xex，e的前后数字都要出现。如果是普通数字的话，本身没有遇到e改变flag，所以返回真值
    }
}
