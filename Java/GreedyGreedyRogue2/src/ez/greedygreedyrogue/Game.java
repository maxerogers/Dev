package ez.greedygreedyrogue;

public class Game extends Thread
{
	//contain users connections
	private Level map;
	private GameGraphics gpu;
	public Game(){
		gpu = new GameGraphics();
	}
	public void run()
	{
		userInput();
		gameLogic();
		updateGraphics();
	}
	private void updateGraphics() {
		// TODO Auto-generated method stub
		
	}
	private void gameLogic() {
		// TODO Auto-generated method stub
		
	}
	private void userInput() {
		// TODO Auto-generated method stub
		
	}
}
