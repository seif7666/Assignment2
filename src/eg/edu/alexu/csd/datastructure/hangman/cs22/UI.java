package eg.edu.alexu.csd.datastructure.hangman.cs22;

import java.util.Scanner;

public class UI {

    public static char getChar(){
        System.out.print("Enter a letter:\r");
        Scanner in=new Scanner(System.in);
        char letter=in.next().charAt(0);
        in.nextLine();
        return letter;
    }
}
