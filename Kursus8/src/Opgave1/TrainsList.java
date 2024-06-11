

package Opgave1;

import java.util.concurrent.CopyOnWriteArrayList;

/*
 * The TrainList is a `CopyOnWriteArrayList` that is a thread safe `ArrayList`.
 */
public class TrainsList extends CopyOnWriteArrayList<Train> implements Runnable {

    private static final long serialVersionUID = 4171685133041698936L;
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
