package ez.greedygreedyrogue;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

/**
 *This is the class that will be handling all of the graphical changes
 *for the game. It will have to handle what graphics need to be refreshed and 
 *what graphics may be left alone. Which need to be deleted and etc
 * @author max
 
 */
public class GameGraphics extends Canvas
{
	private BufferStrategy strategy;
	private boolean gameIsRunning = true;
	public GameGraphics()
	{
		JFrame frame = new JFrame("GreedyGreedyRogue");
		frame.setLayout(null);
		setBounds(0,0,500,500);
		frame.add(this);
		frame.setSize(500,500);
		frame.setResizable(false);
		createBufferStrategy(1);
		strategy = this.getBufferStrategy();
		//gameloop();
	}
	public void gameloop(){
		while(gameIsRunning)
		{
			Graphics2D graphics = (Graphics2D) strategy.getDrawGraphics();
			//Clears the Screen
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0,0,500,500);
		}
	}
}
