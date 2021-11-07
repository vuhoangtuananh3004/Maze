package generateMaze;

/*
 * Cell Class:
 * 	to create new walls for the maze including the leftWall, rightWall, topWall, bottonWall
 * 		based on the number of rows and columns
 * 	
 */

import java.util.ArrayList;

public class Cell {
	// variables
    private final int x;
    private final int y;
    private String color = "white";
    private final int rows;
    private final int cols;
    private boolean visited = false;
    private boolean leftWall = true;
    private boolean rightWall = true;
    private boolean topWall = true;
    private boolean bottomWall = true;
    ArrayList<int[]> possibleMoves = new ArrayList<>();

    /*
     * the construction for Cell Class
     */
    public Cell(int y, int x, int rows, int cols) {
        this.x = x;
        this.y = y;
        this.rows = rows;
        this.cols = cols;
        inititalMove();
    }
    
    /*
     * this method is used to initial all the moves within the maze
     */
    private void inititalMove(){
        if (x - 1 >= 0) possibleMoves.add(new int[]{y, x - 1} );
        if (x + 1 <= cols -1 ) possibleMoves.add( new int[]{y, x + 1});
        if (y - 1 >= 0) possibleMoves.add(new int[]{y - 1, x}) ;
        if (y + 1 <= rows - 1) possibleMoves.add(new int[]{y +1, x});
        //set wall
        if (x == 0 && y == 0) setTopWall(false);
        if (x == cols - 1 && y == rows -1) setBottomWall(false);
    }

    /*
     * method to return the possible moves of the maze
     */
    public ArrayList<int[]> getPossibleMoves() {
        return possibleMoves;
    }

    /*
     * return the left wall of the maze
     */
    public boolean isLeftWall() {
        return leftWall;
    }

    /**
	 * setter for the left wall of the maze
	 * 
	 * @param leftWall
     */
    public void setLeftWall(boolean leftWall) {
        this.leftWall = leftWall;
    }

    /*
     * return the right wall of the maze
     */
    public boolean isRightWall() {
        return rightWall;
    }

    /**
     * setter for the right wall of the maze
     * 
     * @param rightWall
     */
    public void setRightWall(boolean rightWall) {
        this.rightWall = rightWall;
    }

    /*
     * return the top wall of the maze
     */
    public boolean isTopWall() {
        return topWall;
    }

    /**
     * setter for the top wall of the maze
     * 
     * @param topWall
     */
    public void setTopWall(boolean topWall) {
        this.topWall = topWall;
    }

    /*
     * return the bottom wall of the maze
     */
    public boolean isBottomWall() {
        return bottomWall;
    }

    /**
     * setter for the bottom wall of the maze
     * 
     * @param bottomWall
     */
    public void setBottomWall(boolean bottomWall) {
        this.bottomWall = bottomWall;
    }

    /*
     * return the visited cell of the maze
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * setter for the visited cell of the maze
     * 
     * @param visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /*
     * return x
     */
    public int getX() {
        return x;
    }

    /*
     * return y
     */
    public int getY() {
        return y;
    }
    
    /*
     * return the path coordinate in the arrayList
     */
    public int[] getCoordinate() {
		return new int [] {y,x};
	}

    /*
     * set possibleMoves with a new ArrayList
     * set setVisited as false
     */
	public void setAsDefault() {
        possibleMoves = new ArrayList<>();
        setVisited(false);
    }
	
	/**
	 * setter for color of the maze
	 * 
	 * @param color
	 */
    public void setColor(String color) {
        this.color = color;
    }
    
}
