/*
 * THE MAIN CLIENT PROGRAM
 * 
 * Split your train program with the two types of threads into two separate 
 * programs (projects). One is the client program which contains the user 
 * input and one is the server program which handles the list of trains.
 */

package Opgave1;

import java.net.Socket;
import java.net.InetAddress;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class Client implements TrainCLIEvents {
	public Socket clientSocket;
	public TrainsList trains;

	public Client(Socket clientSocket) {
		this.clientSocket = clientSocket;
		this.trains = new TrainsList();
	}

	public void updateLocalTrainsList() {
		// Create payload as GET command
		TrainProtocol payload = new TrainProtocol(TrainProtocolCommandType.GET, null);

		try {
			// Write to server
			ObjectOutputStream output = new ObjectOutputStream(this.clientSocket.getOutputStream());
			output.writeObject(payload);
			output.flush(); // Empty the Stream so that everything is sent
			System.out.println("[CLIENT] Sent GET from client!");

			// Read the response from the server
			ObjectInputStream input = new ObjectInputStream(this.clientSocket.getInputStream());
			TrainProtocol response = (TrainProtocol) input.readObject();

			// Update local train list
			this.trains = response.getTrains();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void exit() {
		// Create payload as EXIT command
		TrainProtocol payload = new TrainProtocol(TrainProtocolCommandType.EXIT, null);

		try {
			// Write to server
			ObjectOutputStream output = new ObjectOutputStream(this.clientSocket.getOutputStream());
			output.writeObject(payload);
			output.flush(); // Empty the Stream so that everything is sent
			System.out.println("[CLIENT] Sent EXIT from client!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onBeforeTrainListModification() {

		// Get train list from server
		TrainProtocol payload = new TrainProtocol(TrainProtocolCommandType.MODIFY, null);

		try {
			ObjectOutputStream output = new ObjectOutputStream(this.clientSocket.getOutputStream());
			output.writeObject(payload);
			output.flush();

			// Read the response from the server
			ObjectInputStream input = new ObjectInputStream(this.clientSocket.getInputStream());
			TrainProtocol response = (TrainProtocol) input.readObject();

			// Update local train list
			this.trains.clear();
			this.trains.addAll(response.getTrains());

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Now call the orginial methods add
		// Then continue to onAfterTrainListModification
	}

	@Override
	public void onAfterTrainListModification() {

		// Push the new train list with the added train to the server
		TrainProtocol payload = new TrainProtocol(null, this.trains);

		try {
			ObjectOutputStream output = new ObjectOutputStream(this.clientSocket.getOutputStream());
			output.writeObject(payload);
			output.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		try {
			// Setup Socket
			Socket clientSocket = new Socket(InetAddress.getLocalHost(), TrainProtocol.DEFAULT_PORT);
			Client client = new Client(clientSocket);
			System.out.println("[CLINET] Socket open with IP: " + clientSocket.getInetAddress());

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
			System.out.println("[CLIENT] Sokcet closed.");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
