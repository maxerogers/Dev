import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


public class Test {

	@org.junit.Test
	public void test() {
		String data = "15 3\n"+
"197812 re_hash\n"+
"78906 5_4\n"+
"189518 tomorrow_comes_today\n"+
"39453 new_genious\n"+
"210492 clint_eastwood\n"+
"26302 man_research\n"+
"22544 punk\n"+
"19727 sound_check\n"+
"17535 double_bass\n"+
"18782 rock_the_house\n"+
"198189 19_2000\n"+
"13151 latin_simone\n"+
"12139 starshine\n"+
"11272 slow_country\n"+
"10521 m1_a1\n";
		InputStream stdin = System.in;
		try {
		  System.setIn(new ByteArrayInputStream(data.getBytes()));
		  ZipfSongProblem zip = new ZipfSongProblem();
		  //zipsLaw zLaws= new zipsLaw();
		  try {
			zip.main(null);
			//zLaws.main(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} finally {
		  System.setIn(stdin);
		}
	}

}
