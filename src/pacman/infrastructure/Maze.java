// Maze.java
package pacman.infrastructure;


//change the wildcards later so as to not include the whole library.
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class Maze{

	public Location[][] representation; 
	public int width = 0;
	public int height = 0;

	private Location start;
	private Location goal;

	/*
	 * current plan is to have the location class
	 * hold the information as to if a point is a goal
	Location goal;
	 * Alternatively, this could be done to house the goal
	 * point within the maze itself.
	 */

	public Maze(String filename) throws IOException{
		//we expect that the filename will include the file extension, which is .txt
		Path fileyPath = Paths.get(filename);
		try(BufferedReader reader = Files.newBufferedReader(fileyPath)){
			getNumRows(reader);
		}
		catch(IOException e){
			System.out.println("Uh-oh. There was an IOException when reading the input file.");
			throw new IOException();
		}

		representation = new Location[width][height];
		
		try(BufferedReader reader = Files.newBufferedReader(fileyPath)){
			populateMaze(reader);
		}
		catch(IOException e){
			System.out.println("Uh-oh. There was an IOException when trying to build the maze itself.");
			throw new IOException();
		}
		// COULD DO SOMETHING LIKE THIS if you don't want to use Paths Library and a try catch block.
		// Use this method if you do not want to use the nio library.
		// File filey = new File(filename);
		// if(!filey.exists())
		// 	throw new IOException("File not found or could not be opened. Please try again.");
		// BufferedReader reader = new BufferedReader()


	}

	public void populateMaze(BufferedReader reader) throws IOException{
		String line = reader.readLine();
		int rowNum = 0;
		while(line != null){

			for(int i = 0; i<width; i++){
				//will need to count number of dots if we choose to do part two
				//also will need to implement if a character is in a spot
				char curChar = line.charAt(i);
				representation[i][rowNum] = new Location(i, rowNum, curChar);
				if(curChar == 'P') start = representation[i][rowNum];
				if(curChar == '.') goal = representation[i][rowNum];
			}
			rowNum++;
			line = reader.readLine();
		}
	}

	public void getNumRows(BufferedReader reader) throws IOException{

		String line = reader.readLine();
		

		//The if statement with the while loop inside it is structured as such
		//to improve the latency of the loop.
		//Executing the if statement on every single row of the array could 
		//decrease performance.
		if(line != null){
			height++;
			line = reader.readLine();
			width = line.length();
			while(line != null){
				height++;
				reader.readLine();
			}
		}
	}

	public boolean isValid(int x, int y){
		if(x >=0 && x< width && y>= 0 && y<height) return true;
		return false;
	}
	public String toString(){

		StringBuilder sb = new StringBuilder();
		for(int rowNum = 0; rowNum <height; rowNum++){
			for(int columnNum = 0; columnNum<width; columnNum++){
				sb.append(representation[columnNum][rowNum]);
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();

	}

	public Location getStart(){
		return start;
	}
	public Location getGoal(){
		return goal;
	}

}

