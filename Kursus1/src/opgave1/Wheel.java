/* Wheel.java */

package opgave1;

public class Wheel {
	public int diameter = 0;
	public int spokes = 0;
	
	Wheel(int diameter, int spokes) {
		this.diameter = diameter;
		this.spokes = spokes;
	}

	public double getCircumference() {
		return this.diameter * Math.PI;
	}
	
}
