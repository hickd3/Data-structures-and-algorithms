/*
 * File: Blackjack.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS231 A
 * Project 01
 * 
 */


 /* Implements the card game, Blackjack */   
public class Blackjack{
    Deck deck = new Deck();
    Hand playHand = new Hand();
    Hand dealHand = new Hand();
    int reshuffleCutoff;
    /* Constructor method, stores reshuffleCutodd and sets up the game */
    public Blackjack( int reshuffleCutoff){
      this.reshuffleCutoff = 10;
      this.playHand= new Hand();
      deck.shuffle();
      reset();
    }
    /* Another constructor method, does nothing functional */
    public Blackjack() {
    }
    /* Reset method is defined with return type void, resets the state of the game */
    public void reset(){
        playHand.reset();
        dealHand.reset();
        if(deck.size() < reshuffleCutoff){
           Deck freshDeck = new Deck();
           freshDeck.shuffle();
           this.deck= freshDeck;
        }
    }
    /*Deal method is defined with return type void, it deals two cards to the player and to the dealer */
    public void deal(){
        for(int i = 0; i <2; i++){
            Card playerCard = deck.deal();
            playHand.add(playerCard);
        }
        for(int i = 0; i <2; i++){
            Card dealerCard = deck.deal();
            dealHand.add(dealerCard);
        }
    }
    /*The player turn method is of return type boolean, it defines the way a player will take a turn */
    public boolean playerTurn(){
    while(playHand.getTotalValue() < 17){
        Card playerCard = deck.deal();
        playHand.add(playerCard);
    }
    return playHand.getTotalValue() <= 21;
    }
    
    /*The dealeer turn method is of return type boolean, it defines the way a dealer will take a turn */
    public boolean dealerTurn(){
        while(dealHand.getTotalValue() < 17){
            Card dealerCard = deck.deal();
            dealHand.add(dealerCard);
        }
        return dealHand.getTotalValue() <= 21;
        }
    /* This method with a void return sets the variable reshuffleCutoff to its integer value */
    public void setReshuffleCutoff(int cutoff){
        this.reshuffleCutoff = cutoff;
    } 
    /*This method returns the integer from the class instance reshuffleCutoff */ 
    public int getReshuffleCutoff(int cutoff){
        return this.reshuffleCutoff;
    }
    /*The toSring() method returns a string of the current game state */
    public String toString(){
        return ("State of the game: ") +
        "Player hand: " + playHand + "\n" +
        "Dealer hand: " + dealHand + "\n";
    
    }
    /* The main function */
    public static void main(String[] args){
        Blackjack newGame = new Blackjack(10);
        newGame.deal();
        System.out.println(newGame);
        while(newGame.playerTurn()){
            System.out.println("Player hit!");
            System.out.println(newGame);

        }
        if(newGame.dealerTurn()){
            System.out.println("Dealer hit!");
            System.out.println(newGame);
        }
        if(newGame.playHand.getTotalValue() <= 21 && newGame.dealHand.getTotalValue() < newGame.playHand.getTotalValue() ||
        newGame.dealHand.getTotalValue() > 21){
            System.out.println(" Player Won!");    
        } else {
            System.out.println("Dealer Won!");
        }
    }
    /* The game method returns an integer with the verbose argument, a boolean
     * it returns and integer 1 for a  player win, 0 for a tie, and -1 for a dealer win
     * and prints certain information when the boolean is evaluated
    */
        public int game(boolean verbose) {
            reset();
            deal();
        
            boolean playerTurn = playerTurn();
            boolean dealerTurn = dealerTurn();
        
            /* Player busts */ 
            if (!playerTurn) {
                if (verbose) {
                    System.out.println("Player busts. Dealer wins.");
                    System.out.println("Player hand: " + playHand + " Total value: " + playHand.getTotalValue());
                    System.out.println("Dealer hand: " + dealHand + " Total value: " + dealHand.getTotalValue());
                }
                return -1;
            }
        
            /* Dealer Busts */
            if (!dealerTurn) {
                if (verbose) {
                    System.out.println("Dealer busts. Player wins.");
                    System.out.println("Player hand: " + playHand + " Total value: " + playHand.getTotalValue());
                    System.out.println("Dealer hand: " + dealHand + " Total value: " + dealHand.getTotalValue());
                }
                return 1;
            }
        
            /*Player chooses to hit */
            if (playerTurn) {
                while (playerTurn) {
                    playHand.add(deck.deal());
                    if (verbose) {
                        System.out.println("Player hits. Player hand: " + playHand + " Total value: " + playHand.getTotalValue());
                    }
                    playerTurn = playerTurn();
                }
            }
        
            /* Dealer chooses to hit */
            if (dealerTurn) {
                while (dealerTurn) {
                    dealHand.add(deck.deal());
                    if (verbose) {
                        System.out.println("Dealer hits. Dealer hand: " + dealHand + " Total value: " + dealHand.getTotalValue());
                    }
                    dealerTurn = dealerTurn();
                }
            }
        
            /* Determines who won */
            int playerTotal = playHand.getTotalValue();
            int dealerTotal = dealHand.getTotalValue();
        
            if (playerTotal > dealerTotal) {
                if (verbose) {
                    System.out.println("Player wins!");
                    System.out.println("Player hand: " + playHand + " Total value: " + playHand.getTotalValue());
                    System.out.println("Dealer hand: " + dealHand + " Total value: " + dealHand.getTotalValue());
                }
                return 1;
            } else if (playerTotal < dealerTotal) {
                if (verbose) {
                    System.out.println("Dealer wins!");
                    System.out.println("Player hand: " + playHand + " Total value: " + playHand.getTotalValue());
                    System.out.println("Dealer hand: " + dealHand + " Total value: " + dealHand.getTotalValue());
                }
                return -1;
            } else {
                if (verbose) {
                    System.out.println("Push.");
                    System.out.println("Player hand: " + playHand + " Total value: " + playHand.getTotalValue());
                    System.out.println("Dealer hand: " + dealHand + " Total value: " + dealHand.getTotalValue());
                }
                return 0;
            }
        }
    }
    
