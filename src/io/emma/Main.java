package io.emma;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

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

        if (inputWord != null && inputWord.equals(word)) {
            isGuessCorrect = true;
        }
        if(Arrays.equals(shownWordArray, correctWordArray)) {
            isGuessCorrect = true;
        }
        return isGuessCorrect;
    }

    // Print bunny depending on how many tries the player have done
    static void printBunny() {

        try {

            switch (guessesLeft) {

                case 0:
                    Scanner scanner = new Scanner(new File("bunny0.txt"));
                    while (scanner.hasNextLine())
                        System.out.println(scanner.nextLine());
                    break;

                case 1:
                    scanner = new Scanner(new File("bunny1.txt"));
                    while (scanner.hasNextLine())
                        System.out.println(scanner.nextLine());
                    break;

                case 2:
                    scanner = new Scanner(new File("bunny2.txt"));
                    while (scanner.hasNextLine())
                        System.out.println(scanner.nextLine());
                    break;

                case 3:
                    scanner = new Scanner(new File("bunny3.txt"));
                    while (scanner.hasNextLine())
                        System.out.println(scanner.nextLine());
                    break;

                case 4:
                    scanner = new Scanner(new File("bunny4.txt"));
                    while (scanner.hasNextLine())
                        System.out.println(scanner.nextLine());
                    break;

                case 5:
                    scanner = new Scanner(new File("bunny5.txt"));
                    while (scanner.hasNextLine())
                        System.out.println(scanner.nextLine());
                    break;

                case 6:
                    scanner = new Scanner(new File("bunny6.txt"));
                    while (scanner.hasNextLine())
                        System.out.println(scanner.nextLine());
                    break;

                case 7:
                    scanner = new Scanner(new File("bunny7.txt"));
                    while (scanner.hasNextLine())
                        System.out.println(scanner.nextLine());
                    break;

                case 8:
                    scanner = new Scanner(new File("bunny8.txt"));
                    while (scanner.hasNextLine())
                        System.out.println(scanner.nextLine());
                    break;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {

        boolean ongoingGame = true;

        // Word-arrays
        word = getRandomWord();
        correctWordArray = word.toCharArray();
        shownWordArray = word.replaceAll("[A-Öa-ö]", "_").toCharArray();

        // Correct answer
        System.out.println(correctWordArray);

        // Game intro
        System.out.println("Välkommen till spelet där du får försöka gissa rätt ord för att rädda gubben från att hängas. Gissa ordet:");
        System.out.println(shownWordArray);

        while (ongoingGame) {

            // Start menu with three options
            System.out.println("");
            System.out.println("||  Antal försök (a)  ||  Gissa bokstav (b)  ||  Gissa ordet (c)");
            System.out.print("Välj vad du vill göra: ");
            Scanner reader = new Scanner(System.in);
            String inputChoice = reader.nextLine();

            // 1. Show number of completed guesses and remaining attempts
            if(inputChoice.equals("a")) {
                System.out.println("Antal försök: " + tries);
                System.out.println("Återstående försök: " + guessesLeft);
                printBunny();
            }

            // 2. Guess a character
            else if(inputChoice.equals("b")) {
                tries++;
                System.out.print("Skriv den bokstav du vill gissa på: ");
                inputCharacter = reader.nextLine().charAt(0);

                if(isCharacterAnswerCorrect()) {

                    if (isWordAnswerCorrect()) {
                        System.out.println("Grattis! Det var rätt ord och kaninen är räddad");
                        guessesLeft = 8;
                        ongoingGame = false;
                    }
                }
                else {
                    System.out.println("Den bokstaven fanns inte med");
                    guessesLeft--;
                }
                printBunny();
                System.out.println(shownWordArray);
            }

            // 3. Guess the whole word
            else if(inputChoice.equals("c")) {
                tries++;
                System.out.print("Skriv det ord som du vill gissa på: ");
                inputWord = reader.nextLine();

                if (isWordAnswerCorrect()) {
                    System.out.println("Grattis! Det var rätt ord och kaninen är räddad");
                    guessesLeft = 8;
                    ongoingGame = false;
                }
                else {
                    System.out.println("Det var fel ord");
                    guessesLeft--;
                }
                printBunny();
                System.out.println(shownWordArray);
            }
            else {
                System.out.println("Det blev fel, välj a, b eller c");
            }

            if(guessesLeft == 0) {
                System.out.println("Game over, hejdå kaninen :( ");
                ongoingGame = false;
            }
        }
    }
}


