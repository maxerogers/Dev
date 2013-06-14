import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class ZipfSongProblem 
{
	private static float f0 = 0;
	private static ArrayList<Song> songs;
	public static void main(String[] args) throws IOException
	{
		int n,m;
		Scanner scan = new Scanner(System.in);
		//read in settings
		// n:- number of songs 
		// m:- number of songs to select
		String temp = scan.nextLine();
		temp = temp.replaceAll("(\\r|\\n)", "");
		String[] split = temp.split(" ");
		n = Integer.parseInt(split[0]);
		m = Integer.parseInt(split[1]);
		songs = new ArrayList<Song>();
		//read in 'n' songs 
		for(int i=0;i<n;i++)
		{
			Song s = new Song();
			songs.add(s);
			String newTemp = scan.nextLine();
			if(newTemp != null)
			{
				newTemp = newTemp.replaceAll("\\r|\\n"," ");
				String[] split2 = newTemp.split(" ");
				songs.get(i).fi = Integer.parseInt(split2[0]);
				songs.get(i).si = split2[1];
				if(f0 == 0)
					f0 = songs.get(i).fi;
				// Quality of a song is the frequency / zepf's frequency
				// a basic zepf's frequency is original / rank
				// qi = fi / (f0/i+1)
				songs.get(i).qi = ( songs.get(i).fi * (i+1) ) ;
			}else{
				i--;
			}
		}
		findOutput(m);
	}
	private static void findOutput(int m)
	{
		Song[] sorted = songs.toArray(new Song[0]);
		Arrays.sort(sorted, Song.SongQualityComparator);
		int i = sorted.length-1;
		for(int j = 0;j<m;j++)
		{
			System.out.println(sorted[i].si);
			i--;
		}
	}
}

class Song implements Comparable<Song>
{
	public float fi;
	public float qi;
	public String si;
	public Song(){}
	@Override
	public int compareTo(Song otherSong) {
		
		return new Float(this.qi).compareTo(new Float(otherSong.qi));
	}
	
	public static Comparator<Song> SongQualityComparator = new Comparator<Song>()
			{
				public int compare(Song song1, Song song2)
				{
					return song1.compareTo(song2);
				}
			};
}
