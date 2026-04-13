/*
 * Filename: MazeAStarSearch.java
 * Author: Dean Hickman
 * Date: May 2023
 * CS 231 A
 */

 import java.util.Comparator;

public class MazeAStarSearch extends AbstractMazeSearch {

    private PriorityQueue<Cell> priorityQueue;

    public MazeAStarSearch(Maze maze) {
        super(maze);
        Comparator<Cell> comparator = (cell1, cell2) -> {
            int numStepsCell1 = traceback(cell1).size();
            int estimatedDistance1 = Math.abs(target.getRow() - cell1.getRow()) + Math.abs(target.getCol() - cell1.getCol());
            int sumSteps1 = numStepsCell1 + estimatedDistance1;

            int numStepsCell2 = traceback(cell2).size();
            int estimatedDistance2 = Math.abs(target.getRow() - cell2.getRow()) + Math.abs(target.getCol() - cell2.getCol());
            int sumSteps2 = numStepsCell2 + estimatedDistance2;

            return Integer.compare(sumSteps1, sumSteps2);
        };
        this.priorityQueue = new PriorityQueue<>(comparator);
    }

    @Override
    public void addCell(Cell next) {
        priorityQueue.offer(next);
    }

    @Override
    public Cell findNextCell() {
        return priorityQueue.poll();
    }

    @Override
    public int numRemainingCells() {
        return priorityQueue.size();
    }
}
