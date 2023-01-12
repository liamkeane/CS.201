/**
 * Name: Liam Keane, Geoffrey Jing
 * Email: keanel@carleton.edu, gjing@carleton.edu
 * Description: The class TextGenerator performs level k analysis on a given text 
 * by determining the probability with which each character follows 
 * every possible sequence of characters of length k. Given this information we
 * can generate text similar to the original based on probability.
 */

import edu.princeton.cs.algs4.StopwatchCPU;

public class TextGenerator {
    /**
     * Construct a new text generator by performing level k analysis on the text in filename
     * @param k The level of analysis
     * @param filename The name of the input file
     */
    private FrequencyTable tab;
    // num is the level of analysis
    private int num;

    // Constructor, creates a new FrequencyTable object and sets num to k
    public TextGenerator(int k, String filename) {
        tab = new FrequencyTable(k, filename);
        num = k;
    }

    // get method for TextGenerator
    public FrequencyTable get() {
        return tab;
    }

    /**
     * Generate and return a random string based on the input text
     * @param length The number of characters worth of random text to generate
     * @return A string of length characters produced by level k frequency analysis
     */
    public String generate(int length) {
        String output = tab.pickStringK();
        output += tab.getNextLetter(output);
        while (output.length() < length) {
            String temp = output.substring(output.length() - num, output.length());
            output += tab.getNextLetter(temp);
        }
        return output;
    }

    public static void main(String[] args) {
        StopwatchCPU stopwatch = new StopwatchCPU();
        // TextGenerator gen = new TextGenerator(1, "small.txt");
        // TextGenerator gen = new TextGenerator(2, "Metamorphosis.txt");
        TextGenerator gen = new TextGenerator(1, "Tom Sawyer.txt");
        gen.generate(500);
        gen.get();     
        System.out.println(stopwatch.elapsedTime());
    }
}
