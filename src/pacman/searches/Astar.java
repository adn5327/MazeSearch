package pacman.searches;

import pacman.infrastructure.*;

import java.util.*;


public class Astar{


	public PriorityQueue<Location> frontier;
	public ArrayList<Location> visited;
	public int distance;
	public int numNodes;
	// Location[][] predecessors;
	public HashMap<Location, Location> predecessors;
	public char direction = 'r';
	public int turnCosts;
	public int forwardCosts;

	public Astar(Maze maze){
		frontier = new PriorityQueue<Location>(maze.width * maze.height, new AstarComparator(maze));
		visited = new ArrayList<Location>();
		// predecessors = new Location[maze.width][maze.height];
		predecessors = new HashMap<Location, Location>();
		//if the maze stores the goal, should the find solution even return a location?
		Location end = findSolution(maze);
		if(end != null) printSolution(maze, end);
		else distance = -1;
	}

	public Astar(Maze maze, int turnC, int forwardC){
		frontier = new PriorityQueue<Location>(maze.width * maze.height, new AstarComparator(maze));
		visited = new ArrayList<Location>();
		// predecessors = new Location[maze.width][maze.height];
		predecessors = new HashMap<Location, Location>();
		//if the maze stores the goal, should the find solution even return a location?
		Location end = findSolution(maze);
		if(end != null) printSolution(maze, end);
		else distance = -1;
		turnCosts = turnC;
		forwardCosts = forwardC;
	}

	//explores the maze to find the solution needed
	//returns the endPoint
	public Location findSolution(Maze maze){

		frontier.add(maze.getStart());
		visited.add(maze.getStart());
		numNodes = 0;
		while(!frontier.isEmpty()){
			Location cur = frontier.remove();
			setAstarHeuristics(maze, cur);
			ArrayList<Location> adjacents = cur.getAdjacent(maze);
			numNodes++;
			for(int i = 0; i<adjacents.size(); i++){
				Location temp = adjacents.get(i);
				if((temp.getClassifier() == ' ' || temp.getClassifier() == '.' || temp.getClassifier() == 'g' || temp.getClassifier() == 'G') && (!visited.contains(temp))){
					//account for ghost characters!!!! otherwise you might never find a solution
					//if it requires you to go through a ghosts location!!!!
					predecessors.put(temp,cur);
					frontier.add(temp);
					visited.add(temp);
					if(temp.isGoal(maze)) return temp;
				}
			}
		}
		return null;
	}


	//modifies the maze to actually put dots on the visited locations.
	//the array of predecessors is particularly helpful here
	public void printSolution(Maze maze, Location cur){
		distance = 0;

		while(predecessors.containsKey(cur)){
			distance++;
			cur = predecessors.get(cur);
			if(!cur.equals(maze.getStart()))
				cur.setClassifier('.');
		}
	}

	public int solutionForAstar(Maze maze, Location cur){
		int distanceAstar = 0;

		while(predecessors.containsKey(cur)){
			distanceAstar++;
			cur = predecessors.get(cur);
			if(!cur.equals(maze.getStart()))
				cur.setClassifier('.');
		}
		return distanceAstar;
	}

	public void setTurnHeuristics(Maze maze, Location loc){
		for(Location loccy : loc.getAdjacent(maze)){
			if(loccy.getClassifier() == ' ' || loccy.getClassifier() == '.' && !visited.contains(loccy)){
				loccy.setTurnHeuristic(new AstarHeuristic(loccy, maze, solutionForAstar(maze, loc), 1, solutionCost(maze, loccy)));
			}
		}
	}

	public void setAstarHeuristics(Maze maze, Location loc){
		for(Location loccy : loc.getAdjacent(maze)){
			if(loccy.getClassifier() == ' ' || loccy.getClassifier() == '.' && !visited.contains(loccy)){
				loccy.setHeuristic(new AstarHeuristic(loccy, maze, solutionForAstar(maze, loc), 0, 0));
				//0 = Manhattan
				//1 = Euclidean

			}
		}
	}

