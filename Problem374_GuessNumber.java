package LeetCode;

/**
 * @author Srunkyo
 * @Date: 2018/8/10
 * @Description:
 */
public class Problem374_GuessNumber extends GuessGame{
    public int guessNumber(int n) {
        int lo = 1;
        int hi = n;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (guess(mid) == 0) return mid;
            if (guess(mid) == -1) { // search for lower
                hi = mid - 1;
            }
            else { // search for higher
                lo = mid + 1;
            }
        }
        return lo;
    }
}
