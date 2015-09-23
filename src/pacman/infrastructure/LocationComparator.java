package pacman.infrastructure;

import java.util.Comparator;

public class LocationComparator implements Comparator<LocationComparator>{

	private Maze mazey;
	public LocationComparator(Maze maze){
		mazey = maze;
	}

	public int compare(Location x, Location y){
		manhattan1 = Heuristic.manhattanDistance(x, mazey);
		manhattan2 = Heuristic.manhattanDistance(y, mazey);
		return manhattan1 - manhattan2;

	}


}