	public int solutionCost(Maze maze, Location loc){

		int curX = maze.getStart().getx();
		int curY = maze.getStart().gety();
		Location cur = maze.representation[curX][curY];
		int solutionCostVar =0;
		char destDirection = 'r';

		while(cur != loc){
			if(maze.isValid(curX+1, curY) && maze.representation[curX+1][curY].getClassifier() == '.'){
				destDirection = 'r';
				curX = curX +1;
			}
			else if(maze.isValid(curX, curY+1) && maze.representation[curX][curY+1].getClassifier() == '.'){
				destDirection = 'd';
				curY = curY+1;
			}
			else if(maze.isValid(curX-1, curY) && maze.representation[curX-1][curY].getClassifier() == '.'){
				destDirection = 'l';
				curX = curX -1;
			}
			else if(maze.isValid(curX, curY-1) && maze.representation[curX][curY-1].getClassifier() == '.'){
				destDirection = 'u';
				curY = curY -1;
			}
			cur.setClassifier('+');
			solutionCostVar = solutionCostVar + forwardCosts + howManyTurns(direction, destDirection)*turnCosts;
			cur = maze.representation[curX][curY];

		}
		return solutionCostVar;


	}

	public int ghostSolver(Maze maze){
		int curX = maze.getStart().getx();
		int curY = maze.getStart().gety();
		int ghostX = maze.getGhost().getx();
		int ghostY = maze.getGhost().gety();
		Location cur = maze.representation[curX][curY];
		int lastX;
		int lastY;
		int ghostCost = 0;
		while(cur != maze.getGoal()){
			lastX = curX;
			lastY = curY;
			if(maze.isValid(curX+1, curY) && maze.representation[curX+1][curY].getClassifier() == '.'){
				curX = curX +1;
			}
			else if(maze.isValid(curX, curY+1) && maze.representation[curX][curY+1].getClassifier() == '.'){
				curY = curY+1;
			}
			else if(maze.isValid(curX-1, curY) && maze.representation[curX-1][curY].getClassifier() == '.'){
				curX = curX -1;
			}
			else if(maze.isValid(curX, curY-1) && maze.representation[curX][curY-1].getClassifier() == '.'){
				curY = curY -1;
			}
			cur = maze.representation[curX][curY];
			ghostCost++;

			char ghostDir = moveGhost(ghostX, ghostY, maze);
			if(ghostDir == 'r') ghostX = ghostX+1;
			else if(ghostDir == 'l') ghostX = ghostX-1;
			else if(ghostDir == 'u') ghostY = ghostY-1;
			else if(ghostDir == 'd') ghostY = ghostY+1;

			if( (ghostX == curX && ghostY == curY) || (ghostX==lastX && ghostY == lastY) ){
				maze.representation[curX][curY].setClassifier('F');
				return ghostCost;
			}

		}
		return ghostCost;
	}
	public char moveGhost(int ghostX, int ghostY, Maze maze){
		if(maze.isValid(ghostX+1, ghostY) && (maze.representation[ghostX+1][ghostY].getClassifier() == 'g' || maze.representation[ghostX+1][ghostY].getClassifier() == 'G'))
			return 'r';
		else if(maze.isValid(ghostX-1, ghostY) && (maze.representation[ghostX-1][ghostY].getClassifier() == 'g' || maze.representation[ghostX-1][ghostY].getClassifier() == 'G'))
			return 'l';
		else if(maze.isValid(ghostX, ghostY+1) && (maze.representation[ghostX][ghostY+1].getClassifier() == 'g' || maze.representation[ghostX][ghostY+1].getClassifier() == 'G'))
			return 'd';
		else if(maze.isValid(ghostX, ghostY-1) && (maze.representation[ghostX][ghostY-1].getClassifier() == 'g' || maze.representation[ghostX][ghostY-1].getClassifier() == 'G'))
			return 'u';
		return 'r';
	}

	public int howManyTurns(char curDirection, char destDirection){
		//right to up or down is one turn - cost = 1*turnCost
		//left to up or down is one turn - cost = 1*turnCost
		//right to left OR left to right is two turns = 2*turn cost
		//up to right or left is one turn
		//down to right or left is one turn
		//up to down or down to up is 2 turns
		direction = destDirection;
		if(curDirection == 'r'){
			switch(destDirection){
				case 'l': return 2;
				case 'r': return 0;
				case 'd': return 1;
				case 'u': return 1;
			}
		}

		if(curDirection == 'l'){
			switch(destDirection){
				case 'l': return 0;
				case 'r': return 2;
				case 'd': return 1;
				case 'u': return 1;
			}
		}

		if(curDirection == 'u'){
			switch(destDirection){
				case 'l': return 1;
				case 'r': return 1;
				case 'd': return 2;
				case 'u': return 0;
			}
		}

		if(curDirection == 'd'){
			switch(destDirection){
				case 'l': return 1;
				case 'r': return 1;
				case 'd': return 0;
				case 'u': return 2;
			}
		}
		//should never reach this line
		return 0;
	}
}


