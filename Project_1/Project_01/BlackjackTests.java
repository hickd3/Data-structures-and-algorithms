/*
file name:      BlackjackTests.java
Authors:        Max Bender & Naser Al Madi
last modified:  8/28/2022

How to run:     java -ea BlackjackTests
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BlackjackTests {

    public static void blackjackTests() {

        // case 1: testing Blackjack() and Blackjack(i)
        {
            // set up
            Blackjack bj1 = new Blackjack();
            Blackjack bj2 = new Blackjack(5);

            // verify

            // test
            assert bj1 != null : "Error in Blackjack::Blackjack()";
            assert bj2 != null : "Error in Blackjack::Blackjack()";
        }
       
        System.out.println("*** Done testing Blackjack! ***\n");
    }
    // case 3: testing game() in verbose mode
{
    // set up
    Blackjack bj = new Blackjack();
    bj.setReshuffleCutoff(5);
    int numGames = 10;

    // play multiple games
    for (int i = 0; i < numGames; i++) {
        System.out.println("Playing game #" + (i+1));
        int result = bj.game(true);

        // verify
        assert result >= -1 && result <= 1 : "Error in Blackjack::game()";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
System.setOut(new PrintStream(outContent));
        // check output
        String output = outContent.toString();
        String[] lines = output.split(System.getProperty("line.separator"));
        String lastLine = lines[lines.length-1];
        assert (result == -1 && lastLine.equals("Dealer wins!"))
            || (result == 0 && lastLine.equals("Push!"))
            || (result == 1 && lastLine.equals("Player wins!")) : "Error in Blackjack::game() output";
        
        // reset output stream
        outContent.reset();
    }
}




    public static void main(String[] args) {

        blackjackTests();
    }
    
}