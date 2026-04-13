/*
 * Filename: AbstractMazeSearch.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS 231 A
 */

import java.awt.Graphics;
import java.awt.Color;

public abstract class AbstractMazeSearch{

    private Maze maze;
    private Cell start;
    public Cell target;
    private Cell cur;
    public MazeSearchDisplay mazeSearchDisplay;

    public AbstractMazeSearch(Maze maze){
        this.maze = maze;
        cur = null;
        start = null;
        target = null;
    }
    //method returns the next Cell to explore
    public abstract Cell findNextCell();
    //method adds the given Cell to whatever structure is storing the future Cells to explore
    public abstract void addCell(Cell next);
    //method returns the number of future Cells to explore
    public abstract int numRemainingCells();

    //returns the underlying Maze
    public Maze getMaze(){
        return maze;
    }

    //sets the given target to be the target of the search
    public void setTarget(Cell target){
        this.target = target;
    }
    //returns the target of the search
    public Cell getTarget(){
        return target;
    }
    //sets the given cell to be the current location of the search
    public void setCur(Cell cell){
        this.cur = cell;
    }
    //returns the current Cell location of the search
    public Cell getCur(){
        return cur;
    }
    //sets the given start to be the start of the search
    public void setStart(Cell start){
        this.start = start;
        start.setPrev(start);
    }
    //returns the start of the search
    public Cell getStart(){
        return start;
    }
    //sets the current, start, and target Cells to be null
    public void reset(){
        start = null;
        target = null;
    }
    //finds a path from the start to the specified cell and returns path
    public LinkedList<Cell> traceback(Cell cell){
        Cell curCell = cell;
        LinkedList<Cell> path = new LinkedList<>();
    
        while (curCell != null){
            path.addFirst(cell);
            if (curCell.equals(start)){
                return path; //completed the path from the start to the specified cell
            }
            curCell = curCell.getPrev();
        } return null; //no path, so we return null
    }

    public LinkedList<Cell> search(Cell start, Cell target, boolean display, int delay){
        setStart(start);
        setTarget(target);
        setCur(start);
    
        //add the starting cell to the set of Cells to explore
        addCell(start);
        if (display == true){
            mazeSearchDisplay = new MazeSearchDisplay(this, 30);
        }

      
        while (numRemainingCells() != 0){
            if(display == true){
                try{
                Thread.sleep(delay);
                mazeSearchDisplay.repaint();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }                
            }
            //set Cur to be the next Cell from the Cells to explore (findNextCell())
            setCur(findNextCell());
    
            for (Cell neighbor : maze.getNeighbors(cur)) {
                if (neighbor.getPrev() == null){
                    //set this neighbor's prev to be cur
                    neighbor.setPrev(this.getCur());
                    //add this neighbor to the future Cells to explore
                    addCell(neighbor);
                    if (neighbor.getRow() == target.getRow() && neighbor.getCol() == target.getCol()){
                        target.setPrev(cur);
                        return traceback(target); 
                    }
                }
            }
        }
    
        return null; 
    }


    public void draw(Graphics g, int scale) {
        //draws the base version of the maze
        getMaze().draw(g, scale);
        //draws the paths taken by the searcher
        getStart().drawAllPrevs(getMaze(), g, scale, Color.RED);
        //draws the start cell
        getStart().draw(g, scale, Color.BLUE);
        //draws the target cell
        getTarget().draw(g, scale, Color.RED);
        //draws the current cell
        getCur().draw(g, scale, Color.MAGENTA);
    
    
        if (getTarget().getPrev() != null) {
            Cell traceBackCur = getTarget().getPrev();
            while (!traceBackCur.equals(getStart())) {
                traceBackCur.draw(g, scale, Color.GREEN);
                traceBackCur = traceBackCur.getPrev();
            }
            getTarget().drawPrevPath(g, scale, Color.BLUE);
        }
    }


    public static void main(String[] args){
        Maze maze = new Maze(20, 20, 0.1);

        MazeDepthFirstSearch mazeDFS = new MazeDepthFirstSearch(maze);
        MazeBreadthFirstSearch mazeBFS = new MazeBreadthFirstSearch(maze);
        MazeAStarSearch mazeAStar = new MazeAStarSearch(maze);
        

        Cell target = new Cell(0, 0, CellType.FREE);
        Cell start = new Cell(19, 19, CellType.FREE);

       
        mazeDFS.search(start,target, true, 10);
        
    }



}