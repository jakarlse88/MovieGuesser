import java.util.Arrays;

public class Game {

    // Fields
    private Movie[] movies;
    private int guessCount;
    private char[] incorrectGuesses;
    private char[] computerGuess;
    private int tilesLeft;

    // Constructors
    Game() {
        movies = new Movie[50];
        guessCount = 10;
        incorrectGuesses = new char[26];
        tilesLeft = 0;
    }

    // Methods
    public int getTiles() {
        return tilesLeft;
    }

    public void decrementTiles(int i) {
        tilesLeft -= i;
    }

    public int occurrences(int i, char c) {
        int x = 0;

        for (int j = 0; j < movies[i].movieName.length(); j++) {
            if (movies[i].movieName.charAt(j) == c) {
                x++;
            }
        }
        return x;
    }

    public void incorrectGuess() {
        guessCount--;
    }

    public int remainingGuesses() {
        return guessCount;
    }

    public void addMovie(Movie movie, int noOfMovies) {
        movies[noOfMovies] = movie;
    }

    public void setCorrect(int i) {
        movies[i].correctGuess = true;
    }

    public char[] getMovie() {
        return computerGuess;
    }

    /* Adds character to list of incorrect guesses.*/
    public void addToList(char c) {
        c = Character.toLowerCase(c);
        incorrectGuesses[(int) c % 26] = c;
    }

    /* Returns true if c is previously guessed.*/
    public boolean isListed(char c) {
        c = Character.toLowerCase(c);

        if (incorrectGuesses[(int) c % 26] == c) {
            return true;
        }

        for (int i = 0; i < computerGuess.length; i++) {
            if (computerGuess[i] == c) {
                return true;
            }
        }
        return false;
    }

    /* Obfuscates computer's chosen title.
    Prints underscores in lieu of letters.
     */
    public void obfuscate(int i) {
        computerGuess = new char[movies[i].movieName.length()];

        for (int j = 0; j < movies[i].movieName.length(); j++) {
            if (Character.isLetter(movies[i].movieName.charAt(j))) {
                computerGuess[j] = '_';
                tilesLeft ++;
            } else {
                computerGuess[j] = movies[i].movieName.charAt(j);
            }
        }
    }

    /*De-obfuscates a letter.*/
    public void deObfuscate(char c, int i) {
        for (int j = 0; j < movies[i].movieName.length(); j++) {
            if (Character.toLowerCase(movies[i].movieName.charAt(j)) == c) {
                computerGuess[j] = c;
            }
        }
    }

    /* Searches an array of characters
    for c. If c is found, returns true.
     */
    public boolean search(char c, int i) {
        for (int j = 0; j < movies[i].movieName.length(); j++) {
            if (Character.toLowerCase(movies[i].movieName.charAt(j)) == c) {
                return true;
            }
        }
        return false;
    }
}

