package LeetCode;

import org.junit.Test;

/**
 * @author Srunkyo
 * @Date: 2018/8/3
 * @Description:
 * Hard
 *
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 *
 * Example 1:
 *
 * Input: 123
 * Output: "One Hundred Twenty Three"
 * Example 2:
 *
 * Input: 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * Example 3:
 *
 * Input: 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * Example 4:
 *
 * Input: 1234567891
 * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */
public class Problem273_IntegerToEnglishWords {
    String[] strs = new String[] {"One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten","Eleven","Twelve", "Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
    String[] str2 = new String[] {"Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
    String[] str3 = new String[] {"Hundred","Thousand","Million","Billion"};
    int[] ints = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
    int[] ints2 = new int[] {20,30,40,50,60,70,80,90};
    int[] ints3 = new int[] {100, 1000, 1000000, 1000000000};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        return numberToWordsHelper(num);
    }

    public String numberToWordsHelper(int num) {
        if (num > 0 && num <= 19) return strs[num - 1];
        else if (num < 100) {
            for (int i = ints2.length - 1; i >= 0; i--) {
                if (num / ints2[i] >= 1) {
                    if (num % ints2[i] == 0) return str2[i];
                    else return str2[i] + " " + numberToWordsHelper(num % ints2[i]);
                }
            }
        }
        else if (num >= 100) {
            for (int i = ints3.length - 1; i >= 0; i--) {
                if (num / ints3[i] >= 1) {
                    if (num % ints3[i] == 0) return numberToWordsHelper(num / ints3[i]) + " " + str3[i];
                    else return numberToWordsHelper(num / ints3[i]) + " " + str3[i] + " " + numberToWordsHelper(num % ints3[i]);
                }
            }
        }
        return "";
    }

    @Test
    public void test() {
        System.out.println(numberToWords(50868));
        System.out.println(numberToWords(20));
        System.out.println(numberToWords(0));
        System.out.println(numberToWords(100));
        System.out.println(numberToWords(123));
    }
}
