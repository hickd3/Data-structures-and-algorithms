public class Cell implements Cloneable{
    private int rowIndex;
    private int columnIndex;
    private int value;
    private boolean valueLocked;

    //initializes all values to 0 or false
    public Cell(){
       rowIndex = 0;
       columnIndex = 0;
       value = 0;
       valueLocked = false;
    }
    /* initialize the row, column, and value fields to the given parameter values. 
    *  Set the locked field to false 
    */
    public Cell (int rowIndex, int columnIndex){
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.value = 0;
        valueLocked = false;
    }
    public Cell(int rowIndex, int columnIndex, int value, boolean valueLocked){
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.value = value;
        this.valueLocked = valueLocked;
    }
    public int getRow(){
        return this.rowIndex;
    }
    public int getCol(){
        return this.columnIndex;
    }
    public int getValue(){
        return this.value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public boolean isLocked(){
    return this.valueLocked;
    }
    public void setLocked(boolean lock){
        valueLocked = lock;
    }
    public String toString(){
        return "Cell's value: " + this.value + "Located at: " + this.rowIndex + " " + this.columnIndex + "Locked: " + this.valueLocked;
    }
    
    public static void main(String[] argv) {
        Cell cellA = new Cell();
        Cell cellB = new Cell(1, 5, 7);
        Cell cellC = new Cell(2, 6, 8, true);
        System.out.println("cellA locked: " + cellA.isLocked());
        System.out.println("cellB locked: " + cellB.isLocked());
        System.out.println("cellC locked: " + cellC.isLocked());

        

        System.out.println("cellB value should be 7: " + cellB.getValue());
        System.out.println("cellC value should be 8: " + cellC.getValue());



        System.out.println("cellA row, col (0, 0): " + cellA.getRow() + ", " + cellA.getCol());
        System.out.println("cellB row, col (1, 5): " + cellB.getRow() + ", " + cellB.getCol());
        System.out.println("cellC row, col (2, 6): " + cellC.getRow() + ", " + cellC.getCol());
    }
  

}