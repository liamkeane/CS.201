/**
 * Name: Liam Keane
 * Email: keanel@carleton.edu
 * Description: This lab contains two methods that return a set of words that
 * can be made on a Boggle board.
 */

import java.util.HashSet;
import java.util.Set;

public class BoggleSolver {

    private Set<String> dictionary;  // set of words in the dictionary
    private Set<String> prefixes;    // set of prefixes to dictionary words

    // constructs a new BoggleSolver with a dictionary of strings d
	public BoggleSolver(String[] d) {
        dictionary = new HashSet<>();
        for (String word : d) {
            dictionary.add(word);
        }
        prefixes = new HashSet<>();
        prefixes.add("");
        for (String word : dictionary) {
            for (int i = 1; i < word.length(); i++) {
                prefixes.add(word.substring(0, i));
            }
        }
	}

    // this method recursively searches for words beginning at the location row, col
    // To Statement: 
    private void getAllValidWordsHelper(BoggleBoard board, Set<String> words, 
                                        String wordSoFar, int row, int col) {
        board.visit(row, col);
        wordSoFar = wordSoFar + board.getLetter(row, col);

        // checks if wordSoFar is already a word
        if (dictionary.contains(wordSoFar) && wordSoFar.length() >= 3) {
            words.add(wordSoFar);
        }
        // base case checks if wordSoFar is a prefix
        // if not, it will unvisit the last letter
        if (!prefixes.contains(wordSoFar)) {
            board.unvisit(row, col);
            return;
        } else {
                // two nested loops to access adjacent tiles
                for (int i = row-1; i <= row+1; i++) {
                    for (int j = col-1; j <= col+1; j++) {
                        // if statement checks if the tile is on the board
                        // and if it has already been visited
                        if (board.isValidLocation(i, j) && !board.isVisited(i, j)) {
                            // recursively calls the Helper with the next row and col
                            getAllValidWordsHelper(board, words, wordSoFar, i, j);
                    }
                }
            }
        }
        // if all the recursive calls return then we want to unvisit
        board.unvisit(row, col);
    }

    // returns a set of words that can be made on board
    
    // TO Statement(s):
    // Takes a 'board' in as input, creates a new hashset, creates a new empty string, calls Helper method on each
    // tile of the board
    // Levels of abstraction: ~2
    // 
	public Set<String> getAllValidWords(BoggleBoard board) {
        Set<String> words = new HashSet<String>();

        // the inProgress String is created to keep track of the letters we are searching
        String inProgress = new String("");
        
        // these two nested loops will call the Helper method
        // on each of the board's letters.
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                getAllValidWordsHelper(board, words, inProgress, i, j);
            }
        }
        
        return words;
    }    
}
