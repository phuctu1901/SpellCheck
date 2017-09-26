/**
 *
 * Created by olga Veksler
 *
 * Created by Sama Rahimian (250 469 668)
 * Course 2210b
 * LinkedList implementation of dictionary
 *
 */

import java.io.*;
import java.util.LinkedList;

public class SpellCheck {


    public static void main(String[] args) throws java.io.IOException{

        //For timing how long it takes ----> start time
        double timeStart = System.currentTimeMillis();

        if (args.length != 2 ) {
            System.out.println("Usage: spell dictionaryFile.txt inputFile.txt ");
            System.exit(0);
        }

        BufferedInputStream dict,file;

        try{
            dict  = new BufferedInputStream(new FileInputStream(args[0]));
            file  = new BufferedInputStream(new FileInputStream(args[1]));
            // To read from specific files, comment the 2 lines above and
            // uncomment 2 lines below
            // dict  = new BufferedInputStream(new FileInputStream("C:\\dictionary.txt"));
            // file  = new BufferedInputStream(new FileInputStream("C:\\fileToCheck.txt"));



              //////////////////******Extra Credit:******\\\\\\\\\\\\\\\\\\\\\
             ////////////// Linked List Dictionary Implementation \\\\\\\\\\\\\
            ////////////////////////||||||||||||||\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\



            // Adds words in textfile to LinkedList
            LinkedList<String> dictionary = new LinkedList<>();
            FileWordRead dictWords = new FileWordRead(dict);
            while(dictWords.hasNextWord()){
                if (dictWords.hasNextWord()){
                    String nextWord = dictWords.nextWord();
                    if(!dictionary.contains(nextWord)){
                        dictionary.add(nextWord);
                    }
                }
            }

            //Number of words in dictionary
            System.out.println("~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~");
            System.out.println("Number of words in dictionary file: " + dictionary.size());
            System.out.println("~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~/~\n");


            //Read in the words from the file to be checked.
            FileWordRead fileWords = new FileWordRead(file);
            while(fileWords.hasNextWord()) {
                String wordCheck = fileWords.nextWord();

                //Check to see if the word is misspelled, i.e., it is not in the dictionary
                if (!dictionary.contains(wordCheck)) {
                    //Store suggested words for the incorrect word
                    String suggestedWords = "";

                    //Used by letter substitution and insertion
                    String alphabeta = "abcdefghijklmnopqrstuvwxyz";
                    char[] letter = alphabeta.toCharArray();


                    ///** Modifier: Letter Substitution **\\\
                    StringBuffer subFixWord = new StringBuffer(wordCheck);
                    int index = 0;

                    //For the length of the word, replace letter at index
                    for(int i = subFixWord.length()-1; i >= 0 ; i--){
                        StringBuffer originalWord = new StringBuffer(subFixWord); //resets originalWord
                        for (int j = 0; j < letter.length; j++) {
                            subFixWord.replace(index, index + 1, Character.toString(letter[j]));

                            //Check to see if the fixed word is in the dictionary
                            if(dictionary.contains(subFixWord.toString())) {
                                //if the word is not part of the suggested list, add the word
                                if(!suggestedWords.contains(subFixWord.toString())){
                                    suggestedWords = suggestedWords + subFixWord.toString() + " ";
                                }
                            }
                        }
                        subFixWord = originalWord; //resets subFixWord
                        index++;
                    }


                    ///** Modifier: Letter Omission **\\\
                    StringBuffer omitFixWord = new StringBuffer(wordCheck);
                    for(int i = 0; i <= omitFixWord.length() - 1 ; i++){
                        StringBuffer originalWord = new StringBuffer(omitFixWord); //resets originalWord
                        omitFixWord.deleteCharAt(i);

                        //Check to see if the fixed word is in the dictionary
                        if(dictionary.contains(omitFixWord.toString())) {
                            //if the word is not part of the suggested list, add the word
                            if(!suggestedWords.contains(omitFixWord.toString())){
                                suggestedWords = suggestedWords + omitFixWord.toString() + " ";
                            }
                        }
                        omitFixWord = originalWord; //resets omitFixWord
                    }


                    ///** Modifier: Letter Insertion **\\\
                    StringBuffer inFixWord = new StringBuffer(wordCheck);
                    int x = 0;
                    for(int i = 0; i <= inFixWord.length(); i++){
                        for (int j = 0; j < letter.length; j++) {
                            inFixWord.insert(x, Character.toString(letter[j]));

                            //Check to see if the fixed word is in the dictionary
                            if(dictionary.contains(inFixWord.toString())) {
                                //if the word is not part of the suggested list, add the word
                                if(!suggestedWords.contains(inFixWord.toString())){
                                    suggestedWords = suggestedWords + inFixWord.toString() + " ";
                                }
                            }
                            inFixWord.deleteCharAt(x);
                        }
                        x++;
                    }


                    ///** Modifier: Letter Reversal **\\\
                    StringBuffer revFixWord = new StringBuffer(wordCheck);
                    char[] reverseLetter = revFixWord.toString().toCharArray();
                    char j;

                    for(int i = 0; i <= revFixWord.length() - 2 ; i++){
                        //swap letters at index i and i+1
                        j = reverseLetter[i];
                        reverseLetter[i] = reverseLetter[i+1];
                        reverseLetter[i+1] = j;
                        //Check to see if the fixed word is in the dictionary
                        if(dictionary.contains(String.valueOf(reverseLetter))) {
                            //if the word is not part of the suggested list, add the word
                            if(!suggestedWords.contains(String.valueOf(reverseLetter))){
                                suggestedWords = suggestedWords + String.valueOf(reverseLetter) + " ";
                            }
                        }
                        //switch the letters back to their original place
                        j = reverseLetter[i];
                        reverseLetter[i] = reverseLetter[i+1];
                        reverseLetter[i+1] = j;
                    }

                    //Display misspelled word and suggestions
                    System.out.println(wordCheck + " ==> " + suggestedWords);

                }
                //This handles the word if it is in the dictionary. No modifications needed.
                else{
                    System.out.println(wordCheck + " ==> No suggestions.");
                }
            }

        }
        catch (IOException e){ // catch exceptions caused by file input/output errors
            System.out.println("Check your file name.");
            System.exit(0);
        }


        //For timing how long it takes ----> end time
        double timeEnd = System.currentTimeMillis();
        double timeTotal = (timeEnd - timeStart);
        System.out.println("\n<---TIME(ms)--->" + timeTotal + "<---TIME(ms)--->");
    }
}

