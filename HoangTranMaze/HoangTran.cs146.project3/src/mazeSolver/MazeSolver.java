package mazeSolver;

/*
 * MazeSolve Class: 
 * 	A class that will find the point (y,x) of the path for the maze.
 * 
 * 	It will also determine the length of the path and the visited cell
 * 		from the algorithm solution.
 * 
 * 	Lastly, it will print the maze as ASCII, the path as point (y,x),
 * 		the length of the path, and the visited cell when solving
 * 		the maze.
 * 
 */

import generateMaze.*;

import java.util.ArrayList;

import java.util.Collections;
import java.util.LinkedList;

public class MazeSolver {
	/**
	 * Method to set the cell based on row and column as default.
	 * 
	 * @param temp
	 * @param row
	 * @param col
	 */
    protected Cell[][] setAsDefault(Cell[][] temp, int row, int col){
        for (int r = 0; r < row; r++ ){
            for (int c = 0; c < col; c++){
                temp[r][c].setAsDefault();
            }
        }
        return temp;
    }
    
    /**
     * method to check for the cell neighbor of the maze
     * 
     * @param cell
     * @param endPointY
     * @param endPointX
     */
    protected void checkCellNeighBor(Cell cell, int endPointY, int endPointX){
        if (!cell.isLeftWall()) cell.getPossibleMoves().add(new int[]{cell.getY(), cell.getX() - 1} );
        if (!cell.isRightWall()) cell.getPossibleMoves().add( new int[]{cell.getY(), cell.getX() + 1});
        if (!cell.isTopWall())
        {
            if ( cell.getY() != 0 || cell.getX() != 0) {
                cell.getPossibleMoves().add(new int[]{cell.getY() - 1, cell.getX()});
            }
        }
        if (!cell.isBottomWall())
        {
            if ( cell.getY() != endPointY || cell.getX() != endPointX){
                cell.getPossibleMoves().add(new int[]{cell.getY() +1, cell.getX()});
            }
        }
    }
    
    /**
     * method to mark what cell is visited and the color of the cell
     * 
     * @param cell 
     */
    protected void markCell (Cell cell){
        cell.setVisited(true);
        cell.setColor("red");
    }
    
    /**
     * method to find the path as points (y,x)
     * 
     * @param method
     * @param path
     * @param endPointY
     * @param endPointX
     */
    protected void findShortestPath(LinkedList<Cell> method, LinkedList<Cell> path, int endPointY, int endPointX){
        Cell temp = null;
        int startIndex = 0;
        for (int i = 0; i < method.size(); i++){
                if (method.get(i).getY() == endPointY && method.get(i).getX( ) == endPointX){
                    temp = method.get(i);
                    startIndex = i;
                }
        }
        path.push(temp);
        for (int i = 0; i < method.size(); i++){
            if (i > startIndex) {
                for (int j = 0; j < temp.getPossibleMoves().size(); j++){
                    int tempX = temp.getPossibleMoves().get(j)[1];
                    int tempY = temp.getPossibleMoves().get(j)[0];

                    int dfsX = method.get(i).getX();
                    int dfsY = method.get(i).getY();

                    if (tempX == dfsX && tempY == dfsY){
                        path.push(method.get(i));
                        temp = method.get(i);
                    }
                }
            }
        }
    }
    
    /**
     * Method to display the point (y,x) of the path, the length of the path, 
     * 	and the total number of cell visited for every random maze.
     * 
     * The maze will also be printed in ASCII using bar '|' and dash '-'
     * 	characters to represent walls, '+' for corners, and space characters
     *  for rooms and removed walls.
     *  
     *  @param method
     *  @param path
     *  @param maze
     */
    protected void display(LinkedList<Cell> method, LinkedList<Cell> path, Cell[][] maze){
        // create a new ArrayList for the coordinates of the path
    	ArrayList<int []> pathCoordinate = new ArrayList<>();
        for (Cell temp: path){
            int [] coordinate = new int[]{temp.getY(), temp.getX()};
            pathCoordinate.add(coordinate);
        }
        
        // create a new ArrayList for the coordinates of the method
        ArrayList<int []> methodCoordinate = new ArrayList<>();
        Collections.reverse(method);
        for (Cell temp: method){
            int [] coordinate = new int[]{temp.getY(), temp.getX()};
            methodCoordinate.add(coordinate);
        }
        for (int i = 0; i < 2; i++){
            String result = "";
            String top = "";
            String middle = "";
            String bottom = "";
            for (int r = 0; r < maze.length; r++){
                for (int c = 0; c < maze[0].length; c++){
                    String order = (symbol(pathCoordinate,r,c));
                    String index = (index(methodCoordinate,r,c));
                    String symbol = (i == 0) ? order : index;
                    if ( r == 0 ){
                        top += (maze[r][c].isTopWall()) ? "+-" : "+ ";
                        top +=  (c == maze[0].length - 1) ? "+":"";
                    }
                    middle += (maze[r][c].isLeftWall()) ? "|" + symbol : " " + symbol;
                    bottom += (maze[r][c].isBottomWall()) ? "+-" : "+ ";

                    if (c == maze[0].length - 1) {
                        middle += maze[r][c].isRightWall() ? "|" :"";
                        bottom += "+";
                    }
                }
                if (r == 0) result += top + "\n";

                result += middle + "\n";
                result += bottom + "\n";
                middle = "";
                bottom = "";
            }
            System.out.println(result);
        }
        
        // print the output 
        System.out.print("Path (y, x): ");
        for (int [] temp: pathCoordinate){
            System.out.print(" (" + temp[0] + "," + temp[1] +")");
        }
        System.out.println("\nLength of path: " + path.size());
        System.out.println("Visited Cell: " + method.size());
    }
    
    /**
     * method to use hash '#' character for rooms and wall openings on the solution path.
     * 
     * @param temp
     * @param r
     * @param c
     */
    private String symbol(ArrayList<int[]> temp, int r, int c){
        for (int []arr : temp){
            if ( r == arr[0] && c == arr[1]) return "#";
        }
        return " ";
    }
    
    /**
     * method to find the index of the maze
     * 
     * @param temp
     * @param r
     * @param c
     */
    private String index (ArrayList<int[]> temp, int r, int c){
        for (int i = 0; i < temp.size(); i++){
            if (r == temp.get(i)[0] && c == temp.get(i)[1]){
                int tenth = (int) Math.round(Math.floor(i/10) * 10);
                return String.valueOf(i - tenth);
            }
        }
        return " ";
    }
}
