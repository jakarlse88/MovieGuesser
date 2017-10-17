import java.io.File;
import java.util.Scanner;

public class MovieGuesser {

    public static void main(String [] args) throws Exception {
        // Read file
        File file = new File("movies.txt");
        Scanner fileScanner = new Scanner(file);

        // Tracks number of movies
        int noOfMovies = 0;

        // Initialises game object
        Game myGame = new Game();

        // Adds movies to list
        while (fileScanner.hasNextLine()) {
            Movie newMovie = new Movie();
            newMovie.movieName = fileScanner.nextLine().toLowerCase();
            newMovie.correctGuess = false;

            myGame.addMovie(newMovie, noOfMovies);
            noOfMovies++;
        }

        // Computer guesses
        int computerGuess = (int) (Math.random() * 100) % noOfMovies;
        myGame.setCorrect(computerGuess);

        // Prints computer guess as underscores
        System.out.println("Computer's movie is: ");
        myGame.obfuscate(computerGuess);
        System.out.println(myGame.getMovie());

        // User input scanner
        Scanner userInput = new Scanner(System.in);

        // User searches
        while (myGame.remainingGuesses() > 0 &&
                myGame.getTiles() > 0) {

            System.out.print("Guess a letter: ");
            char userGuess = Character.toLowerCase(userInput.next().trim().charAt(0));

            if (!myGame.isListed(userGuess)) {
                if (myGame.search(userGuess, computerGuess)) {
                    myGame.deObfuscate(userGuess, computerGuess);
                    System.out.println("Correct guess.");
                    System.out.println(myGame.getMovie());
                    System.out.println(myGame.remainingGuesses() + " guesses remaining.\n");
                    myGame.decrementTiles(myGame.occurrences(computerGuess, userGuess));
                } else {
                    System.out.println("Incorrect guess.");
                    System.out.println(myGame.getMovie());
                    myGame.addToList(userGuess);
                    myGame.incorrectGuess();
                    System.out.println(myGame.remainingGuesses() + " guesses remaining.\n");
                }
            } else {
                System.out.println("That character has already been guessed. Please choose another.");
            }
        }

        if (myGame.remainingGuesses() > 0 &&
            myGame.getTiles() == 0) {
            System.out.println("Congratulations! You win!");
        } else {
            System.out.println("Too bad! You lose!");
        }
    }
}
