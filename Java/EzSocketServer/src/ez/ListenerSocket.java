package ez;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ListenerSocket extends Thread
{
	private boolean isOn = true;
	private int portNumber = 9876;
	private ServerSocket mySocket;
	private ArrayList<ClientSocket> myClients;
	public ListenerSocket()
	{
		try {
			mySocket = new ServerSocket(portNumber);
			myClients = new ArrayList<ClientSocket>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run()
	{
		int i = 0;
		while(isOn || i < 10)
		{
			Socket temp = null;
			try {
				temp = mySocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(temp != null)
			{
				System.out.println("Found a new Client");
				ClientSocket result = new ClientSocket(temp);
				myClients.add(result);
				result.start();
			}
			i++;
		}
	}
}
