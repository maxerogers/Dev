import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyInputHandler implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			System.out.println("LEFT");
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			System.out.println("RIGHT");
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			System.out.println("SPACE");
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
