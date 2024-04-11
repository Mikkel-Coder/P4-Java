package Opgave2;

public class Train {
	public PassengerTrainTypes type;
	public int length;
	public int weight;
	public boolean isElectric; 
	

	public Train(PassengerTrainTypes type, int length, int weight, boolean electric) {
		this.type = type;
		this.length = length;
		this.weight = weight;
		this.isElectric = electric;
	}

}
