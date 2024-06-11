/*
 * Extend the client/server to allow the interaction to happen across a 
 * network. Use your local network and you fellow students’ laptop to run. 
 * You may need to open a port on the firewalls of the machines involved. 
 * Feel free to use a virtual machine or two instead as an alternative, 
 * or just use the internal loopback interface (127.0.0.1) and start the 
 * client/server as individual programs.
 * 
 * a. FIRST USE THE CLIENTS WITH MANUAL INPUTS/EDITS.
 * 	  - I have done this on my LAN at home via IPv6. It works just fine
 * 
 * b. Then extend the client design and implementation to trigger a random 
 *    action based upon a mouse click.
 *    
 *    i. How fast can you “click” a request before you run into some issues?
 *       What type of issues to you experience? 
 *       Can you do something about this?
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
import Opgave1.TrainCLI;
import Opgave1.TrainProtocol;

public class a {
	public static void main(String[] args) {
		try {
			// Setup Socket
			InetAddress ip = InetAddress.getByName("2a00:fd00:901e:2b0b:67b:cbff:feba:9420");
			
			Socket clientSocket = new Socket(ip, TrainProtocol.DEFAULT_PORT);
			Client client = new Client(clientSocket);
			System.out.println("[A_CLINET] Socket open with IP: " + clientSocket.getInetAddress());

			// First time update local train list
			client.updateLocalTrainsList();
			for (Train train : client.trains) {
				train.printInfo();
			}

			// Create the train cli thread
			TrainCLI traincli = new TrainCLI(client.trains, System.in, System.out);
			Thread traincliThread = new Thread(traincli);
			traincliThread.start();

			// Add event when the TranCLI updates or deletes trains
			traincli.addListener(client);

			// Only continue to exit when the traincli thread is dead
			traincliThread.join();
			
			// Remember to close the socket gracefully
			client.exit();
			System.out.println("[A_CLIENT] Sokcet closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
