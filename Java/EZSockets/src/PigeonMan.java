import java.io.IOException;

/**
 * This class's purpose is to manage both the inbox(incoming messages) and the outbox(the outgoing messages)
 * 
 */
public class PigeonMan implements Runnable 
{
	private static final int PORTNUMBER = 9405;
	private Roost roost;
	private String[] input;
	private String output;
	private boolean isOn = true;
	public void run()
	{
		while(isOn)
		{
			input = roost.getNewInput();
			
		}
	}
	
	public void printInbound(String[] inbound)
	{
		for(int i=0;i<inbound.length;i++)
		{
			System.out.println(inboud[i])
		}
	}
}
