/*
 * File: Card.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS231 A
 * Project 01
 * 
 */

 /*Imports the Random package for use */
 import java.util.Random;

/*Creates a class that holds all the information for a card */
 public class Card{
    Integer value;
    Random ran = new Random();
/* Constructor method for a card with a certain value checking the value of the card */
  public Card( int v){
    this.value = v;
    if((v < 2) || (v > 11)){
      System.out.println("error: card value out of range - must be within range 2-11.");
    }
}
/*Return the numeric value of the card */
    public int getValue(){
        return this.value; 
    }
    @Override
    /*Returns the integer value cast as a string */
    public String toString(){
        return Integer.toString(value);
    }
    /* Main funtion */
    public static void main(String[] args){
        Random ran = new Random();
        Card card = new Card(ran.nextInt(2, 12));
        System.out.println(card.getValue());
        System.out.println(card.toString());
    }

}


