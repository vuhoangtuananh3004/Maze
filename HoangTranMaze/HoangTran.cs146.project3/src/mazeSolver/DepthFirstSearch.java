package mazeSolver;

/*
 * DepthFirstSearch Class:
 * 	This class uses the depth-first search to solve the maze.
 * 
 * 	It will find a path from the starting room to the finishing room.
 * 
 * 	It then returns and displays the depth-first search solution.
 * 
 */

import java.util.*;

import generateMaze.Cell;

public class DepthFirstSearch extends MazeSolver {
	// variables
    private final Cell [][] maze;
    private final LinkedList<Cell> dfs = new LinkedList<>();
    private final LinkedList<Cell> path = new LinkedList<>();
    private final int endPointY;
    private final int endPointX;
    private int counter = 0;

    /*
     * the construction for the DepthFirstSearch Class
     */
    public DepthFirstSearch(Cell[][] temp) {
        this.maze = temp;
        this.endPointY = maze.length - 1;
        this.endPointX = maze[0].length - 1;
        setAsDefault(maze, maze.length, maze[0].length);
    }

    /**
     * This method will use the depth-first search to solve the maze 
     * and find the path.
     * 
     * @param startX
     * @param startY
     */
    public void dfsFindPath(int startX, int startY){
        markCell(maze[startY][startX]);
        dfs.push(maze[startY][startX]);

        dfsRecursive(maze[startY][startX]);
        findShortestPath(dfs, path, endPointY, endPointX);
    }
    
    /**
     * method for the recursion of the depth-first search
     * 
     * @param cell
     */
    private void dfsRecursive(Cell cell) {
        if (cell.getY() == endPointY && cell.getX() == endPointX) {
            checkCellNeighBor(cell, endPointY, endPointX);
            counter++;
            return;
        }else {
            checkCellNeighBor(cell, endPointY, endPointX);
            for (int i = 0; i < cell.getPossibleMoves().size(); i++){
                Cell next = maze[cell.getPossibleMoves().get(i)[0]][cell.getPossibleMoves().get(i)[1]];
                if (!next.isVisited() && counter != 1){
                    markCell(next);
                    dfs.push(next);
                    dfsRecursive(next);
                }
            }
        }
    }
    
    /*
     * method to display the depth-first solution, order of visited rooms,
     * and the maze.
     */
    public void display(){
        display(dfs, path, maze);
    }

    /*
     * method to return the dfs (depth-first search)
     */
	public LinkedList<Cell> getDfs() {
		return dfs;
	}

	/*
	 * method to return the path of the maze that been solved by the depth-first search 	
	 */
	public LinkedList<Cell> getPath() {
		return path;
	}
    
}

