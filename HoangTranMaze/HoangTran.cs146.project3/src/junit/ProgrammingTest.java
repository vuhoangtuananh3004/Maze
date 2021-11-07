package junit;

/*
 * Project 3 - Maze or Labyrinth Generator and Solver
 * 
 * 	A program that will automatically generate and solve mazes.
 * 	This program will use depth-first search (DFS) and breadth-first search (BFS)
 * 		to generate and print a new random maze and the solution every time 
 * 		running the program.
 * 
 * Anh Hoang, Thuy Tran, November 2, 2021
 */

import static org.junit.jupiter.api.Assertions.*;


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import generateMaze.Cell;
import generateMaze.Maze;
import mazeSolver.BreathFirstSearch;
import mazeSolver.DepthFirstSearch;
import mazeSolver.MazeSolver;

class ProgrammingTest {

	// variables
	static BreathFirstSearch breathFirstSearch;
	static DepthFirstSearch depthFirstSearch;
	static MazeSolver mazeSolver;
	static Maze getMaze;
	static Cell[][] maze;
	static LinkedList<Cell> bfs;
	static LinkedList<Cell> dfs;
    static LinkedList<Cell> path;

    /*
     * JUnit test to test if the solution from the breath-first search and depth-first search
     * is correct or not. This method will test for all of the cases with generating the maze
     * and solving the maze.
     */
	@Test
	public void specificCaseTest() {
		int rows = 2;
		int cols = 2;
		bfs = new LinkedList<Cell>();
		path = new LinkedList<Cell>();
		
		//TODO: Test Case Maze size: 2 x 2
		getMaze = new Maze(rows, cols);
		maze = getMaze.getMaze();

		maze[0][0].setTopWall(false);
		maze[0][0].setRightWall(false);
		maze[0][1].setLeftWall(false);
		maze[0][1].setBottomWall(false);
		maze[1][1].setLeftWall(false);
		maze[1][1].setTopWall(false);
		maze[1][1].setBottomWall(false);
		maze[1][0].setRightWall(false);
		
		String mazeToString = 
				  "+ +-+\n"
				+ "|   |\n"
				+ "+-+ +\n"
				+ "|   |\n"
				+ "+-+ +";
		
		assertTrue(mazeToString.equals(getMaze.toString()));
		
		getMaze.setMaze(maze);
		System.out.println("\n\n********** Maze size: 2 x 2 **********");
		
/*		Test Breath First Search 	*/
		
		breathFirstSearch = new BreathFirstSearch(maze);
		breathFirstSearch.bfsFindPath(0, 0);
//		BFS should be (0,0)(0,1)(1,1)
		assertTrue(Arrays.equals(new int [] {0,0}, breathFirstSearch.getBfs().get(2).getCoordinate()));
		assertTrue(Arrays.equals(new int [] {0,1}, breathFirstSearch.getBfs().get(1).getCoordinate()));
		assertTrue(Arrays.equals(new int [] {1,1}, breathFirstSearch.getBfs().get(0).getCoordinate()));
		
//		Path should be equal to bfs
		path = breathFirstSearch.getPath();
		Collections.reverse(path);
		bfs = breathFirstSearch.getBfs();
		boolean compareList = path.equals(bfs);
		assertTrue(compareList);
		
//		Print out
		System.out.println("BFS: ");
		breathFirstSearch.display();
		
/*		Test Depth First Search 	*/
		depthFirstSearch = new DepthFirstSearch(maze);
		depthFirstSearch.dfsFindPath(0, 0);
//		DFS should be (0,0)(0,1)(1,1)
		assertTrue(Arrays.equals(new int [] {0,0}, depthFirstSearch.getDfs().get(2).getCoordinate()));
		assertTrue(Arrays.equals(new int [] {0,1}, depthFirstSearch.getDfs().get(1).getCoordinate()));
		assertTrue(Arrays.equals(new int [] {1,1}, depthFirstSearch.getDfs().get(0).getCoordinate()));
		
//		Path should be equal to Dfs
		path = depthFirstSearch.getPath();
		Collections.reverse(path);
		dfs = depthFirstSearch.getDfs();
		compareList = path.equals(dfs);
		assertTrue(compareList);
		
//		Print out
		System.out.println("\nDFS: ---------");
		depthFirstSearch.display();
		
		//TODO: Test Case Maze size: 4 x 4
		rows = 4;
		cols = 4;
		
		getMaze = new Maze(rows, cols);
		maze = getMaze.getMaze();
		
		maze[0][0].setTopWall(false);
		maze[0][0].setRightWall(false);
		maze[0][1].setLeftWall(false);
		maze[0][1].setBottomWall(false);
		maze[0][2].setRightWall(false);
		maze[0][3].setLeftWall(false);
		maze[0][3].setBottomWall(false);
		
		maze[1][0].setBottomWall(false);
		maze[1][1].setTopWall(false);
		maze[1][1].setRightWall(false);
		maze[1][2].setLeftWall(false);
		maze[1][2].setRightWall(false);
		maze[1][3].setLeftWall(false);
		maze[1][3].setTopWall(false);
		maze[1][3].setBottomWall(false);
		
		maze[2][0].setTopWall(false);
		maze[2][0].setRightWall(false);
		maze[2][0].setBottomWall(false);
		maze[2][1].setLeftWall(false);
		maze[2][1].setRightWall(false);
		maze[2][2].setLeftWall(false);
		maze[2][2].setRightWall(false);
		maze[2][3].setTopWall(false);
		maze[2][3].setLeftWall(false);
		
		maze[3][0].setTopWall(false);
		maze[3][0].setRightWall(false);
		maze[3][1].setLeftWall(false);
		maze[3][1].setRightWall(false);
		maze[3][2].setLeftWall(false);
		maze[3][2].setRightWall(false);
		maze[3][3].setLeftWall(false);
		maze[3][3].setBottomWall(false);
		
		getMaze.setMaze(maze);
		
		System.out.println("\n\n********** Maze size: 3 x 3 **********");
		
/*		Test Breath First Search 	*/
		
		breathFirstSearch = new BreathFirstSearch(maze);
		breathFirstSearch.bfsFindPath(0, 0);
//		BFS should be (0,0)(0,1)(1,1)(1,2)(1,3)(0,3)(2,3)(0,2)(2,2)(2,1)(2,0)(1,0)(3,0)(3,1)(3,2)(3,3)
		
		assertTrue(Arrays.equals(new int [] {0,0}, breathFirstSearch.getBfs().get(15).getCoordinate())); // start point
		assertTrue(Arrays.equals(new int [] {3,3}, breathFirstSearch.getBfs().get(0).getCoordinate()));  // end point
		assertTrue(Arrays.equals(new int [] {2,3}, breathFirstSearch.getBfs().get(9).getCoordinate()));  // at index  9 (at corner)
		assertTrue(Arrays.equals(new int [] {1,3}, breathFirstSearch.getBfs().get(11).getCoordinate()));  // at index  11 (go up from corner)
		assertTrue(Arrays.equals(new int [] {0,3}, breathFirstSearch.getBfs().get(10).getCoordinate()));  // at index  10 (go down from corner)
		
//		Path shouldn't be equal to bfs --> Path (0,0)(0,1)(1,1)(1,2)(1,3)(2,3)(2,2)(2,1)(2,0)(3,0)(3,1)(3,2)(3,3)
		LinkedList<Cell> pathBfs = breathFirstSearch.getPath();
		Collections.reverse(pathBfs);
		bfs = breathFirstSearch.getBfs();
		compareList = pathBfs.equals(bfs);
		assertFalse(compareList);
		
//		Print out
		System.out.println("BFS: ");
		breathFirstSearch.display();
		
/*		Test Depth First Search 	*/
		depthFirstSearch = new DepthFirstSearch(maze);
		depthFirstSearch.dfsFindPath(0, 0);
//		DFS should be (0,0)(0,1)(1,1)(1,2)(1,3)(0,3)(0,2)(2,3)(2,2)(2,1)(2,0)(1,0)(3,0)(3,1)(3,2)(3,3)

		assertTrue(Arrays.equals(new int [] {0,0}, depthFirstSearch.getDfs().get(15).getCoordinate())); // start point
		assertTrue(Arrays.equals(new int [] {3,3}, depthFirstSearch.getDfs().get(0).getCoordinate()));  // end point
		
//		Path shouldn't equal to Dfs
		LinkedList<Cell> pathDfs = depthFirstSearch.getPath();
		Collections.reverse(pathDfs);
		dfs = depthFirstSearch.getDfs();
		compareList = pathDfs.equals(dfs);
		assertFalse(compareList);
		
//		PathDfs equal pathBfs
		compareList = pathBfs.equals(pathDfs);
		assertTrue(compareList);
		
//		Print out
		System.out.println("\nDFS: ---------");
		depthFirstSearch.display();
		
		//TODO: Test Case Maze size: 6 x 6, and random maze.
		rows = 6;
		cols = 6;
		
		getMaze = new Maze(rows, cols);
		maze = getMaze.generateMaze();
		
		System.out.println("\n\n********** Maze random size: 6 x 6 **********");
		
/*		Test Breath First Search 	*/
		
		breathFirstSearch = new BreathFirstSearch(maze);
		breathFirstSearch.bfsFindPath(0, 0);

//		Get ShortestPath, and All Path of visited Cell.
	    pathBfs = breathFirstSearch.getPath();
		Collections.reverse(pathBfs);
		bfs = breathFirstSearch.getBfs();

		
		
/*		Test Depth First Search 	*/
		depthFirstSearch = new DepthFirstSearch(maze);
		depthFirstSearch.dfsFindPath(0, 0);

//		Get ShortestPath, and All Path of visited Cell.
		pathDfs = depthFirstSearch.getPath();
		Collections.reverse(pathDfs);
		dfs = depthFirstSearch.getDfs();

//		Check result from both BFS and DFS.
		assertTrue(pathDfs.equals(pathBfs));
		
//		Print out BFS && DFS
		System.out.println("BFS: ");
		breathFirstSearch.display();
		System.out.println("\nDFS: ---------");
		depthFirstSearch.display();
		
	}


}
