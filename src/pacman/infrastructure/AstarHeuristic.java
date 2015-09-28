package pacman.infrastructure;

import java.util.*;

public class AstarHeuristic{


	private int astardist;

	public AstarHeuristic(Location loc, Maze maze, int distToCur, int type){

		if(type == 0)
			astardist = Heuristic.manhattanDistance(loc, maze) + distToCur;
		else
			astardist = Heuristic.euclideanDistance(loc, maze) + distToCur;
		
		//added "type" param to specify type of heuristic to use

		//NEEDS TO BE THIS + printSolution(maze, CURRENT POINT) -- that will get us the distance to this point.
		//This needs to be implemented as a property of the LOCATION CLASS
		//heuristic will need to be set as a property during the FIND SOLUTION METHOD
		//THIS NEEDS TO BE DONE ****BEFORE**** adding anything do the queue.

	}


	public void updateDist(int x){
		astardist += x;

	}

	public int getDist(){
		return astardist;
	}
}