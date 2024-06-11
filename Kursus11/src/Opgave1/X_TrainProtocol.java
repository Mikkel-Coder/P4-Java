/*
 * THIS FILE IS NOT A PART OF THE ASSIGENMENT
 */

package Opgave1;

import java.io.Serializable;

public class X_TrainProtocol implements Serializable {
	private static final long serialVersionUID = 386446770544370498L;
	public static final int DEFAULT_PORT = 5234;
	
	private X_TrainProtocolCommandType command;
	private X_Train train;
	private X_TrainsList trains;
	
	public X_TrainProtocol(X_TrainProtocolCommandType command, X_TrainsList trains) {
		this.command = command;
		this.trains = trains;
	}

	public X_TrainProtocolCommandType getCommand() {
		return this.command;
	}
	
	public X_Train getTrain() {
		return this.train;
	}
	
	public X_TrainsList getTrains() {
		return this.trains;
	}
}
