package eg.edu.alexu.csd.datastructure.hangman.cs22;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class GameSet implements IHangman {
    private String[] dictionary;
    private int maxWrongGuesses=8;
    private String gameWord;
    private char[] unknown;
    ArrayList<Character> usedLetters=new ArrayList<>();


    public void readFile(String filePath) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(filePath));
        ArrayList<String> temp = new ArrayList<>();//To determine the length of file.
        String aWord;
        while ((aWord = file.readLine()) != null)
            temp.add(aWord);
        file.close();
        //Now we need to put the words in an array.
        String[] dict = new String[temp.size()];
        for (int i = 0; i < temp.size(); i++)
            dict[i] = temp.get(i);
        setDictionary(dict);
    }

    @Override
    public void setDictionary(String[] words) {
        dictionary = new String[words.length];
        dictionary = words;
    }

    public char checkLetter(char letter) {
        if (letter <= 'z' && letter >= 'a')
            return (char) (letter - 32);
        else if (letter >= 'A' && letter <= 'Z')
            return letter;
        else if (letter == '-')
            return letter;
        return '\0';
    }

    @Override
    public String selectRandomSecretWord() {
        Random numb = new Random();
        String theWord = dictionary[numb.nextInt(dictionary.length)];//Random word is initialized.
        for (int i = 0; i < theWord.length(); i++) {
            if (checkLetter(theWord.charAt(i)) == '\0')
                return null;
        }
        return theWord;
    }

    public void setGameWord() {
        this.gameWord = selectRandomSecretWord().toUpperCase();//The Word is in upperCase.
        setUnknown();
        for(int i=0;i<unknown.length;i++)
            System.out.print(unknown[i]);
        System.out.println();
    }

    public void setUnknown() {
        if (gameWord != null) {
            unknown = new char[gameWord.length()];
            for (int i = 0; i < gameWord.length(); i++)
                if (gameWord.charAt(i) == '-')
                    unknown[i] = '-';
                else
                    unknown[i] = '*';
        }
    }

    @Override
    public String guess(Character c) throws Exception {
        if (gameWord != null) {
            c = checkLetter(c);
            if(alreadyThere(c))
                System.out.println(c+" Already done.");
            else {
                if (c != '\0') {//C is valid
                    usedLetters.add(c);
                    boolean found = false;
                    String temp = "";
                    for (int i = 0; i < gameWord.length(); i++) {
                        if (c == gameWord.charAt(i)) {//Found
                            unknown[i] = c;
                            found = true;
                        }
                        temp += unknown[i];
                    }
                    if (!found)
                        setMaxWrongGuesses(--maxWrongGuesses);
                    if (maxWrongGuesses > 0)
                        return temp;
                }
            }
        }
        return null;
    }

    @Override
    public void setMaxWrongGuesses(Integer max) {
        maxWrongGuesses=max;
    }

    public String getGameWord() {
        return gameWord;
    }

    public int getMaxWrongGuesses() {
        return maxWrongGuesses;
    }
   private boolean alreadyThere(char letter){
        for (int i=0;i<usedLetters.size();i++){
            if(letter==usedLetters.get(i))
            return true;
        }
        return false;
    }

    public void printUsed(){
        if(usedLetters.size()>0)
            System.out.println(usedLetters.toString());

    }
}
