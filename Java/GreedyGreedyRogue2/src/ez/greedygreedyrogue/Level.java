package ez.greedygreedyrogue;
/**
 * Thie is the class used to store information about a particular level
 * in the game. 
 * @author max
 *
 */
public class Level 
{
	private int maxNumOfPlayers;
	private String nameOfLevel;
	private String typeOfLevel;
	private int[] dimensions;
	private int titlesize;
	private short[][] floorplan;
	private Space[][] tiles;
	public Level(){}
	
	public void draw(){}
}
