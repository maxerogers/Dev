import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

public class HashtableDemo {

	private static double[] makeBitArray(double[] array, int m) {
		double[] output = new double[array.length];
		double min = array[array.length-1];
		double max = -1;
		for(int i=array.length-1;i>=0;i--)
		{
			if(i == array.length || i%m == m-1 || min > array[i])
			{
				min = output[i];
			}
			output[i] = min;
			if(max < array[i])
				max = array[i];
		}
		return output;
	}
	
	public static double[] makeArray(int size)
	{
		double[] result = new double[size];
		for(int i=0;i<size;i++)
			result[i] = Math.floor(1000*Math.random())/1000;
		return result;
	}
	
public static void main(String args[]) {

// Creating Hashtable for example
Hashtable companies = new Hashtable();



// Java Hashtable example to put object into Hashtable
// put(key, value) is used to insert object into map
companies.put("Google", "United States");
companies.put("Nokia", "Finland");
companies.put("Sony", "Japan");


// Java Hashtable example to get Object from Hashtable
// get(key) method is used to retrieve Objects from Hashtable
companies.get("Google");


// Hashtable containsKey Example
// Use containsKey(Object) method to check if an Object exits as key in
// hashtable
System.out.println("Does hashtable contains Google as key: "
+ companies.containsKey("Google"));


// Hashtable containsValue Example
// just like containsKey(), containsValue returns true if hashtable
// contains specified object as value
System.out.println("Does hashtable contains Japan as value: "
+ companies.containsValue("Japan"));


// Hashtable enumeration Example
// hashtabl.elements() return enumeration of all hashtable values
Enumeration enumeration = companies.elements();

while (enumeration.hasMoreElements()) {
System.out
.println("hashtable values: " + enumeration.nextElement());
}


// How to check if Hashtable is empty in Java
// use isEmpty method of hashtable to check emptiness of hashtable in
// Java
System.out.println("Is companies hashtable empty: "
+ companies.isEmpty());


// How to find size of Hashtable in Java
// use hashtable.size() method to find size of hashtable in Java
System.out.println("Size of hashtable in Java: " + companies.size());


// How to get all values form hashtable in Java
// you can use keySet() method to get a Set of all the keys of hashtable
// in Java
Set hashtableKeys = companies.keySet();


// you can also get enumeration of all keys by using method keys()
Enumeration hashtableKeysEnum = companies.keys();


// How to get all keys from hashtable in Java
// There are two ways to get all values form hashtalbe first by using
// Enumeration and second getting values ad Collection

Enumeration hashtableValuesEnum = companies.elements();


Collection hashtableValues = companies.values();


// Hashtable clear example
// by using clear() we can reuse an existing hashtable, it clears all
// mappings.
companies.clear();

double[] sequence = makeArray(10);
double[] keys = makeBitArray(sequence,m);
Hashtable bitarray = new Hashtable();
for(int i=0;i<)

}
}

/*
Output:
Does hashtable contains Google as key: true
Does hashtable contains Japan as value: true
hashtable values: Finland
hashtable values: United States
hashtable values: Japan
Is companies hashtable empty: false
Size of hashtable in Java: 3


Read more: http://javarevisited.blogspot.com/2012/01/java-hashtable-example-tutorial-code.html#ixzz2LW7kfagO

*/