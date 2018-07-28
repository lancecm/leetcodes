package LeetCode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Srunkyo
 * @Date: 2018/7/28
 * @Description:
 * Note: This is a companion problem to the System Design problem: Design TinyURL.
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
 *
 * Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 *
 * 思路1：
 * 建立两个hashmap, 分别代表url->hash以及hash->url, url与hash分别为key
 * 其实就是生成一个6位的随机数作为key进行哈希
 *
 * 另一种不太好的解法是对于每一个请求的longurl使用从0开始的整数进行编码；缺点在于：1. 对于相同的url每次编码结果不同 2.把短网址的计数器暴露给用户，存在安全隐患
 *
 * System Design
 * https://www.youtube.com/watch?v=fMZMm_0ZhK4:
 *
 * */

public class Problem535_EncodeAndDecodeTinyUrl {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    Map<String, String> urlToHash = new HashMap<>();
    Map<String, String> hashToUrl = new HashMap<>();
    String base = "http://tinyurl.com/";
    Random random = new Random();

    public String encode(String longUrl) {
        if (urlToHash.containsKey(longUrl)) return base + urlToHash.get(longUrl);
        StringBuilder hash = new StringBuilder();
        do {
            for (int i = 0; i < 6; i++) {
                hash.append(characters.charAt(random.nextInt(characters.length())));
            }
        }
        while (urlToHash.containsKey(hash.toString()));
        urlToHash.put(longUrl, hash.toString());
        hashToUrl.put(hash.toString(), longUrl);
        return base + hash.toString();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return hashToUrl.get(shortUrl.substring(base.length()));
    }

    @Test
    public void test() {
        String s1 = encode("https://leetcode.com/problems/design-tinyurl");
        System.out.println(s1);
        String s2 = decode(s1);
        System.out.println(s2);
    }
}
