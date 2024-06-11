/*
 * Extend the client/server to allow the interaction to happen across a 
 * network. Use your local network and you fellow students’ laptop to run. 
 * You may need to open a port on the firewalls of the machines involved. 
 * Feel free to use a virtual machine or two instead as an alternative, 
 * or just use the internal loopback interface (127.0.0.1) and start the 
 * client/server as individual programs.
 * 
 * a. First use the clients with manual inputs/edits.
 * 
 * b. THEN EXTEND THE CLIENT DESIGN AND IMPLEMENTATION TO TRIGGER A RANDOM 
 * 	  ACTION BASED UPON A MOUSE CLICK.
 * 	  - We use a JButton in TrainCLISimulator to create a btn to run random
 * 		actions.
 *    
 *    i. How fast can you “click” a request before you run into some issues?
 *       What type of issues to you experience? 
 *       Can you do something about this?
 *       - I do not see any issues. My program seems to behave nicely
 *       
 * c. Then use the clients with automatic actions i.e. the clients should 
 *    randomly without user interaction select a query, send it to the server 
 *    and show the response.
 * 
 *    i. How fast can you have a large (as many as feasible) number of 
 *       clients execute random queries, as fast as possible, without running 
 *       into problems? What kind of problems do you observe? 
 *       What can you do about these problems?
 *      
 *    ii. Does it matter if you use a TCP or UDP socket for the communication?
 */

package Opgave3;

import java.net.Socket;
import java.net.InetAddress;

import Opgave1.Client;
import Opgave1.Train;
import Opgave1.TrainProtocol;

public class b {
	public static void main(String[] args) {
		try {
			// Setup Socket
			InetAddress ip = InetAddress.getByName("::1");
			Socket clientSocket = new Socket(ip, TrainProtocol.DEFAULT_PORT);
			Client client = new Client(clientSocket);
			System.out.println("[B_CLINET] Socket open with IP: " + clientSocket.getInetAddress());

			// First time update local train list
			client.updateLocalTrainsList();
			for (Train train : client.trains) {
				train.printInfo();
			}

			// Create a new random TranCLi simulator with GUI Button
			TrainCLISimulator traincliSim = new TrainCLISimulator(client.trains);
			Thread traincliSimThread = new Thread(traincliSim);
			traincliSimThread.start();

			// Attach ourself to the traincli for button events
			traincliSim.traincli.addListener(client);

			// Only continue to exit when the traincli thread is dead
			traincliSimThread.join();

			// Remember to close the socket gracefully
			client.exit();
			System.out.println("[B_CLIENT] Sokcet closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
