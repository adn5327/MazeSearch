package pacman.searches;

import pacman.infrastructure.*;

import java.util.*;


public class Astar{

		
		PriorityQueue<Location> frontier;
		ArrayList<Location> visited;
		int distance;
		int numNodes;
		Location[][] predecessors;

		public Astar(Maze maze){
			frontier = new PriorityQueue<Location>(maze.width * maze.height, new AstarComparator(maze));
			visited = new ArrayList<Location>();
			predecessors = new Location[maze.width][maze.height];
			//if the maze stores the goal, should the find solution even return a location?
			Location end = findSolution(maze);
			if(end != null) printSolution(maze);
			else distance = -1;
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
					if(temp.getClassifier() == ' ' || temp.getClassifier() == '.' && !visited.contains(temp)){
						predecessors[temp.getx()][temp.gety()] = cur;
						frontier.add(temp);
						visited.add(temp);
						if(temp == maze.getGoal()) return temp;
					}
				}
			}
			return null;
		}


		//modifies the maze to actually put dots on the visited locations.
		//the array of predecessors is particularly helpful here
		public void printSolution(Maze maze){
			int curX = maze.getGoal().getx();
			int curY = maze.getGoal().gety();			
			distance = 0;

			while(curX != maze.getStart().getx() && curY != maze.getStart().gety()){
				distance++;
				maze.representation[curX][curY].setClassifier('.');
				curX = predecessors[curX][curY].getx();
				curY = predecessors[curX][curY].gety();
			}
		}

		public int solutionForAstar(Maze maze, Location loc){
			int curX = loc.getx();
			int curY = loc.gety();
			distance = 0;

			while(curX != maze.getStart().getx() && curY != maze.getStart().gety()){
				distance++;
				curX = predecessors[curX][curY].getx();
				curY = predecessors[curX][curY].gety();
			}
			return distance;
		}

		public void setAstarHeuristics(Maze maze, Location loc){
			for(Location loccy : loc.getAdjacent(maze)){
				if(loccy.getClassifier() == ' ' || loccy.getClassifier() == '.' && !visited.contains(loccy)){
					loccy.setHeuristic(new AstarHeuristic(loc, maze, solutionForAstar(maze, loccy)));
				}
			}
		}
}

