package io.emma;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    static char inputCharacter;
    static char[] shownWordArray;
    static char[] correctWordArray;
    static String inputWord;
    static String word;
    static int tries = 0;
    static int guessesLeft = 8;

    // Method to get a random word
    static String getRandomWord() {
        String[] words = {
                "fåtölj",
                "säng",
                "nattduksbord",
                "stol",
                "byrå",
                "klocka",
                "klädhängare",
                "soffbord",
                "skåp",
                "skrivbord",
                "dubbelsäng",
                "toalettbord",
                "spritskåp",
                "spegel",
                "piano",
                "soffa",
                "bäddsoffa",
                "pall",
                "bord",
                "garderob",
                "badkar",
                "soptunna",
                "kvast",
                "hink",
                "klädhängare",
                "dörrhandtag",
                "dörrmatta",
                "soptunna",
                "sopskyffel",
                "växt",
                "strykbräda",
                "lampskärm",
                "strömbrytare",
                "målning",
                "tavla",
                "vägguttag",
                "ficklampa",
                "vas",
                "papperskorg",
                "pensel",
                "penna",
                "blomma",
                "kastrull",
                "stekpanna",
                "visp"
        };
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    // Get the word and make it to arrays
    static void getWordToArrays (String newWord) {
        word = newWord;
        correctWordArray = word.toCharArray();
        shownWordArray = word.replaceAll("[A-Öa-ö]", "_").toCharArray();
    }

    // Check if a correct character is given
    static boolean isCharacterAnswerCorrect() {
        boolean isGuessCorrect = false;

        for(int i=0; i<correctWordArray.length; i++) {
            char correctCharacter = correctWordArray[i];

            if (inputCharacter == correctCharacter) {
                shownWordArray[i] = inputCharacter;
                isGuessCorrect = true;
            }
        }
        return isGuessCorrect;
    }

    // Check if the right word is given
    static boolean isWordAnswerCorrect() {
        boolean isGuessCorrect = false;

        if(Arrays.equals(shownWordArray, correctWordArray)) {
            isGuessCorrect = true;
        }

        if (inputWord != null && inputWord.equals(word)) {
            isGuessCorrect = true;
            shownWordArray = correctWordArray;
        }
        return isGuessCorrect;
    }

    // Print bunny depending on how many tries the player have done
    static void printBunny() {

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("bunny" + guessesLeft + ".txt"));
            while (scanner.hasNextLine())
                System.out.println(scanner.nextLine());
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }


    public static void main(String[] args) {

        boolean ongoingGame = true;

        // Get new random word for this game
        getWordToArrays(getRandomWord());

        // Correct answer
        System.out.println(correctWordArray);

        // Game intro
        System.out.println("Välkommen till spelet där du får försöka gissa rätt ord för att rädda kaninen från att uppslukas av marken. Gissa ordet:");
        System.out.println(shownWordArray);

        while (ongoingGame) {

            // Start menu with three options
            System.out.println("\n" + "||  Antal försök (a)  ||  Gissa bokstav (b)  ||  Gissa ordet (c)  ||  Eget ord (d)");
            System.out.print("Välj vad du vill göra: ");
            Scanner reader = new Scanner(System.in);
            String inputChoice = reader.nextLine();

            switch (inputChoice) {

                // 1. Show number of completed guesses and remaining attempts
                case "a" -> {
                    System.out.println("Antal försök: " + tries);
                    System.out.println("Återstående försök: " + guessesLeft);
                    printBunny();
                }

                // 2. Guess a character
                case "b" -> {
                    tries++;
                    System.out.print("Skriv den bokstav du vill gissa på: ");
                    inputCharacter = reader.nextLine().charAt(0);
                    if (isCharacterAnswerCorrect()) {

                        if (isWordAnswerCorrect()) {
                            System.out.println("\n" + "Grattis! Det var rätt ord och kaninen är räddad");
                            guessesLeft = 8;
                            ongoingGame = false;
                        }
                    }
                    else {
                        System.out.println("\n" + "Den bokstaven fanns inte med");
                        guessesLeft--;
                    }
                    printBunny();
                    System.out.println(shownWordArray);
                }

                // 3. Guess the whole word
                case "c" -> {
                    tries++;
                    System.out.print("Skriv det ord som du vill gissa på: ");
                    inputWord = reader.nextLine();
                    if (isWordAnswerCorrect()) {
                        System.out.println("\n" + "Grattis! Det var rätt ord och kaninen är räddad");
                        guessesLeft = 8;
                        ongoingGame = false;
                    }
                    else {
                        System.out.println("\n" + "Det var fel ord");
                        guessesLeft--;
                    }
                    printBunny();
                    System.out.println(shownWordArray);
                }

                // The player get to choose a word and this word replaces the original word
                case "d" -> {
                    guessesLeft = 8;
                    System.out.println("Skriv det ord som du vill att spelet ska använda: ");
                    String playersWord = reader.nextLine();
                    getWordToArrays(playersWord);
                }
                default -> System.out.println("Det blev fel, välj a, b eller c");
            }

            // End the game
            if(guessesLeft == 0) {
                System.out.println("Game over, hejdå kaninen :( ");
                System.out.print("Rätt svar var: ");
                System.out.print(correctWordArray);
                ongoingGame = false;
                reader.close();
            }
        }
    }
}


