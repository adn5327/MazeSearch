package pacman.infrastructure;

public class Heuristic{

	public static int manhattanDistance(Location loc, Maze maze){
		return Math.abs(loc.getx()- maze.getGoal().getx()) + Math.abs(loc.gety() - maze.getGoal().gety());
	}
	
	public static int euclideanDistance(Location loc, Maze maze){
		int dx = loc.getx()- maze.getGoal().getx();
		int dy = loc.gety() - maze.getGoal().gety();
		return (int) Math.sqrt((dx*dx) + (dy*dy));
	}

	
}