package pacman.searches;

import java.util.*;
import pacman.infrastructure.*;

public class DFS{

		
		Stack<Location> frontier;
		ArrayList<Location> visited;
		public int distance;
		public int numNodes;
		// Location[][] predecessors;
		HashMap<Location, Location> predecessors;

		public DFS(Maze maze){
			frontier = new Stack<Location>();
			visited = new ArrayList<Location>();
			// predecessors = new Location[maze.width][maze.height];
			predecessors = new HashMap<Location, Location>();
			//if the maze stores the goal, should the find solution even return a location?
			Location end = findSolution(maze);
			if(end != null) printSolution(maze, end);
			else distance = -1;
		}

		//explores the maze to find the solution needed
		//returns the endPoint
		public Location findSolution(Maze maze){

			frontier.push(maze.getStart());
			visited.add(maze.getStart());
			numNodes = 0;
			while(!frontier.isEmpty()){
				Location cur = frontier.pop();
				ArrayList<Location> adjacents = cur.getAdjacent(maze);
				numNodes++;
				for(int i = 0; i<adjacents.size(); i++){
					Location temp = adjacents.get(i);
					if((temp.getClassifier() == ' ' || temp.getClassifier() == '.') && (!visited.contains(temp))){
						predecessors.put(temp, cur);
						frontier.push(temp);
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


}
