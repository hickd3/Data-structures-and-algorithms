/**
 * File: Sudoku.java
 * Author: Dean  Hickman
 * Date: May 2023
 * CS 231 A
 * Project 05
 */

import java.util.Random;
import java.util.Stack;

//sudoku class
public class Sudoku{
    private Board board;
    private static LandscapeDisplay ld;

    public Sudoku() {
        board = new Board();
        ld = new LandscapeDisplay(board);
    }


    //randomly generate and lock values on the board
    public Sudoku(int numChosenCells){
        board = new Board();
        ld = new LandscapeDisplay(board);
        Random random  = new Random();
        int chosenCells = 0 ;
        while (chosenCells < numChosenCells){
            int rowIndex = random.nextInt(Board.SIZE);
            int columnIndex = random.nextInt(Board.SIZE);
            int value = random.nextInt(Board.SIZE) + 1;
            //is the generated value valid in its current location
            if(board.validValue(rowIndex, columnIndex, value) && !board.isLocked(rowIndex, columnIndex)){
                board.set(rowIndex, columnIndex, value);
                chosenCells++;
            }
        }
    }
    //finds a valid value for a cell
    public int findValidValue(Cell cell){
        for (int value = 1; value <= Board.SIZE; value++){
            if(board.validValue(cell.getRow(), cell.getCol(), value)){
                return value;
            }
        }
        return 0;
    }
    //find the next cell to go to and find an appropriate value for it
    public Cell findEmptyCell() {
        for (int rowIndex = 0; rowIndex < Board.SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < Board.SIZE; columnIndex++) {
                Cell cell = board.get(rowIndex, columnIndex);
                if (cell.getValue() == 0) {
                    int validValue = findValidValue(cell);
                    if (validValue != 0) {
                        cell.setValue(validValue);
                        return cell; 
                    } 
                    }
                }
            }
        return null; 
    }
//solves the board  
public boolean solve(int delay) throws InterruptedException {
    Stack<Cell> stack = new Stack<>();
    Cell currentCell = findEmptyCell();

    while (currentCell != null) {
        int validValue = findValidValue(currentCell);

        if (validValue != 0) {
            currentCell.setValue(validValue);
            stack.push(currentCell);
            currentCell = findEmptyCell(); // Move to the next empty cell
        } else {
            if (stack.isEmpty()) {
                return false; // No solution found
            }

            Cell poppedCell = stack.pop();
            poppedCell.setValue(0);
            currentCell = poppedCell; // Go back to the previous cell
        }

        Thread.sleep(delay); 
    }

    return true; 
}



public static void main(String[] args) throws InterruptedException {
    Sudoku sudoku = new Sudoku(0); //create a Sudoku board with # locked cells
    boolean solved = sudoku.solve(10); //solve the board with a delay of 10ms
    if (solved) {
        System.out.println("Board solved!");
    } else {
        System.out.println("No solution found!");
    }
}
}





