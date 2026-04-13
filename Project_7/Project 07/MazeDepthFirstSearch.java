/*
 * Filename: MazeDepthFirstSearch.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS 231 A
 */

 import java.util.Stack;

 public class MazeDepthFirstSearch extends AbstractMazeSearch {
 
     private Stack<Cell> stack;
 
     public MazeDepthFirstSearch(Maze maze) {
         super(maze);
         stack = new Stack<>();
     }
 
     @Override
     public Cell findNextCell() {
         return stack.pop();
     }
 
     @Override
     public void addCell(Cell next) {
         stack.push(next);
     }
 
     @Override
     public int numRemainingCells() {
         return stack.size();
     }
 }
 

