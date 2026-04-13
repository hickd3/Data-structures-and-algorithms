
/*
 * Filename: MazeBreadthFirstSearch.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS 231 A
 */
import java.util.Queue;
import java.util.LinkedList;

public class MazeBreadthFirstSearch extends AbstractMazeSearch {
    private Queue<Cell> queue;

    public MazeBreadthFirstSearch(Maze maze) {
        super(maze);
        queue = new LinkedList<>();
    }

    @Override
    public Cell findNextCell() {
        return queue.poll();
    }

    @Override
    public void addCell(Cell next) {
        queue.offer(next);
    }

    @Override
    public int numRemainingCells() {
        return queue.size();
    }
}
