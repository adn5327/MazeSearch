package pacman.searches;

import pacman.infrastructure.*;

import java.util.*;


public class GreedyBestFirstSearch{

		
		PriorityQueue<Location> frontier;
		ArrayList<Location> visited;
		int distance;
		int numNodes;
		Location[][] predecessors;

		public GreedyBestFirstSearch(Maze maze){
			frontier = new PriorityQueue<Location>(maze.width * maze.height, new LocationComparator<Location>(maze));
			visited = new ArrayList<Location>();
			predecessors = new Location[maze.width][maze.height];
			//if the maze stores the goal, should the find solution even return a location?
			Location end = findSolution(maze);
			if(end != null) printSolution(Maze maze);
			else distance = -1;
		}

		//explores the maze to find the solution needed
		//returns the endPoint
		public Location findSolution(Maze maze){

			froniter.add(maze.getStart());
			visited.add(maze.getStart());
			nodesExpanded = 0;
			while(!froniter.isEmpty()){
				Location cur = frontier.remove();
				ArrayList<Location> adjacents = cur.getAdjacent(maze);
				numNodes++;
				for(int i = 0; i<adjacents.size(); i++){
					Location temp = adjacents.get(i);
					if(temp.getClassifier() == ' ' || temp.getClassifier() == '.' && !visisted.contains(temp)){
						predecessors[temp.getx()][temp.gety()] = cur;
						frontier.add(temp);
						visited.add(temp);
						if(temp == maze.getGoal) return temp;
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
				maze.grid[curX][curY].setClassifier('.');
				curX = predecessors[curX][curY].getx();
				curY = predecessors[curX][curY].gety();
			}
		}



}
