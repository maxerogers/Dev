package org.newdawn.tilemap;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * A very simple example to illustrate how simple tile maps can be
 * used for basic collision. This particular technique only works
 * in certain circumstances and for small time updates. However, this 
 * fits many maze based games perfectly. 
 * 
 * @author Kevin Glass
 */
public class Game extends Canvas implements KeyListener {
	/** The sprite we're going to use for our player */
	private Image sprite;
	/** The buffered strategy used for accelerated rendering */
	private BufferStrategy strategy;
	
	/** True if the left key is currently pressed */
	private boolean left;
	/** True if the right key is currently pressed */
	private boolean right;
	/** True if the up key is currently pressed */
	private boolean up;
	/** True if the down key is currently pressed */
	private boolean down;

	/** The map our player will wander round */
	private Map map;
	/** The player entity that will be controlled with cursors */
	private Entity player;
	
	/**
	 * Create the simple game - this also starts the game loop
	 */
	public Game() {
		// right, I'm going to explain this in detail since it always seems to 
		// confuse. 
		//
		// Here we're just loading a sprite, however we're doing this by using 
		// the class loader. The reason we do this is so that when developing you
		// can use local files, but when distributing to your players you can 
		// package everything up into jar files which can be handled by webstart
		//
		// However, this means that when you run the program the directory above
		// "res" must be in your classpath so the resources can be found.
		try {
			URL url = Thread.currentThread().getContextClassLoader().getResource("res/sprite.gif");
			if (url == null) {
				System.err.println("Unable to find sprite: res/sprite.gif");
				System.exit(0);
			}
			sprite = ImageIO.read(url);
		} catch (IOException e) {
			System.err.println("Unable to load sprite: res/sprite.gif");
			System.exit(0);
		}
		
		// create the AWT frame. Its going to be fixed size (500x500) 
		// and not resizable - this just gives us less to account for
		Frame frame = new Frame("Tile Map Example");
		frame.setLayout(null);
		setBounds(0,0,500,500);
		frame.add(this);
		frame.setSize(500,500);
		frame.setResizable(false);
		
		// add a listener to respond to the window closing so we can
		// exit the game
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		// add a key listener that allows us to respond to player
		// key presses. We're actually just going to set some flags
		// to indicate the current player direciton
		frame.addKeyListener(this);
		addKeyListener(this);
		
		// show the frame before creating the buffer strategy!
		frame.setVisible(true);
		
		// create the strategy used for accelerated rendering. More details
		// in the space invaders 2D tutorial
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		// create our game objects, a map for the player to wander around
		// and an entity to represent out player
		map = new Map();
		player = new Entity(sprite, map, 1.5f, 1.5f);
		
		// start the game loop
		gameLoop();
	}
	
	/**
	 * The game loop handles the basic rendering and tracking of time. Each
	 * loop it calls off to the game logic to perform the movement and 
	 * collision checking.
	 */
	public void gameLoop() {
		boolean gameRunning = true;
		long last = System.nanoTime();
		
		// keep looking while the game is running
		while (gameRunning) {
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			
			// clear the screen
			g.setColor(Color.black);
			g.fillRect(0,0,500,500);
			
			// render our game objects
			g.translate(100,100);
			map.paint(g);
			player.paint(g);
			
			// flip the buffer so we can see the rendering
			g.dispose();
			strategy.show();
			
			// pause a bit so that we don't choke the system
			try { Thread.sleep(4); } catch (Exception e) {};
			
			// calculate how long its been since we last ran the
			// game logic
			long delta = (System.nanoTime() - last) / 1000000;
			last = System.nanoTime();
		
			// now this needs a bit of explaining. The amount of time
			// passed between rendering can vary quite alot. If we were
			// to move our player based on the normal delta it would
			// at times jump a long distance (if the delta value got really
			// high). So we divide the amount of time passed into segments
			// of 5 milliseconds and update based on that
			for (int i=0;i<delta / 5;i++) {
				logic(5);
			}
			// after we've run through the segments if there is anything
			// left over we update for that
			if ((delta % 5) != 0) {
				logic(delta % 5);
			}
		}
	}
	
	/**
	 * Our game logic method - for this example purpose this is very 
	 * simple. Check the keyboard, and attempt to move the player
	 * 
	 * @param delta The amount of time to update for (in milliseconds)
	 */
	public void logic(long delta) {
		// check the keyboard and record which way the player
		// is trying to move this loop
		float dx = 0;
		float dy = 0;
		if (left) {
			dx -= 1;
		}
		if (right) {
			dx += 1;
		}
		if (up) {
			dy -= 1;
		}
		if (down) {
			dy += 1;
		}
		
		// if the player needs to move attempt to move the entity
		// based on the keys multiplied by the amount of time thats
		// passed
		if ((dx != 0) || (dy != 0)) {
			player.move(dx * delta * 0.003f,
						dy * delta * 0.003f);
		}
	}
	
	/**
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e) {
		// check the keyboard and record which keys are pressed
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = true;
		}
	}

	/**
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent e) {
		// check the keyboard and record which keys are released
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = false;
		}
	}
	
	/**
	 * The entry point to our example code
	 * 
	 * @param argv The arguments passed into the program
	 */
	public static void main(String[] argv) {
		new Game();
	}
}
