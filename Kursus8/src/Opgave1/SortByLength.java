package Opgave1;

import java.util.Comparator;

public class SortByLength implements Comparator<Train> {

	public int compare(Train o1, Train o2) {
		return Integer.compare(o1.length, o2.length);
	}
	
}
