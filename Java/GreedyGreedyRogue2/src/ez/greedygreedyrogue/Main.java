package ez.greedygreedyrogue;

import javax.swing.JFrame;

public class Main 
{
	
	public static void main(String[] args)
	{
		System.out.println("Hello User");
		//now we develop the menu for the user to select a game,options,or quit
		//Skipping the menu going to actual game
		Game game = new Game();
		game.start();
	}
}
