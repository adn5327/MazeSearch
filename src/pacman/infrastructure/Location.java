//Location.java
package pacman.infrastructure;

import java.util.*;

/*Location class should hold the following things:
 * x coord
 * y coord
 * classifier - 
 * 		% if it's a wall
 *		space if its an empty space
 * 		. if its a space that's been traversed
 *		g if its a goal state (or maybe not this)
 *		Is a character there??????? SHOULD THE CLASSIFIER CHECK THIS
 * Could we use enumerated types to manage these classifiers given that they're a fixed set of values?
 * this could make it a ton easier to read.
 * 
 *
 */

public class Location{

	private int x;
	private int y;
	private char classifier;
	// private Heuristic heuristic;

	public Location(int inx, int iny, char typey){
		x = inx;
		y = iny;
		classifier = typey;
	}

	public ArrayList<Location> getAdjacent(Maze maze){
		ArrayList<Location> listy = new ArrayList<Location>();
		if(maze.isValid(x+1, y) listy.add(maze.grid[x+1][y]);
		if(maze.isValid(x, y+1)) listy.add(maze.grid[x][y+1]);
		if(maze.isValid(x-1, y)) listy.add(maze.grid[x-1][y]);
		if(maze.isValid(x, y-1)) listy.add(maze.grid[x][y-1]);

		return listy;
	}

	// public Heuristic getHeuristic(){
	// 	return heuristic;
	// }

	public int getx(){
		return x;
	}
	public int gety(){
		return y;
	}
	public char getClassifier(){
		return classifier;
	}

	// public void setHeuristic(Heuristic h){
	// 	heuristic = h;
	// }

	public void setx(int setter){
		x = setter;
	}
	public void sety(int setter){
		y = setter;
	}
	public void setClassifier(char setter){
		classifier = setter;
	}
}