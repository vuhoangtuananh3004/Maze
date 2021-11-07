package mazeSolver;

/*
 * BreathFirstSearch Class:
 * 	This class uses the breath-first search to solve the maze.
 * 
 * 	It will find a path from the starting room to the finishing room.
 * 
 * 	It then returns and displays the breath-first search solution.
 * 
 */

import generateMaze.*;

import java.util.LinkedList;
import java.util.Queue;


public class BreathFirstSearch extends MazeSolver{
	// variables
    private final Cell[][] maze;
    private final LinkedList<Cell> bfs = new LinkedList<>();
    private final LinkedList<Cell> path = new LinkedList<>();
    private final int endPointY;
    private final int endPointX;

    /*
     * The construction for BreathFirstSearch class
     */
    public BreathFirstSearch(Cell[][] maze) {
        this.maze = maze;
        this.endPointY = maze.length - 1;
        this.endPointX = maze[0].length - 1;
        setAsDefault(maze, maze.length, maze[0].length);
       
    }

    /**
     * Method to find the path for the maze using the breath-first search.
     * 
     * @param startX
     * @param startY
     */
    public void bfsFindPath(int startX, int startY){
        markCell(maze[startY][startX]);
        bfs.push(maze[startY][startX]);

        Queue<Cell> loop = new LinkedList<>();
        loop.add(maze[0][0]);

        while(!loop.isEmpty()){
            Cell current = loop.remove();
            checkCellNeighBor(current, endPointY, endPointX);
            if (current.getY() == endPointY && current.getX() == endPointX){
                break;
            }
            for (int i = 0; i < current.getPossibleMoves().size(); i++){
                Cell next = maze[current.getPossibleMoves().get(i)[0]][current.getPossibleMoves().get(i)[1]];
                if (!next.isVisited()){
                    markCell(next);
                    loop.add(next);
                    bfs.push(next);
                }
            }
        }

        findShortestPath(bfs, path, endPointY, endPointX);
    }

    /*
     * Method to display the solution including the maze,
     * breath-first search path, and the order that rooms are visited
     * by the algorithm.
     */
    public void display(){
        display(bfs, path, maze);
    }

    /*
     * method to return the bfs (breath-first search)
     */
	public LinkedList<Cell> getBfs() {
		return bfs;
	}

	/*
	 * method to return the path of the maze, which is solving by the BreathFirstSearch
	 */
	public LinkedList<Cell> getPath() {
		return path;
	}
    
    
    
    
}
