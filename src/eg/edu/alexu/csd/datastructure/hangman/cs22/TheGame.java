package eg.edu.alexu.csd.datastructure.hangman.cs22;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TheGame {
    public static final GameSet game = new GameSet();

    public static void main(String[] args) throws Exception {
        game.readFile("F:\\CS\\my projects\\Java\\Assignment 2\\words.txt");
        game.setGameWord();//The word is generated.
        System.out.println(game.getGameWord());
        if (game.getGameWord() != null) {//The word isn't a buggy one.
            while (game.getMaxWrongGuesses() > 0) {//We are in the game.
                String trial;
                System.out.print("Remaining trials: " + game.getMaxWrongGuesses()+"\t\t\t");
                game.printUsed();
                System.out.println();
                char letter = UI.getChar();
                trial = game.guess(letter);
                if (game.getMaxWrongGuesses() == 0) {
                    System.out.println("You lost\nThe word is " + game.getGameWord());
                    return;
                }
                if (trial != null) {
                    System.out.println(trial);
                    if (trial.equals(game.getGameWord())) {
                        System.out.println("You won!!");
                        return;
                    }
                }


            }


        }
    }
}
