package student;

import java.util.HashMap;
import java.util.List;

/**
 * A simple insert-only Trie implementation
 * CS 201
 * Aaron Bauer
 */
public class Trie {
    private TrieNode overallRoot;

    private class TrieNode {
        String content;
        HashMap<Character, TrieNode> children;
        boolean isWord;

        public TrieNode(String v) {
            content = v;
            children = new HashMap<>();
        }
    }

    public Trie() {
        overallRoot = new TrieNode("");
    }

    /**
     * Insert a new word into the Trie, creating any necessary nodes.
     * @param word The word to be inserted.
     */
    public void insert(String word) {
        TrieNode current = overallRoot;
    
        for (char l: word.toCharArray()) {
            if (!current.children.containsKey(l)) {
                current.children.put(l, new TrieNode(Character.toString(l)));
            }
            current = current.children.get(l);
        }
        current.isWord = true;
    }

    /**
     * Problem 2
     * Given a String prefix, return a list of words in the Trie that begin with prefix.
     * @param prefix The prefix to search for.
     * @return A list of words in the Trie that begin with prefix.
     */
    public List<String> possibleWords(String prefix) {
        return null; // <-- REPLACE ME
    }

    private 

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("house");
        trie.insert("home");
        trie.insert("homeland");
        trie.insert("hope");
        trie.insert("hound");
        trie.insert("hose");
    }
}
