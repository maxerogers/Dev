package ez;

import java.util.ArrayList;

public class Line 
{
	public String name;
	public ArrayList<Stop> stops;
	public Line(){}
	public Line(String n){name = n;}
	public void setName(String n){name = n;}
	public String getName(){return name;}
	public Stop getStop(int x){return this.stops.get(x);}
	public void addStop(Stop x)
	{
		if(!this.stops.contains(x))
			this.stops.add(x);
	}
	public int findStop(Stop x)
	{
		int index = -1;
		if(this.stops.contains(x))
		{	for(int i=0;i<stops.size();i++)
			{
				if(this.stops.get(i).getName().equals(x.getName()))
				{
					index = i;
					break;
				}
			}
		}
		return index;
	}
	public String toString()
	{
		String result = this.name + " Line";
		for(int i=0;i<stops.size();i++)
		{
			result += "\nStop "+i+" : "+this.getStop(i).getName();
		}
		return result;
	}
}
