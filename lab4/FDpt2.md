# Wk3 Function Design Pt.2

``` java
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
```

Looking at the code above, there are a few alarm bells going off in my brain. I loosly remember what I wrote this function to do ( other than it worked ), and it
might be difficult for me to re-remember, although my comments do add to the comprehendability. This was also a recursive helper method, so there is a piece of the picture missing. This function takes in five parameters, and we were advised to include at a maximum of three. Furthermore, the structure of the function is confusing and could be handled more elegantly. The function is titled getALLValidWordsHelper, but it is doing a few different things when it is called: marking tiles and it adds words to an array.
