package pacman.infrastructure;

import java.util.Comparator;

public class AstarComparator implements Comparator<Location>{
	

	private Maze mazey;

	public AstarComparator(Maze maze){
		mazey = maze;
	}

	public int compare(Location x, Location y){
		int astar1 = x.getHeuristic().getDist();
		int astar2 = y.getHeuristic().getDist();
		return astar1 - astar2;
	}
}