package pacman.infrastructure;

import java.util.Comparator;

public class LocationComparator implements Comparator<Location>{

	private Maze mazey;
	public LocationComparator(Maze maze){
		mazey = maze;
	}

	public int compare(Location x, Location y){
		int manhattan1 = Heuristic.manhattanDistance(x, mazey);
		int manhattan2 = Heuristic.manhattanDistance(y, mazey);
		return manhattan1 - manhattan2;

	}


}