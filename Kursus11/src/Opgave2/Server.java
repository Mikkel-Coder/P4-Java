/* 
 * Repeat with the server-side code that you may have. 
 * Write at least three fault possibilities.
 * 
 * NOTE: Only one exception can happen for the server.
 *       See ClientHanlder for more exceptions
 */

package Opgave2;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;

import java.io.IOException;

import java.util.Random;

import Opgave1.X_TrainsList;
import Opgave1.X_TrainProtocol;
import Opgave1.X_Train;
import Opgave1.X_PassengerTrainTypes;

public class Server {
	public int servicePort;

	protected int trainQuantity;
	public int maxConnectionQueue;
	protected int autoSaveFrequncyInSecounds;

	public X_TrainsList trains;
	protected Random random;

	public Server(int port) {
		this.servicePort = port;
		this.trainQuantity = 3;
		this.random = new Random();
		this.maxConnectionQueue = 10;
		this.autoSaveFrequncyInSecounds = 20;
	}

	public static void main(String[] args) {

		// Start a new server with default arguments
		Server server = new Server(X_TrainProtocol.DEFAULT_PORT);

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
			System.out.println("[SERVER] IO exception on sever socket: " + e.getMessage());
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
		this.trains = new X_TrainsList();

		for (int i = 0; i < this.trainQuantity; i++) {

			// Chose a random Enum
			X_PassengerTrainTypes randomType = X_PassengerTrainTypes.values()[this.random
					.nextInt(X_PassengerTrainTypes.values().length)];

			// Chose a random length between 100 and 3000 meters inclusive
			int randomLength = this.random.nextInt(2901) + 100;

			// Chose a random weight between 100 and 300 tons
			int randomWeight = this.random.nextInt(201) + 100;

			// Random boolean for electric
			boolean randomElectric = this.random.nextBoolean();

			this.trains.add(new X_Train(randomType, randomLength, randomWeight, randomElectric));
		}

	}

}
