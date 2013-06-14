import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class zipsLaw {
	
	String trackName = "";
	float fi = 0;
	float zi = 0;
	float qi = 0;
	
	int rank = 0;
	
	int trackPostion = 0;
	
	/**
	 * Constructor for song
	 * 
	 * @param trackName name of track
	 * @param fi number of plays
	 * @param trackPostion position on playlist
	 */
	public zipsLaw( float fi, String trackName, int trackPostion)
	{
		this.trackName = trackName;
		this.fi = fi;
		this.trackPostion = trackPostion;
		
	}
	public zipsLaw(){}
	public static void main(String [] args) throws IOException
	{
		int lineCount = 0;
		int songCount = 0;
		float totalPlays = 0;
		int songsToSelect = 0;
		
		ArrayList<zipsLaw> songs = new ArrayList<zipsLaw>();
		
		try
		{

		 BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in)); 
	     String line; 

	        while ((line = stdin.readLine()) != null) // this is fucking shit it dosn't work! Never gets null!
	        { 
	        	
	            String[] input1 = line.split(" "); 
	            
	            	if (lineCount == 0) 
	            	{ 
	            	
	            		
	            		songCount = Integer.parseInt(input1[0]);
	               		songsToSelect = Integer.parseInt(input1[1]);
	               		lineCount ++;
	            	}
	            	else if(lineCount  <= songCount)
	            	{
	            		
	            		songs.add(new zipsLaw(Float.parseFloat(input1[0]),input1[1],lineCount));
	            	        lineCount ++;
	            		
	            	
	            	}
	            	
	            	if(lineCount > songCount)
	            		break;
	            		
	        }
	        
	        for(zipsLaw track : songs ) { 	totalPlays += track.fi; }
	        
	        for(zipsLaw track : songs )
	        {
	            track.zi =  (songs.get(0).fi / track.trackPostion);
	        	
	        
	        	track.qi =   (track.fi / (track.zi)) ;
	        }
	      
	         songs = rank(songs, songsToSelect);
	       // songs = rank(songs, songs.size()-1);
	        for(zipsLaw track : songs) {System.out.println(track.trackName + "\t" +track.qi );} 
	       
		}
	        catch(Exception ignore)
	        {
	        	
	        }
	  
	} 
	
	private static ArrayList<zipsLaw> rank(ArrayList<zipsLaw> songs,int songsToSelect)
	{
		ArrayList<zipsLaw> sortedSongs = new ArrayList<zipsLaw>();
		ArrayList<zipsLaw> unSortedSongs = songs;
		 zipsLaw topTrack = songs.get(0);
		 
		 while(sortedSongs.size() < songsToSelect )
		 {
			 
			 for(zipsLaw track : unSortedSongs)
			 {
				 if(track.qi > topTrack.qi)
				 {
					 topTrack = track;
				 }
				 else if(track.qi == topTrack.qi)
				 {
					 if(track.trackPostion < topTrack.trackPostion)
					 {
						 topTrack = track;
					 }
				 }
			 }
			 
			 sortedSongs.add(topTrack);
			 unSortedSongs.remove(topTrack);
			
			 topTrack = unSortedSongs.get(0);
			 topTrack.qi = 0;
			
		 }
		
		return sortedSongs;
	}
	
	
}
