import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


public class Test {

	@org.junit.Test
	public void test() {
		String data = "1\n"+
"3 3 9\n"+
"C1 D1\n"+
"C1 D1\n"+
"C2 D1\n"+
"C2 D2\n"+
"C2 D2\n"+
"D1 C1\n"+
"D1 C2\n"+
"D2 C1\n"+
"D2 C2\n";
		InputStream stdin = System.in;
		try {
		  System.setIn(new ByteArrayInputStream(data.getBytes()));
		  CatVSDogProblem problem = new CatVSDogProblem();
		  problem.main(null);
		} finally {
		  System.setIn(stdin);
		}
	}

}
