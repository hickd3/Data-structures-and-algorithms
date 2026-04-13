import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Board {
    private Cell[][] boardArray;

    //creates a 9x9 2D array of Cells, initialized to 0
    public Board() {
        boardArray = new Cell[9][9];
        for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
            for (int columnIndex = 0; columnIndex < 9; columnIndex++) {
                boardArray[rowIndex][columnIndex] = new Cell();
            }
        }
    }

    //find cell at given row and column
    public Cell get(int rowIndex, int columnIndex) {
        return boardArray[rowIndex][columnIndex];
    }

    //set cell to given value
    public void set(int rowIndex, int columnIndex, int value) {
        boardArray[rowIndex][columnIndex].setValue(value);
    }

    //determine boolean locked
    public void set(int rowIndex, int columnIndex, boolean locked) {
        boardArray[rowIndex][columnIndex].setLocked(locked);
    }

    //read file
  

    //string representation of a board
    public String toString() {
    
        String str = "";													// initialize the str variable
        for(int columnIndex=0; columnIndex<9; columnIndex++)												// run through the board
        {
            for(int rowIndex=0; rowIndex<9; rowIndex++)											
            {
                str += this.boardArray[rowIndex][columnIndex].getValue();					// get the value of the cell at the given location
                if((rowIndex+1)%3 == 0 && rowIndex < 8)											// separate the board into 3x3 blocks
                {		
                    str += "\t";											// tab the string in
                }
            }
            str += "\n";													// create a new line
            if((columnIndex+1)%3 == 0 && columnIndex < 8)
            {
                str += "\n";												// create a new line
            }				
        }
        return str;			
    }
    //read file
    public boolean read(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            int rowIndex = 0;
            while (line != null && rowIndex < 9) {
                String[] values = line.split("[ ]+");
                for (int columnIndex = 0; columnIndex < 9 && columnIndex < values.length; columnIndex++) {
                    int value = Integer.parseInt(values[columnIndex]);
                    set(rowIndex, columnIndex, value);
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
    

    public static void main(String[] args) {
        if (args.length > 0) {
            String filename = args[0];
            Board board = new Board();
            boolean good = board.read(filename);
            if (good) {
                System.out.println(board);
            }
        } else {
            System.out.println("What is the filename");
        }
    }
}
