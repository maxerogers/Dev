public class CountingSorter implements Sorter {
	private final int maxValue;
 
	public CountingSorter(int maxValue) {
		this.maxValue = maxValue;
	}
 
	@Override
	public int[] sort(int[] values) {
		int[] counts = new int[maxValue + 1];
		for (int i = 0; i < values.length; i++) {
			counts[values[i]]++;
		}
 
		int total = 0;
		for (int i = 0; i <= maxValue; i++) {
			int count = counts[i];
			counts[i] = total;
			total += count;
		}
 
		int[] sortedValues = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			sortedValues[counts[values[i]]] = values[i];
			counts[values[i]]++;
		}
		return sortedValues;
	}
}