/*
 * Instead of parsing raw strings between the server and client,
 * a protocol is used instead. A protocol in Java can be implemented
 * as a object as seen below.
 */

package Opgave1;

import java.io.Serializable;

public class TrainProtocol implements Serializable {
	private static final long serialVersionUID = 386446770544370498L;
	public static final int DEFAULT_PORT = 5234;
	
	private TrainProtocolCommandType command;
	private Train train;
	private TrainsList trains;
	
	public TrainProtocol(TrainProtocolCommandType command, TrainsList trains) {
		this.command = command;
		this.trains = trains;
	}

	public TrainProtocolCommandType getCommand() {
		return this.command;
	}
	
	public Train getTrain() {
		return this.train;
	}
	
	public TrainsList getTrains() {
		return this.trains;
	}
}
