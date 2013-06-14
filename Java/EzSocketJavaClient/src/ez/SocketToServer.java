package ez;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketToServer extends Thread
{
	private Socket mySocket;
	private boolean isOn = true;
	private int portNumber = 9876;
	private String ipAddress = "localhost";
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private String msgIn = "null";
	private String msgOut = "null";
	public SocketToServer()
	{
		try {
			mySocket = new Socket(ipAddress,portNumber);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run()
	{
		int i =0;
		while(isOn && i<10)
		{
			System.out.println("I'm a Client");
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
	
	private void parse(String msgNew)
	{
		System.out.println("From the Server: "+msgNew);
	}
}
