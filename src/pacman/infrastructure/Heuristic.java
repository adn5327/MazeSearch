package pacman.infrastructure;

public class Heuristic{

	public static int manhattanDistance(Location loc, Maze maze){
		return Math.abs(loc.getx()- maze.getGoal().getx()) + Math.abs(loc.gety() - maze.getGoal().gety());
	}

}