package Opgave2;

import java.util.Comparator;

public class SortByWeight implements Comparator<Train> {

	public int compare(Train o1, Train o2) {
		return Integer.compare(o1.weight, o2.weight);
	}
	
}
