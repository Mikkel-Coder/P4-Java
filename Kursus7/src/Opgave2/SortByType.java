package Opgave2;

import java.util.Comparator;

public class SortByType implements Comparator<Train> {

	public int compare(Train o1, Train o2) {
		return o1.type.compareTo(o2.type);
	}

}
