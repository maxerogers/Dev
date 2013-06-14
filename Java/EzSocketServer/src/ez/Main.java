package ez;

public class Main 
{
	public static void main(String[] args)
	{
		System.out.println("Setting up EZSocket Server");
		ListenerSocket ls = new ListenerSocket();
		ls.start();
	}
}
