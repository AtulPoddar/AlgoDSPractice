package Neetcode.practice2;

import java.util.*;

public class PrefixTree {

    public class TrieNode {
        Map<Character,TrieNode> children;
        boolean word;

        public TrieNode() {
            word = false;
            children = new HashMap<>();
        }
    }

    TrieNode root;
    public PrefixTree() {
         root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            var ch = word.charAt(i);
            if (!curr.children.containsKey(ch)) {
                curr.children.put(ch, new TrieNode());
            }
            curr = curr.children.get(ch);
        }

        curr.word = true;
    }

    public boolean search(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            var ch = word.charAt(i);
            if (!curr.children.containsKey(ch)) {
                return false;
            }
            curr = curr.children.get(ch);
        }

        return curr.word;
    }

    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            var ch = prefix.charAt(i);
            if (!curr.children.containsKey(ch)) {
                return false;
            }
            curr = curr.children.get(ch);
        }

        return true;
    }
}
