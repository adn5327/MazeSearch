package pacman;
import java.io.IOException;

import pacman.*;
import pacman.infrastructure.Maze;
import pacman.searches.*;


public class Main {
	public static void main(String[] args) throws IOException{
		// Maze small = new Maze("/home/dev/workspace/MazeSearch-master/mazes/smallTurns.txt");
		Maze big = new Maze("C:\\Users\\samir.agarwal\\Desktop\\MazeSearch\\mazes\\bigMaze.txt");
		//Maze open = new Maze("C:\\Users\\samir.agarwal\\Desktop\\MazeSearch\\mazes\\openMaze.txt");


		//System.out.println(maze);



	/*	System.out.println("DFS Search");
		DFS dfs = new DFS (maze);
		System.out.println (maze);

		System.out.println("Nodes Expanded: " + dfs.numNodes);
		System.out.println("Path cost: " + dfs.distance);

		System.out.println();
		DFS dfs1 = new DFS (big);
		System.out.println (big);

		System.out.println("Nodes Expanded: " + dfs1.numNodes);
		System.out.println("Path cost: " + dfs1.distance);

		System.out.println();
		DFS dfs2 = new DFS (open);
		System.out.println (open);

		System.out.println("Nodes Expanded: " + dfs2.numNodes);
		System.out.println("Path cost: " + dfs2.distance);*/
/*
		System.out.println("BFS Search");
		BFS bfs = new BFS (maze);
		System.out.println (maze);

		System.out.println("Nodes Expanded: " + bfs.numNodes);
		System.out.println("Path cost: " + bfs.distance);

		System.out.println();
		BFS bfs1 = new BFS (big);
		System.out.println (big);

		System.out.println("Nodes Expanded: " + bfs1.numNodes);
		System.out.println("Path cost: " + bfs1.distance);

		System.out.println();
		BFS bfs2 = new BFS (open);
		System.out.println (open);

		System.out.println("Nodes Expanded: " + bfs2.numNodes);
		System.out.println("Path cost: " + bfs2.distance);
		*/
		/*System.out.println("BFS Search");
		GreedyBestFirstSearch gbfs = new GreedyBestFirstSearch (maze);
		System.out.println (maze);

		System.out.println("Nodes Expanded: " + gbfs.numNodes);
		System.out.println("Path cost: " + gbfs.distance);

		System.out.println();
		GreedyBestFirstSearch gbfs1 = new GreedyBestFirstSearch (big);
		System.out.println (big);

		System.out.println("Nodes Expanded: " + gbfs1.numNodes);
		System.out.println("Path cost: " + gbfs1.distance);
1,
		System.out.println();
		GreedyBestFirstSearch gbfs2 = new GreedyBestFirstSearch (open);
		System.out.println (open);

		System.out.println("Nodes Expanded: " + gbfs2.numNodes);
		System.out.println("Path cost: " + gbfs2.distance);*/


		/*Astar gbfs = new Astar (maze);
		System.out.println (maze);

		System.out.println("Nodes Expanded: " + gbfs.numNodes);
		System.out.println("Path cost: " + gbfs.distance);

		System.out.println();
		Astar gbfs1 = new Astar (big);
		System.out.println (big);

		System.out.println("Nodes Expanded: " + gbfs1.numNodes);
		System.out.println("Path cost: " + gbfs1.distance);

		System.out.println();
		Astar gbfs2 = new Astar (open);
		System.out.println (open);

		System.out.println("Nodes Expanded: " + gbfs2.numNodes);
		System.out.println("Path cost: " + gbfs2.distance);*/


		/*System.out.println (small);
		Astar astar = new Astar (small, 1, 2);
		// Astar astar = new Astar (small);
		System.out.println (small);
		*/

		//System.out.println (big);
		Astar astar = new Astar (big, 2, 1);
		// Astar astar = new Astar (big);
		System.out.println (big);

		System.out.println("Nodes Expanded: " + astar.numNodes);
		System.out.println("Solution cost: " + astar.solutionCost(big, big.getGoal()));
		System.out.println (big);


		/*
		System.out.println("Nodes Expanded: " + astar.numNodes);
		System.out.println("Solution cost: " + astar.solutionCost(small, small.getGoal()));
		System.out.println (small);
		*/


		/*Astar astar1 = new Astar (small, 2, 1);
		System.out.println (small);

		System.out.println("Nodes Expanded: " + astar1.numNodes);
		System.out.println("Solution cost: " + astar1.solutionCost(small, small.getGoal()));*/


		/*System.out.println("BFS Search");
		BFS bfs = new BFS (small);
		System.out.println(small);*/
/*
		/*System.out.println("GreedyBestFirst Search");
		GreedyBestFirstSearch gbfs = new GreedyBestFirstSearch(open);
		System.out.println (open);*/

	/*System.out.println("AStar Search");
		Astar astar = new Astar (open);
		System.out.println (open);*/


	}
}

