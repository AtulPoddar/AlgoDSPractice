package Neetcode.practice;

import java.util.*;

public class WordDictionary {

    public class TrieNode {
        boolean word;
        Map<Character, TrieNode> children;

        public TrieNode() {
            word = false;
            children = new HashMap<>();
        }
    }

    TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode curr = root;
        for (Character ch : word.toCharArray()) {
            if (!curr.children.containsKey(ch)) {
                curr.children.put(ch, new TrieNode());
            }

            curr = curr.children.get(ch);
        }

        curr.word = true;
    }

    public boolean search(String word) {
        return searchHelper(root, word, 0);
    }

    public boolean searchHelper(TrieNode curr, String word, int i) {
        for (int j = i; j < word.length(); j++) {
            Character ch = word.charAt(j);
            if (ch == '.') {
                var sets = curr.children.entrySet();
                for (var set : sets) {
                    if (searchHelper(set.getValue(), word, j+1)) {
                        return true;
                    }
                }

                return false;
            }
            else {
                if (!curr.children.containsKey(ch)) {
                    return false;
                }

                curr = curr.children.get(ch);
            }
        }

        return curr.word;
    }
}

