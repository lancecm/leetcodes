package LeetCode;

/**
 * @author Srunkyo
 * @Date: 2018/8/13
 * @Description:
 * Medium
 *
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Example:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * Note:
 *
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 *
 * 前缀树的关键结构：
 * 1. dummy node (root)
 * 2. TrieNode 需要包括一个hashmap, 用来映射key和下一个TrieNode；还需要一个当前是否是单词的标记
 * hashMap 可以用map来实现，也可以用简单的字母表数组
 */
public class Problem208_ImplementTire_PrefixTree {
    class TrieNode {
        TrieNode[] children;
        boolean isWord;
        public TrieNode() {
            children = new TrieNode[26];
            isWord = false;
        }
    }

    TrieNode dummy;
    public Problem208_ImplementTire_PrefixTree() {
        dummy = new TrieNode();
    }

    public void insert(String word) {
        TrieNode runner = dummy;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (runner.children[index] == null) {
                runner.children[index] = new TrieNode();
            }
            runner = runner.children[index];
        }
        runner.isWord = true;
    }

    public boolean search(String word) {
        TrieNode runner = dummy;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (runner.children[index] == null) {
                return false;
            }
            runner = runner.children[index];
        }
        return runner.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode runner = dummy;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (runner.children[index] == null) {
                return false;
            }
            runner = runner.children[index];
        }
        return true;
    }
}
