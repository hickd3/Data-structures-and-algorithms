/*
 * File: Card.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS231 A
 * Project 01
 * 
 */

 /*Imports the Random package and the ArrayList package for use */
 import java.util.ArrayList;
 import java.util.Random;

 /* This is the Hand class that holds a set of cards */
 public class Hand{
    ArrayList<Card> hand = new ArrayList<Card>();
    Random ran = new Random();

    /* Initializing the ArrayList */
    public Hand(){
    }
    /*Resest method returns nothing but clears the hand, so no cards */
    public void reset(){
        hand.clear();
    }
    /* Add a card of object of the Card class to the add */
    public void add(Card card){
        hand.add(card);
    }
    /* Returns the size of the hand, how many cards are in this hand */
    public int size(){
        return hand.size();
    }
    /*Returns the card at position i in the hand */
    public Card getCard( int i){
        return hand.get(i);
    }
    /* Adds the full value of the hand */
    public int getTotalValue(){
        int total= 0;
        int size = hand.size();
        Card get;
        for (int i = 0; i < size; i++){
            get = hand.get(i);
            int k = get.getValue();
            total= total+ k;
        }
        return total;
    }
    /* The contents of the hand and its value is retuned in a string */
    public String toString(){
        int size = hand.size();
        String pile = "[";
        for( int i= 0; i < size; i++){
            int Value= this.hand.get(i).getValue();
            pile =  (pile+ Value + ", ");
        }
        if (size > 0){
            pile = pile.substring(0, pile.length()-2);
        }
        pile += "] : " + getTotalValue();
        
        return pile;
    }
    /* Main function */
    public static void main (String[] args){
        Hand hand = new Hand();
        for (int i = 0; i < 10; i++){
            Random ran = new Random();
            Card card = new Card(ran.nextInt(2,12));
            hand.add(card);
        }
        System.out.println(hand.toString());
    }
 }
