package ez;

public class Main 
{
	public static void main(String[] args)
	{
		System.out.println("Setting up EZSocket: Java Client");
		SocketToServer sos = new SocketToServer();
		sos.start();
	}
}
