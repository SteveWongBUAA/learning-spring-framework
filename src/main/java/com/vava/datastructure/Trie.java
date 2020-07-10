package com.vava.datastructure;

/**
 * @author Steve
 * Created on 2020-07
 */
public class Trie {


    static class TreeNode {
        public TreeNode[] children = new TreeNode[26];
        public boolean end = false;
    }

    private TreeNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TreeNode();
//        root.c = '/';
        root.children = new TreeNode[26];
        root.end = false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TreeNode iter = root;
        for (char c: word.toCharArray()) {
            if (iter.children[c-'a'] == null) {
                iter.children[c-'a'] = new TreeNode();
            }
            iter = iter.children[c-'a'];
        }
        iter.end = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TreeNode iter = root;
        for (char c: word.toCharArray()) {
            iter = iter.children[c-'a'];
            if (iter == null) return false;
        }
        return iter.end;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TreeNode iter = root;
        for (char c: prefix.toCharArray()) {
            iter = iter.children[c-'a'];
            if (iter == null) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        boolean res;

        trie.insert("apple");
        res = trie.search("apple");   // 返回 true
        System.out.println(res);
        res = trie.search("app");     // 返回 false
        System.out.println(res);
        res = trie.startsWith("app"); // 返回 true
        System.out.println(res);
        trie.insert("app");
        res = trie.search("app");     // 返回 true
        System.out.println(res);

    }
}
