package generateMaze;

/*
 * Maze class:
 * 	This class is used to randomly create a perfect maze with one and only one
 * 		path from any point in the maze to any other point.
 * 	The maze has no inaccessible sections, no circular paths, no open areas.
 * 
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Maze {
	// variable
    private final int r;
    private final int c;
    private Cell[][] maze;
    private final LinkedList<Cell> dfsGraph;

    /*
     * the construction of the Maze class
     */
    public Maze(int r, int c) {
        this.r = r;
        this.c = c;
        this.maze = new Cell[r][c];
        dfsGraph = new LinkedList<>();
        setUpMaze();
    }

    /*
     * method to set up the maze based on the number of row and column
     */
    private void setUpMaze() {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                Cell cell = new Cell(i, j, r, c);
                maze[i][j] = cell;
            }
        }
    }

    /*
     * method to start generate a perfect maze with no rooms or areas
     * that are completely blocked off from the rest of the maze.  
     */
    public Cell [][] generateMaze() {
    	// Start
        int totalCell = r * c;
        Cell current = maze[0][0];
        current.setVisited(true);
        int visitedCells = 1;

        while (visitedCells < totalCell) {
            int moves = checkNeighBor(current);

            if (moves != 0) {
                Cell next = getNextCell(moves, current);
                wallRemove(current, next);
                dfsGraph.add(current);
                current = next;
                visitedCells++;
            } else {
                dfsGraph.pop();
                current = dfsGraph.getFirst();
            }
        }
        return maze;
    }

    /**
     * method to remove the wall of the maze if some other path 
     * already connects the twos rooms on either side of the wall.
     * 
     * @param current
     * @param next
     */
    private void wallRemove(Cell current, Cell next) {
        int currentX = current.getX();
        int currentY = current.getY();

        int nextX = next.getX();
        int nextY = next.getY();

        if (nextX - currentX == 1) {
            current.setRightWall(false);
            next.setLeftWall(false);
        }
        if (nextX - currentX == -1) {
            current.setLeftWall(false);
            next.setRightWall(false);
        }
        if (nextY - currentY == 1) {
            current.setBottomWall(false);
            next.setTopWall(false);
        }
        if (nextY - currentY == -1) {
            current.setTopWall(false);
            next.setBottomWall(false);
        }
    }

    /**
     * Method to check the neighbor cell to if it is possible to move
     * or to remove.
     * 
     * @param current
     */
    private int checkNeighBor(Cell current) {
        ArrayList<int[]> moves = current.getPossibleMoves();
        for (int i = 0; i < moves.size(); i++) {
            int tempY = moves.get(i)[0];
            int tempX = moves.get(i)[1];
            if (maze[tempY][tempX].isVisited()) {
                current.getPossibleMoves().remove(i);
                i--;
            }
        }
        return current.getPossibleMoves().size();
    }

    /**
     * Method to generate unpredictable different cells
     * and it is selected randomly as candidates for removal.
     * 
     * @param moves
     * @param current
     */
    private Cell getNextCell(int moves, Cell current) {
        Random rd = new Random();
        int randomMove = rd.nextInt(moves);
        int tempY = current.getPossibleMoves().get(randomMove)[0];
        int tempX = current.getPossibleMoves().get(randomMove)[1];
        maze[tempY][tempX].setVisited(true);
        return maze[tempY][tempX];
    }

    /*
     * method to return the maze
     */
    public Cell[][] getMaze() {
        return maze;
    }
    
    /**
     * method for the setter of the maze
     * 
     * @param newMaze
     */
    public void setMaze(Cell [][] newMaze) {
    	maze = newMaze;
    }
    

//    public LinkedList<Cell> getDfsGraph() {
//		return dfsGraph;
//	}
    
    /*
     * method to display the maze with only one path 
     * between the starting room and the finishing room.
     */
	@Override
    public String toString() {
        String result = "";
        String top = "";
        String middle = "";
        String bottom = "";

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                top += (maze[i][j].isTopWall()) ? "+-" : "+ ";
                middle += (maze[i][j].isLeftWall()) ? "| " : "  ";
                if (j == c - 1) {
                    top += "+";
                    middle += maze[i][j].isRightWall() ? "|" :"";
                }
                if (i == r - 1) {
                    bottom += (maze[i][j].isBottomWall()) ? "+-" : "+ ";
                    if (j == c - 1) {
                        bottom += "+";
                    }
                }
            }
            result += top + "\n";
            result += middle + "\n";
            top = "";
            middle = "";
        }
        result += bottom;
        return result;
    }
}


