package student;

import java.util.ArrayList;
import java.util.HashMap;

// To submit, upload just this file (Quiz5.java) to the Quiz 5 assignment on Gradescope

public class Quiz5 {

    /**
     * Problem 1
     * 
     * Write a method named maxOccurrences that accepts a list of integers as a
     * parameter and returns the number of times the most frequently occurring
     * integer (the "mode") occurs in the list. Solve this problem using a map.
     * If the list is empty, return 0. Do not modify the list passed in as a
     * parameter.
     */
    public int maxOccurrences(ArrayList<Integer> list) {
        if (list == null) {
            return 0;
        }
        // HashMap<Integer, Integer>
        // if key is contained within map: increment it's value by one
        // else: add key to map and set value to zero
        HashMap<Integer, Integer> modeMap = new HashMap<>();

        // assigns int count of each int in num
        for (int num : list) {
            if (modeMap.get(num) == null) {
                modeMap.put(num, 1);
            } else {
                int oldVal = modeMap.get(num);
                modeMap.replace(num, oldVal++);
            }

        }

        int maxOcc = 0;

        for (int num : list) {
            if (modeMap.get(num) > maxOcc) maxOcc = modeMap.get(num);
        }

        return maxOcc;
    }

    /**
     * Problem 2
     * 
     * Write a method named countLeaves that returns the total number of leaf
     * nodes in a binary tree. Your method accepts as its parameter a TreeNode
     * that refers to the root of the tree. For example, the following tree has
     * four leaves (nodes 1, 4, 8, and 9):
     *       __5
     *      /   \
     *     3     6__
     *    / \       \
     *   2   4       7
     *  /           / \
     * 1           8   9
     * 
     * The definition for the TreeNode class is given in TreeNode.java.
     */
    public int countLeaves(TreeNode root) {
        int leafCount = 0;
        return countLeaves(root, leafCount);
    }

    private int countLeaves(TreeNode node, int leafCount) {
        if (node.left == null && node.right == null) {
            return leafCount++;
        }
        if (node.left != null) {
            return countLeaves(node.left, leafCount);
        }
        if (node.right != null) {
            return countLeaves(node.right, leafCount);
        }
        return leafCount;
    }

    /**
     * Problem 3
     * 
     * Write a method named depthSum that returns the sum of the values stored
     * in a binary tree of integers weighted by the depth of each value. Your
     * method accepts as its parameter a reference to a TreeNode representing
     * the root of the tree. You should return the value at the root, plus 2
     * times the values stored at the next level of the tree, plus 3 times the
     * values stored at the next level of the tree, plus 4 times the values
     * stored at the next level of the tree, and so on. For example, in the
     * tree below:
     * 
     *     ____9
     *    /     \
     *   7__     6
     *  /   \     \
     * 3     2     4
     *      /       \
     *     5         2
     * 
     * The sum would be computed as:
     * 
     * 1 * 9 + 2 * (7 + 6) + 3 * (3 + 2 + 4) + 4 * (5 + 2) = 90
     * 
     * Note: you are allowed to define helper methods
     * 
     * The definition for the TreeNode class is given in TreeNode.java.
     */
    public int depthSum(TreeNode node) {
        int treeSum = 0;
        int count = 1;
        return depthSum(node, count, treeSum);
    }
    private int depthSum(TreeNode node, int count, int treeSum) {
        if (node.left == null && node.right == null) {
            return treeSum + (node.data * count);
        }
        if (node.left != null) {
            return depthSum(node.left, count++, treeSum + (node.data * count));
        }
        if (node.right != null) {
            return depthSum(node.right, count++, treeSum + (node.data * count));
        }
        return treeSum;
    }
}