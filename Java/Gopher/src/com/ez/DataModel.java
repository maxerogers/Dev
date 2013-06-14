package com.ez;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class DataModel 
{
	private ArrayList<Stop> stops;
	private ArrayList<Route> routes;
	String path;
	public void buildDataBase(String city)
	{
		stops = new ArrayList<Stop>();
		routes = new ArrayList<Route>();
		//path = "google_transit/stops.txt";
		//readTxt(path, new Stop());
		path = "google_transit/routes.txt";
		readTxt(path, new Route());
	}
	private void readTxt(String path, Object obj)
	{
		String everything="";
		BufferedReader br = null;
	   try{
		   URL url = Main.class.getClassLoader().getResource(path);
		   br = new BufferedReader(new FileReader(url.getPath()));
		   StringBuilder sb = new StringBuilder();
		   String line = br.readLine();
		   while(line != null)
		   {
			   sb.append(line);
			   sb.append("\n");
			   line = br.readLine();
			   if(line != null)
			   {
				   if(obj.getClass() == Route.class)
				   {
					   Route temp = new Route(line);
					   routes.add(temp);
				   }else if(obj.getClass() == Stop.class)
				   {
					   Stop temp = new Stop(line);
					   stops.add(temp);
				   }
			   }
		   }
		   everything = sb.toString();
		   //System.out.println(everything);
	   }catch(Exception e)
	   { e.printStackTrace();}finally{
		   try {
			br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	}
}
