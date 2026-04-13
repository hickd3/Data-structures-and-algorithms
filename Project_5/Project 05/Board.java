/**
 * File: Board.java
 * Author: Dean  Hickman
 * Date: May 2023
 * CS 231 A
 * Project 05
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;

//board class 
public class Board {
    private Cell[][] boardArray;
    public static final int SIZE = 9;

    //default constructor
    public Board() {
        boardArray = new Cell[SIZE][SIZE];
        for (int rowIndex = 0; rowIndex < SIZE; rowIndex++) {
            for (int columnIndex = 0; columnIndex < SIZE; columnIndex++) {
                boardArray[rowIndex][columnIndex] = new Cell();
            }
        }
    }

    //auxiliary constructor
    public Board(String filename) {
        this(); // Call default constructor to initialize the board
        read(filename); // Call read method to populate the board from the file
    }

    //returns the number of columns
    public int getCols() {
        return SIZE;
    }
//returns the number of rows
    public int getRows() {
        return SIZE;
    }
    //returns the Cell at location r, c
    public Cell get(int r, int c) {
        return boardArray[r][c];
    }
    //returns whether the Cell at r, c, is locked
    public boolean isLocked(int r, int c) {
        return boardArray[r][c].isLocked();
    }
    //returns the number of locked Cells on the board
    public int numLocked() {
        int count = 0;
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (boardArray[r][c].isLocked()) {
                    count++;
                }
            }
        }
        return count;
    }
    //returns the value at Cell r, c
    public int value(int r, int c) {
        return boardArray[r][c].getValue();
    }
    //sets the value of the Cell at r, c
    public void set(int r, int c, int value) {
        boardArray[r][c].setValue(value);
    }
    //sets the value and locked fields of the Cell at r, c
    public void set(int r, int c, int value, boolean locked) {
        boardArray[r][c].setValue(value);
        boardArray[r][c].setLocked(locked);
    }

    // Read file
    public boolean read(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            int rowIndex = 0;
            while (line != null && rowIndex < SIZE) {
                String[] values = line.split("[ ]+");
                for (int columnIndex = 0; columnIndex < SIZE && columnIndex < values.length; columnIndex++) {
                    int value = Integer.parseInt(values[columnIndex]);
                    set(rowIndex, columnIndex, value, value != 0);
                }
                line = br.readLine();
                rowIndex++;
            }
            br.close();
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println("Board.read():: unable to open file " + filename);
        } catch (IOException ex) {
            System.out.println("Board.read():: error reading file " + filename);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Board.read():: invalid format in the input file");
        }
        return false;
    }
    // Valid value check
    public boolean validValue(int row, int col, int value) {
        if (value < 1 || value > SIZE) {
            return false; // Value is out of range
        }

        // Check uniqueness in row
        for (int c = 0; c < SIZE; c++) {
            if (c != col && boardArray[row][c].getValue() == value) {
                return false; // Value already exists in the row
            }
        }

        // Check uniqueness in column
        for (int r = 0; r < SIZE; r++) {
            if (r != row &&            boardArray[r][col].getValue() == value) {
                return false; // Value already exists in the column
            }
        }

        // Check uniqueness in local 3x3 square
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (r != row && c != col && boardArray[r][c].getValue() == value) {
                    return false; 
                }
            }
        }

        return true; 
    }

    //checks if the board is solved
    public boolean validSolution() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                int value = boardArray[r][c].getValue();
                if (value < 1 || value > SIZE || !validValue(r, c, value)) {
                    return false; // Board is not solved
                }
            }
        }
        return true;
    }
    public void draw(Graphics g, int scale) {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                get(i, j).draw(g, j * scale + 5, i * scale + 10, scale);
            }
        }

        
            if (validSolution()) {
                g.setColor(new Color(0, 127, 0));
                g.drawChars("Hurray!".toCharArray(), 0, "Hurray!".length(), scale * 3 + 5, scale * 10 + 10);
            } else {
                g.setColor(new Color(127, 0, 0));
                g.drawChars("No solution!".toCharArray(), 0, "No Solution!".length(), scale * 3 + 5, scale * 10 + 10);
            }
        
    }

    //main method
    public static void main(String[] args) {
        if (args.length > 0) {
            String filename = args[0];
            Board board = new Board(filename);
            System.out.println("Is the board solved? " + board.validSolution());
        } else {
            System.out.println("What is the filename?");
        }
    }
}
