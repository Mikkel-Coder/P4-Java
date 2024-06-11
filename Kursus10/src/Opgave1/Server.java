/*
 * THE MAIN SERVER PROGRAM
 * 
 * Split your train program with the two types of threads into two separate 
 * programs (projects). One is the client program which contains the user 
 * input and one is the server program which handles the list of trains.
 */


/*
 * The main server program
 * 
 * Run this file to start a server
 */

package Opgave1;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;

import java.io.IOException;

import java.util.Random;

public class Server {
	public int servicePort;

	protected int trainQuantity;
	public int maxConnectionQueue;

	public TrainsList trains;
	protected Random random;

	public Server(int port) {
		this.servicePort = port;
		this.maxConnectionQueue = 10;
		this.trains = new TrainsList();
		this.trainQuantity = 3;
		this.random = new Random();
	}

	public static void main(String[] args) {

		// Start a new server with default arguments
		Server server = new Server(TrainProtocol.DEFAULT_PORT);

		// Generate some random trains for the sever to use
		server.generateRandomTrains();

		// Start the sever!
		try {
			ServerSocket serverSocket = new ServerSocket(server.servicePort, server.maxConnectionQueue,
					InetAddress.getLocalHost());
			System.out.println("[SERVER] Listening for connections...");

			while (true) {
				Socket clientSocket = serverSocket.accept();

				System.out.println("[SERVER] Got connection from IP: " + clientSocket.getInetAddress());

				ClientHandler clientHandler = new ClientHandler(clientSocket, server.trains);
				Thread clientHandlerThread = new Thread(clientHandler);
				clientHandlerThread.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Generate a list of random trains
	 * 
	 * @param quantity How many randomly trains to generate
	 * 
	 * @param randomContextObject A Random type Object used for randomness
	 * 
	 * NOTE: This is NOT a part of the assignment
	 */
	public void generateRandomTrains() {
		this.trains = new TrainsList();

		for (int i = 0; i < this.trainQuantity; i++) {

			// Chose a random Enum
			PassengerTrainTypes randomType = PassengerTrainTypes.values()[this.random
					.nextInt(PassengerTrainTypes.values().length)];

			// Chose a random length between 100 and 3000 meters inclusive
			int randomLength = this.random.nextInt(2901) + 100;

			// Chose a random weight between 100 and 300 tons
			int randomWeight = this.random.nextInt(201) + 100;

			// Random boolean for electric
			boolean randomElectric = this.random.nextBoolean();

			this.trains.add(new Train(randomType, randomLength, randomWeight, randomElectric));
		}

	}

}
