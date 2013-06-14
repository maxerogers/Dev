package com.ez;

public class Stop 
{
	private String name;
	private String id;
	private String code;
	private String desc;
	private String stopLat;
	private String stopLon;
	private String zoneId;
	private String stopType;
	private String parentId;
	public Stop(){}
	public Stop(String input)
	{
		//System.out.println(input);
		try{

			String[] split = input.split("\\,");
			this.loadData(split);
		}catch(NullPointerException e){
			System.out.println("null route");
		}
	}
	private void loadData(String[] split)
	{
		name = split[0];
		id = split[1];
		code = split[2];
		desc = split[3];
		stopLat = split[4];
		stopLon = split[5];
		zoneId = split[6];
		stopType = split[7];
		parentId = split[8];
	}
}
