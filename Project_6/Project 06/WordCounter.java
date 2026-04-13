/*
 * File: WordCounter.java
 * Author: Dean Hickman
 * Date: May 2023
 * Project 06
 * CS 321 A
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//analyzes, reads, and writes text files
public class WordCounter {
    private Map<String, Integer> wordCountMap; //map to store words and their counts
    private int totalWordCount; //total word count

    //constructor, initializes the wordCountMap and sets totalWordCount to 0 
    public WordCounter() {
        wordCountMap = new HashMap<>();
        totalWordCount = 0;
    }

    //analyzes the text file and builds the wordCountMap 
    public void analyze(String filename) {
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            while (line != null) {
                String[] words = line.split("[^a-zA-Z0-9']");
                for (String word : words) {
                    word = word.trim().toLowerCase();
                    if (!word.isEmpty()) {
                        wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                        totalWordCount++;
                    }
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file: " + filename);
        } catch (IOException ex) {
            System.out.println("Error reading file: " + filename);
        }
    }

    //returns the total word count
    public int getTotalWordCount() {
        return totalWordCount;
    }

    //returns the number of unique words 
    public int getUniqueWordCount() {
        return wordCountMap.size();
    }

    //returns the count of the given word
    public int getCount(String word) {
        return wordCountMap.getOrDefault(word.toLowerCase(), 0);
    }

    //returns the frequency of the given word 
    public double getFrequency(String word) {
        int count = getCount(word);
        return (double) count / totalWordCount;
    }

    //writes the word count to a file
    public void writeWordCountFile(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write("totalWordCount: " + totalWordCount + "\n");

            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
        }
    }

    //reads the word count from a file and updates the wordCountMap 
    public void readWordCountFile(String filename) {
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            line = bufferedReader.readLine(); // Skip the first line (header)

            while (line != null) {
                String[] values = line.split(" ");
                String word = values[0];
                int count = Integer.parseInt(values[1]);
                wordCountMap.put(word, count);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file: " + filename);
        } catch (IOException ex) {
            System.out.println("Error reading file: " + filename);
        }
    }

//main method
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a filename as a command-line argument.");
            return;
        }
    
        String filename = args[0];
        WordCounter wordCounter = new WordCounter();
    
        //analyze a text file
        wordCounter.analyze(filename);
    
        
        System.out.println("Total word count: " + wordCounter.getTotalWordCount());
        System.out.println("Unique word count: " + wordCounter.getUniqueWordCount());
    
       
        String word = "ate";
        System.out.println("Count of '" + word + "': " + wordCounter.getCount(word));
        System.out.println("Frequency of '" + word + "': " + wordCounter.getFrequency(word));
    
        //write the word count to a file
        String outputFilename = "wordcount.txt";
        wordCounter.writeWordCountFile(outputFilename);
        System.out.println("Word count saved to: " + outputFilename);
    
        String inputFilename = outputFilename;
        wordCounter.readWordCountFile(inputFilename);
    

        System.out.println("\nAfter reading from file:");
        System.out.println("Total word count: " + wordCounter.getTotalWordCount());
        System.out.println("Unique word count: " + wordCounter.getUniqueWordCount());
    }
    
   
}

