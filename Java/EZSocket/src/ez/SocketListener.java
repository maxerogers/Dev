package ez;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketListener extends Thread
{
	private ServerSocket listener;
	private int portNumber = 8888;
	private boolean isOn = false;
	public SocketListener()
	{
		try {
			listener = new ServerSocket(portNumber);
			isOn=true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void checkForNewConnections()
	{
		try {
			Socket temp = listener.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run()
	{
		int i = 0;
		while(i<10)
		{
			checkForNewConnections();
		}
		try {
			listener.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
