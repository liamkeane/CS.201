/**
 * Name: Liam Keane, Geoffrey Jing
 * Email: keanel@carleton.edu, gjing@carleton.edu
 * Description: LetterInventory class that creates a HashMap and contains methods 
 * to be called in other classes. Used to store frequencies of characters in key
 * value pairs. 
 */

import java.util.HashMap;
import edu.princeton.cs.algs4.StdRandom;

public class LetterInventory {
    private HashMap<Character, Integer> frequencies;

    // constructor
    public LetterInventory() {
        frequencies = new HashMap<>();
    }  

    // calculating the total of the frequencies in the HashMap
    public int getTotal() {
        int total = 0;
        for (char key : frequencies.keySet()) {
            total += (frequencies.get(key));
        }
        return total;
    }

    // put method for LetterInventory
    public void put(char c, int n) {
        frequencies.put(c, n);
    }

    // get method for LetterInventory
    public int get(char c) {
        return frequencies.get(c);
    }

    // checks if HashMap contains a key
    public boolean containsKey(char c) {
        return frequencies.containsKey(c);
    }

    // selects the next character based on probability of their frequency
    public char selectNextChar() {
        double threshold = StdRandom.uniform();
        for (char key : frequencies.keySet()) {
            double dFreq = frequencies.get(key);
            double dTotal = getTotal();
            threshold -= dFreq / dTotal;
            if (threshold < 0) {
                return key;
            }
        }
        return 'a';
    }

    // toString method for HashMap
    public String toString() {
        return frequencies.toString();
    }

}
