/*
 * File: Deck.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS231 A
 * Project 01
 * 
 */

 /*Imports the ArrayList and Random packages for use */
import java.util.ArrayList;
import java.util.Random;

/* Holds and shuffles a set of cards */
public class Deck{
    Random ran = new Random();
    ArrayList<Card> deck = new ArrayList<Card>();

    /*Builds a deck from the constructor method build*/
    public Deck(){
        build();
    }

    /* Defines the critera needed to build a deck */
    public void build(){
          for(int i = 0; i < 4; i++){
            Card card = new Card(2);
            deck.add(card);
        }
        for(int i = 0; i < 4; i++){
            Card card = new Card(3);
            deck.add(card);
        }
        for(int i = 0; i < 4; i++){
            Card card = new Card(4);
            deck.add(card);
        }
        for(int i = 0; i < 4; i++){
            Card card = new Card(5);
            deck.add(card);
        }
        for(int i = 0; i < 4; i++){
            Card card = new Card(6);
            deck.add(card);
        }
        for(int i = 0; i < 4; i++){
            Card card = new Card(7);
            deck.add(card);
        }
        for(int i = 0; i < 4; i++){
            Card card = new Card(8);
            deck.add(card);
        }
        for(int i = 0; i < 4; i++){
            Card card = new Card(9);
            deck.add(card);
        }
        for(int i = 0; i < 4; i++){
            Card card = new Card(11);
            deck.add(card);
        }
        for (int i = 0; i < 16; i++){
            Card card = new Card(10);
            deck.add(card);
        }
    }

/* Returns the size of the deck, should 52 */
public int size(){
    return deck.size();
}
/* Deals a card and removes it from the deck */
public Card deal(){
    Card k = deck.get(0);
    deck.remove(0);
    return k;
}
/* Shuffles the deck using the Fisher-Yates algorithm*/
public void shuffle(){
    Random ran = new Random();
    for(int i = deck.size() - 1; i > 0; i--){
    Integer j = ran.nextInt(i + 1);
    Card h = deck.get(i);
    deck.set(i, deck.get(j));
    deck.set(j, h);
    }
}
/* Returns the contents of the deck in a string */
public String toString(){
    return ("contents: " + deck + ", ");
}
/* Main function */
public static void main( String[] args){
    Deck deck = new Deck();
    System.out.println( "Starting deck  "+ deck);
    System.out.println( "Deck size:  " + deck.size());
    deck.shuffle();
    System.out.println("Shuffled deck " + deck);
    System.out.println("Deck size:  " + deck.size());
    System.out.println("Dealt card: " + deck.deal());
}
}

