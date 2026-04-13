/*
 * File: Simulation.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS231 A
 * Project 01
 * 
 */

 /* Runs a simulation of the Blackjack 1000 times */
public class Simulation {


/* Main function */    
public static void main(String[] args){
        Blackjack gameSim = new Blackjack(39);
        int playerWins = 0;
        int dealerWins = 0;
        int pushes = 0; 
        

        for ( int i = 0; i < 1000; i++){
            gameSim.reset();
            gameSim.deal();

             boolean playerBust = !gameSim.playerTurn();
             boolean dealerBust = !gameSim.dealerTurn();
             
            if(!playerBust){
                dealerBust = !gameSim.dealerTurn();
            }
            if (playerBust){
                dealerWins++;
            } else if (dealerBust) {
                playerWins++;
            } else if (gameSim.playHand.getTotalValue() > gameSim.dealHand.getTotalValue()) {
                playerWins++;
            } else if (gameSim.playHand.getTotalValue() < gameSim.dealHand.getTotalValue()) {
                dealerWins++;
            } else {
                pushes++;
            }
            gameSim.reset();
        }

        System.out.println("Player wins: " + playerWins + " (" + ((double) playerWins / 1000 * 100) + "%)");
        System.out.println("Dealer wins: " + dealerWins + " (" + ((double) dealerWins / 1000 * 100) + "%)");
        System.out.println("Pushes: " + pushes + " (" + ((double) pushes / 1000 * 100) + "%)");
    }
}
