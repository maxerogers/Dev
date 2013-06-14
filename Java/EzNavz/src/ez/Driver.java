package ez;

public class Driver 
{
	public static void main(String[] args)
	{
		Stop timessq = new Stop("Times Square");
		Stop pennst = new Stop("Penn Station");
		Stop west14th = new Stop("West 14th Street");
		Stop grandcentral = new Stop("Grand Central Station");
		Line the1 = new Line("The 1");
		the1.addStop(west14th);
		the1.addStop(pennst);
		the1.addStop(grandcentral);
		the1.addStop(timessq);
		System.out.println(the1.toString());
		the1.removeStop(grandcentral);
		System.out.println(the1.toString());
	}
}
