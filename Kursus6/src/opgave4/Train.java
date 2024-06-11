package opgave4;

import java.util.Stack;

public class Train {
	private Stack<RollingStock> rollingStocks;
	
	public Train() {
		this.rollingStocks = new Stack<RollingStock>();
	}
	
	public void pushRollingStock(RollingStock rs) {
		this.rollingStocks.push(rs);
	}
	
	public RollingStock popRollingStock() {
		return this.rollingStocks.pop();
	}
}
