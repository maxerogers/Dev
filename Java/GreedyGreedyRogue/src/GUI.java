import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GUI extends Canvas
{
	private JFrame frame;
	public GUI()
	{
		frame = new JFrame("Greedy Greedy Rogues");
		JPanel panel = (JPanel) this.frame.getContentPane();
		panel.setPreferredSize(new Dimension(800,600));
		panel.setLayout(null);
		// setup our canvas size and put it into the content of the frame

		setBounds(0,0,800,600);
		panel.add(this);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		addKeyListener(new KeyInputHandler());
		// request the focus so key events come to us

		requestFocus();
	}
}
