package student;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * A binary search tree implementation, except remove
 * CS 201
 * Aaron Bauer
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node overallRoot;

    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;

        public Node(Key k, Value v) {
            key = k;
            value = v;
        }
    }

    public Value get(Key key) {
        return get(overallRoot, key);
    }

    private Value get(Node root, Key key) {
        if (root == null) {
            return null;
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            return get(root.left, key);
        } else if (cmp > 0) {
            return get(root.right, key);
        } else {
            return root.value;
        }
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value val) {
        if (overallRoot == null) {
            overallRoot = new Node(key, val);
        } else {
            put(overallRoot, key, val);
        }
    }

    private void put(Node root, Key key, Value val) {
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            if (root.left == null) {
                root.left = new Node(key, val);
            } else {
                put(root.left, key, val);
            }
        } else if (cmp > 0) {
            if (root.right == null) {
                root.right = new Node(key, val);
            } else {
                put(root.right, key, val);
            }
        } else {
            root.value = val;
        }
    }

    public Key firstKey() {
        if (overallRoot == null) {
            throw new NoSuchElementException();
        }
        Node node = overallRoot;
        while (node.left != null) {
            node = node.left;
        }
        return node.key;
    }

    public Key lastKey() {
        if (overallRoot == null) {
            throw new NoSuchElementException();
        }
        Node node = overallRoot;
        while (node.right != null) {
            node = node.right;
        }
        return node.key;
    }

    public Iterable<Key> keys() {
        LinkedList<Key> result = new LinkedList<Key>();
        keys(overallRoot, result);
        return result;
    }

    private void keys(Node node, LinkedList<Key> result) {
        if (node == null) return;
        keys(node.left, result);
        result.add(node.key);
        keys(node.right, result);
    }

    public void printInOrder() {
        printInOrder(overallRoot);
        System.out.println();
    }

    private void printInOrder(Node root) {
        if (root == null)
            return;
        printInOrder(root.left);
        System.out.print(root.value + " ");
        printInOrder(root.right);
    }

    public void printTree() {
        System.out.println(formatTree(overallRoot, 2, 0));
    }

    private String formatTree(Node root, int indentSize, int indent) {
        String tree = "";
        if (root == null)
            return tree;
        tree += formatTree(root.left, indentSize, indent + indentSize);

        for (int i = 0; i < indent; i++) {
            tree += " ";
        }
        tree += root.value + "\n";

        tree += formatTree(root.right, indentSize, indent + indentSize);

        return tree;
    }

    /**
     * Problem 1
     * Compute whether this binary search tree is equal to another BST.
     * Two trees are equal if they have all the same nodes arranged in the same structure.
     * @param otherBST a BST
     * @return true if this BST is equal to otherBST, otherwise return false
     */
    public boolean equals(BST<Key, Value> otherBST) {
        return equals(otherBST.overallRoot, overallRoot);
    }

    private boolean equals(Node otherBST, Node currentBST) {
        // base case: if both nodes are null, return true
        if (otherBST == null && currentBST == null) return true;
        // recursive cases: if left and right nodes are the same, call equals on node's children
        else if (otherBST.key == currentBST.key) {
            equals(otherBST.left, currentBST.left);
            equals(otherBST.right, currentBST.right);
        } else {
            return false;
        }
        return false;
    }

    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        Random rng = new Random();
        for (int i = 0; i < 20; i++) {
            bst.put(rng.nextInt(100), "hello I am a cool value");
        }
        for (int k : bst.keys()) {
            System.out.print(k + " ");
        }
    }
}