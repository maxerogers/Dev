package com.ez;

public class Route 
{
	private String id;
	private String agencyId;
	private String name;
	private String longName;
	private String desc;
	private String type;
	private String url;
	private String color;
	private String textColor;
	public Route(){}
	public Route(String input)
	{
		//System.out.println(input);
		try{
			
			String line = input;
	        String[] tokens = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
	     //   for(String t : tokens) {
	     //      System.out.println("> "+t);
	     //  }
		}catch(NullPointerException e){
			System.out.println("null route");
		}
	}
	private void loadData(String[] split) 
	{
		id = split[0];
		agencyId = split[1];
		name = split[2];
		longName = split[3];
		desc = split[4];
		type = split[5];
		url = split[6];
		color = split[7];
		textColor= split[8];
	}
}
