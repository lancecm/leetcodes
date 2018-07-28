package LeetCode;

/**
 * @author Srunkyo
 * @Date: 2018/7/28
 * @Description:
 */
public class Problem657_JudgeRouteCircle {
    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;
        for (char c : moves.toCharArray()) {
            switch(c) {
                case 'U':
                    x--;
                    break;
                case 'D':
                    x++;
                    break;
                case 'L':
                    y--;
                    break;
                case 'R':
                    y++;
                    break;
                default:
                    break;
            }
        }
        if (x == 0 && y == 0) return true;
        return false;
    }
}
