//Location.java
package pacman.infrastructure;

import java.util.*;

/*Location class should hold the following things:
 * x coord
 * y coord
 * classifier - 
 * 		% if it's a wall
 *		space if its an empty space
 * 		. if its a space that's been traversed
 *		g if its a goal state (or maybe not this)
 *		Is a character there??????? SHOULD THE CLASSIFIER CHECK THIS
 * Could we use enumerated types to manage these classifiers given that they're a fixed set of values?
 * this could make it a ton easier to read.
 * 
 *
 */

public class Location{

	int x;
	int y;
	char classifier;

	public Location(int inx, int iny, char typey){
		x = inx;
		y = iny;
		classifier = typey;
	}
}