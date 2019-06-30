package LeetCode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Srunkyo
 * @Date: 2018/7/1
 * @Description:
 * Reverse bits of a given 32 bits unsigned integer.
 *
 * Example:
 *
 * Input: 43261596
 * Output: 964176192
 * Explanation: 43261596 represented in binary as 00000010100101000001111010011100,
 *              return 964176192 represented in binary as 00111001011110000010100101000000.
 * Follow up:
 * If this function is called many times, how would you optimize it?
 *
 * 思路：位运算
 * 注意>>和>>>的区别：
 *  >> signed shift 向右移位，左边部分用原来数字填充
 *  >>> 为 unsined shift，左边数字无论是什么都用0来填充
 *
 * 优化方案1：
 * 将32bits分隔为8个4bit的组，设置长度=2^4=16的查找表；
 * 优化方案2：
 * 建立缓存Map, 查找时先看map中有木有
 * 优化方案3：
 * 酷炫的Bit Operation！使用特殊构造的16进制数作为掩码，用分治法不断交换左边与右边
 */
public class Problem190_ReverseBits {
    public int reverseBits(int n) {
        int result = 0;
        for(int i = 0; i < 32; i++) {
            result = result << 1 | n & 1;
            n = n >>> 1;
        }
        return result;
    }

    private static int[] table = new int[] {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15};

    // 优化版本1: 然而并没有提升很多速度
    public int reverseBits2(int n) {
        int result = 0;
        int mask = 15; // 0xF
        for (int i = 0; i < 8; i++) {
            result = result << 4 | table[n & mask];
            n = n >>> 4;
        }
        return result;
    }

    private final Map<Integer, Integer> cache = new HashMap<>();

    // 优化版本2: 在优化版本1的基础上加入cache
    // 由于存在map操作会降低效率
    public int reverseBits3(int n) {
        Integer result = cache.get(n);
        if (result == null) {
            result = 0;
            int tmpN = n;
            int mask = 15; // 0xF
            for (int i = 0; i < 8; i++) {
                result = result << 4 | table[n & mask];
                n = n >>> 4;
            }
            cache.put(tmpN, result);
        }
        return result;
    }

    // 优化版本3：


    /**
     * 2019/06/24
     */
    public int reverseBits4(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = res << 1 | (n & 1); // left shift 1 position then append the last bit of current n
            n >>>= 1; // n unsigned right shift by 1 position
        }
        return res;
    }

    @Test
    public void test() {
//        System.out.println(9&1);
//        System.out.println(reverseBits4(43261596)); //964176192
//        System.out.println(reverseBits4(43261596)); //964176192
//        System.out.println(reverseBits4(43261596)); //964176192
//        int n = 10100101000001111010011100;
//        System.out.println(reverseBits4(10100101000001111010011100));
    }
}
