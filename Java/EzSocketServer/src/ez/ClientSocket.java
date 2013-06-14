package ez;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocket extends Thread
{
	private Socket mySocket;
	private boolean isOpen = true;
	private String msgIn = "null";
	private String msgOut = "null";
	private ObjectInputStream input;
	private ObjectOutputStream output;
	public ClientSocket(Socket temp)
	{
		mySocket = temp;
		try {
			input =new ObjectInputStream(temp.getInputStream());
			output = new ObjectOutputStream(temp.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run()
	{
		int i = 0;
		while(isOpen && i<10)
		{
			System.out.println("Im a Server-Side Socket Looking for a client");
			i++;
		}
		try {
			input.close();
			output.close();
			mySocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void parse (String msgNew)
	{
		System.out.println("Cleint says: "+msgNew);
	}
}
