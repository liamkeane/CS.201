/**
 * Name: Liam Keane, Geoffrey Jing
 * Email: keanel@carleton.edu, gjing@carleton.edu
 * Description: FrequencyTable class that stores a hashmap of a key and a hashmap pair 
 * that reads the file
 */

import java.util.HashMap;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

public class FrequencyTable {
    private HashMap<String, LetterInventory> table;

    // constructor
    public FrequencyTable(int k, String filename) {
        table = new HashMap<>();
        
        In inputFile = new In(filename);
        String current = "";
        while (inputFile.hasNextChar()) {
            char c = inputFile.readChar();
            // checks if length of current is equal to k
            if (current.length() == k) {    
                // checks if table contains a key value pair and if it does replaces it with one increased frequency count,
                // otherwise creates a new key value pair with frequency of 1
                if (table.containsKey(current)) {
                    if (table.get(current).containsKey(c)) {
                        table.get(current).put(c, table.get(current).get(c) + 1);
                    } else {
                        table.get(current).put(c, 1);
                    }
                } else {
                    LetterInventory let = new LetterInventory();
                    let.put(c, 1);
                    table.put(current, let);
                }
                current = current.substring(1, current.length()); 
            }
            current += c;
        }
    }

    // get method for FrequencyTable
    public LetterInventory get(String s) {
        return table.get(s);
    }

    // creates an array of keys and randomly selects one
    public String pickStringK() {
        Object[] keys = table.keySet().toArray();
        String randomKey = (String) keys[StdRandom.uniform(keys.length)];
        return randomKey;
    }

    // calls LetterInventory's selectNextChar method to get the next letter
    public char getNextLetter(String s) {
        return table.get(s).selectNextChar();
    }

    // toString method for HashMap
    public String toString() {
        return table.toString();
    }
}
