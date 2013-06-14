import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
 
import org.junit.Test;
 
public class CountingSorterTest {
	@Test
	public void testSort() {
		int maxScore = 100;
		int[] expectedSortedScores = { 60, 80, 80, 100 };
		int[] scores = { 80, 80, 60, 100 };
 
		Sorter sorter = new CountingSorter(maxScore);
		int[] sortedScores = sorter.sort(scores);
 
		assertThat(sortedScores, is(expectedSortedScores));
	}
}